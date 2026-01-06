package com.dvalfonso.repos

import com.dvalfonso.models.RolesTable
import com.dvalfonso.models.UsersTable
import com.dvalfonso.models.dao.RoleEntity
import com.dvalfonso.models.dao.UserEntity
import com.dvalfonso.models.dtos.UserDto
import com.dvalfonso.models.utils.toDto
import org.jetbrains.exposed.sql.SizedCollection
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
    ): UserDto = transaction {

        val roles = RoleEntity
            .find { RolesTable.name inList roleNames }
            .toList()

        val user = UserEntity.new {
            this.username = username
            this.passwordHash = passwordHash
            this.email = email
        }

        user.roles = SizedCollection(roles)

        user.toDto()
    }


    /* ========================
       READ
    ========================= */

    fun findById(id: Long): UserDto? = transaction {
        val user = UserEntity.findById(id) ?: return@transaction null
        user.toDto()
    }

    fun findByUsername(username: String): UserEntity? = transaction {
        UserEntity.find { UsersTable.username eq username }
            .singleOrNull()//Cambiar
    }

    fun findAll(): List<UserDto> = transaction {
        UserEntity.all().map {it.toDto()}
    }

    /* ========================
       UPDATE
    ========================= */

    fun update(
        id: Long,
        username: String?,
        email: String?,
        roleNames: List<String>?
    ): UserDto? = transaction {

        val user = UserEntity.findById(id) ?: return@transaction null

        username?.let { user.username = it }
        email?.let { user.email = it }

        roleNames?.let {
            val roles = RoleEntity
                .find { RolesTable.name inList it }
                .toList()
            user.roles = SizedCollection(roles)
        }

        user.toDto()
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