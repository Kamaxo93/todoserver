package com.camacho.domain.repository

import com.camacho.domain.model.Task

// Contrato de acceso a datos de tareas.
interface TaskRepository {
    fun listForUser(userId: Long): List<Task>
    fun get(id: Long, userId: Long): Task?
    fun create(userId: Long, title: String, description: String?): Task
    fun update(id: Long, userId: Long, title: String, description: String?, completed: Boolean): Task?
    fun delete(id: Long, userId: Long): Boolean
}
