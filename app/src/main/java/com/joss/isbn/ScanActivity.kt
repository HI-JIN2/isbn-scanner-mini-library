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


//    companion object {
//        private const val callPermission = android.Manifest.permission.CALL_PHONE
//        private const val mediaPermission = android.Manifest.permission.READ_EXTERNAL_STORAGE
//        private const val imagePermission = android.Manifest.permission.READ_MEDIA_IMAGES
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityScanBinding.inflate(layoutInflater)
        setContentView(binding.root)

//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//            requestTiramisuPermission()
//        } else {
//            requestCallAndStoragePermission()
//        }

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
//
//    private fun checkPermissionsAndStartMotion(permissions: Array<String>, requestCode: Int) {
//        val permissionResults = permissions.map {
//            ContextCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED
//        }
//
//        if (permissionResults.all { it }) {
//            // TODO 권한이 허용 되었을 때의 액선
//        } else {
//            ActivityCompat.requestPermissions(this, permissions, requestCode)
//        }
//    }
//
//    // Android 13 미만일 때
//    private fun requestCallAndStoragePermission() {
//        checkPermissionsAndStartMotion(arrayOf(callPermission, mediaPermission), 100)
//    }
//
//    // Android 13 이상일 때
//    private fun requestTiramisuPermission() {
//        checkPermissionsAndStartMotion(arrayOf(callPermission, imagePermission), 200)
//    }
//
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        when (requestCode) {
//            100, 200 -> {
//                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    // TODO 권한이 허용 되었을 때의 액션
//                } else {
//                    finish()
//                }
//            }
//        }
//    }
}