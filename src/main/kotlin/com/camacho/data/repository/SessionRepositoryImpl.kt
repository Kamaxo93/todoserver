package com.camacho.data.repository

import com.camacho.db.TodoDatabase
import com.camacho.domain.repository.SessionRepository
import java.util.UUID

// TODO (Fase 2): Implementación con SQLDelight (tabla sessions, token = UUID).
class SessionRepositoryImpl(private val database: TodoDatabase) : SessionRepository {

    override fun create(userId: Long): String {
        val token = UUID.randomUUID().toString()
        database.sessionQueries.insertSession(token, userId, System.currentTimeMillis())
        return token
    }

    override fun findUserIdByToken(token: String): Long? =
        database.sessionQueries.selectSessionByToken(token).executeAsOneOrNull()?.user_id

    override fun delete(token: String) {
        database.sessionQueries.deleteSessionByToken(token)
    }
}
