package com.soopeach.thethethe_android.couple

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.soopeach.thethethe_android.MainActivity
import com.soopeach.thethethe_android.PopFragment
import com.soopeach.thethethe_android.R
import com.soopeach.thethethe_android.databinding.ActivityLoadingBinding

class LoadingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityLoadingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //binding.idText.setText()

        binding.checkbutton.setOnClickListener {
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}