plugins {
    id("local.library2")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.noatnoat.movieapp.download"
}

dependencies {
    implementation(projects.featureBase)
}
