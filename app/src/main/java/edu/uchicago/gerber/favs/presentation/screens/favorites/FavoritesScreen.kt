package edu.uchicago.gerber.favs.presentation.screens.favorites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import edu.uchicago.gerber.favs.authorization.AmplifyService
import edu.uchicago.gerber.favs.common.Constants
import edu.uchicago.gerber.favs.data.models.YelpResponse
import edu.uchicago.gerber.favs.presentation.viewmodels.YelpViewModel
import edu.uchicago.gerber.favs.presentation.widgets.CustomBottomNavigationBar
import edu.uchicago.gerber.favs.presentation.widgets.CustomTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(navController: NavController, amplifyService: AmplifyService, viewModel: YelpViewModel) {
    val favoritesList = viewModel.favoritesList.value

    Scaffold(
        modifier = Constants.modifier,
        bottomBar = { CustomBottomNavigationBar(navController) },
        topBar = {
            CustomTopBar(titleText = "Favorites", navController = navController, amplifyService = amplifyService)
        }
    ) { paddingValues ->
        if (favoritesList.isEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues = paddingValues)
                    .background(color = Color(0x37000000))
                    .wrapContentSize(Alignment.Center)
            ) {
                Text(
                    text = "No favorites added yet.",
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    textAlign = TextAlign.Center,
                    fontSize = 25.sp
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues = paddingValues)
                    .background(color = Color(0x37000000))
            ) {
                items(
                    items = favoritesList,
                    key = { it.id }  // assuming 'id' is unique
                ) { favoriteItem ->
                    FavoriteItemRow(favoriteItem)
                }
            }
        }
    }
}

@Composable
fun FavoriteItemRow(business: YelpResponse.Business) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = business.name ?: "Unknown",
            fontWeight = FontWeight.Bold,
            color = Color.White,
            fontSize = 20.sp
        )
        business.location?.let { location ->
            Text(
                text = "${location.address1}, ${location.city}, ${location.state} ${location.zipCode}",
                color = Color.White,
                fontSize = 16.sp
            )
        }
    }
}
