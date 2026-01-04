package com.dvalfonso.service

import com.dvalfonso.models.UsersTable
import com.dvalfonso.models.dtos.RegisterUserDto
import com.dvalfonso.models.dtos.UserDto
import com.dvalfonso.models.utils.toDto
import com.dvalfonso.repos.UserRepository
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

class UserService(private val userRepository: UserRepository) {
    fun getAll(): List<UserDto> =
        userRepository.findAll().map { it.toDto() }

    fun getById(id: Long): UserDto =
        userRepository.findById(id)?.toDto()
            ?: throw NoSuchElementException("User not found")

    fun create(request: RegisterUserDto): UserDto =
        userRepository
            .create(request.username, request.password, request.email, roleNames = listOf("USER"))
            .toDto()

    fun update(
        id: Long,
        username: String?,
        email: String?,
        roles: List<String>?
    ): UserDto =
        userRepository
            .update(id, username, email, roles)
            ?.toDto()
            ?: throw NoSuchElementException("User not found")

    fun delete(id: Long) {
        if (!userRepository.delete(id)) {
            throw NoSuchElementException("User not found")
        }
    }
}