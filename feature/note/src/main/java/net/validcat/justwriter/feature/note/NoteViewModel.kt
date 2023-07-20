package net.validcat.justwriter.feature.note

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import net.validcat.justwriter.core.data.repository.NoteRepository
import net.validcat.justwriter.core.model.data.Note
import net.validcat.justwriter.core.result.Result
import net.validcat.justwriter.core.result.asResult
import net.validcat.justwriter.feature.note.navigation.NoteIdArg
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val noteRepository: NoteRepository
) : ViewModel() {

    private val noteId: Int = savedStateHandle[NoteIdArg] ?: -1
    private val currentNoteFlow = MutableStateFlow<Note?>(null)

    val uiState: StateFlow<NoteUiState> = noteUiState()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = NoteUiState.Loading
        )

    fun onEvent(event: NoteEvent) {
        when (event) {
            is NoteEvent.SaveNote -> onSaveNote()
        }
    }

    private fun noteUiState(): Flow<NoteUiState> = combine(
        currentNoteFlow,
        noteRepository.getNote(id = noteId),
        ::Pair,
    )
        .asResult()
        .map { result ->
            when (result) {
                is Result.Success -> {
                    val (currentNote, note) = result.data
                    if (currentNoteFlow.value == null) {
                        currentNoteFlow.update { note }
                    }

                    NoteUiState.Success(currentNote)
                }

                is Result.Loading -> NoteUiState.Loading
                is Result.Error -> NoteUiState.Error
            }
        }

    private fun onNoteChanged(noteFields: NoteFields) {
        currentNoteFlow.update { note ->
            note?.copy(
                firstWord = noteFields.firstWord,
                secondWord = noteFields.secondWord
            )
        }
    }

    private fun onNotePinned(isPinned: Boolean) {
        currentNoteFlow.update { note ->
            note?.copy(isPinned = isPinned)
        }
    }

    private fun onColorSelect(color: Int) {
        currentNoteFlow.update { note ->
            note?.copy(color = color)
        }
    }

    private fun onSaveNote() {
        currentNoteFlow.value?.let { note ->
            if (note.isEmpty) return

            val updatedNote = note.copy(createDate = Clock.System.now()) // updateTime probably
            viewModelScope.launch {
                noteRepository.insertNote(updatedNote)
            }
        }
    }
}

sealed interface NoteUiState {
    data class Success(val note: Note?) : NoteUiState
    object Error : NoteUiState
    object Loading : NoteUiState
}

sealed interface NoteEvent {
    object SaveNote : NoteEvent
}