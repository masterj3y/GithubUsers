@file:Suppress("UnstableApiUsage")

include(":feature:favorites")


include(":feature:profile")

pluginManagement {
    includeBuild("build-logic")
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven(url = "https://oss.sonatype.org/content/repositories/snapshots/")
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven(url = "https://oss.sonatype.org/content/repositories/snapshots/")
    }
}
rootProject.name = "GithubUsers"
include(":app")
include(":core:coroutines")
include(":core:designsystem")
include(":core:network")
include(":core:data")
include(":feature:search")
