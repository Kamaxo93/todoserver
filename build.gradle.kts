
plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(ktorLibs.plugins.ktor)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.sqldelight)
}

group = "com.camacho"
version = "1.0.0-SNAPSHOT"

application {
    mainClass = "com.camacho.ApplicationKt"
}

kotlin {
    jvmToolchain(21)
}

sqldelight {
    databases {
        create("TodoDatabase") {
            packageName.set("com.camacho.db")
        }
    }
}

dependencies {
    implementation(ktorLibs.serialization.kotlinx.json)
    implementation(ktorLibs.server.callLogging)
    implementation(ktorLibs.server.contentNegotiation)
    implementation(ktorLibs.server.core)
    implementation(ktorLibs.server.netty)
    implementation(ktorLibs.server.statusPages)
    implementation("io.ktor:ktor-server-auth:3.6.1")
    implementation(libs.logback.classic)
    implementation(libs.sqldelight.sqlite.driver)
    implementation(libs.sqlite.jdbc)
    implementation(libs.koin.ktor)
    implementation(libs.koin.logger.slf4j)
    implementation(libs.bcrypt)

    testImplementation(kotlin("test"))
    testImplementation(ktorLibs.server.testHost)
}
