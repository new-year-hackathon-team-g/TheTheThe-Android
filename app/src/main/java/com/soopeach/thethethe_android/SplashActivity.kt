package com.soopeach.thethethe_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.soopeach.thethethe_android.data.local.AccountDataStore
import com.soopeach.thethethe_android.signup.SignUpActivity
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        lifecycleScope.launch {

            val accessToken = AccountDataStore(context = this@SplashActivity).getAccessToken()

            if (accessToken.isNullOrEmpty()) {
                val intent = Intent(this@SplashActivity, SignUpActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                val intent = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}