package com.soopeach.thethethe_android.couple

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.soopeach.thethethe_android.MainActivity
import com.soopeach.thethethe_android.R
import com.soopeach.thethethe_android.data.local.AccountDataStore
import com.soopeach.thethethe_android.data.network.NetworkModule
import com.soopeach.thethethe_android.databinding.ActivityConnectBinding
import com.soopeach.thethethe_android.model.couple.CoupleRequest
import com.soopeach.thethethe_android.utils.toToken
import kotlinx.coroutines.launch

class ConnectActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityConnectBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.checkbutton2.setOnClickListener {
            binding.idCheck.text

            lifecycleScope.launch {
                val token =
                    AccountDataStore(context = this@ConnectActivity).getAccessToken()
                val isSuccess = NetworkModule.joinCouple(
                    token!!.toToken(),
                    binding.idCheck.text
                )

                if (isSuccess) {
                    val intent = Intent(this@ConnectActivity, MainActivity::class.java)
                    startActivity(intent)
                }
            }

        }
    }
}