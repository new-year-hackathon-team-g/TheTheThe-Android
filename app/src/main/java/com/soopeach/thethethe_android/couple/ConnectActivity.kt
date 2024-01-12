package com.soopeach.thethethe_android.couple

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.soopeach.thethethe_android.MainActivity
import com.soopeach.thethethe_android.R
import com.soopeach.thethethe_android.databinding.ActivityConnectBinding

class ConnectActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityConnectBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.checkbutton2.setOnClickListener {
            binding.idCheck.text.toString()

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}