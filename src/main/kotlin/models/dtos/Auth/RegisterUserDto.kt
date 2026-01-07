package com.dvalfonso.models.dtos.Auth

import kotlinx.serialization.Serializable

@Serializable
class RegisterUserDto(
    val username: String,
    val email: String,
    val password: String,
)