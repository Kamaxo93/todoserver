package com.camacho.domain.usecase

import at.favre.lib.crypto.bcrypt.BCrypt
import com.camacho.domain.model.AuthResult
import com.camacho.domain.repository.SessionRepository
import com.camacho.domain.repository.UserRepository


class LoginUser(
    private val userRepository: UserRepository,
    private val sessionRepository: SessionRepository
) {
    suspend operator fun invoke(username: String, password: String): AuthResult {
        val user = userRepository.findByUsername(username) ?: error("Invalid credentials")
        if (!BCrypt.verifyer().verify(password.toCharArray(), user.passwordHash).verified) {
            error("Invalid credentials")
        }
        val token = sessionRepository.create(user.id)
        return AuthResult(token, user)
    }
}
