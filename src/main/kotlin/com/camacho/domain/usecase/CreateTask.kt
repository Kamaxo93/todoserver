package com.camacho.domain.usecase

import com.camacho.domain.model.Task
import com.camacho.domain.repository.TaskRepository

class CreateTask(
    private val taskRepository: TaskRepository
) {
    suspend operator fun invoke(userId: Long, title: String, description: String?): Task =
        taskRepository.create(userId, title, description)
}
