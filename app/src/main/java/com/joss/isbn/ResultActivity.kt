package com.joss.isbn

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.joss.isbn.databinding.ActivityResultBinding
import com.journeyapps.barcodescanner.CaptureManager


class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding

    private lateinit var capture: CaptureManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // CaptureManager에 context와 xml에서 적용한 레이아웃을 넣어줍니다.
        capture = CaptureManager(this, binding.decoratedBarCodeView)
        // intent와 savedInstanceState를 넣어줍니다.
        capture.initializeFromIntent(intent, savedInstanceState)
        capture.decode() //decode
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        capture.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}