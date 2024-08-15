package edu.uchicago.gerber.favs.presentation.screens.search.paging

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import edu.uchicago.gerber.favs.presentation.navigation.Screen
import edu.uchicago.gerber.favs.presentation.viewmodels.YelpViewModel

@Composable
fun BookList(bookViewModel: YelpViewModel, navController: NavController) {

    // This is what consumes the flow
    val lazyPagingItems = bookViewModel.searchState.value.data?.collectAsLazyPagingItems()

    LazyColumn {
        items(
            count = lazyPagingItems!!.itemCount,
            key = lazyPagingItems.itemKey(),
            contentType = lazyPagingItems.itemContentType()
        ) { index ->
            // The following lines define the onItemClick behavior
            val businessItem = lazyPagingItems[index]!!
            BookRow(book = businessItem) {
                // The following lines define the onItemClick behavior
                bookViewModel.setBusiness(businessItem)
                navController.navigate(
                    route = Screen.Detail.route
                )
            }
        }

        // This will display a spinner in-place of a BookRow in the following events
        lazyPagingItems.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item {
                        Spinner()
                    }
                }
                loadState.append is LoadState.Loading -> {
                    item {
                        Spinner()
                    }
                }
                loadState.prepend is LoadState.Loading -> {
                    item {
                        Spinner()
                    }
                }
            }
        }
    }
}

@Composable
fun Spinner() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .padding(12.dp)
                .align(
                    Alignment.Center
                )
        )
    }
}
