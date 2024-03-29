plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.sqlDelight)
    alias(libs.plugins.kotlin.serialization)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            // SqlDelight Extensions
            implementation(libs.sqldelight.extensions)
            // Koin
            implementation(libs.koin.core)
            // Coroutines
            implementation(libs.kotlinx.coroutines.core)
            // Ktor
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.content.negotiation)
            implementation(libs.ktor.serializtion.json)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        androidMain.dependencies {
            // SQLDelight Android Driver
            implementation(libs.sqldelight.driver.android)
            // Ktor
            implementation(libs.ktor.client.okhttp)
        }
        iosMain.dependencies {
            // SQLDelight IOS Driver
            implementation(libs.sqldelight.driver.ios)
            // Ktor
            implementation(libs.ktor.client.darwin)
        }
    }
}

android {
    namespace = "ir.hossein.notekmm"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

sqldelight {
    databases {
        create("NoteDatabase") {
            packageName.set("ir.hossein.notekmm.database")
        }
    }
}