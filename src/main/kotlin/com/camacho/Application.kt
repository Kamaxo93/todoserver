package com.camacho

import com.camacho.api.plugins.configureSecurity
import com.camacho.api.plugins.configureSerialization
import com.camacho.api.plugins.configureStatusPages
import com.camacho.di.appModule
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.routing.routing
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun main() {
    // TODO: Configuración programática. El puerto sale de la variable de entorno PORT (Docker-friendly).
    val port = System.getenv("PORT")?.toIntOrNull() ?: 8080
    embeddedServer(Netty, port = port, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    install(Koin) {
        slf4jLogger()
        modules(appModule)
    }

    configureSerialization()
    configureSecurity()
    configureStatusPages()

    routing {
        // TODO (Fase 2): inyectar repositorios/use cases vía Koin y montar las rutas
        // authRoutes(...)
        // taskRoutes(...)
    }
}
