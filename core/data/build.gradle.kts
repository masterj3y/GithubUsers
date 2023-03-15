plugins {
    id("android.library")
    id("android.hilt")
    id("kotlin-kapt")
}

android {

    namespace = "github.masterj3y.data"
}

dependencies {

    api(project(":core:network"))

    api(libs.androidx.room.ktx)
    kapt(libs.androidx.room.compiler)

    api(libs.androidx.paging.compose)
    api(libs.arrowkt.core)
}