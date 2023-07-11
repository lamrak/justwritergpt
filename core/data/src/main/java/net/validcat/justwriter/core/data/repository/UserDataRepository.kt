package net.validcat.justwriter.core.data.repository

import kotlinx.coroutines.flow.Flow
import net.validcat.justwriter.core.model.data.DarkThemeConfig
import net.validcat.justwriter.core.model.data.NoteListType
import net.validcat.justwriter.core.model.data.ThemeBrand
import net.validcat.justwriter.core.model.data.UserData

interface UserDataRepository {
    val userData: Flow<UserData>
    suspend fun setThemeBrand(themeBrand: ThemeBrand)
    suspend fun setDarkThemeConfig(darkThemeConfig: DarkThemeConfig)
    suspend fun toggleNoteListType(noteListType: NoteListType)
}