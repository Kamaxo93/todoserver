package com.camacho.api.plugins

import io.ktor.server.application.Application

// TODO (Fase 2): Configuración de autenticación con Ktor Authentication (proveedor bearer).
fun Application.configureSecurity() {
    // install(Authentication) {
    //     bearer("auth-bearer") {
    //         authenticate { credential ->
    //             val userId = sessionRepository.findUserIdByToken(credential.token)
    //             if (userId != null) UserIdPrincipal(userId.toString()) else null
    //         }
    //     }
    // }
}
