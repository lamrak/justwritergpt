package net.validcat.justwriter.core.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.map
import net.validcat.justwriter.core.model.data.DarkThemeConfig
import net.validcat.justwriter.core.model.data.NoteListType
import net.validcat.justwriter.core.model.data.ThemeBrand
import net.validcat.justwriter.core.model.data.UserData
import javax.inject.Inject

private const val AppPreferences = "app_preferences"
private val Context.dataStore by preferencesDataStore(name = AppPreferences)

class AppPreferencesDataSource @Inject constructor(
    @ApplicationContext context: Context
) {
    private val dataStore = context.dataStore

    val userData = dataStore.data.map { preferences ->
        val themeBrand = ThemeBrand.valueOf(
            preferences[themeBrandKey] ?: ThemeBrand.DEFAULT.name
        )
        val darkThemeConfig = DarkThemeConfig.valueOf(
            preferences[darkThemeConfigKey] ?: DarkThemeConfig.FOLLOW_SYSTEM.name
        )
        val noteListType = NoteListType.valueOf(
            preferences[noteListTypeKey] ?: NoteListType.List.name
        )

        UserData(
            themeBrand = themeBrand,
            darkThemeConfig = darkThemeConfig,
            noteListType = noteListType,
        )
    }

    suspend fun setThemeBrand(themeBrand: ThemeBrand) {
        dataStore.edit { preferences ->
            preferences[themeBrandKey] = when (themeBrand) {
                ThemeBrand.DEFAULT -> ThemeBrand.DEFAULT.name
                ThemeBrand.ANDROID -> ThemeBrand.ANDROID.name
            }
        }
    }

    suspend fun setDarkThemeConfig(darkThemeConfig: DarkThemeConfig) {
        dataStore.edit { preferences ->
            preferences[darkThemeConfigKey] = when (darkThemeConfig) {
                DarkThemeConfig.FOLLOW_SYSTEM -> DarkThemeConfig.FOLLOW_SYSTEM.name
                DarkThemeConfig.LIGHT -> DarkThemeConfig.LIGHT.name
                DarkThemeConfig.DARK -> DarkThemeConfig.DARK.name
            }
        }
    }

    suspend fun toggleNoteListType(noteListType: NoteListType) {
        dataStore.edit { preferences ->
            preferences[noteListTypeKey] = when (noteListType) {
                NoteListType.List -> NoteListType.Grid.name
                NoteListType.Grid -> NoteListType.List.name
            }
        }
    }

    companion object {
        private const val THEME_BRAND = "theme_brand"
        private const val DARK_THEME_CONFIG = "dark_theme_config"
        private const val NOTE_LIST_TYPE = "note_list_type"
        private val themeBrandKey = stringPreferencesKey(THEME_BRAND)
        private val darkThemeConfigKey = stringPreferencesKey(DARK_THEME_CONFIG)
        private val noteListTypeKey = stringPreferencesKey(NOTE_LIST_TYPE)
    }
}