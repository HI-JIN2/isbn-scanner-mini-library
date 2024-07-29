import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.joss.isbn"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.joss.isbn"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    }

    buildTypes {
        release {
            val p = Properties()
            p.load(project.rootProject.file("local.properties").reader())
            val apiKey: String = p.getProperty("api_key")
            buildConfigField("String", "api_key", "\"$apiKey\"")

            val sheetId: String = p.getProperty("sheet_id")
            buildConfigField("String", "sheet_id", "\"$sheetId\"")

        }

        debug {
            val p = Properties()
            p.load(project.rootProject.file("local.properties").reader())
            val apiKey: String = p.getProperty("api_key")
            buildConfigField("String", "api_key", "\"$apiKey\"")

            val sheetId: String = p.getProperty("sheet_id")
            buildConfigField("String", "sheet_id", "\"$sheetId\"")

        }
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
        viewBinding = true
        buildConfig = true
    }

    packagingOptions {
        exclude("META-INF/DEPENDENCIES")
        exclude("META-INF/LICENSE")
        exclude("META-INF/LICENSE.txt")
        exclude("META-INF/license.txt")
        exclude("META-INF/NOTICE")
        exclude("META-INF/NOTICE.txt")
        exclude("META-INF/notice.txt")
        exclude("META-INF/ASL2.0")
        exclude("META-INF/*.kotlin_module")

    }
}


dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //scanner
    implementation(libs.zxing.android.embedded)

    //retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.convertor)

    //google spread sheet
    implementation(libs.google.api)
    implementation(libs.google.api.sheets)
}