package com.soopeach.thethethe_android.couple

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View.OnTouchListener
import android.view.inputmethod.InputMethodManager
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.soopeach.thethethe_android.data.local.AccountDataStore
import com.soopeach.thethethe_android.data.network.NetworkModule
import com.soopeach.thethethe_android.databinding.ActivityCoupleProfileBinding
import com.soopeach.thethethe_android.model.couple.CoupleRequest
import com.soopeach.thethethe_android.utils.toToken
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream

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


        val requestLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode === android.app.Activity.RESULT_OK) {
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

//        binding.coupleSave.setOnTouchListener(OnTouchListener { v, event ->
//            val inputManager =
//                this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//            inputManager.hideSoftInputFromWindow(
//                this.currentFocus!!.windowToken,
//                InputMethodManager.HIDE_NOT_ALWAYS
//            )
//            false
//        })
        binding.coupleSave.setOnClickListener {

            if (binding.coupleNameSetting.text.toString().isNotEmpty()) {
                if (binding.coupleIntroSetting.text.toString().isNotEmpty()) {
                    lifecycleScope.launch {

                        var imageUrl = "https://source.unsplash.com/user/max_duz/300x300"
                        binding.profileimage.apply {
                            isDrawingCacheEnabled = true
                            buildDrawingCache(true)
                            val bitmap = (this.drawable as BitmapDrawable).bitmap
                            val baos = ByteArrayOutputStream()
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
                            val data = baos.toByteArray()
                            imageUrl = NetworkModule.uploadImage(data)
                        }

                        val token =
                            AccountDataStore(context = this@CoupleProfileActivity).getAccessToken()
                        val secretId = NetworkModule.createCouple(
                            token!!.toToken(),
                            CoupleRequest(
                                binding.coupleNameSetting.text.toString(),
                                imageUrl,
                                binding.coupleIntroSetting.text.toString(),
                                "2020-05-05"
                            )
                        )
                        val intent = Intent(this@CoupleProfileActivity, LoadingActivity::class.java)
                        intent.putExtra("secretId", secretId)
                        startActivity(intent)
                        finish()
                    }

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