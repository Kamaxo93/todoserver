package com.camacho.api.routes

import com.camacho.domain.repository.TaskRepository
import io.ktor.server.routing.Route

// TODO (Fase 2): Rutas CRUD de tareas, protegidas con authenticate("auth-bearer").
fun Route.taskRoutes(taskRepository: TaskRepository) {
    // TODO: GET /api/tasks        -> 200 lista de TaskResponse del usuario
    // TODO: POST /api/tasks       -> 201 TaskResponse
    // TODO: PUT /api/tasks/{id}   -> 200 TaskResponse (404 si no existe/no es suya)
    // TODO: DELETE /api/tasks/{id}-> 204 (404 si no existe/no es suya)
}
