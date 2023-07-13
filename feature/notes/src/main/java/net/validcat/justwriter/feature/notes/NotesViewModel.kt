package net.validcat.justwriter.feature.notes

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
import net.validcat.justwriter.core.data.repository.NoteRepository
import net.validcat.justwriter.core.data.repository.UserDataRepository
import net.validcat.justwriter.core.model.data.Note
import net.validcat.justwriter.core.model.data.NoteListType
import net.validcat.justwriter.core.model.data.UserData
import net.validcat.justwriter.core.result.Result.*
import net.validcat.justwriter.core.result.asResult
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val userDataRepository: UserDataRepository,
    private val noteRepository: NoteRepository
) : ViewModel() {

    private val selectedNoteIds = MutableStateFlow(emptyList<Int>())
    private val userData: Flow<UserData> = userDataRepository.userData

    val uiState: StateFlow<NotesUiState> = notesUiState()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = NotesUiState.Loading
        )

    fun onEvent(event: NotesEvent) {
        when (event) {
            is NotesEvent.ToggleNoteListType -> {}
            is NotesEvent.NoteSelected -> {}
            is NotesEvent.UnselectAllNotes -> {}
            is NotesEvent.NotesDelete -> {}
            is NotesEvent.ColorSelect -> {}
            is NotesEvent.NotesPin -> {}
        }
    }

    private fun notesUiState(): Flow<NotesUiState> = combine(
        selectedNoteIds,
        userData,
        noteRepository.getNotes(),
        ::Triple,
    ).asResult().map { result ->
        when (result) {
            is Loading -> NotesUiState.Loading
            is Error -> NotesUiState.Error
            is Success -> {
                val (selectedNoteIds, userData, notes) = result.data
                val noteItems = notes.toNoteItems(selectedNoteIds)
                val selectedNotesColor = selectedNotesColor(noteItems)
                val selectedNotesPinned = selectedNotesPinned(noteItems)

                NotesUiState.Success(
                    noteItems = noteItems,
                    userData = userData,
                    selectedNotesColor = selectedNotesColor,
                    selectedNotesPinned = selectedNotesPinned
                )
            }
        }
    }

    private fun selectedNotesColor(noteItems: List<NoteItem>): Int? {
        return noteItems
            .filter { it.isSelected }
            .map { it.note.color }
            .distinct()
            .singleOrNull()
    }

    private fun selectedNotesPinned(noteItems: List<NoteItem>): Boolean {
        return noteItems
            .filter { it.isSelected }
            .map { it.note.isPinned }
            .distinct()
            .singleOrNull() ?: false
    }

    sealed interface NotesUiState {
        data class Success(
            val noteItems: List<NoteItem>,
            val userData: UserData,
            val selectedNotesColor: Int?,
            val selectedNotesPinned: Boolean
        ) : NotesUiState

        object Error : NotesUiState
        object Loading : NotesUiState
    }

    sealed interface NotesEvent {
        data class ToggleNoteListType(val noteListType: NoteListType) : NotesEvent
        data class NoteSelected(val noteId: Int) : NotesEvent
        object UnselectAllNotes : NotesEvent
        object NotesDelete : NotesEvent
        data class ColorSelect(val color: Int) : NotesEvent
        object NotesPin : NotesEvent
    }

    private fun List<Note>.toNoteItems(selectedNoteIds: List<Int>): List<NoteItem> {
        return map {
            NoteItem(
                note = it,
                isSelected = selectedNoteIds.contains(it.id)
            )
        }
    }
}