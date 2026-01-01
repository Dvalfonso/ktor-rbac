package com.dvalfonso.models.dtos

import kotlinx.serialization.Serializable

@Serializable
class AuthResponse(
    val token: String,
    val user: UserDto
)