package com.camacho.data.repository

import com.camacho.data.mapper.toDomain
import com.camacho.db.TodoDatabase
import com.camacho.domain.model.User
import com.camacho.domain.repository.UserRepository

// TODO (Fase 1/2): Implementación con SQLDelight (inyectar TodoDatabase).
class UserRepositoryImpl(private val dataBase: TodoDatabase) : UserRepository {

    override fun findByUsername(username: String): User? =
        dataBase.userQueries.selectUserByUsername(username).executeAsOneOrNull()?.toDomain()

    override fun findById(id: Long): User? = dataBase.userQueries.selectUserById(id).executeAsOneOrNull()?.toDomain()

    override fun create(username: String, passwordHash: String): User {
        dataBase.userQueries.insertUser(username, passwordHash,
            System.currentTimeMillis())
        return findByUsername(username) ?: error("No se ha encontado el usuario en la base de datos")
    }
}
