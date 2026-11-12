package com.camacho.api.routes

import com.camacho.domain.repository.SessionRepository
import com.camacho.domain.repository.UserRepository
import io.ktor.server.routing.Route
import io.ktor.server.routing.post

// TODO (Fase 2): Rutas de autenticación.
fun Route.authRoutes(userRepository: UserRepository, sessionRepository: SessionRepository) {
    post("/api/auth/register") {
        // TODO: recibir RegisterRequest, validar username libre, crear usuario y sesión, responder 201 AuthResponse (o 409).
    }
    post("/api/auth/login") {
        // TODO: recibir LoginRequest, verificar credenciales, crear sesión, responder 200 AuthResponse (o 401).
    }
}
