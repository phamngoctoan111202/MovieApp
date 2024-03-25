plugins {
    id("local.library2")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.noatnoat.movieapp.profile"
}

dependencies {
    implementation(projects.featureBase)

    ksp(libs.roomCompiler)
    implementation(libs.appCompat)
    implementation("androidx.compose.ui:ui:1.6.1")
    implementation("androidx.compose.ui:ui-tooling:1.6.1")
    implementation("androidx.compose.runtime:runtime-livedata:1.6.1")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.4.0")
    ksp("com.google.devtools.ksp:symbol-processing-api:1.5.31-1.0.0")

//    implementation("com.google.accompanist:accompanist-coil:1.7")
//    implementation("io.coil-kt:coil:2.5.0")
    implementation("io.coil-kt:coil-compose:2.5.0")
    implementation("androidx.navigation:navigation-compose:2.5.0")
    implementation(libs.firebase.firestore.ktx)
    implementation("com.google.firebase:firebase-firestore:24.10.2")

    val nav_version = "2.4.0-rc01"
    implementation("androidx.navigation:navigation-fragment-ktx:$nav_version")
    implementation("androidx.navigation:navigation-ui-ktx:$nav_version")
}
