package com.joss.isbn

import com.google.api.services.sheets.v4.model.UpdateValuesResponse

data class BookResponse(
    val items: List<BookItem>
)

data class BookItem(
    val volumeInfo: VolumeInfo
)

data class VolumeInfo(
    val title: String,
    val authors: List<String>
)


data class Book(
    val title: String?,
    val author: String?,
    val isbn: String?
)


data class SheetResponse(
    val spreadsheetId: String?,
    val tableRange: String,
    val updates: UpdateValuesResponse

)
