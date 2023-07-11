package net.validcat.justwriter.core.data.repository

import kotlinx.coroutines.flow.Flow
import net.validcat.justwriter.core.datastore.AppPreferencesDataSource
import net.validcat.justwriter.core.model.data.DarkThemeConfig
import net.validcat.justwriter.core.model.data.NoteListType
import net.validcat.justwriter.core.model.data.ThemeBrand
import net.validcat.justwriter.core.model.data.UserData
import javax.inject.Inject

class LocalUserDataRepository @Inject constructor(
    private val preferencesDataSource: AppPreferencesDataSource
) : UserDataRepository {

    override val userData: Flow<UserData> = preferencesDataSource.userData

    override suspend fun setThemeBrand(themeBrand: ThemeBrand) {
        preferencesDataSource.setThemeBrand(themeBrand)
    }

    override suspend fun setDarkThemeConfig(darkThemeConfig: DarkThemeConfig) {
        preferencesDataSource.setDarkThemeConfig(darkThemeConfig)
    }

    override suspend fun toggleNoteListType(noteListType: NoteListType) {
        preferencesDataSource.toggleNoteListType(noteListType)
    }
}