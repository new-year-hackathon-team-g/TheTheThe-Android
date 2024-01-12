package com.soopeach.thethethe_android.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.soopeach.thethethe_android.MainActivity
import com.soopeach.thethethe_android.R
import com.soopeach.thethethe_android.data.local.AccountDataStore
import com.soopeach.thethethe_android.data.network.NetworkModule
import com.soopeach.thethethe_android.databinding.FragmentLoginBinding
import com.soopeach.thethethe_android.databinding.FragmentPopBinding
import com.soopeach.thethethe_android.databinding.FragmentRankBinding
import com.soopeach.thethethe_android.model.login.LoginRequest
import com.soopeach.thethethe_android.signup.SignUpActivity
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding
        get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signUp.setOnClickListener {
            val intent = Intent(requireContext(), SignUpActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }

        binding.button.setOnClickListener {

            lifecycleScope.launch {
                val token = NetworkModule.postLogin(
                    LoginRequest(
                        email = binding.emailEditText.text,
                        password = binding.passwordEditText.text

                    )
                ).accessToken

                AccountDataStore(requireContext()).updateAccessTokenKey(token)

                val intent = Intent(requireContext(), MainActivity::class.java)
                startActivity(intent)
                requireActivity().finish()
            }
        }
    }

}