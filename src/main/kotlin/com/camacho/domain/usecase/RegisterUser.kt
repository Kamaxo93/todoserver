package com.camacho.domain.usecase

// TODO (Fase 2): Caso de uso "Registrar usuario".
// 1. Comprobar que el username no exista.
// 2. Hashear la contraseña (BCrypt).
// 3. Persistir usuario vía UserRepository.
// 4. Crear sesión vía SessionRepository y devolver (token, user).
class RegisterUser {
    // TODO: inyectar UserRepository y SessionRepository por constructor.
    // TODO: suspend operator fun invoke(username: String, password: String): AuthResult
}
