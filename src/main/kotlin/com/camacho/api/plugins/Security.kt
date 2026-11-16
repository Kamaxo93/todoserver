package com.camacho.api.plugins

import com.camacho.domain.repository.SessionRepository
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.auth.Authentication
import io.ktor.server.auth.UserIdPrincipal
import io.ktor.server.auth.bearer
import org.koin.ktor.ext.inject

fun Application.configureSecurity() {
    val sessionRepository by inject<SessionRepository>()
    install(Authentication) {
        bearer("auth-bearer") {
            authenticate { credential ->
                val userId = sessionRepository.findUserIdByToken(credential.token)
                if (userId != null) UserIdPrincipal(userId.toString()) else null
            }
        }
    }
}
