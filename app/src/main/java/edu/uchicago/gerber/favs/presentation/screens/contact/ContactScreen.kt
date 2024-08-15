package edu.uchicago.gerber.favs.presentation.screens.contact

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import edu.uchicago.gerber.favs.authorization.AmplifyService
import edu.uchicago.gerber.favs.common.Constants
import edu.uchicago.gerber.favs.presentation.viewmodels.YelpViewModel
import edu.uchicago.gerber.favs.presentation.widgets.CustomBottomNavigationBar
import edu.uchicago.gerber.favs.presentation.widgets.CustomTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactScreen( navController: NavController, amplifyService: AmplifyService) {

    Scaffold(
        modifier = Constants.modifier,
        bottomBar = { CustomBottomNavigationBar(navController) },
        topBar = {
            CustomTopBar(titleText = "Contact Us", navController=navController, amplifyService=amplifyService)
        }
    ) {
            paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues)
                .background(color = Color(0x37000000))
                .wrapContentSize(Alignment.Center)
        ) {
            Text(
                text = "Contact View",
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center,
                fontSize = 25.sp
            )
        }
    }
}