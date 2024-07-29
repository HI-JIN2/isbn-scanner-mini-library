package com.joss.isbn

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url


interface GoogleSheetApi {

    @GET
    fun addBookatSheet(
        @Url url: String,
        @Query("datetime") datetime: String,
        @Query("title") title: String,
        @Query("author") range: String,
        @Query("isbn") isbn: String,
        @Query("callNumber") callNumber: String,
    ): Call<String> //스프레드시트에 값을 추가합니다.
}
