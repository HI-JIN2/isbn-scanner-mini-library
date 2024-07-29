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

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val apiKey = "AIzaSyAG-zIw81MIbeBKDiueFzTUvOK9x0-T7rI"


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

                fetchBookInfo(isbn)

                binding.btnAddDb.setOnClickListener() {
                    addDB(isbn)
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

    fun addDB(isbnCode: String) {
        Toast.makeText(this, isbnCode, Toast.LENGTH_SHORT).show()
    }

    private fun fetchBookInfo(isbn: String) {
        val query = "isbn:$isbn"
        RetrofitClient.instance.getBookByISBN(query, apiKey).enqueue(object :
            Callback<BookResponse> {
            override fun onResponse(call: Call<BookResponse>, response: Response<BookResponse>) {

                Log.d("bookinfo", response.body().toString())

                if (response.isSuccessful && response.body()?.items?.isNotEmpty() == true) {
                    val book = response.body()?.items?.get(0)?.volumeInfo
                    binding.bookTitleTextView.text = "Title: ${book?.title}"
                    binding.bookAuthorTextView.text =
                        "Authors: ${book?.authors?.joinToString(", ")}"
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
}
