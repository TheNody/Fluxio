package io.fluxio.app.presentation.ui.screens.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.fluxio.app.domain.usecase.home.GetHomeFeedUseCase
import io.fluxio.app.presentation.ui.screens.home.state.HomeUiState
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getHomeFeedUseCase: GetHomeFeedUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadHomeFeed()
    }

    fun loadHomeFeed() {
        _uiState.update { it.copy(isLoading = true, errorMessage = null) }
        viewModelScope.launch {
            try {
                val result = getHomeFeedUseCase()
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        videos = result.items,
                        errorMessage = null
                    )
                }
            } catch (throwable: Throwable) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = throwable.message ?: "Unknown error"
                    )
                }
            }
        }
    }
}
