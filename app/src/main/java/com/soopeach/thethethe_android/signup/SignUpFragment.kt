package com.soopeach.thethethe_android.signup

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.soopeach.thethethe_android.couple.RequestActivity
import com.soopeach.thethethe_android.data.local.AccountDataStore
import com.soopeach.thethethe_android.data.network.NetworkModule
import com.soopeach.thethethe_android.databinding.FragmentSignUpBinding
import com.soopeach.thethethe_android.model.SignUpRequest
import com.soopeach.thethethe_android.model.login.LoginRequest
import kotlinx.coroutines.launch

class SignUpFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null
    private val binding
        get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                val isSuccess = NetworkModule.postSignUp(
                    signUpRequest = SignUpRequest(
                        email = binding.emailEditText.text,
                        password = binding.passwordEditText.text,
                        nickname = binding.nicknameEditText.text
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