package com.soopeach.thethethe_android.couple

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.soopeach.thethethe_android.R
import com.soopeach.thethethe_android.databinding.ActivityCoupleProfileBinding
import com.soopeach.thethethe_android.databinding.ActivityRequestBinding

class CoupleProfileActivity : AppCompatActivity() {
    lateinit var binding: ActivityCoupleProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoupleProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.changebutton.setOnClickListener {
           val imageResult = registerForActivityResult(
               ActivityResultContracts.StartActivityForResult()
           ){
               result ->
               if (result.resultCode == RESULT_OK){
               }
           }
        }

        binding.coupleSave.setOnClickListener {

            if (binding.coupleNameSetting.text.toString().isNotEmpty()){
                if (binding.coupleIntroSetting.text.toString().isNotEmpty()){
                    save()
                    val intent = Intent(this, LoadingActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }

    private fun save() {
        val coupleName = binding.coupleNameSetting.text.toString()
        val coupleIntro =  binding.coupleIntroSetting.text.toString()

        val data = hashMapOf(
            "coupleName" to coupleName,
            "introduction" to  coupleIntro
        )
    }
}