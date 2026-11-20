package com.camacho.domain.usecase

import com.camacho.domain.repository.TaskRepository

class DeleteTask(
    private val taskRepository: TaskRepository
) {
    suspend operator fun invoke(id: Long, userId: Long): Boolean =
        taskRepository.delete(id, userId)
}
