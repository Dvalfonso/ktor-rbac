package com.dvalfonso.service

import com.dvalfonso.models.UsersTable
import com.dvalfonso.models.dtos.Auth.RegisterUserDto
import com.dvalfonso.models.dtos.user.UserDto
import com.dvalfonso.models.utils.toDto
import com.dvalfonso.repos.UserRepository
import io.ktor.client.request.request
import io.ktor.http.cio.parseRequest
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

class UserService(private val userRepository: UserRepository) {
    fun getAll(): List<UserDto> =
        userRepository.findAll()

    fun getById(id: Long): UserDto =
        userRepository.findById(id) ?: throw NoSuchElementException("User not found")

    fun create(request: RegisterUserDto): UserDto {
        val roles = listOf("USER")

        val user = userRepository.create(
            username = request.username,
            email = request.email,
            passwordHash = request.password,
            roleNames = roles
        )

        return user
    }

    fun update(
        id: Long,
        username: String?,
        email: String?,
        roles: List<String>?
    ): UserDto =
        userRepository
            .update(id, username, email, roles)
            ?: throw NoSuchElementException("User not found")

    fun delete(id: Long) {
        if (!userRepository.delete(id)) {
            throw NoSuchElementException("User not found")
        }
    }
}