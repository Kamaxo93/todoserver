package com.camacho

import com.camacho.api.dto.AuthResponse
import com.camacho.api.dto.TaskResponse
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.server.testing.testApplication
import kotlinx.serialization.json.Json
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

    @Test
    fun `task CRUD flow`() = testApplication {
        application { module() }

        val username = "tasktest_${System.currentTimeMillis()}"
        val password = "secret123"

        // Register and get token
        val registerResponse = client.post("/api/auth/register") {
            contentType(ContentType.Application.Json)
            setBody("""{"username":"$username","password":"$password"}""")
        }
        assertEquals(HttpStatusCode.Created, registerResponse.status)
        val token = Json.decodeFromString<AuthResponse>(registerResponse.bodyAsText()).token

        // Create task
        val createResponse = client.post("/api/tasks") {
            contentType(ContentType.Application.Json)
            header(HttpHeaders.Authorization, "Bearer $token")
            setBody("""{"title":"Buy milk","description":"From the store"}""")
        }
        assertEquals(HttpStatusCode.Created, createResponse.status)
        val task = Json.decodeFromString<TaskResponse>(createResponse.bodyAsText())

        // List tasks
        val listResponse = client.get("/api/tasks") {
            header(HttpHeaders.Authorization, "Bearer $token")
        }
        assertEquals(HttpStatusCode.OK, listResponse.status)

        // Update task
        val updateResponse = client.put("/api/tasks/${task.id}") {
            contentType(ContentType.Application.Json)
            header(HttpHeaders.Authorization, "Bearer $token")
            setBody("""{"title":"Buy milk","description":"Done","completed":true}""")
        }
        assertEquals(HttpStatusCode.OK, updateResponse.status)

        // Delete task
        val deleteResponse = client.delete("/api/tasks/${task.id}") {
            header(HttpHeaders.Authorization, "Bearer $token")
        }
        assertEquals(HttpStatusCode.NoContent, deleteResponse.status)
    }
}
