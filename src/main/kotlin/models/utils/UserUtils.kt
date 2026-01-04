package com.dvalfonso.models.utils

import com.dvalfonso.models.dao.UserEntity
import com.dvalfonso.models.dtos.UserDto

fun UserEntity.allPermisions() : Set<String> {
    return this.roles.flatMap {// flatMap une las listas (Recordar que un rol puede tener varios permisos)
        it.permissions.map {
            permissionEntity -> permissionEntity.name
        }
    }.toSet()
}

fun UserEntity.toDto(): UserDto =
    UserDto(
        id = this.id.value,
        username = this.username,
        email = this.email,
        roles = this.roles.map { it.rolename }
    )