package com.dicoding.gotrip.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.gotrip.data.local.TourismEntity
import com.dicoding.gotrip.data.repository.Repository
import com.dicoding.gotrip.utils.TourismData
import com.dicoding.gotrip.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private val _allTourism = MutableStateFlow<UiState<List<TourismEntity>>>(UiState.Loading)
    val allTourism = _allTourism.asStateFlow()

    private val _homeState = mutableStateOf(HomeState())
    val homeState: State<HomeState> = _homeState

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllTourism().collect { tourism ->
                when (tourism.isEmpty()) {
                    true -> repository.insertAllTourism(TourismData.dummy)
                    else -> _allTourism.value = UiState.Success(tourism)
                }
            }
        }
    }

    private fun searchTourism(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.searchTourism(query)
                .catch { _allTourism.value = UiState.Error(it.message.toString()) }
                .collect { _allTourism.value = UiState.Success(it) }
        }
    }

    fun updateFavoriteTourism(id: Int, isFavorite: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.apply {
                updateFavoriteTourism(id, isFavorite)
            }
        }
    }

    fun onQueryChange(query: String) {
        _homeState.value = _homeState.value.copy(query = query)
        searchTourism(query)
    }
}