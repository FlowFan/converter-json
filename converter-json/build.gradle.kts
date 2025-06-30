import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    `maven-publish`
    signing
    alias(libs.plugins.publishPlugin)
}

android {
    namespace = "com.retrofit2.converter"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

kotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_1_8)
    }
}

mavenPublishing {
    publishToMavenCentral()
    signAllPublications()
    coordinates(
        groupId = "io.github.flowfan",
        artifactId = "converter-json",
        version = "1.9.0"
    )

    pom {
        name.set("JsonConverterFactory")
        description.set("A Retrofit 2 Converter.Factory for Kotlin Serialization.")
        url.set("https://flowfan.github.io/converter-json/")
        licenses {
            license {
                name.set("The Apache License, Version 2.0")
                url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
            }
        }
        developers {
            developer {
                id.set("fan1138612367")
                name.set("FlowFan")
                email.set("fan1138612367@vip.qq.com")
            }
        }
        scm {
            connection.set("https://github.com/FlowFan/converter-json.git")
            developerConnection.set("https://github.com/FlowFan/converter-json.git")
            url.set("https://flowfan.github.io/converter-json/")
        }
    }
}

dependencies {

    implementation(libs.okhttp)
    implementation(libs.retrofit)
    implementation(libs.kotlinx.serialization.json)
}