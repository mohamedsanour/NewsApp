plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("androidx.navigation.safeargs")
    id("kotlin-kapt")
    id ("kotlin-parcelize")
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "com.example.newsapp"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.newsapp"
        minSdk = 28
        targetSdk = 36
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
    buildFeatures {
        viewBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.viewpager2)
    implementation(libs.firebase.auth)
    implementation(libs.androidx.credentials)
    implementation(libs.androidx.credentials.play.services.auth)
    implementation(libs.googleid)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //navigation
    implementation ("androidx.navigation:navigation-fragment-ktx:2.8.9")
    implementation ("androidx.navigation:navigation-ui-ktx:2.8.9")
    implementation ("com.airbnb.android:lottie:6.6.4")
    //viewModelScope
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.7")
    implementation("com.google.code.gson:gson:2.12.1")
    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.11.0")

    // View model
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.7")
    implementation("androidx.activity:activity-ktx:1.10.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:2.8.7")

    // Live data
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.7")

    // Logging interceptor
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    // Gson
    implementation("com.google.code.gson:gson:2.12.1")

    // Glide
    implementation("com.github.bumptech.glide:glide:4.16.0")

    // Coroutine
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.10.1")

    // RecyclerView
    implementation("androidx.recyclerview:recyclerview:1.4.0")

    // Card view
    implementation("androidx.cardview:cardview:1.0.0")

    // Navigation
    implementation("androidx.navigation:navigation-fragment-ktx:2.8.9")
    implementation("androidx.navigation:navigation-ui-ktx:2.8.9")
    implementation("androidx.navigation:navigation-dynamic-features-fragment:2.8.9")

    // Fragment
    implementation("androidx.fragment:fragment-ktx:1.8.6")

    // Lottie
    implementation("com.airbnb.android:lottie:6.6.4")

    // Preferences
    implementation("androidx.preference:preference-ktx:1.2.1")

    // Android splash API
    implementation("androidx.core:core-splashscreen:1.0.1")

    // GIF
    implementation("pl.droidsonroids.gif:android-gif-drawable:1.2.29")


    //Room DB
    implementation("androidx.room:room-runtime:2.7.0")
    implementation("androidx.room:room-ktx:2.7.0")
    annotationProcessor("androidx.room:room-compiler:2.7.0")
    testImplementation("androidx.room:room-testing:2.7.0")
    kapt("androidx.room:room-compiler:2.7.0")

    implementation ("com.google.android.gms:play-services-location:21.0.1")
    implementation("com.tbuonomo:dotsindicator:5.0")
    implementation("de.hdodenhof:circleimageview:3.1.0")
}