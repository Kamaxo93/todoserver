package com.camacho.data.repository

import com.camacho.domain.repository.SessionRepository

// TODO (Fase 2): Implementación con SQLDelight (tabla sessions, token = UUID).
class SessionRepositoryImpl : SessionRepository {
    override fun create(userId: Long): String = TODO("insertSession con UUID.randomUUID()")
    override fun findUserIdByToken(token: String): Long? = TODO("selectSessionByToken")
    override fun delete(token: String) = TODO("deleteSessionByToken")
}
