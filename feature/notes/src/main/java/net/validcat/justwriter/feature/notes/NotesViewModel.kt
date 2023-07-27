package net.validcat.justwriter.feature.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import net.validcat.justwriter.core.data.repository.JWOpenAIRepository
import net.validcat.justwriter.core.data.repository.NoteRepository
import net.validcat.justwriter.core.data.repository.UserDataRepository
import net.validcat.justwriter.core.model.data.Story
import net.validcat.justwriter.core.result.Result.*
import net.validcat.justwriter.core.result.asResult
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val userDataRepository: UserDataRepository,
    private val openaiRepository: JWOpenAIRepository,
    private val noteRepository: NoteRepository
) : ViewModel() {

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    private val _noteDescription = MutableStateFlow("")
    val noteDescription = _noteDescription.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage = _errorMessage.asStateFlow()

    val overview: StateFlow<Story> =
        openaiRepository.getOverview(id = 0)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = Story()
            )

    fun getRemoteOverview() {
        viewModelScope.launch {
                _isLoading.update { true }
            openaiRepository.fetchOverview("Tell story with the next words: snake, fox, friendship")
                _isLoading.update { false }
        }
    }

    sealed interface NotesEvent {
        object NotesDelete : NotesEvent
        object NotesPin : NotesEvent
    }
}
