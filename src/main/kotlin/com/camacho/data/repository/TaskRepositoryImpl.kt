package com.camacho.data.repository

import com.camacho.data.mapper.toDomain
import com.camacho.data.mapper.toDomains
import com.camacho.db.TodoDatabase
import com.camacho.domain.model.Task
import com.camacho.domain.repository.TaskRepository

class TaskRepositoryImpl(private val database: TodoDatabase) : TaskRepository {
    override fun listForUser(userId: Long): List<Task> =
        database.taskQueries.selectTasksByUser(userId).executeAsList().toDomains()

    override fun get(id: Long, userId: Long): Task? =
        database.taskQueries.selectTaskById(id, userId).executeAsOneOrNull()?.toDomain()

    override fun create(userId: Long, title: String, description: String?): Task {
        val createdAt = System.currentTimeMillis()
        val id = database.taskQueries.insertTask(userId, title, description, 0L, createdAt).executeAsOne()
        return Task(id, userId, title, description, completed = false, createdAt = createdAt)
    }

    override fun update(id: Long, userId: Long, title: String, description: String?, completed: Boolean): Task? {
        database.taskQueries.updateTask(title, description, if (completed) 1L else 0L, id, userId)
        return get(id, userId)
    }

    override fun delete(id: Long, userId: Long): Boolean =
        database.taskQueries.deleteTask(id, userId).value > 0L
}
