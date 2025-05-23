//plugins {
////    id("vkid.android.library.compose")
////    id("vkid.android.publish")
////    id("vkid.dokka")
////    id("org.jetbrains.kotlin.plugin.parcelize")
////    id("org.jetbrains.kotlinx.binary-compatibility-validator")
//    id("com.android.library")
//    id("kotlin-android")
//    id("kotlin-kapt")
//}
//
//android {
//    namespace = "com.mozhimen.basick"
//    compileSdk = 34
//
//    defaultConfig {
//        minSdk = 19
//        targetSdk = 33
//        multiDexEnabled true
//
//        //fileProvider
//        manifestPlaceholders = [fileProviderAuthoritiesSuffix: ".fileProvider"]
//        buildConfigField("String", "FILE_PROVIDER_AUTHORITIES_SUFFIX", "\"${manifestPlaceholders['fileProviderAuthoritiesSuffix']}\"")
//
//        //test
//        testOptions {
//            unitTests.returnDefaultValues = true
//        }
//        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
//        consumerProguardFiles("consumer-rules.pro")
//    }
//
//    lintOptions {
//        checkReleaseBuilds = false
//        abortOnError = false
//    }
//
//    buildTypes {
//        release {
//            isMinifyEnabled = false
//            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
//        }
//    }
//    compileOptions {
//        sourceCompatibility = JavaVersion.VERSION_1_8
//        targetCompatibility = JavaVersion.VERSION_1_8
//    }
//    kotlinOptions {
//        jvmTarget = '1.8'
//    }
//    lintOptions {
//        checkReleaseBuilds = false
//        abortOnError = false
//    }
//}
//
//dependencies {
//    //////////////////////////////////////////////////////////////////
//    //google
//    api("org.jetbrains.kotlin:kotlin-stdlib:1.8.0")
//    api("androidx.appcompat:appcompat:1.6.1")
//    api("androidx.core:core-ktx:1.7.0")//1.10.1
//    api("com.google.android.material:material:1.9.0")//1.9.0
//    api("androidx.activity:activity:1.7.0")
//    api("androidx.fragment:fragment-ktx:1.6.2")
//    api("androidx.activity:activity-ktx:1.7.2")
//    api("androidx.documentfile:documentfile:1.0.1")
//    api("androidx.exifinterface:exifinterface:1.3.6")
//    api("androidx.annotation:annotation:1.7.1")
//
//    //////////////////////////////////////////////////////////////////
//    //multidex
//    api("androidx.multidex:multidex:2.0.1")
//
//    //////////////////////////////////////////////////////////////////
//    //thread
//    //coroutine
//    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
//    api("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.0")
//
//    //concurrent
//    api("androidx.concurrent:concurrent-futures:1.1.0")
//    api("androidx.concurrent:concurrent-futures-ktx:1.1.0")
//
//    //rxjava
//    api("io.reactivex.rxjava2:rxjava:2.2.14")
//    api("io.reactivex.rxjava2:rxandroid:2.1.1")
//
//    //work manager
////    api 'androidx.work:work-runtime:2.8.1'
//
//    //////////////////////////////////////////////////////////////////
//    //databinding
//    api("androidx.databinding:databinding-common:7.1.2")
//    api("androidx.databinding:databinding-runtime:7.1.2")
//
//    //////////////////////////////////////////////////////////////////
//    //lifecycle
//    api("androidx.lifecycle:lifecycle-livedata-ktx:2.5.0-alpha03")
//    api("androidx.lifecycle:lifecycle-livedata:2.6.1")
//    api("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.0-alpha03")
//    api("androidx.lifecycle:lifecycle-viewmodel-savedstate:2.4.0")
//    api("androidx.lifecycle:lifecycle-extensions:2.2.0")
//    api("androidx.lifecycle:lifecycle-runtime-ktx:2.4.0")
//
//    //////////////////////////////////////////////////////////////////
//    //room cachek
//    api("androidx.room:room-runtime:2.6.1")
//    kapt("androidx.room:room-compiler:2.6.1")
//
//    //datastore
//    api("androidx.datastore:datastore-preferences:1.0.0")
//    api("androidx.datastore:datastore:1.0.0")
//
//    //////////////////////////////////////////////////////////////////
//    //serialize
//    //moshi
//    api("com.squareup.moshi:moshi:1.13.0")
//    api("com.squareup.moshi:moshi-kotlin:1.13.0")
//    kapt("com.squareup.moshi:moshi-kotlin-codegen:1.8.0")
//
//    //gson
//    implementation("com.google.code.gson:gson:2.10.1")
//
//    //kotlin
//    //implementation 'org. jetbrains.kotlinx: kotlinx-serialization-json:1.3.2'
//
//    //////////////////////////////////////////////////////////////////
//    //firebase
////    api 'com.google.firebase:firebase-crashlytics-buildtools:2.9.2'
//
//    //////////////////////////////////////////////////////////////////
//    //test
//    testImplementation("junit:junit:4.13.2")
//    androidTestImplementation("androidx.test.ext:junit:1.1.5")
//    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
//}