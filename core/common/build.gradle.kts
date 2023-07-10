@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.com.android.library)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    id("justwriter.kotlin.detekt")
    id("justwriter.android.hilt")
}

android {
    namespace = "net.validcat.justwriter.core.common"
}

dependencies {
    implementation(libs.kotlinx.coroutines.android)
}