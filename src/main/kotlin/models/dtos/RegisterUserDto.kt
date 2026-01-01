package com.dvalfonso.models.dtos

import kotlinx.serialization.Serializable

@Serializable
class RegisterUserDto(
    val username: String,
    val email: String,
    val password: String,
)