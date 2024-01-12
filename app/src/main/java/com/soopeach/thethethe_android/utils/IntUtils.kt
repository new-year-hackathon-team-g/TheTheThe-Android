package com.soopeach.thethethe_android.utils

fun Int.toProcessedString(): String {
    return if (1000000 <= this) {
        "${this / 1000000}M"
    } else if (this in 1000..999999) {
        "${this / 1000}K"
    } else {
        this.toString()
    }
}