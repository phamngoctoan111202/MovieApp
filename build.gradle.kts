buildscript {
    dependencies {
        classpath("com.google.gms:google-services:4.4.1")
    }
}
plugins {
    id("local.detekt")
    id("local.spotless")
}

