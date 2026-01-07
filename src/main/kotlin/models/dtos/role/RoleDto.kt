package com.dvalfonso.models.dtos.role

import com.dvalfonso.models.dtos.permission.PermissionDto

data class RoleDto(
    val id: Long,
    val name: String,
    val permissions: List<PermissionDto>
)