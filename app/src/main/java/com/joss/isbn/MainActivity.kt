package com.joss.isbn

import android.Manifest
import android.content.pm.PackageManager

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.joss.isbn.databinding.ActivityMainBinding
import com.journeyapps.barcodescanner.BarcodeCallback
import com.journeyapps.barcodescanner.BarcodeResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Locale


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val apiKey = BuildConfig.api_key

    var title: String = ""
    var author: String = ""
    var isbnCode: String = ""
    var callNumber: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            startScanning()
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 1)
        }

        binding.btnDoScan.setOnClickListener {
            binding.barcodeView.resume()
        }

        binding.barcodeView.decodeContinuous(object : BarcodeCallback {
            override fun barcodeResult(result: BarcodeResult) {
                binding.barcodeView.pause()
                val isbn = result.text

                binding.tbIsbn.text = "Scanned ISBN: ${isbn}"

                binding.btnFindInfo.setOnClickListener() {
                    fetchBookInfo(isbn)
                    createCallNumber()
                }

                binding.btnAddDb.setOnClickListener {
                    if (title.isNotEmpty()) {
                        addBook(title, author, isbn, callNumber)
                    }
                }
            }

            override fun possibleResultPoints(resultPoints: List<com.google.zxing.ResultPoint>) {}
        })
    }

    private fun startScanning() {
        binding.barcodeView.resume()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1 && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            startScanning()
        } else {
            Toast.makeText(this, "Camera permission is required for scanning", Toast.LENGTH_SHORT)
                .show()
        }
    }

    override fun onResume() {
        super.onResume()
        binding.barcodeView.resume()
    }

    override fun onPause() {
        super.onPause()
        binding.barcodeView.pause()
    }


    private fun fetchBookInfo(isbn: String) {
        val query = "isbn:$isbn"

        Toast.makeText(
            this@MainActivity,
            "$isbn+의 정보를 조회 중",
            Toast.LENGTH_SHORT
        ).show()
        RetrofitClient.booksInstance.getBookByISBN(query, apiKey).enqueue(object :
            Callback<BookResponse> {
            override fun onResponse(call: Call<BookResponse>, response: Response<BookResponse>) {

                Log.d("bookinfo", response.body().toString())

                if (response.isSuccessful && response.body()?.items?.isNotEmpty() == true) {
                    val book = response.body()?.items?.get(0)?.volumeInfo
                    binding.bookTitleTextView.text = "Title: ${book?.title}"
                    binding.bookAuthorTextView.text =
                        "Authors: ${book?.authors?.joinToString(", ")}"


                    title = book?.title.toString()
                    author = book?.authors.toString()

                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "No book found for this ISBN",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<BookResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Failed to fetch book info", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }

    fun createCallNumber(): String {
        callNumber =
            author.substring(0 until 3).uppercase(Locale.getDefault()) + title.substring(0 until 1)
        return callNumber
    }

    val sheetId = BuildConfig.sheet_id
    val url = "https://script.google.com/macros/s/$sheetId/exec?action=create"


    fun addBook(title: String, author: String, isbn: String, callNumber: String) {

        Toast.makeText(this, isbnCode + "의 정보를 DB에 추가합니다.", Toast.LENGTH_SHORT).show()

        RetrofitClient.sheetInstance.addBookatSheet(url, title, author, isbn, callNumber)
            .enqueue(object :
                Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>) {

                    Log.d("sheetresponse", response.body().toString())

                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Failed to add to DB", Toast.LENGTH_SHORT)
                        .show()
                }
            })
    }
}
