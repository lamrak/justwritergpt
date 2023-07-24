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
include(":core:model")
include(":core:data")
include(":core:database")
include(":core:common")
include(":core:datastore")
include(":feature:notes")
include(":feature:note")
include(":core:designsystem")
include(":core:networking")
