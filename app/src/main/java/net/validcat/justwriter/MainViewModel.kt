package net.validcat.justwriter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import net.validcat.justwriter.core.data.repository.UserDataRepository
import net.validcat.justwriter.core.model.data.UserData
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userDataRepository: UserDataRepository
) : ViewModel() {

    val uiState: StateFlow<MainState> = userDataRepository.userData.map {
        MainState.Success(it)
    }.stateIn(
        scope = viewModelScope,
        initialValue = MainState.Loading,
        started = SharingStarted.WhileSubscribed(5_000)
    )
}

sealed interface MainState {
    object Loading : MainState
    data class Success(val userData: UserData) : MainState
}