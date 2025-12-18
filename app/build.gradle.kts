plugins {
    id("com.android.application")  // 确保应用插件已启用
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
    id("org.jlleitschuh.gradle.ktlint") version "10.2.0"
}

android {
    namespace = "com.example.androidlearn"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.androidlearn"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    // ViewBinding 是 Android 提供的一种新的视图绑定机制，它自动生成一个与布局文件相对应的绑定类，用于访问视图
    viewBinding {
        enable = true
    }

    // 支持数据和 UI 的双向绑定。它不仅自动生成视图绑定类，还允许您在布局文件中直接绑定数据和执行逻辑，如条件显示、动态更新 UI 元素等
    buildFeatures {
        dataBinding = true
        compose = true
        aidl = true
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

ktlint {
    android = true
    outputToConsole = true
}

dependencies {

    //集中管理项目中所有依赖的坐标和版本
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation("androidx.appcompat:appcompat:1.3.1")  // 或者使用最新版本
    implementation("androidx.constraintlayout:constraintlayout:2.1.0")
    implementation(libs.androidx.recyclerview)
    implementation(libs.androidx.media3.exoplayer)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // 只添加了 kotlinx-coroutines-android,也可以使用Lunch
    // kotlinx-coroutines-android 本身依赖 kotlinx-coroutines-core
    //Gradle 会把 core 库也加入到编译和运行时 classpath
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.coroutines.core)
}