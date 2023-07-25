package net.validcat.justwriter.feature.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aallam.openai.api.BetaOpenAI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import net.validcat.justwriter.core.data.repository.JWOpenAIRepository
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
    private val openaiRepository: JWOpenAIRepository,
    private val noteRepository: NoteRepository
) : ViewModel() {

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    private val _noteDescription = MutableStateFlow("")
    val noteDescription = _noteDescription.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage = _errorMessage.asStateFlow()

    init {
        getRemoteOverview()
    }

    @OptIn(BetaOpenAI::class)
    fun getRemoteOverview() {
        viewModelScope.launch {
            openaiRepository.getOverview().map {
                _isLoading.update { false }

//                    .onEach { print(it.choices.first().delta?.content.orEmpty()) }
//                .onCompletion {
//                    Log.d(this.toString(), "Creating chat completions stream...")
//                }
//                .launchIn(this)
//                .join()
                _noteDescription.update {
                    "updated"
                }
            }
        }
    }


    val noteItems = noteRepository.getNotes().map {
        _isLoading.update { false }
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