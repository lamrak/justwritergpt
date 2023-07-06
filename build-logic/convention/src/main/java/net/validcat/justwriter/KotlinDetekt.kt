package net.validcat.justwriter

import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.api.Project

internal fun Project.configureDetekt(extension: DetektExtension) {
    extension.apply {
        config = files("$rootDir/config/detekt/detekt.yml")
        parallel = true
        buildUponDefaultConfig = true
        autoCorrect = true
    }
}
