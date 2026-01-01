package com.dvalfonso.models.utils

import com.dvalfonso.models.dao.UserEntity

fun UserEntity.allPermisions() : Set<String> {
    return this.roles.flatMap {// flatMap une las listas (Recordar que un rol puede tener varios permisos)
        it.permissions.map {
            permissionEntity -> permissionEntity.name
        }
    }.toSet()
}