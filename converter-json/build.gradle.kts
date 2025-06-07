import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    `maven-publish`
    signing
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
    publishing {
        singleVariant("release") {
            withSourcesJar()
        }
    }
}

kotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_1_8)
    }
}

publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = "io.github.flowfan"
            artifactId = "converter-json"
            version = "1.9.0"

            afterEvaluate {
                from(components["release"])
            }
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
    }
    repositories {
        maven {
            url = uri("https://ossrh-staging-api.central.sonatype.com/service/local/staging/deploy/maven2/")
            credentials {
                username = properties["ossrhUsername"] as String
                password = properties["ossrhPassword"] as String
            }
        }
    }
}
signing {
    sign(publishing.publications["release"])
}

dependencies {

    implementation(libs.okhttp)
    implementation(libs.retrofit)
    implementation(libs.kotlinx.serialization.json)
}