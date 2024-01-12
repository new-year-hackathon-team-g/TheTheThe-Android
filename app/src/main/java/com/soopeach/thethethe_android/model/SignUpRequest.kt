package com.soopeach.thethethe_android.model

data class SignUpRequest(
    val email: String,
    val password: String,
    val nickname: String,
    val profileImageUrl: String,
)
