package com.dvalfonso.models

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.Table

// object = singleton, LongIdTable = id automatico
object UsersTable : LongIdTable("users") {
    val username = varchar("username", 50).uniqueIndex()
    val email = varchar("email", 40)
    val passwordHash = varchar("password_hash", 255)
}

object RolesTable : LongIdTable("roles") {
    val name = varchar("name", 20).uniqueIndex()
}

object UserRolesTable : Table("user_roles") {
    val user = reference("user_id", UsersTable)
    val role = reference("role_id", RolesTable)
    override val primaryKey = PrimaryKey(user, role)
}

object PermissionsTable : LongIdTable("permissions") {
    val name = varchar("name", 50).uniqueIndex()
}

object RolePermissionsTable : Table("role_permissions") {
    val role = reference("role_id", RolesTable)
    val permission = reference("permission_id", PermissionsTable)
    override val primaryKey = PrimaryKey(role, permission)
}