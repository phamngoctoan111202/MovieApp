plugins {
    id("com.android.library")
    id("local.kotlin")
    id("local.test")
    id("androidx.navigation.safeargs.kotlin")
    id("com.google.devtools.ksp")
}

android {
    val catalogs = extensions.getByType<VersionCatalogsExtension>()
//    Dòng mã val catalogs = extensions.getByType<VersionCatalogsExtension>()
//    có tác dụng lấy ra đối tượng của extension VersionCatalogsExtension từ các extension có sẵn trong dự án Gradle.
//    Extension này thường được sử dụng để quản lý các phiên bản của thư viện và được đặt trong file libs.version.toml.
//
//Khi bạn có đối tượng catalogs, bạn có thể sử dụng nó để truy cập các thông tin chi tiết về các phiên bản,
// như là cách bạn có thể sử dụng catalogs.named("libs") để lấy ra extension với tên là "libs" (thường là tên của file version catalog).
    val libs = catalogs.named("libs")

    namespace = "com.noatnoat.movieapp"
    compileSdk = libs.findVersion("compileSdk").get().toString().toInt()

    defaultConfig {
        minSdk = libs.findVersion("minSdk").get().toString().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    @Suppress("UnstableApiUsage")
//    Sử dụng để tắt cảnh báo về API không ổn định khi sử dụng các tính năng mới của Gradle hoặc Android Gradle Plugin.
    buildFeatures {
        viewBinding = true
        buildConfig = true
        compose = true
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

    packaging {
        resources.excludes += setOf(
            "META-INF/AL2.0",
            "META-INF/licenses/**",
            "**/attach_hotspot_windows.dll",
            "META-INF/LGPL2.1",
        )
    }
}
