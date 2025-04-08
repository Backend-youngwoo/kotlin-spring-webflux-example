package com.example.market.entity

import com.example.market.common.status.Gender
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "members")
data class Member(
    @Id val id: String? = null,
    @Indexed(unique = true)
    val loginId: String,
    val password: String,
    val name: String,
    val birthDate: String,
    val gender: Gender,
    val email: String,
)