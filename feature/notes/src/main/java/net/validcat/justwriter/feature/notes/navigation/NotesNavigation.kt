package net.validcat.justwriter.feature.notes.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import net.validcat.justwriter.feature.notes.NotesRoute


const val NotesNavigationRoute = "notes_route"

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.notesScreen(
//    onSettingsClick: () -> Unit,
//    onAddNoteClick: () -> Unit,
    onNoteClick: (Int) -> Unit
) {
    composable(route = NotesNavigationRoute) {
        NotesRoute(
//            onSettingsClick = onSettingsClick,
//            onAddNoteClick = onAddNoteClick,
            onNoteClick = onNoteClick
        )
    }
}