package com.camacho.api.mapper

import com.camacho.api.dto.UserResponse
import com.camacho.domain.model.User

fun User.toUserResponse(): UserResponse = UserResponse(id, username)
