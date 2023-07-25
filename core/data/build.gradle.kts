plugins {
    id("justwriter.android.library")
    id("justwriter.kotlin.detekt")
    id("justwriter.android.hilt")
}

android {
    namespace = "net.validcat.justwriter.core.data"


    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:model"))
    implementation(project(":core:database"))
    implementation(project(":core:datastore"))

    implementation(libs.androidx.core.ktx)

    implementation(libs.kotlinx.datetime)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.openai.client)
}