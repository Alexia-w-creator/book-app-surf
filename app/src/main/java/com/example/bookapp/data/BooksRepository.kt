package com.example.bookapp.data

import android.os.Build
import com.example.bookapp.network.BookService

interface BooksRepository {
    suspend fun getBooks(query: String, maxResults: Int) : List<Book>
}

class NetworkBooksRepository(
    private val bookService: BookService
) : BooksRepository {
    override suspend fun getBooks(
        query: String,
        maxResults: Int
    ): List<Book> = bookService.bookSearch(query, maxResults).items.map { items ->
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.VANILLA_ICE_CREAM) {
            Book(
                author = items.volumeInfo?.authors!!.first,
                title = items.volumeInfo?.title,
                previewLink = items.volumeInfo?.previewLink,
                imageLink = items.volumeInfo?.imageLinks?.thumbnail
            )
        } else {
            TODO("VERSION.SDK_INT < VANILLA_ICE_CREAM")
        }
    }
}