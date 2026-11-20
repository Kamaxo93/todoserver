package com.camacho.data.repository

import com.camacho.db.TodoDatabase
import com.camacho.domain.model.Task
import com.camacho.domain.repository.TaskRepository

// TODO (Fase 2): Implementación con SQLDelight (inyectar TodoDatabase).
class TaskRepositoryImpl(private val database: TodoDatabase) : TaskRepository {
    override fun listForUser(userId: Long): List<Task> = TODO("selectTasksByUser")
    override fun get(id: Long, userId: Long): Task? = TODO("selectTaskById (por id Y user_id)")
    override fun create(userId: Long, title: String, description: String?): Task = TODO("insertTask")
    override fun update(id: Long, userId: Long, title: String, description: String?, completed: Boolean): Task? = TODO("updateTask")
    override fun delete(id: Long, userId: Long): Boolean = TODO("deleteTask")
}
