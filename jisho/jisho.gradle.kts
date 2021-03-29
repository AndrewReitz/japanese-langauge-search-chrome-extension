plugins {
    kotlin("js")
}

kotlin {
    js(IR) {
        browser {
            binaries.executable()
        }
    }
}

dependencies {
    implementation(libs.bundles.extension)
    implementation(libs.html)
}

val content: Configuration by configurations.creating

artifacts {
    add("content", file("$buildDir/distributions/${name}.js")) {
        builtBy("browserProductionWebpack")
    }
}
