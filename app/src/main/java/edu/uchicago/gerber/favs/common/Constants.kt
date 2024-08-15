package edu.uchicago.gerber.favs.common

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.gson.Gson
import edu.uchicago.gerber.favs.data.models.YelpResponse

object Constants {

    val authenticate = false

    val modifier = Modifier.padding(paddingValues = PaddingValues(all = 0.dp))

    const val yelpUrl = "https://api.yelp.com/v3/"
    val fakeBusiness: YelpResponse.Business
    val fakeResponse: YelpResponse

    init {
        val gson = Gson()
        val hardCodedResponse = """
{
  "total": 2,
  "businesses": [
    {
      "id": "yelp-san-francisco",
      "name": "Yelp",
      "rating": 5.0,
      "location": {
        "address1": "140 New Montgomery St",
        "city": "San Francisco",
        "state": "CA",
        "zip_code": "94105"
      },
      "image_url": "https://s3-media3.fl.yelpcdn.com/bphoto/AGkF3k9OdHJYqR00tBO5_g/o.jpg",
      "url": "https://www.yelp.com/biz/yelp-san-francisco"
    },
    {
      "id": "business2-id",
      "name": "Another Business",
      "rating": 4.5,
      "location": {
        "address1": "123 Market St",
        "city": "San Francisco",
        "state": "CA",
        "zip_code": "94103"
      },
      "image_url": "https://s3-media4.fl.yelpcdn.com/bphoto/another_image/o.jpg",
      "url": "https://www.yelp.com/biz/another-business-san-francisco"
    }
  ],
  "region": {
    "center": {
      "latitude": 37.786882,
      "longitude": -122.399972
    }
  }
}
        """
        fakeResponse = gson.fromJson(hardCodedResponse, YelpResponse::class.java)
        fakeBusiness = fakeResponse.getBusinesses().get(0)
    }
}
