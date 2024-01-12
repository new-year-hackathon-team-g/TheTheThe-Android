package com.soopeach.thethethe_android.couple

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.soopeach.thethethe_android.R
import com.soopeach.thethethe_android.databinding.ActivityCoupleProfileBinding
import com.soopeach.thethethe_android.databinding.ActivityRequestBinding

class CoupleProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityCoupleProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.coupleSave.setOnClickListener {
            val a = mapOf(
                "couplename" to binding.coupleNameSetting.text
            )
        }
    }
}