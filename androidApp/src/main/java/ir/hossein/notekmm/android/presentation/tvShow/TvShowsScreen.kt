package ir.hossein.notekmm.android.presentation.tvShow

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ir.hossein.notekmm.android.core.globalStateValue
import ir.hossein.notekmm.android.presentation.loading.LoadingScreen
import ir.hossein.notekmm.domain.model.TvShow
import org.koin.androidx.compose.koinViewModel

@Composable
fun TvShowsScreen(
    viewModel: TvShowsViewModel = koinViewModel(),
    onBack: () -> Unit
) {

    val state by viewModel.state().collectAsState()

    if (globalStateValue().darkTheme)
    
    viewModel.setTheme(state.tvShows.size)

    AnimatedContent(targetState = state.loading, label = "Animated Content") { isLoading ->
        when (isLoading) {
            false -> ShowTvShowsList(state = state, loadNextPage = { viewModel.loadNextPage() })
            true -> LoadingScreen()
        }
    }

    BackHandler { onBack() }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ShowTvShowsList(
    state: TvShowsUiState,
    loadNextPage: () -> Unit
) {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            itemsIndexed(state.tvShows, key = { _, item -> item.id }) { position, item ->
                TvShowItem(
                    modifier = Modifier
                        .animateItemPlacement()
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(state.backgroundColor[position]),
                    tvShow = item
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
            item {
                AnimatedContent(
                    targetState = state.loadingMore,
                    label = "",
                    contentAlignment = Alignment.Center
                ) { isLoadingMore ->
                    when (isLoadingMore) {
                        true -> LoadingScreen()
                        else -> {
                            IconButton(onClick = { loadNextPage() }) {
                                Icon(imageVector = Icons.Default.Add, contentDescription = null)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TvShowItem(
    modifier: Modifier,
    tvShow: TvShow
) {
    Row(modifier = modifier) {
        AsyncImage(
            model = tvShow.thumbnail,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(100.dp)
                .height(150.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(text = tvShow.name)
            Text(text = "start on: ${tvShow.startDate}")
            Text(text = tvShow.status)
        }
    }
}