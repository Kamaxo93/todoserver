package com.camacho.domain.usecase

import com.camacho.domain.model.Task
import com.camacho.domain.repository.TaskRepository

class GetTasks(private val taskRepository: TaskRepository) {
    suspend operator fun invoke(userId: Long): List<Task> =
        taskRepository.listForUser(userId)
}
