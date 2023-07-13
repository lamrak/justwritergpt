plugins {
    id("justwriter.android.library")
    id("justwriter.kotlin.detekt")
    id("justwriter.android.hilt")
}

android {
    namespace = "net.validcat.justwriter.core.common"
}

dependencies {
    implementation(libs.kotlinx.coroutines.android)
}