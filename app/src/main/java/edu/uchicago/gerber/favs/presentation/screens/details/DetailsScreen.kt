package edu.uchicago.gerber.favs.presentation.screens.details

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.skydoves.landscapist.glide.GlideImage
import edu.uchicago.gerber.favs.R
import edu.uchicago.gerber.favs.common.Constants
import edu.uchicago.gerber.favs.presentation.viewmodels.YelpViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    yelpViewModel: YelpViewModel,
    navController: NavController
) {

    // Observe the selected business from the ViewModel
    val business = yelpViewModel.business.value
    val activity = (LocalContext.current as? Activity)

    Scaffold(
        modifier = Constants.modifier,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color.Transparent
                ),
                title = {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        Icon(imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Arrow Back",
                            modifier = Modifier
                                .clickable {
                                    navController.popBackStack()
                                }
                                .align(Alignment.CenterVertically)
                                .padding(20.dp, 0.dp, 0.dp, 0.dp))

                        Text(
                            text = "Details",
                            modifier = Modifier
                                .align(Alignment.CenterVertically),
                            style = TextStyle(color = Color.Black, fontWeight = FontWeight.Bold),
                            textAlign = TextAlign.Center,
                            fontSize = 25.sp
                        )

                        Row(
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                        ) {

                            Icon(imageVector = Icons.Default.Share,
                                contentDescription = "Share",
                                modifier = Modifier
                                    .clickable {
                                        val sendIntent = Intent(Intent.ACTION_SEND)
                                        sendIntent.type = "text/plain"
                                        sendIntent.putExtra(
                                            Intent.EXTRA_TEXT,
                                            "You must check out this place!: ${business.name}, ${business.url}"
                                        )
                                        activity?.startActivity(sendIntent)
                                    }
                                    .align(Alignment.CenterVertically)
                                    .padding(10.dp, 0.dp, 0.dp, 0.dp))
                            Spacer(modifier = Modifier.width(20.dp))
                            Icon(painter = painterResource(id = R.drawable.ic_navigation),
                                contentDescription = "Map",

                                modifier = Modifier
                                    .clickable {
                                        val address = "${business.location.address1}, ${business.location.city}, ${business.location.state} ${business.location.zipCode}"
                                        val intent = Intent(
                                            Intent.ACTION_VIEW,
                                            Uri.parse("google.navigation:q=$address")
                                        )
                                        activity?.startActivity(intent)
                                    }
                                    .align(Alignment.CenterVertically)
                                    .padding(10.dp, 0.dp, 10.dp, 0.dp))
                            Spacer(modifier = Modifier.width(20.dp))
                            Icon(imageVector = Icons.Default.Phone,
                                contentDescription = "Phone",

                                modifier = Modifier
                                    .clickable {
                                        // No phone number available in the provided JSON
                                        Toast.makeText(activity, "Phone number not available", Toast.LENGTH_SHORT).show()
                                    }
                                    .align(Alignment.CenterVertically)
                                    .padding(0.dp, 0.dp, 20.dp, 0.dp))
                        }

                    }

                })
        }) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxHeight()
                .padding(paddingValues = paddingValues)
                .fillMaxWidth()
                .verticalScroll(state = rememberScrollState(0))
                .padding(20.dp, 0.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {

                Divider()
                Spacer(Modifier.height(20.dp))

                // Display business image
                GlideImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(300.dp),
                    imageModel = business.imageUrl ?: "https://picsum.photos/id/1026/200/300",
                    contentScale = ContentScale.Fit
                )

                business.name?.let {
                    Text(
                        text = it,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        style = TextStyle(fontWeight = FontWeight.Bold),
                        textAlign = TextAlign.Start,
                        fontSize = 22.sp
                    )
                }

                business.location.let {
                    Text(
                        text = "${it.address1}, ${it.city}, ${it.state} ${it.zipCode}",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        style = TextStyle(fontWeight = FontWeight.Normal),
                        textAlign = TextAlign.Start,
                        fontSize = 16.sp
                    )
                }

                Button(
                    modifier =
                    Modifier
                        .padding(10.dp, 0.dp)
                        .fillMaxWidth(1f),

                    onClick = {
                        // Use the addFavorite function from YelpViewModel to add the business to favorites
                        yelpViewModel.addFavorite(business)

                        // Optional: Show a Toast message confirming the addition
                        Toast.makeText(activity, "${business.name} added to favorites", Toast.LENGTH_LONG).show()
                    },

                    colors = ButtonDefaults.buttonColors(containerColor = Color.Green)

                ) {
                    Text(text = "Add to Favorites")
                }

            }
        }
    }

}
