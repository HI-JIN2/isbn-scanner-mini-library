package com.joss.isbn

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.integration.android.IntentIntegrator

import com.joss.isbn.databinding.ActivityScanBinding


class ScanActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityScanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.scanBtn.setOnClickListener {
            val integrator = IntentIntegrator(this)
            with(integrator) {
                setBeepEnabled(false)
                captureActivity = ResultActivity::class.java
                initiateScan()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            val scanRes = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
            val content = scanRes.contents

            Toast.makeText(this, content, Toast.LENGTH_SHORT).show()
        } else Toast.makeText(this, "인식 실패", Toast.LENGTH_SHORT).show()
    }
}