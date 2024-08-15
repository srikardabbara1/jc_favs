package edu.uchicago.gerber.favs.data.repository

import edu.uchicago.gerber.favs.data.models.YelpResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class YelpRepository(private val yelpApi: YelpApi) {

    suspend fun getHappyHours(
        location: String
    ): Response<YelpResponse> {
        return withContext(Dispatchers.IO) {
            yelpApi.getHappyHours(location = location)
        }
    }
}
