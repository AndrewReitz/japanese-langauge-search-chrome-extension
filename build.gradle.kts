allprojects {
    group = "jplangsearch"
    version = "0.1"

    repositories {
        mavenCentral()
        jcenter()
        maven("https://jitpack.io")
    }
}

plugins {
    distribution
    kotlin("js") version libs.versions.kotlin.get() apply false
}

val content: Configuration by configurations.creating

dependencies {
    content(projects.background) {
        targetConfiguration = "content"
    }
    content(projects.jisho) {
        targetConfiguration = "content"
    }
}

distributions {
    main {
        contents {
            from("manifest.json") {
                expand(
                        "name" to "Japanese Search",
                        "version" to "${project.version}",
                        "description" to "Quickly find information about Japanese words."
                )
            }

            from(content)

            from(file("resources"))

            into("/")
        }
    }
}

