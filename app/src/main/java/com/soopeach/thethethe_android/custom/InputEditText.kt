package com.soopeach.thethethe_android.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.soopeach.thethethe_android.R
import com.soopeach.thethethe_android.databinding.InputEditTextBinding

class InputEditText(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    private val binding = InputEditTextBinding.inflate(LayoutInflater.from(context), this, true)

    val text get() = binding.nicknameEditText.text.toString()
    val editText = binding.nicknameEditText

    init {

        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.InputEditText,
            0, 0
        ).apply {
            try {

                getString(R.styleable.InputEditText_inputEditTextHint)?.let {
                    binding.nicknameEditText.hint = it
                    binding.nicknameEditText.setHintTextColor(
                        ContextCompat.getColor(
                            context,
                            R.color.grey300
                        )
                    )
                }
            } finally {
                recycle()
            }
        }

        removeAllViews()
        addView(binding.root)

    }
}