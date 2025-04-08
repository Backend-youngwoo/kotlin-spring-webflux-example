package com.example.market.member.dto

import com.example.market.common.status.Gender

data class MemberRequest(
    val id: String?,
    val loginId: String,
    val password: String,
    val name: String,
    val birthDate: String,
    val gender: Gender,
    val email: String,
)