enableFeaturePreview("VERSION_CATALOGS")
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(
    "background",
    "jisho"
)

rootProject.name = "japanese-language-search"

fun setBuildFileName(projects: Collection<ProjectDescriptor>) {
    for (project in projects) {
        project.apply { buildFileName = "$name.gradle.kts" }
        setBuildFileName(project.children)
    }
}

setBuildFileName(rootProject.children)

val localSettings = file("local.settings.gradle.kts")
if (localSettings.exists()) {
    apply(from = localSettings)
}
