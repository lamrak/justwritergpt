package net.validcat.justwriter.feature.note.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import net.validcat.justwriter.feature.note.NoteRoute

const val NoteNavigationRoute = "note_route"

const val NoteIdArg = "noteId"

fun NavController.navigateToNote(
    noteId: Int = -1,
    navOptions: NavOptions? = null
) {
    this.navigate(NoteNavigationRoute.plus("/$noteId"), navOptions)
}

fun NavGraphBuilder.noteScreen(
    onBackClick: () -> Unit
) {
    composable(
        route = NoteNavigationRoute.plus("/{$NoteIdArg}"),
        arguments = listOf(
            navArgument(NoteIdArg) { type = NavType.IntType }
        )
    ) {
        NoteRoute(
            onBackClick = onBackClick
        )
    }
}