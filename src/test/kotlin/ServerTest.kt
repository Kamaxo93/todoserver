package com.camacho

import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.server.testing.testApplication
import org.koin.core.context.stopKoin
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class ServerTest {

    @BeforeTest
    fun setup() {
        stopKoin()
    }

    @Test
    fun `application module loads`() = testApplication {
        application { module() }
    }

    @Test
    fun `register and login flow`() = testApplication {
        application { module() }

        val username = "test_${System.currentTimeMillis()}"
        val password = "secret123"

        val registerResponse = client.post("/api/auth/register") {
            contentType(ContentType.Application.Json)
            setBody("""{"username":"$username","password":"$password"}""")
        }
        assertEquals(HttpStatusCode.Created, registerResponse.status)

        val loginResponse = client.post("/api/auth/login") {
            contentType(ContentType.Application.Json)
            setBody("""{"username":"$username","password":"$password"}""")
        }
        assertEquals(HttpStatusCode.OK, loginResponse.status)
    }
}
