import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.serialization)
    alias(libs.plugins.devtools)
    alias(libs.plugins.ktorfitPlugin)
    id("kotlin-parcelize")
}



android {
    namespace = "com.example.coopletesttask"
    compileSdk = 34


    defaultConfig {
        val properties = Properties()
        properties.load(project.rootProject.file("local.properties").inputStream())
        buildConfigField("String", "API_KEY", properties.getProperty("API_KEY"))
        applicationId = "com.example.coopletesttask"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {

    implementation(libs.core)
    implementation(libs.appcompat)
    implementation(libs.material.v1110)
    implementation(libs.constraintlayout)
    implementation(libs.navFragment)
    implementation(libs.navUI)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidJunit)
    androidTestImplementation(libs.espresso)

    implementation(libs.koin)
    implementation(libs.viewbinding)

    implementation(libs.timber)

    implementation(libs.ktorGson)

    implementation(libs.bundles.di)
    implementation(libs.picasso)

    ksp(libs.ktorfitKsp)
    implementation(libs.bundles.ktor)

    implementation(libs.bundles.room)
    annotationProcessor(libs.roomAnnotations)
    ksp(libs.roomKsp)
}