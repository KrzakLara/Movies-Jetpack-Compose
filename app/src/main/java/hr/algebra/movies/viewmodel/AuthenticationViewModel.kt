package hr.algebra.movies.viewmodel

import android.util.Patterns
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import hr.algebra.movies.repository.AuthenticationRepository
import hr.algebra.movies.utils.isValidPassword
import hr.algebra.movies.view.auth.AuthenticationState

class AuthenticationViewModel : ViewModel() {

    private val _authenticationState = mutableStateOf(
        AuthenticationState()
    )
    val authenticationState : State<AuthenticationState>
        get() = _authenticationState

    fun onEmailChanged(email: String) {
        _authenticationState.value = _authenticationState.value.copy(
            email = email,
            isEmailValid = Patterns.EMAIL_ADDRESS.matcher(email).matches()
        )
    }
    fun onPasswordChanged(password: String) {
        _authenticationState.value = _authenticationState.value.copy(
            password = password,
            isPasswordValid = password.isValidPassword()
        )
    }

    fun login(
        onSuccess: () -> Unit,
        onFail: () -> Unit,
    ) {
        AuthenticationRepository.login(
            email = authenticationState.value.email,
            password = authenticationState.value.password,
            onSuccess = onSuccess,
            onFail = onFail
        )
    }

    fun register(
        onSuccess: () -> Unit,
        onFail: () -> Unit,
    ) {
        AuthenticationRepository.register(
            email = authenticationState.value.email,
            password = authenticationState.value.password,
            onSuccess = onSuccess,
            onFail = onFail
        )
    }
}