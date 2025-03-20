package com.example.bookapp.ui.screens


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.example.bookapp.R
import com.example.bookapp.data.Book

@Composable
fun BooksGridScreen(
    books: List<Book>,
    modifier: Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        contentPadding = PaddingValues(20.dp)
    ) {
        itemsIndexed(books) { _, book ->
            BooksCard(book = book, modifier)
        }
    }
}

@Composable
fun BooksCard(
    book: Book,
    modifier: Modifier
) {
    Card(
        modifier = modifier
            .padding(12.dp)
            .fillMaxWidth()
            .requiredHeight(230.dp)

    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            AsyncImage(
                modifier = modifier.fillMaxWidth(),
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(book.imageLink?.replace("http:", "https:"))
                    .build(),
                placeholder = painterResource(id = R.drawable.loading_spin_icon),
                contentDescription = stringResource(id = R.string.content_description),
                contentScale = ContentScale.Crop
            )
            book.author?.let {
                Text(
                    text = it,
                    textAlign = TextAlign.Center,
                    modifier = modifier
                        .padding(top = 8.dp, bottom = 2.dp)
                )
            }
            book.title?.let {
                Text(
                    text = it,
                    textAlign = TextAlign.Center,
                    modifier = modifier
                        .padding(top = 2.dp, bottom = 20.dp)
                )
            }
        }
    }
}