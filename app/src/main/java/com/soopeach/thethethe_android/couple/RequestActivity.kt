package com.soopeach.thethethe_android.couple

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.soopeach.thethethe_android.R
import com.soopeach.thethethe_android.databinding.ActivityRequestBinding

class RequestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityRequestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.createCouple.setOnClickListener {
            val intent = Intent(this,CoupleProfileActivity::class.java)
            startActivity(intent)
        }

        binding.requestCouple.setOnClickListener {
            val intent = Intent(this, ConnectActivity::class.java)
            startActivity(intent)
        }
    }
}