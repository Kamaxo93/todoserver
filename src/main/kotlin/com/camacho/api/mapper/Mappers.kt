package com.camacho.api.mapper

import com.camacho.api.dto.TaskResponse
import com.camacho.api.dto.UserResponse
import com.camacho.domain.model.Task
import com.camacho.domain.model.User

fun User.toUserResponse(): UserResponse = UserResponse(id, username)

fun Task.toTaskResponse(): TaskResponse = TaskResponse(id, title, description, completed, createdAt)
