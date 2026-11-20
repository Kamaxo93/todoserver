package com.camacho.domain.usecase

import com.camacho.domain.model.Task
import com.camacho.domain.repository.TaskRepository

class UpdateTask(
    private val taskRepository: TaskRepository
) {
    suspend operator fun invoke(
        id: Long,
        userId: Long,
        title: String,
        description: String?,
        completed: Boolean
    ): Task? =
        taskRepository.update(
            id = id,
            userId = userId,
            title = title,
            description = description,
            completed = completed
        )

}
