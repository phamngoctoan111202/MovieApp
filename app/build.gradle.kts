import com.android.build.api.dsl.ApplicationDefaultConfig
import java.util.Locale

plugins {
    id("local.app")
    id("com.google.gms.google-services")
}

android {
    val catalogs = extensions.getByType<VersionCatalogsExtension>()
    val libs = catalogs.named("libs")

    namespace = "com.noatnoat.movieapp"

    compileSdk = libs.findVersion("compileSdk").get().toString().toInt()

    defaultConfig {
        minSdk = libs.findVersion("minSdk").get().toString().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    defaultConfig {
        applicationId = "com.noatnoat.movieapp"

        versionCode = 1
        versionName = "0.0.1" // SemVer (Major.Minor.Patch)
        minSdk = 28
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        multiDexEnabled = true

        vectorDrawables {
            useSupportLibrary = true
        }

    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles("proguard-android.txt", "proguard-rules.pro")
        }
    }

    @Suppress("UnstableApiUsage")
    buildFeatures {
        viewBinding = true
        buildConfig = true
        compose = true
        dataBinding = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.findVersion("kotlinCompilerExtensionVersion").get().toString()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }

    @Suppress("UnstableApiUsage")
    testOptions {
        unitTests.isReturnDefaultValues = true
    }


}

dependencies {
    implementation("com.google.android.gms:play-services-auth:21.0.0")

    implementation(projects.featureBase)
    implementation(projects.featureHome)
    implementation(projects.featureProfile)
    implementation(projects.featureDownload)
    implementation(projects.featureFavourite)


    implementation(libs.google.material)
    implementation(libs.firebase.common.ktx)
    implementation("androidx.ui:ui-framework:0.1.0-dev03")
    implementation("com.google.code.gson:gson:2.8.8")

    api(libs.bundles.compose)
    implementation(libs.firebase.common.ktx)
    implementation("com.google.firebase:firebase-firestore:24.10.2")
    implementation(libs.firebase.auth.ktx)
    implementation("com.google.firebase:firebase-auth:22.3.1")

    implementation(libs.appCompat)


    implementation("androidx.activity:activity-compose:1.3.1")
    implementation("androidx.compose.foundation:foundation:1.0.5")
    implementation("androidx.compose.material:material:1.0.5")
    implementation("androidx.compose.ui:ui-tooling:1.0.5")
    implementation("androidx.compose.runtime:runtime-livedata:1.0.5")
    implementation("com.google.android.material:material:1.8.0")
    implementation(libs.androidx.databinding.runtime)

    implementation("androidx.media3:media3-exoplayer:1.2.1")
    implementation("androidx.media3:media3-exoplayer-dash:1.2.1")
    implementation("androidx.media3:media3-ui:1.2.1")
    implementation("androidx.media3:media3-exoplayer-hls:1.2.1")
}
