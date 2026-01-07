package com.dvalfonso.models.dtos.Auth

import kotlinx.serialization.Serializable

@Serializable
class LoginUserDto(
    val username: String,
    val password: String
)