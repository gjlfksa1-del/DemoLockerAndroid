plugins {
id("com.android.application")
id("org.jetbrains.kotlin.android")
}

android {
namespace = "com.cart3r.demolocker"
compileSdk = 35

ndkVersion = "27.0.12077973"

defaultConfig {
    applicationId = "com.cart3r.demolocker"
    minSdk = 23
    targetSdk = 35

    versionCode = 1
    versionName = "1.0"

    externalNativeBuild {
        cmake {
            cppFlags += "-std=c++17"
        }
    }
}

buildTypes {
    release {
        isMinifyEnabled = false
    }

    debug {
        isDebuggable = true
    }
}

compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlinOptions {
    jvmTarget = "17"
}

externalNativeBuild {
    cmake {
        path = file("src/main/cpp/CMakeLists.txt")
        version = "3.22.1"
    }
}

}
