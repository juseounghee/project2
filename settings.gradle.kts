pluginManagement {
    repositories {
        maven { url = uri("https://plugins.gradle.org/m2/") }
        gradlePluginPortal()
        mavenCentral()
        maven("https://repo.spring.io/snapshot")
    }
}

rootProject.name = "project2"

include("restapi", "netty")
