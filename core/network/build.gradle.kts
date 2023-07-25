plugins {
    id("justwriter.android.library")
    id("justwriter.kotlin.detekt")
    id("justwriter.android.hilt")
    id("kotlinx-serialization")
}

android {
    namespace = "net.validcat.justwriter.core.networking"
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:model"))

    implementation(project(":core:common"))
    implementation(project(":core:model"))
    implementation(libs.coil.kt)
    implementation(libs.coil.kt.svg)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.datetime)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.okhttp.logging)
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlin.serialization)
    implementation(libs.openai.client)
}