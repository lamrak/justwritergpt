plugins {
    id("justwriter.android.library")
    id("justwriter.android.library.compose")
    id("justwriter.kotlin.detekt")
}

android {
    namespace = "com.ch13mob.materialyounotes.core.designsystem"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    lint {
        checkDependencies = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.coil.kt.compose)
}