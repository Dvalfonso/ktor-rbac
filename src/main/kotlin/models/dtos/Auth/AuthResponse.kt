package com.dvalfonso.models.dtos.Auth

import com.dvalfonso.models.dtos.user.UserDto
import kotlinx.serialization.Serializable

@Serializable
class AuthResponse(
    val token: String,
    val user: UserDto
)