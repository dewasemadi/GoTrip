package com.dicoding.gotrip.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dicoding.gotrip.data.local.TourismEntity
import com.dicoding.gotrip.ui.components.*
import com.dicoding.gotrip.utils.UiState

@Composable
fun HomeScreen(navController: NavController, scaffoldState: ScaffoldState) {
    val homeViewModel = hiltViewModel<HomeViewModel>()
    val homeState by homeViewModel.homeState

    homeViewModel.allTourism.collectAsState(UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> LoadingIndicator()
            is UiState.Error -> ErrorContent()
            is UiState.Success -> {
                HomeContent(
                    listTourism = uiState.data,
                    navController = navController,
                    scaffoldState = scaffoldState,
                    query = homeState.query,
                    onQueryChange = homeViewModel::onQueryChange,
                    onUpdateFavoriteTourism = homeViewModel::updateFavoriteTourism
                )
            }
        }
    }
}

@Composable
fun HomeContent(
    listTourism: List<TourismEntity>,
    navController: NavController,
    scaffoldState: ScaffoldState,
    query: String,
    onQueryChange: (String) -> Unit,
    onUpdateFavoriteTourism: (id: Int, isFavorite: Boolean) -> Unit
) {
    Column {
        SearchBar(query = query, onQueryChange = onQueryChange)
        when (listTourism.isEmpty()) {
            true -> EmptyContent()
            false -> AvailableContent(listTourism, navController, scaffoldState, onUpdateFavoriteTourism)
        }
    }
}

