plugins {
    id("justwriter.android.library")
    id("justwriter.kotlin.detekt")
    id("justwriter.android.hilt")
}

android {
    namespace = "net.validcat.justwriter.core.datastore"

}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:model"))

    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.androidx.dataStore.preferences)
}