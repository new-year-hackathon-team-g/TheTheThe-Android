package com.soopeach.thethethe_android

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.soopeach.thethethe_android.databinding.FragmentPopBinding
import kotlinx.coroutines.delay
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
        viewLifecycleOwner.lifecycleScope.launch {
            while (true) {

                val current = binding.progressBar.progress
                val target = (0..100).shuffled().first()

                val cnt = (current - target).absoluteValue
                repeat(cnt) {
                    delay(30)
                    if (current < target) binding.progressBar.progress += 1
                    else binding.progressBar.progress -= 1
                }

                delay(1000)
            }
        }

    }

}