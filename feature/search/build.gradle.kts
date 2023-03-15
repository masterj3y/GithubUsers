plugins {
    id("android.library")
    id("android.library.compose")
    id("android.feature")
    id("android.hilt")
}

android {

    namespace = "github.masterj3y.search"
}

dependencies {

    implementation(libs.glide)
}