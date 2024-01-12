package com.soopeach.thethethe_android

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.soopeach.thethethe_android.data.local.AccountDataStore
import com.soopeach.thethethe_android.data.network.NetworkModule
import com.soopeach.thethethe_android.databinding.FragmentPopBinding
import com.soopeach.thethethe_android.model.pop.PopResponse
import com.soopeach.thethethe_android.utils.toToken
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

class PopFragment : Fragment() {

    private var _binding: FragmentPopBinding? = null
    private val binding
        get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPopBinding.inflate(inflater, container, false)
        return binding.root
    }

    private var _popState: MutableStateFlow<PopResponse?> = MutableStateFlow(null)
    private val popState = _popState.asStateFlow()

    private var _countState: MutableStateFlow<Int> = MutableStateFlow(0)
    private val countState = _countState.asStateFlow()

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.popButton.addOnTouchEvent { _, event ->
            when (event.action) {
                MotionEvent.ACTION_UP -> {
                    binding.heartEruptionLottie.speed = 1f
                }

                MotionEvent.ACTION_DOWN -> {
                    binding.heartEruptionLottie.speed = 5f
                }
            }
            false
        }

        lifecycleScope.launch {
            while (true) {
                val token = AccountDataStore(context = requireContext()).getAccessToken()
                val result = NetworkModule.postPop(token!!.toToken(), countState.value)
                _countState.emit(0)
                _popState.emit(result)
                delay(1000)
            }
        }

        binding.popButton.setOnClickListener {
            lifecycleScope.launch {
                _countState.emit(countState.value + 1)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {

            popState.collect { popResponse ->

                if (popResponse != null) {
                    val (my, love) = popResponse
                    val sum = my + love

                    val myPercent = my.toFloat() / sum.toFloat() * 100
                    val prePercent = binding.progressBar.progress

                    val cnt = (myPercent - prePercent).absoluteValue.toInt()
                    repeat(cnt) {
                        delay(10)
                        if (prePercent < myPercent) binding.progressBar.progress += 1
                        else binding.progressBar.progress -= 1
                    }

                }


            }
        }

    }

}