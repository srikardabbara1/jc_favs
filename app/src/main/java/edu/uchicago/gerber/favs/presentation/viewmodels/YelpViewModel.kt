package edu.uchicago.gerber.favs.presentation.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import edu.uchicago.gerber.favs.common.Constants
import edu.uchicago.gerber.favs.data.models.YelpResponse
import edu.uchicago.gerber.favs.data.repository.ApiProvider
import edu.uchicago.gerber.favs.data.repository.YelpRepository
import edu.uchicago.gerber.favs.presentation.screens.search.paging.YelpSource
import edu.uchicago.gerber.favs.presentation.screens.search.paging.Paginate
import edu.uchicago.gerber.favs.presentation.screens.search.paging.SearchOperation
import edu.uchicago.gerber.favs.presentation.screens.search.paging.SearchState
import kotlinx.coroutines.launch

class YelpViewModel : ViewModel() {

    private val yelpRepository: YelpRepository = YelpRepository(ApiProvider.yelpApi())

    //////////////////////////////////////////
    // MUTABLE-STATES AND OBSERVABLE STATES for YELP BUSINESSES
    //////////////////////////////////////////
    private var _queryText = mutableStateOf("")
    val queryText: State<String> = _queryText

    private var _business = mutableStateOf(Constants.fakeBusiness)
    val business: MutableState<YelpResponse.Business> = _business

    private val _searchState = mutableStateOf(SearchState())
    val searchState: State<SearchState> = _searchState

    //////////////////////////////////////////
    // MUTABLE-STATES AND OBSERVABLE STATES for FAVORITES
    //////////////////////////////////////////
    private val _favoritesList = mutableStateOf(listOf<YelpResponse.Business>())
    val favoritesList: State<List<YelpResponse.Business>> = _favoritesList

    //////////////////////////////////////////
    // MUTABLE-STATES AND OBSERVABLE STATES for AUTHENTICATION
    //////////////////////////////////////////
    private var _username = mutableStateOf("")
    val username: State<String> = _username

    private var _email = mutableStateOf("")
    val email: State<String> = _email

    private var _password = mutableStateOf("")
    val password: State<String> = _password

    private var _code = mutableStateOf("")
    val code: State<String> = _code

    //////////////////////////////////////////
    // FUNCTIONS
    //////////////////////////////////////////
    fun setUsername(text: String) {
        _username.value = text
    }

    fun setEmail(text: String) {
        _email.value = text
    }

    fun setPassword(text: String) {
        _password.value = text
    }

    fun setCode(text: String) {
        _code.value = text
    }

    fun setBusiness(business: YelpResponse.Business) {
        _business.value = business
    }

    fun setQueryText(query: String) {
        _queryText.value = query
    }

    fun addFavorite(business: YelpResponse.Business) {
        _favoritesList.value += business
    }

    fun onSearch() {
        _searchState.value = SearchState(searchOperation = SearchOperation.LOADING)
        viewModelScope.launch {
            _searchState.value = SearchState(
                data = Pager(
                    config = PagingConfig(pageSize = 10, prefetchDistance = 5),
                    pagingSourceFactory = {
                        YelpSource(
                            yelpRepository = yelpRepository,
                            paginateData = Paginate(
                                query = _queryText.value,
                            )
                        )
                    }
                ).flow.cachedIn(viewModelScope),
                searchOperation = SearchOperation.DONE
            )
        }
    }
}
