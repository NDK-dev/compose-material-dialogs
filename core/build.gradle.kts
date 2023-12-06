import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("org.jetbrains.compose")
    id("maven-publish")
}

val localProperty = gradleLocalProperties(rootDir)

val currentGroupId= extra["GROUP"] as String

group = currentGroupId
version = "0.1.1"

kotlin {
    androidTarget {
        publishAllLibraryVariants()
        compilations {
            all {
                kotlinOptions.jvmTarget = "1.8"
            }
        }
    }
    jvm {
        compilations {
            all {
                kotlinOptions.jvmTarget = "11"
            }
        }
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                compileOnly(compose.ui)
                compileOnly(compose.foundation)
                compileOnly(compose.material)
                compileOnly(compose.animation)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }

        val jvmCommon by creating {
            dependsOn(commonMain)
        }

        val jvmMain by getting {
            dependsOn(jvmCommon)
            dependencies {
            }
        }
        val jvmTest by getting

        val androidMain by getting {
            dependsOn(jvmCommon)
            dependencies {
                compileOnly(Dependencies.AndroidX.Compose.ui)
                compileOnly(Dependencies.AndroidX.Compose.foundation)
                compileOnly(Dependencies.AndroidX.Compose.material)
                compileOnly(Dependencies.AndroidX.Compose.animation)
            }
        }
        val androidUnitTest by getting

        val iosMain by creating {
            dependsOn(commonMain)
        }
        val iosTest by creating {
            dependsOn(commonTest)
        }

        listOf(
            "iosX64",
            "iosArm64",
            "iosSimulatorArm64",
        ).forEach {
            getByName(it + "Main").dependsOn(iosMain)
            getByName(it + "Test").dependsOn(iosTest)
        }
    }

    publishing {

        val publicationsFromMainHost = listOf(
            android(),
            iosX64(),
            iosArm64(),
            iosSimulatorArm64(),
        ).map { it.name } + "kotlinMultiplatform"

        publications {
            matching { it.name in publicationsFromMainHost }.all {
                val targetPublication = this@all
                tasks.withType<AbstractPublishToMaven>()
                    .matching { it.publication == targetPublication }
                    .configureEach {
                        onlyIf { findProperty("isMainHost") == "true" }
                    }
            }
        }

        repositories {
            mavenLocal {
                url = uri(localProperty.getProperty("maven.local"))
            }
        }
    }
}

android {
    namespace = "com.vanpra.composematerialdialogs.core"
}
dependencies {
    implementation("androidx.wear.compose:compose-material:1.2.1")
}
