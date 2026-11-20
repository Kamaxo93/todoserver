package com.camacho.config

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.camacho.db.TodoDatabase
import java.util.Properties


// TODO (Fase 1): Configuración de la base de datos SQLite vía SQLDelight (driver JDBC).
// - Crear un JdbcSqliteDriver("jdbc:sqlite:todo.db")
// - Ejecutar TodoDatabase.Schema.create(driver)
// - Devolver un TodoDatabase(driver)
// La clase TodoDatabase la generará SQLDelight cuando existan los archivos .sq.
object DatabaseFactory {

    private const val DB_FILE = "todo.db"

    fun createDriver(): SqlDriver =
        JdbcSqliteDriver("jdbc:sqlite:$DB_FILE", Properties().apply { setProperty("foreign_keys", "true") }, TodoDatabase.Schema)


    fun createDatabase(driver: SqlDriver): TodoDatabase =
        TodoDatabase(driver)
}
