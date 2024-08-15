package edu.uchicago.gerber.favs.presentation.screens.search.paging

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.uchicago.gerber.favs.data.models.YelpResponse

@Composable
fun BookRow(
    book: YelpResponse.Business,
    onItemClick: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                book.id?.let {
                    //passed down from composable (BookList) that instantiates BookRow
                    onItemClick(it)
                }
            }
            .padding(10.dp, 5.dp, 5.dp, 10.dp),
        shape = RoundedCornerShape(corner = CornerSize(4.dp)),
        elevation = CardDefaults.cardElevation(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Row(horizontalArrangement = Arrangement.Start) {

            Column {

                Text(
                    text = book.name ?: "Unknown Business",
                    style = TextStyle(fontWeight = FontWeight.Bold),
                    textAlign = TextAlign.Start,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(10.dp)
                )
                Text(
                    text = book.location.address1 ?: "No Address Available",
                    style = TextStyle(fontWeight = FontWeight.Normal),
                    textAlign = TextAlign.Start,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(10.dp)
                )
            }
        }
    }
}
