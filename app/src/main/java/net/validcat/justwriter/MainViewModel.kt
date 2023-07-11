package net.validcat.justwriter

import android.service.autofill.UserData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
//    private val userDataRepository: UserDataRepository
) : ViewModel() {
//
//    val uiState: StateFlow<MainState> = userDataRepository.userData.map {
//        Success(it)
//    }.stateIn(
//        scope = viewModelScope,
//        initialValue = MainState.Loading,
//        started = SharingStarted.WhileSubscribed(5_000)
//    )
}

sealed interface MainState {
    object Loading : MainState
    data class Success(val userData: UserData) : MainState
}