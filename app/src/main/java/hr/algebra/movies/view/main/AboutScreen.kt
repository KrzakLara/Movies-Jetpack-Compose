package hr.algebra.movies.view.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.*
import hr.algebra.movies.R
import hr.algebra.movies.view.common.BlinkingText

@Composable
fun AboutScreen(modifier: Modifier = Modifier) {
    var showFeedbackDialog by remember { mutableStateOf(false) }
    val feedbackTextState = remember { mutableStateOf(TextFieldValue("")) }

    val scrollState = rememberScrollState()
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.movie_animation))
    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever,
        isPlaying = true,
        restartOnPlay = false
    )

    AnimatedVisibility(
        visibleState = remember { MutableTransitionState(false).apply { targetState = true } },
        enter = fadeIn(animationSpec = tween(durationMillis = 3000))
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LottieAnimation(
                composition = composition,
                progress = progress,
                modifier = Modifier.padding(16.dp)
            )
            Text(
                text = stringResource(R.string.description),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(vertical = 16.dp)
            )
            Button(
                onClick = { showFeedbackDialog = true },
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                Text(text = stringResource(R.string.send_feedback))
            }
            BlinkingText(
                text = stringResource(R.string.updated_by_Lara),
                style = MaterialTheme.typography.displayMedium,
                duration = 1000,
                modifier = Modifier.padding(bottom = 75.dp)
            )
        }
    }

    if (showFeedbackDialog) {
        FeedbackDialog(
            feedbackTextState = feedbackTextState,
            onDismiss = { showFeedbackDialog = false }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedbackDialog(
    feedbackTextState: MutableState<TextFieldValue>,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text(text = stringResource(id = R.string.feedback_title)) },
        text = {
            Column {
                Text(text = stringResource(id = R.string.feedback_prompt))
                OutlinedTextField(
                    value = feedbackTextState.value,
                    onValueChange = { feedbackTextState.value = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text(text = stringResource(id = R.string.feedback_placeholder)) }
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    // Handle feedback submission
                    onDismiss()
                }
            ) {
                Text(text = stringResource(id = R.string.submit))
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text(text = stringResource(id = R.string.cancel))
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewAboutScreen() {
    AboutScreen()
}
