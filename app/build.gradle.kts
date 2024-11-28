plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-android")
    id("com.google.devtools.ksp")
    id("kotlin-parcelize")


}

android {
    namespace = "com.dicoding.finalsubmission1"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.dicoding.finalsubmission1"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures {
        viewBinding = true
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {

    implementation (libs.androidx.lifecycle.viewmodel.ktx)
    ksp (libs.androidx.room.compiler)
    implementation (libs.androidx.room.runtime.v251)
    annotationProcessor (libs.androidx.room.compiler)
    implementation (libs.androidx.room.runtime.v250)
    implementation (libs.androidx.room.runtime)
    implementation (libs.androidx.room.ktx)
    implementation (libs.androidx.preference.ktx)
    implementation (libs.kotlinx.coroutines.android.v152)
    implementation (libs.kotlin.stdlib)
    implementation (libs.androidx.work.runtime.ktx)
    implementation (libs.kotlinx.coroutines.core)
    implementation (libs.kotlinx.coroutines.android)
    implementation (libs.androidx.navigation.fragment.ktx.v253)
    implementation (libs.androidx.navigation.ui.ktx.v253)
    implementation (libs.androidx.navigation.fragment.ktx.v2xx)
    implementation (libs.androidx.navigation.ui.ktx.v2xx)
    implementation (libs.glide)
    implementation (libs.logging.interceptor)
    implementation (libs.retrofit)
    implementation (libs.converter.gson)
    implementation (libs.androidx.recyclerview)
    implementation (libs.material.v180)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}