package com.soopeach.thethethe_android.custom

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatImageView
import com.soopeach.thethethe_android.R

class PopButton(context: Context, attrs: AttributeSet) : AppCompatImageView(context, attrs){

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_UP -> {
                setImageResource(R.drawable.normal)
                performClick()
            }

            MotionEvent.ACTION_DOWN -> {
                setImageResource(R.drawable.pressed)
            }
        }
        return true
    }


}