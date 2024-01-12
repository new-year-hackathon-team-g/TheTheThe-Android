package com.soopeach.thethethe_android.model.couple

data class CoupleResponse(
    val coupleImageUrl: String,
    val coupleName: String,
    val coupleTotalScore: Int,
    val id: Int,
    val introduction: String,
    val startDate: String,
    val status: String,
    val users: List<User>
)