package com.camacho.data.mapper

import com.camacho.db.Tasks
import com.camacho.db.Users
import com.camacho.domain.model.Task
import com.camacho.domain.model.User

fun Users.toDomain(): User =
    User(
        id = id,
        username = username,
        passwordHash = password_hash,
        createdAt = created_at
    )

fun Tasks.toDomain() = Task(
    id = id,
    userId = user_id,
    title = title,
    description = description,
    completed = completed == 1L,
    createdAt = created_at,
)
