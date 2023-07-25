plugins {
    id("justwriter.android.feature")
    id("justwriter.android.library.compose")
    id("justwriter.kotlin.detekt")
}

android {
    namespace = "net.validcat.justwriter.feature.notes"
}

dependencies {
    implementation(libs.accompanist.navigation.animation)
    implementation(libs.kotlinx.datetime)
    implementation(libs.openai.client)
}