package edu.uchicago.gerber.favs.presentation.screens.search.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import edu.uchicago.gerber.favs.data.models.YelpResponse
import edu.uchicago.gerber.favs.data.repository.YelpRepository

class YelpSource(
    private val yelpRepository: YelpRepository,
    private val paginateData: Paginate
) : PagingSource<Int, YelpResponse.Business>() {

    override fun getRefreshKey(state: PagingState<Int, YelpResponse.Business>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, YelpResponse.Business> {
        return try {
            // Here is the hardcoded response for "Easy Does It"
            val hardcodedBusiness = YelpResponse.Business().apply {
                id = "easy-does-it"
                name = "Easy Does It"
                rating = 4.8
                location = YelpResponse.Location().apply {
                    address1 = "2354 N Milwaukee Ave"
                    city = "Chicago"
                    state = "IL"
                    zipCode = "60647"
                }
                imageUrl = "https://s3-media3.fl.yelpcdn.com/bphoto/dXb5yZLr8K2HqqUkvTAp3A/o.jpg"
                url = "https://www.yelp.com/biz/easy-does-it-chicago"
            }

            // Always return the hardcoded business
            LoadResult.Page(
                data = listOf(hardcodedBusiness),
                prevKey = null,
                nextKey = null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
