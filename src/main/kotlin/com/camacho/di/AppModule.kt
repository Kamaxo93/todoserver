package com.camacho.di

import org.koin.dsl.module

// TODO: Módulo(s) de Koin. Organiza por capa (data/domain) si crece.
val appModule = module {
    // TODO (Fase 1): single { DatabaseFactory.createDatabase(DatabaseFactory.createDriver()) }

    // TODO (Fase 2): bindings de repositorios (interfaz -> implementación)
    // single<UserRepository> { UserRepositoryImpl(get()) }
    // single<TaskRepository> { TaskRepositoryImpl(get()) }
    // single<SessionRepository> { SessionRepositoryImpl(get()) }

    // TODO (Fase 2): use cases como single { ... } si se resuelven por DI
}
