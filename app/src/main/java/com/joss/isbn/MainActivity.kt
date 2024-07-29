package com.joss.isbn

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.joss.isbn.databinding.ActivityMainBinding
import com.journeyapps.barcodescanner.BarcodeCallback
import com.journeyapps.barcodescanner.BarcodeResult

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

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

        binding.scanButton.setOnClickListener {
            binding.barcodeView.resume()
        }

        binding.barcodeView.decodeContinuous(object : BarcodeCallback {
            override fun barcodeResult(result: BarcodeResult) {
                binding.barcodeView.pause()
                binding.isbnTextView.text = "Scanned ISBN: ${result.text}"
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
}
