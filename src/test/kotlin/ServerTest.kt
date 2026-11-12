package com.camacho

import io.ktor.server.testing.testApplication
import kotlin.test.Test

class ServerTest {

    @Test
    fun `application module loads`() = testApplication {
        // TODO (Fase 2): añadir tests de endpoints una vez implementados.
        module()
    }
}
