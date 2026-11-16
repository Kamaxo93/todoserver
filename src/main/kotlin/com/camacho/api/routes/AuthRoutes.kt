package com.camacho.api.routes

import com.camacho.api.dto.AuthResponse
import com.camacho.api.dto.ErrorResponse
import com.camacho.api.dto.LoginRequest
import com.camacho.api.dto.RegisterRequest
import com.camacho.api.mapper.toUserResponse
import com.camacho.domain.usecase.LoginUser
import com.camacho.domain.usecase.RegisterUser
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.post
import org.koin.ktor.ext.inject

fun Route.authRoutes() {
    val registerUser by inject<RegisterUser>()
    val loginUser by inject<LoginUser>()

    post("/api/auth/register") {
        val request = call.receive<RegisterRequest>()
        try {
            val result = registerUser(request.username, request.password)
            call.respond(HttpStatusCode.Created, AuthResponse(result.token, result.user.toUserResponse()))
        } catch (e: IllegalStateException) {
            call.respond(HttpStatusCode.Conflict, ErrorResponse("Username already exists"))
        }
    }

    post("/api/auth/login") {
        val request = call.receive<LoginRequest>()
        try {
            val result = loginUser(request.username, request.password)
            call.respond(HttpStatusCode.OK, AuthResponse(result.token, result.user.toUserResponse()))
        } catch (e: IllegalStateException) {
            call.respond(HttpStatusCode.Unauthorized, ErrorResponse("Invalid credentials"))
        }
    }
}
