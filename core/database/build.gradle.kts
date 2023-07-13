plugins {
    id("justwriter.android.library")
    id("justwriter.kotlin.detekt")
    id("justwriter.android.hilt")
    id("justwriter.android.room")
}

android {
    namespace = "net.validcat.justwriter.core.database"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    implementation(project(":core:model"))

    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.datetime)
}