package com.dvalfonso.models.dao

import com.dvalfonso.models.RolesTable
import com.dvalfonso.models.UserRolesTable
import com.dvalfonso.models.UsersTable
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class UserEntity (id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<UserEntity>(UsersTable)

    var username by UsersTable.username
    var passwordHash by UsersTable.passwordHash
    var email by UsersTable.email
    var roles by RoleEntity via UserRolesTable
}