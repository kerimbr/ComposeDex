plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}


android {
    namespace = "com.kerimbr.compokedex"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.kerimbr.compokedex"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile(
                    name = "proguard-android-optimize.txt"
                ),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        compose = true
    }

//    composeOptions {
//        kotlinCompilerExtensionVersion = "1.4.4"
//    }

    packagingOptions {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
}

dependencies{

    val composeUIVersion: String = "1.4.1"
    val composeCompilerVersion: String = "1.4.5"
    val composeMaterial3Version: String = "1.1.0-beta02"
    val hiltVersion: String = "2.45"
    val navVersion: String = "2.5.3"
    val retrofitVersion: String = "2.9.0"
    val okhttpVersion: String = "4.9.0"
    val timberVersion: String = "5.0.1"
    val coroutinesVersion: String = "1.6.4"
    val lifecycleVersion: String = "2.6.1"
    val coilVersion: String = "2.3.0"
    val hiltComposeVersion: String = "1.0.0"

    // AndroidX
    implementation("androidx.core:core-ktx:1.10.0")
    implementation("androidx.activity:activity-compose:1.7.0")

    // Compose
    implementation("androidx.compose.compiler:compiler:$composeCompilerVersion")
    implementation("androidx.compose.ui:ui:$composeUIVersion")
    implementation("androidx.compose.ui:ui-tooling-preview:$composeUIVersion")
    implementation("androidx.compose.material3:material3:$composeMaterial3Version")

    // Test Dependencies
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:$composeUIVersion")
    debugImplementation("androidx.compose.ui:ui-tooling:$composeUIVersion")
    debugImplementation("androidx.compose.ui:ui-test-manifest:$composeUIVersion")

    // Material Icons
    implementation("androidx.compose.material:material-icons-core:$composeUIVersion")
    implementation("androidx.compose.material:material-icons-extended:$composeUIVersion")

    // Lifecycle Compose
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion")

    // Navigation Compose
    implementation("androidx.navigation:navigation-compose:$navVersion")

    // Retrofit & OkHttp
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-gson:$retrofitVersion")
    implementation("com.squareup.okhttp3:okhttp:$okhttpVersion")
    implementation("com.squareup.okhttp3:logging-interceptor:$okhttpVersion")

    // Timber
    implementation("com.jakewharton.timber:timber:$timberVersion")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion")

    // Coil
    implementation("io.coil-kt:coil-compose:$coilVersion")

    // Hilt
    implementation("com.google.dagger:hilt-android:$hiltVersion")
    kapt("com.google.dagger:hilt-compiler:$hiltVersion")

    // Hilt & Compose
    implementation("androidx.hilt:hilt-navigation-compose:$hiltComposeVersion")
    kapt("androidx.hilt:hilt-compiler:1.0.0")

    // Palette
    implementation("androidx.palette:palette:1.0.0")

}

kapt {
    correctErrorTypes = true
}
