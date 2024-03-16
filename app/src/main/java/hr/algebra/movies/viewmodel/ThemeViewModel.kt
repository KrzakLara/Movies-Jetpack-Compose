package hr.algebra.movies.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class ThemeViewModel(private val context: Context) : ViewModel() {
    private val dataStoreKey = booleanPreferencesKey("darkThemeEnabled")

    // Expose whether the dark theme is enabled as a flow
    val isDarkThemeEnabled: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            // Return the value of darkThemeEnabled, defaulting to false if not set
            preferences[dataStoreKey] ?: false
        }

    // Function to toggle the dark theme
    fun toggleTheme(isEnabled: Boolean) {
        viewModelScope.launch {
            context.dataStore.edit { preferences ->
                preferences[dataStoreKey] = isEnabled
            }
        }
    }
}
