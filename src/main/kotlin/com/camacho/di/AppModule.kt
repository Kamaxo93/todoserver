package com.camacho.di

import com.camacho.config.DatabaseFactory
import com.camacho.data.repository.SessionRepositoryImpl
import com.camacho.data.repository.TaskRepositoryImpl
import com.camacho.data.repository.UserRepositoryImpl
import com.camacho.domain.repository.SessionRepository
import com.camacho.domain.repository.TaskRepository
import com.camacho.domain.repository.UserRepository
import com.camacho.domain.usecase.CreateTask
import com.camacho.domain.usecase.DeleteTask
import com.camacho.domain.usecase.GetTasks
import com.camacho.domain.usecase.LoginUser
import com.camacho.domain.usecase.RegisterUser
import com.camacho.domain.usecase.UpdateTask
import org.koin.dsl.module

val appModule = module {
    single { DatabaseFactory.createDatabase(DatabaseFactory.createDriver()) }
    single<UserRepository> { UserRepositoryImpl(get()) }
    single<TaskRepository> { TaskRepositoryImpl(get()) }
    single<SessionRepository> { SessionRepositoryImpl(get()) }

    // Use cases are stateless -> factory
    factory { RegisterUser(get(), get()) }
    factory { LoginUser(get(), get()) }
    factory { GetTasks(get()) }
    factory { CreateTask(get()) }
    factory { DeleteTask(get()) }
    factory { UpdateTask(get()) }

}
