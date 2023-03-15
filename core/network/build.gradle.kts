plugins {
    id("android.library")
    id("android.hilt")
}

android {

    namespace = "github.masterj3y.network"
}

dependencies {
    api(libs.okhttp.logging)
    api(libs.retrofit.core)
    api(libs.retrofit.gson)
    api(libs.sandwich)
    api(libs.timber)
}