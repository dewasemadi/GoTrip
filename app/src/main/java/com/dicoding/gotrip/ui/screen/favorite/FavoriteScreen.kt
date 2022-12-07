package com.dicoding.gotrip.ui.screen.favorite

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dicoding.gotrip.data.local.TourismEntity
import com.dicoding.gotrip.ui.components.*
import com.dicoding.gotrip.utils.UiState

@Composable
fun FavoriteScreen(navController: NavController, scaffoldState: ScaffoldState) {
    val favoriteViewModel = hiltViewModel<FavoriteViewModel>()

    favoriteViewModel.allFavoriteTourism.collectAsState(UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> LoadingIndicator()
            is UiState.Error -> ErrorContent()
            is UiState.Success -> {
                FavoriteContent(
                    listFavoriteTourism = uiState.data,
                    navController = navController,
                    scaffoldState = scaffoldState,
                    onUpdateFavoriteTourism = favoriteViewModel::updateFavoriteTourism
                )
            }
        }
    }
}

@Composable
fun FavoriteContent(
    listFavoriteTourism: List<TourismEntity>,
    navController: NavController,
    scaffoldState: ScaffoldState,
    onUpdateFavoriteTourism: (id: Int, isFavorite: Boolean) -> Unit
) {
    when (listFavoriteTourism.isEmpty()) {
        true -> EmptyContent()
        false -> AvailableContent(listFavoriteTourism, navController, scaffoldState, onUpdateFavoriteTourism)
    }
}