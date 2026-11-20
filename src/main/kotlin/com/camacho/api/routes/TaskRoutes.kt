package com.camacho.api.routes

import com.camacho.api.dto.ErrorResponse
import com.camacho.api.dto.TaskRequest
import com.camacho.api.mapper.toTaskResponse
import com.camacho.domain.usecase.CreateTask
import com.camacho.domain.usecase.DeleteTask
import com.camacho.domain.usecase.GetTasks
import com.camacho.domain.usecase.UpdateTask
import io.ktor.http.HttpStatusCode
import io.ktor.server.auth.UserIdPrincipal
import io.ktor.server.auth.authenticate
import io.ktor.server.auth.principal
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.put
import org.koin.ktor.ext.inject

fun Route.taskRoutes() {
    val getTasks by inject<GetTasks>()
    val createTask by inject<CreateTask>()
    val updateTask by inject<UpdateTask>()
    val deleteTask by inject<DeleteTask>()

    authenticate("auth-bearer") {
        get("/api/tasks") {
            val userId = call.principal<UserIdPrincipal>()?.name?.toLongOrNull()
            if (userId == null) {
                call.respond(HttpStatusCode.Unauthorized, ErrorResponse("Unauthorized"))
                return@get
            }
            val tasks = getTasks(userId)
            call.respond(tasks.map { it.toTaskResponse() })
        }

        post("/api/tasks") {
            val userId = call.principal<UserIdPrincipal>()?.name?.toLongOrNull()
            if (userId == null) {
                call.respond(HttpStatusCode.Unauthorized, ErrorResponse("Unauthorized"))
                return@post
            }
            val request = try {
                call.receive<TaskRequest>()
            } catch (e: Exception) {
                return@post call.respond(HttpStatusCode.BadRequest, ErrorResponse("Invalid data format"))
            }
            if (request.title.isBlank()) {
                return@post call.respond(HttpStatusCode.BadRequest, ErrorResponse("Title is required"))
            }
            val newTask = createTask(userId, request.title, request.description).toTaskResponse()
            call.respond(HttpStatusCode.Created, newTask)
        }

        put("/api/tasks/{id}") {
            val userId = call.principal<UserIdPrincipal>()?.name?.toLongOrNull()
            if (userId == null) {
                call.respond(HttpStatusCode.Unauthorized, ErrorResponse("Unauthorized"))
                return@put
            }
            val id = call.parameters["id"]?.toLongOrNull()
            if (id == null) {
                call.respond(HttpStatusCode.BadRequest, ErrorResponse("Invalid id"))
                return@put
            }
            val request = try {
                call.receive<TaskRequest>()
            } catch (e: Exception) {
                return@put call.respond(HttpStatusCode.BadRequest, ErrorResponse("Invalid data format"))
            }
            val updated = updateTask(id, userId, request.title, request.description, request.completed)
            if (updated == null) {
                call.respond(HttpStatusCode.NotFound, ErrorResponse("Task not found"))
            } else {
                call.respond(updated.toTaskResponse())
            }
        }

        delete("/api/tasks/{id}") {
            val userId = call.principal<UserIdPrincipal>()?.name?.toLongOrNull()
            if (userId == null) {
                call.respond(HttpStatusCode.Unauthorized, ErrorResponse("Unauthorized"))
                return@delete
            }
            val id = call.parameters["id"]?.toLongOrNull()
            if (id == null) {
                call.respond(HttpStatusCode.BadRequest, ErrorResponse("Invalid id"))
                return@delete
            }
            val deleted = deleteTask(id, userId)
            if (!deleted) {
                call.respond(HttpStatusCode.NotFound, ErrorResponse("Task not found"))
            } else {
                call.respond(HttpStatusCode.NoContent)
            }
        }
    }
}
