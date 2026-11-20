package com.camacho.domain.usecase

import at.favre.lib.crypto.bcrypt.BCrypt
import com.camacho.domain.model.AuthResult
import com.camacho.domain.repository.SessionRepository
import com.camacho.domain.repository.UserRepository

// 1. Comprobar que el username no exista.
// 2. Hashear la contraseña (BCrypt).
// 3. Persistir usuario vía UserRepository.
// 4. Crear sesión vía SessionRepository y devolver (token, user).
class RegisterUser(
    private val userRepository: UserRepository,
    private val sessionRepository: SessionRepository
) {
    suspend operator fun invoke(username: String, password: String): AuthResult {
        if (userRepository.findByUsername(username) != null) {
            error("Username already exists")
        }
        val passwordHash = BCrypt.withDefaults().hashToString(12, password.toCharArray())
        val user = userRepository.create(username, passwordHash)
        val token = sessionRepository.create(user.id)
        return AuthResult(token, user)
    }
}
