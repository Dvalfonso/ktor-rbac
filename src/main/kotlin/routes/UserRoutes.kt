package com.dvalfonso.routes

import com.dvalfonso.models.dao.UserEntity
import com.dvalfonso.models.dtos.Auth.RegisterUserDto
import com.dvalfonso.service.UserService
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route

fun Route.userRoutes(userService: UserService) {
    route("/users") {
        get("/{id}") {
            val id =
                call.parameters["id"]?.toLongOrNull() ?: return@get call.respond(HttpStatusCode.BadRequest, "Invalid id")

            val user = userService.getById(id)

            call.respond(HttpStatusCode.OK, user)
        }

        post {
            val request = call.receive<RegisterUserDto>()

            if (request.username.isBlank() || request.password.isBlank()) {
                return@post call.respond(
                    HttpStatusCode.BadRequest,
                    "Username and password are required"
                )
            }

            val createdUser = userService.create(request)

            call.respond(
                HttpStatusCode.Created,
                createdUser
            )
        }
    }
}