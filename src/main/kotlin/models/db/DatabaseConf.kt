package com.dvalfonso.models.db

import com.dvalfonso.models.PermissionsTable
import com.dvalfonso.models.RolePermissionsTable
import com.dvalfonso.models.RolesTable
import com.dvalfonso.models.UserRolesTable
import com.dvalfonso.models.UsersTable
import com.dvalfonso.models.dao.PermissionEntity
import com.dvalfonso.models.dao.RoleEntity
import io.ktor.server.application.Application
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.SizedCollection
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

        // ---- roles ----
        val userRole = RoleEntity.find { RolesTable.name eq "USER" }.firstOrNull()
            ?: RoleEntity.new {
                rolename = "USER"
            }

        val adminRole = RoleEntity.find { RolesTable.name eq "ADMIN" }.firstOrNull()
            ?: RoleEntity.new {
                rolename = "ADMIN"
            }

        // ---- crear permiso si no existe ----
        fun perm(name: String): PermissionEntity =
            PermissionEntity.find { PermissionsTable.name eq name }.firstOrNull()
                ?: PermissionEntity.new {
                    this.name = name
                }

        // ---- permisos base ----
        val pReadSelf = perm("USER.READ_SELF")
        val pUpdateSelf = perm("USER.UPDATE_SELF")
        val pChangePassword = perm("USER.CHANGE_PASSWORD")

        val pReadAll = perm("USER.READ_ALL")
        val pRead = perm("USER.READ")
        val pCreate = perm("USER.CREATE")
        val pUpdate = perm("USER.UPDATE")
        val pDelete = perm("USER.DELETE")

        // ---- asignar permisos a USER ----
        userRole.permissions = SizedCollection(
            listOf(
                pReadSelf,
                pUpdateSelf,
                pChangePassword
            )
        )

        // ---- asignar permisos a ADMIN ----
        adminRole.permissions = SizedCollection(
            listOf(
                pReadSelf,
                pUpdateSelf,
                pChangePassword,
                pReadAll,
                pRead,
                pCreate,
                pUpdate,
                pDelete
            )
        )
    }
}