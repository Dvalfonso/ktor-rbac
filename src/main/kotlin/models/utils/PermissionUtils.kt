package com.dvalfonso.models.utils

import com.dvalfonso.models.dao.PermissionEntity
import com.dvalfonso.models.dtos.permission.PermissionDto

fun PermissionEntity.toDto() : PermissionDto =
    PermissionDto(
        id = id.value,
        name = name
    )