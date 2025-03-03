package com.kotleters.mobile.feature.client.domain.entity

data class TargetInfo(
    val age: Int,
    val gender: Gender
) {
    enum class Gender{
        MALE,
        FEMALE
    }
}
