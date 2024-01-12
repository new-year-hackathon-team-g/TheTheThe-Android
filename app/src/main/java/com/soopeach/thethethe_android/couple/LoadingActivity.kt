package com.soopeach.thethethe_android.couple

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.soopeach.thethethe_android.MainActivity
import com.soopeach.thethethe_android.data.local.AccountDataStore
import com.soopeach.thethethe_android.data.network.NetworkModule
import com.soopeach.thethethe_android.databinding.ActivityLoadingBinding
import com.soopeach.thethethe_android.model.couple.CoupleRequest
import com.soopeach.thethethe_android.utils.toToken
import kotlinx.coroutines.launch

class LoadingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityLoadingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.idText.text = intent.getStringExtra("secretId")

        binding.checkbutton.setOnClickListener {

            lifecycleScope.launch {
                val token =
                    AccountDataStore(context = this@LoadingActivity).getAccessToken()
                val coupleInfo = NetworkModule.getCoupleInfo(token!!.toToken())

                if (coupleInfo.status != "ACCEPTED") {
                    Toast.makeText(this@LoadingActivity, "아직 상대방이 수락하지 않았습니다.", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    var intent = Intent(this@LoadingActivity, MainActivity::class.java)
                    startActivity(intent)
                }
            }

        }
    }
}