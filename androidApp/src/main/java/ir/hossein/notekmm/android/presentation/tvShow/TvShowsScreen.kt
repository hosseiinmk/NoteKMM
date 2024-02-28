package ir.hossein.notekmm.android.presentation.tvShow

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ir.hossein.notekmm.android.utilities.randomColor
import ir.hossein.notekmm.domain.model.TvShow
import org.koin.androidx.compose.koinViewModel

@Composable
fun TvShowsScreen(
    viewModel: TvShowsViewModel = koinViewModel(),
    onBack: () -> Unit
) {

    val state by remember { viewModel.state }

    AnimatedContent(targetState = state.loading, label = "Animated Content") { isLoading ->
        when (isLoading) {
            true -> ShowLoading()
            false -> ShowTvShowsList(state = state, loadNextPage = { viewModel.loadNextPage() })
        }
    }

    BackHandler { onBack() }
}

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
            modifier = Modifier.fillMaxSize().padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(state.tvShows) {
                TvShowItem(
                    tvShow = it,
                    backgroundColor = randomColor()
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
                        true -> ShowLoading()
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
    tvShow: TvShow,
    backgroundColor: Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(backgroundColor)
    ) {
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

@Composable
fun ShowLoading() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}