pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositories {
        google()
        // Added for testing local Konsist artifacts
        mavenLocal()
        mavenCentral()
    }
}
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")


rootProject.name = "MovieApp"
include(":app")
include(":feature_base")
include(":feature_home")
include(":feature_profile")
include(":feature_favourite")
include(":feature_download")
