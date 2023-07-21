package net.validcat.justwriter.feature.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import net.validcat.justwriter.core.data.repository.LocalNoteRepository
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

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage = _errorMessage.asStateFlow()

    val noteItems = noteRepository.getNotes().map {
        it.toNoteItems()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )

    sealed interface NotesEvent {
        object NotesDelete : NotesEvent
        object NotesPin : NotesEvent
    }
}