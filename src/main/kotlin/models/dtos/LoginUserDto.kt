package com.dvalfonso.models.dtos

import kotlinx.serialization.Serializable

@Serializable
class LoginUserDto(
    val username: String,
    val password: String
)