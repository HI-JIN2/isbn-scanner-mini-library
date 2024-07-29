package com.joss.isbn

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GoogleBooksApi {

    @GET("volumes")
    fun getBookByISBN(
        @Query("q") query: String,
        @Query("key") apiKey: String
    )
            : Call<BookResponse>
}
