package com.camacho.api.dto

import kotlinx.serialization.Serializable

// DTOs de la API de autenticación (request/response HTTP).
@Serializable
data class RegisterRequest(val username: String, val password: String)

@Serializable
data class LoginRequest(val username: String, val password: String)

@Serializable
data class UserResponse(val id: Long, val username: String)

@Serializable
data class AuthResponse(val token: String, val user: UserResponse)

@Serializable
data class ErrorResponse(val error: String)
