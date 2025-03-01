package com.kotleters.mobile.feature.client.domain

data class TargetInfo(
    val age: Int,
    val gender: Gender
) {
    enum class Gender{
        MALE,
        FEMALE
    }
}
