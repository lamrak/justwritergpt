package net.validcat.justwriter.core.model.data

data class UserData(
    val themeBrand: ThemeBrand,
    val darkThemeConfig: DarkThemeConfig,
    val noteListType: NoteListType,
) {
    val isDarkTheme: Boolean
        get() = darkThemeConfig == DarkThemeConfig.DARK
}