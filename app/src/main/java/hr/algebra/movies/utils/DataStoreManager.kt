package hr.algebra.movies.utils

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Create a top-level property to create the DataStore
val Context.dataStore by preferencesDataStore(name = "user_preferences")

class UserPreferencesRepository(context: Context) {

    private val dataStore = context.dataStore

    companion object {
        val DARK_MODE_KEY = booleanPreferencesKey("dark_mode")
    }

    // Save the user's preference for dark mode
    suspend fun saveDarkModePref(isDarkModeEnabled: Boolean) {
        dataStore.edit { preferences ->
            preferences[DARK_MODE_KEY] = isDarkModeEnabled
        }
    }

    // Get the user's preference for dark mode
    val isDarkModeEnabled: Flow<Boolean> = dataStore.data
        .map { preferences ->
            preferences[DARK_MODE_KEY] ?: false
        }
}