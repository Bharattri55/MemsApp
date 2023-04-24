package com.example.memes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.memes.data.PostResponse
import com.example.memes.ui.theme.MemesTheme
import com.example.memes.viewModel.PostViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: PostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MemesTheme {
                PostInfoDetailsView()
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun PostInfoDetailsView() {
        val swipeRefreshState = rememberSwipeRefreshState(viewModel.isRefreshing)
        SwipeRefresh(
            modifier = Modifier.fillMaxSize(),
            state = swipeRefreshState,
            indicatorAlignment = Alignment.BottomCenter,
            onRefresh = {
                viewModel.fetchPostInfoDetails()
            },
            indicator = { state, trigger ->
                SwipeRefreshIndicator(
                    // Pass the SwipeRefreshState + trigger through
                    state = state,
                    refreshTriggerDistance = trigger,
                    // Enable the scale animation
                    scale = true,
                    // Change the color and shape
                    backgroundColor = MaterialTheme.colors.primary,

                    shape = MaterialTheme.shapes.small,
                )
            }
        ) {
            Row {
                LazyColumn(
                ) {
                    itemsIndexed(viewModel.mutableList) { i, item ->
                        Box(
                            modifier = Modifier.padding(10.dp)
                        ) {
                            ImageAndText(image = item[0].url, text = item[0].title)
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun ImageAndText(
    modifier: Modifier = Modifier,
    image: String,
    text: String
) {
    val shape = RoundedCornerShape(10.dp)
    val height = 500.dp
    Box(
        modifier = modifier
            .height(height)
            .fillMaxWidth()
            .fillMaxHeight(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Surface(
            color = Color.Black.copy(alpha = 0.6f),
            modifier = Modifier.fillMaxSize().align(Alignment.BottomCenter).align(Alignment.BottomCenter)
        ){
            AsyncImage(
                model = image,
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxSize()
            )

            Text(
                text = text,
                color = White,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily.Monospace,
                fontStyle = FontStyle.Companion.Normal,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
                    .fillMaxWidth()
            )
        }
    }
}