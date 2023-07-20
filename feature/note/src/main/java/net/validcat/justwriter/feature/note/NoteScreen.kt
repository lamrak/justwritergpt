package net.validcat.justwriter.feature.note

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import net.validcat.justwriter.feature.note.ui.NoteTextFields

@Composable
internal fun NoteRoute(
    viewModel: NoteViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    NoteScreen(
        uiState = uiState,
        onEvent = viewModel::onEvent,
        onBackClick = {
            viewModel.onEvent(NoteEvent.SaveNote)
            onBackClick()
        },
    )
}

@Suppress("LongMethod")
@Composable
fun NoteScreen(
    uiState: NoteUiState,
    onEvent: (NoteEvent) -> Unit,
    onBackClick: () -> Unit,
) {
    var openColorPickerDialog by remember { mutableStateOf(false) }

    var isPinned by remember { mutableStateOf(false) }

    MaterialTheme.colorScheme.onSurface

    BackHandler(
        enabled = true,
        onBack = onBackClick
    )

    when (uiState) {
        NoteUiState.Loading -> { } //TODO
        NoteUiState.Error -> TODO()
        is NoteUiState.Success -> {
            isPinned = uiState.note?.isPinned ?: false

            NoteTextFields(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.surface)
                    .fillMaxSize(),
                note = uiState.note,
                onNoteChanged = { noteFields ->
//                    onEvent(NoteEvent.NoteChanged(noteFields))
                }
            )
        }
    }
}