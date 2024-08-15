package edu.uchicago.gerber.favs.presentation.widgets

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton

import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

import edu.uchicago.gerber.favs.R
import edu.uchicago.gerber.favs.authorization.AmplifyService
import edu.uchicago.gerber.favs.common.Constants
import edu.uchicago.gerber.favs.presentation.navigation.Screen
import edu.uchicago.gerber.favs.presentation.screens.auth.navigateAndPop
import edu.uchicago.gerber.favs.presentation.viewmodels.YelpViewModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar(titleText: String,  navController: NavController, amplifyService: AmplifyService) {
    val context = LocalContext.current

    TopAppBar(
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = Color.Transparent
        ),
        title = {
            Text(
                text = titleText,
                modifier = Modifier
                    .fillMaxWidth(),
                style = TextStyle(color = Color.Black, fontWeight = FontWeight.Bold),
                textAlign = TextAlign.Center,
                fontSize = 20.sp
            )

        },
        actions = {
            if (Constants.authenticate) IconButton(
                onClick = {
                    amplifyService.logOut {
                        MainScope().launch {
                            navigateAndPop(navController, Screen.Login.route)
                        }

                    }

                }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_logout),
                    contentDescription = "log-out"
                )
            }
        }
    )

}