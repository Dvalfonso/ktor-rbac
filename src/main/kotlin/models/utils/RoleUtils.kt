package com.dvalfonso.models.utils

import com.dvalfonso.models.dao.RoleEntity
import com.dvalfonso.models.dtos.role.RoleDto

fun RoleEntity.toDto() : RoleDto =
    RoleDto(id = id.value,
        name = rolename,
        permissions = permissions.map { it.toDto() }
    )