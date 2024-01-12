package com.soopeach.thethethe_android.couple

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.soopeach.thethethe_android.R
import com.soopeach.thethethe_android.databinding.ActivityConnectBinding

class ConnectActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityConnectBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}