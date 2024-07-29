package com.joss.isbn

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    val booksInstance: GoogleBooksApi by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.googleapis.com/books/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(GoogleBooksApi::class.java)
    }

    val sheetInstance: GoogleSheetApi by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://script.google.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(GoogleSheetApi::class.java)
    }
}
