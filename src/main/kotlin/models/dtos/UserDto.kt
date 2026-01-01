package com.dvalfonso.models.dtos

import kotlinx.serialization.Serializable

@Serializable
class UserDto(
    val id: Long,
    val username: String,
    val email: String,
    val roles: List<String>
)
