plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
    // Kotlin serialization plugin for type safe routes and navigation arguments
    kotlin("plugin.serialization") version "2.0.21"
}

android {
    namespace = "com.codenova.mindmate"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.codenova.mindmate"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        // enabling build config features
        android.buildFeatures.buildConfig = true
    }

    packagingOptions {
        resources {
            pickFirsts += "META-INF/versions/9/OSGI-INF/MANIFEST.MF"
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "BASE_URL", "http://localhost:8081")
        }

        getByName("debug") {
            applicationIdSuffix = ".debug"
            isDebuggable = true
            buildConfigField("String", "BASE_URL",  "\"http://192.168.1.137:8080\"")
        }

        create("staging") {
            initWith(getByName("debug"))
            applicationIdSuffix = ".debugStaging"
            buildConfigField("String", "BASE_URL", "http://localhost:8082")

        }
    }

    // specifies one flavor dimension
    flavorDimensions += "version"

    productFlavors {
        create("prod")  {
            dimension = "version"
            applicationIdSuffix = ".prod"
            //buildConfigField("String", "BASE_URL", "http://localhost:8081")
        }

        create("dev") {
            dimension = "version"
            applicationIdSuffix = ".dev"
            //buildConfigField("String", "BASE_URL", "http://localhost:8080")
        }

        create("qa") {
            dimension = "version"
            applicationIdSuffix = ".staging"
            //buildConfigField("String", "BASE_URL", "http://localhost:8082")
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
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation3.ui.android)
    implementation(libs.identity.jvm)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // dagger and hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)

    // hilt and navigation
    implementation(libs.androidx.hilt.navigation.compose)

    // navigation
    implementation(libs.androidx.navigation.compose)
    // testing navigation
    implementation(libs.androidx.navigation.testing)
    implementation(libs.kotlinx.serialization.json)

    // adaptive navigation suite
    implementation(libs.androidx.material3.adaptive.navigation.suite)

    // phosphor icons
    implementation(libs.phosphor.icon)

    // graphics-shapes
    implementation(libs.androidx.graphics.shapes)

    // retrofit
    implementation(libs.retrofit)

    // gson converter
    implementation(libs.converter.gson)

    // gson
    implementation(libs.gson)

    // room db
    implementation(libs.androidx.room.runtime)
    // if this project uses any kotlin sources, use Kotlin Symbol Processing (KSP)
    ksp(libs.androidx.room.compiler)
    // kotlin extensions and coroutines support for room
    implementation(libs.androidx.room.ktx)

    implementation(libs.androidx.compose.bom.v20250500)

    // preferences datastore
    implementation(libs.androidx.datastore.preferences)

}