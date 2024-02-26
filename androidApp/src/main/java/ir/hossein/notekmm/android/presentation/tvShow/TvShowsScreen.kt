package ir.hossein.notekmm.android.presentation.tvShow

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ir.hossein.notekmm.domain.model.TvShow
import org.koin.androidx.compose.koinViewModel

@Composable
fun TvShowsScreen(
    viewModel: TvShowsViewModel = koinViewModel(),
    onBack: () -> Unit
) {

    val state by viewModel.state
    val listState = rememberLazyListState()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        AnimatedVisibility(visible = state.isLoading) {
            CircularProgressIndicator()
        }
        AnimatedVisibility(visible = !state.isLoading) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                state = listState,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(state.tvShows) {
                    TvShowItem(tvShow = it)
                }
                item {
                    AnimatedVisibility(visible = state.isLoadingMore, modifier = Modifier.padding(8.dp)) {
                        CircularProgressIndicator()
                    }
                    AnimatedVisibility(visible = !state.isLoadingMore, modifier = Modifier.padding(8.dp)) {
                        IconButton(onClick = { viewModel.loadMore() }) {
                            Icon(imageVector = Icons.Default.Add, contentDescription = null)
                        }
                    }
                }
            }
        }
    }
    BackHandler { onBack() }
}

@Composable
fun TvShowItem(tvShow: TvShow) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color.LightGray)
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