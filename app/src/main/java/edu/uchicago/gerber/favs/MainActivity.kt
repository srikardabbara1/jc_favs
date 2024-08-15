package edu.uchicago.gerber.favs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import edu.uchicago.gerber.favs.presentation.navigation.Navigation
import androidx.compose.animation.ExperimentalAnimationApi
import edu.uchicago.gerber.favs.authorization.AmplifyService
import edu.uchicago.gerber.favs.ui.theme.FavoriteBooksTheme

@OptIn(ExperimentalAnimationApi::class)
class MainActivity : ComponentActivity() {
    private val amplifyService: AmplifyService = AmplifyService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        amplifyService.configureAmplify(this)

        setContent {
            FavoriteBooksTheme {
                val navController = rememberAnimatedNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Navigation(navController = navController, amplifyService = amplifyService)
                }
            }
        }
    }
}
