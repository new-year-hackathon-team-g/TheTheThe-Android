package com.soopeach.thethethe_android.signup

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.lifecycleScope
import com.soopeach.thethethe_android.couple.RequestActivity
import com.soopeach.thethethe_android.data.local.AccountDataStore
import com.soopeach.thethethe_android.data.network.NetworkModule
import com.soopeach.thethethe_android.databinding.FragmentSignUpBinding
import com.soopeach.thethethe_android.model.SignUpRequest
import com.soopeach.thethethe_android.model.login.LoginRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream

class SignUpFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null
    private val binding
        get() = requireNotNull(_binding)

    private var _imageUriState: MutableStateFlow<Uri?> = MutableStateFlow(null)
    private val imageUriState = _imageUriState.asStateFlow()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)

        val pickMedia =
            requireActivity().registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
                if (uri != null) {
                    viewLifecycleOwner.lifecycleScope.launch {
                        _imageUriState.emit(uri)
                    }
                }
            }

        binding.imageContainer.setOnClickListener {
            if (imageUriState.value == null) {
                pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            imageUriState.collect { uri ->
                if (uri != null) {
                    binding.selectImagesButton.setImageURI(uri)
                }
            }
        }

        binding.button.setOnClickListener {

            viewLifecycleOwner.lifecycleScope.launch {

                if (imageUriState.value == null) {
                    Toast.makeText(requireContext(), "프로필 사진을 선택해주세요.", Toast.LENGTH_SHORT).show()
                } else {

                    binding.progressBar.visibility = View.VISIBLE

                    var imageUrl = "https://source.unsplash.com/user/max_duz/300x300"
                    binding.selectImagesButton.apply {
                        isDrawingCacheEnabled = true
                        buildDrawingCache(true)
                        val bitmap = (this.drawable as BitmapDrawable).bitmap
                        val baos = ByteArrayOutputStream()
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
                        val data = baos.toByteArray()
                        imageUrl = NetworkModule.uploadImage(data)
                    }

                    val isSuccess = NetworkModule.postSignUp(
                        signUpRequest = SignUpRequest(
                            email = binding.emailEditText.text,
                            password = binding.passwordEditText.text,
                            nickname = binding.nicknameEditText.text,
                            profileImageUrl = imageUrl
                        )
                    )

                    if (isSuccess) {

                        val accessToken = NetworkModule.postLogin(
                            LoginRequest(
                                email = binding.emailEditText.text,
                                password = binding.passwordEditText.text
                            )
                        ).accessToken

                        AccountDataStore(requireContext()).updateAccessTokenKey(accessToken)

                        binding.progressBar.visibility = View.GONE

                        val intent = Intent(requireContext(), RequestActivity::class.java)
                        startActivity(intent)
                        requireActivity().finish()

                    } else {
                        Toast.makeText(requireContext(), "회원가입에 실패했습니다.", Toast.LENGTH_SHORT).show()
                    }
                }

            }
        }

    }

}