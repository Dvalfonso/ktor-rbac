package com.dvalfonso.models.db

import com.dvalfonso.models.PermissionsTable
import com.dvalfonso.models.RolePermissionsTable
import com.dvalfonso.models.RolesTable
import com.dvalfonso.models.UserRolesTable
import com.dvalfonso.models.UsersTable
import com.dvalfonso.models.dao.RoleEntity
import io.ktor.server.application.Application
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.configureDatabases() {
    val database = Database.connect(
        url = "jdbc:sqlite:database.db"
    )

    transaction(database) {
        SchemaUtils.create(
            UsersTable,
            RolesTable,
            PermissionsTable,
            UserRolesTable,
            RolePermissionsTable
        )

        if (RoleEntity.find { RolesTable.name eq "USER" }.empty()) {
            RoleEntity.new {
                rolename = "USER"
            }
        }

        if (RoleEntity.find { RolesTable.name eq "ADMIN" }.empty()) {
            RoleEntity.new {
                rolename = "ADMIN"
            }
        }
    }
}