plugins {
  id("com.android.application")
  id("org.jetbrains.kotlin.android")
  id("kotlin-kapt")
}

android {
  namespace = "com.glasssutdio.wear"
  compileSdk = 34
  
  defaultConfig {
    applicationId = "com.glasssutdio.wear"
    minSdk = 24
    targetSdk = 34
    versionCode = 1
    versionName = "rev"
    multiDexEnabled = true
  }
  
  buildTypes {
    debug { }
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
  
  compileOptions { 
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11 
  }
  
  kotlinOptions { 
    jvmTarget = "11" 
  }
  
  packaging { 
    resources.excludes += setOf("META-INF/*","kotlin/**","**/*.version") 
  }
  
  buildFeatures {
    dataBinding = true
    viewBinding = true
  }
}

dependencies {
  // Android core dependencies
  implementation("androidx.appcompat:appcompat:1.7.0")
  implementation("androidx.core:core-ktx:1.13.1")
  implementation("com.google.android.material:material:1.12.0")
  implementation("androidx.constraintlayout:constraintlayout:2.1.4")
  implementation("androidx.recyclerview:recyclerview:1.3.2")
  implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
  implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")
  implementation("androidx.fragment:fragment-ktx:1.6.2")
  implementation("androidx.activity:activity-ktx:1.8.2")
  
  // Event bus
  implementation("org.greenrobot:eventbus:3.3.1")
  
  // Networking
  implementation("com.squareup.okhttp3:okhttp:4.12.0")
  implementation("com.squareup.retrofit2:retrofit:2.9.0")
  implementation("com.squareup.retrofit2:converter-gson:2.9.0")
  
  // JSON parsing
  implementation("com.squareup.moshi:moshi:1.15.0")
  implementation("com.squareup.moshi:moshi-kotlin:1.15.0")
  kapt("com.squareup.moshi:moshi-kotlin-codegen:1.15.0")
  
  // Logging
  implementation("com.elvishew:xlog:1.11.0")
  
  // Image loading and processing
  implementation("com.github.bumptech.glide:glide:4.16.0")
  kapt("com.github.bumptech.glide:compiler:4.16.0")
  
  // File download
  implementation("com.amitshekhar.android:android-networking:1.0.2")
  
  // Bluetooth and BLE (if needed)
  implementation("com.polidea.rxandroidble2:rxandroidble:1.18.0")
  
  // Coroutines
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
  
  // MultiDex
  implementation("androidx.multidex:multidex:2.0.1")
}
