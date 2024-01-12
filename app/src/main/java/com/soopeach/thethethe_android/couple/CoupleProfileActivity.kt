package com.soopeach.thethethe_android.couple

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.view.View.OnTouchListener
import android.view.inputmethod.InputMethodManager
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.soopeach.thethethe_android.MainActivity
import com.soopeach.thethethe_android.R
import com.soopeach.thethethe_android.databinding.ActivityCoupleProfileBinding
import com.soopeach.thethethe_android.databinding.ActivityRequestBinding

class CoupleProfileActivity : AppCompatActivity() {
    lateinit var binding: ActivityCoupleProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoupleProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.playout.setOnTouchListener(OnTouchListener { v, event ->
            val inputManager =
                this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(
                this.currentFocus!!.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )
            false
        })


        val requestLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if(it.resultCode === android.app.Activity.RESULT_OK){
                Glide
                    .with(applicationContext)
                    .load(it.data?.data)
                    .apply(RequestOptions().override(250, 250))
                    .centerCrop()
                    .into(binding.profileimage)
            }
        }

        binding.changebutton.setOnClickListener {
          val intent = Intent(Intent.ACTION_PICK)
          intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*")
          requestLauncher.launch(intent)
        }

        binding.coupleSave.setOnTouchListener(OnTouchListener { v, event ->
            val inputManager =
                this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(
                this.currentFocus!!.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )
            false
        })
        binding.coupleSave.setOnClickListener {

            if (binding.coupleNameSetting.text.toString().isNotEmpty()){
                if (binding.coupleIntroSetting.text.toString().isNotEmpty()){
                    save()
                    //val intent = Intent(this, LoadingActivity::class.java)
                    //startActivity(intent)
                    //binding.idLayout.visibility= View.VISIBLE
                }
            }
        }
/*
        binding.checkbutton.setOnClickListener {
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }*/
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