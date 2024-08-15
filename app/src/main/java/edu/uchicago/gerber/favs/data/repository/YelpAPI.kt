package edu.uchicago.gerber.favs.data.repository

import edu.uchicago.gerber.favs.data.models.YelpResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface YelpApi {

    @GET(value = "v3/businesses/search")
    suspend fun getHappyHours(
        @Query("location") location: String,
        @Query("term") term: String = "happy hour",
        @Query("limit") limit: Int = 5
    ): Response<YelpResponse>

}
