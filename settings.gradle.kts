pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        //maven{ url;"https:/maven.google.com"}
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "CamiloPatino_SevastianSilva_COMP304Lab5Ex1"
include(":app")
 