package com.soopeach.thethethe_android.model.user

data class User(
    val uid: Long,
    val email: String,
    val password: String,
    val nickname: String,
    val score: Int,
    val cid: Long,
)
