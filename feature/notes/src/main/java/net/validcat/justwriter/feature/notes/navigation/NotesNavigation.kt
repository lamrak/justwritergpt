package net.validcat.justwriter.feature.notes.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import net.validcat.justwriter.feature.notes.NotesRoute

const val NotesNavigationRoute = "notes_route"

fun NavGraphBuilder.notesScreen(
    onAddNoteClick: () -> Unit,
    onNoteClick: (Int) -> Unit
) {
    composable(route = NotesNavigationRoute) {
        NotesRoute(
            onAddNoteClick = onAddNoteClick,
            onNoteClick = onNoteClick
        )
    }
}


