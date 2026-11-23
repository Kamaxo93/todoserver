package com.camacho.config

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.camacho.db.TodoDatabase
import java.util.Properties

object DatabaseFactory {

    // Database file path. Configurable via the DB_PATH environment variable so
    // it can point to a mounted volume inside the Docker container.
    private val dbFile: String = System.getenv("DB_PATH") ?: "todo.db"

    fun createDriver(): SqlDriver =
        JdbcSqliteDriver(
            "jdbc:sqlite:$dbFile",
            Properties().apply { setProperty("foreign_keys", "true") },
            TodoDatabase.Schema
        )

    fun createDatabase(driver: SqlDriver): TodoDatabase =
        TodoDatabase(driver)
}
