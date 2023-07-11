pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "JustWriter"
include(":app")
//include(":build-logic")
include(":core:model")
include(":core:data")
include(":core:database")
include(":core:common")
include(":core:datastore")
