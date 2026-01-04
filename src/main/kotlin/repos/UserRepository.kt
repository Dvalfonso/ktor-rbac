package com.dvalfonso.repos

import com.dvalfonso.models.RolesTable
import com.dvalfonso.models.UsersTable
import com.dvalfonso.models.dao.RoleEntity
import com.dvalfonso.models.dao.UserEntity
import org.jetbrains.exposed.sql.transactions.transaction

class UserRepository {
    /* ========================
      CREATE
   ========================= */

    fun create(
        username: String,
        passwordHash: String,
        email: String,
        roleNames: List<String>
    ): UserEntity = transaction {

        val roles = RoleEntity
            .find { RolesTable.name inList roleNames }

        UserEntity.new {
            this.username = username
            this.passwordHash = passwordHash
            this.email = email
            this.roles = roles
        }
    }

    /* ========================
       READ
    ========================= */

    fun findById(id: Long): UserEntity? = transaction {
        UserEntity.findById(id)
    }

    fun findByUsername(username: String): UserEntity? = transaction {
        UserEntity.find { UsersTable.username eq username }
            .singleOrNull()
    }

    fun findAll(): List<UserEntity> = transaction {
        UserEntity.all().toList()
    }

    /* ========================
       UPDATE
    ========================= */

    fun update(
        id: Long,
        username: String?,
        email: String?,
        roleNames: List<String>?
    ): UserEntity? = transaction {

        val user = UserEntity.findById(id) ?: return@transaction null

        username?.let { user.username = it }
        email?.let { user.email = it }

        roleNames?.let {
            val roles = RoleEntity
                .find { RolesTable.name inList it }
            user.roles = roles
        }

        user
    }

    /* ========================
       DELETE
    ========================= */

    fun delete(id: Long): Boolean = transaction {
        val user = UserEntity.findById(id) ?: return@transaction false
        user.delete()
        true
    }
}