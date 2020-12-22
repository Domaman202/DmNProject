import org.jetbrains.kotlin.ir.backend.js.compile

buildscript {
    repositories { jcenter() }

    dependencies {
        val kotlinVersion = "1.4.20"
        classpath(kotlin("gradle-plugin", version = kotlinVersion))
        classpath(kotlin("serialization", version = kotlinVersion))
    }
}

plugins {
    kotlin("multiplatform") version "1.4.10"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.4.10"
}
group = "me.user"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    jcenter()

//    maven { url = "https://jitpack.io" }
    maven ("https://jitpack.io")
}
kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
    }
    js {
        browser {
            testTask {
                useKarma {
                    useChromeHeadless()
                    webpackConfig.cssSupport.enabled = true
                }
            }
        }
    }
    val hostOs = System.getProperty("os.name")
    val isMingwX64 = hostOs.startsWith("Windows")
    val nativeTarget = when {
        hostOs == "Mac OS X" -> macosX64("native")
        hostOs == "Linux" -> linuxX64("native")
        isMingwX64 -> mingwX64("native")
        else -> throw GradleException("Host OS is not supported in Kotlin/Native.")
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                // Works as common dependency as well as the platform one
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.1")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation("com.github.Mouse0w0:FastReflection:1.0.1")

                implementation(kotlin("script-runtime", "1.4.20"))
                implementation(kotlin("reflect", "1.4.20"))

                implementation("org.codehaus.groovy:groovy-all:2.6.0-alpha-4")
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("org.junit.jupiter:junit-jupiter-api:5.0.2")

                implementation("io.kotest:kotest-runner-junit5:4.3.1") // for kotest framework
                implementation("io.kotest:kotest-assertions-core:4.3.1") // for kotest core jvm assertions
                implementation("io.kotest:kotest-property:4.3.1") // for kotest property test
            }
        }
        val jsMain by getting {
            dependencies {
                implementation(kotlin("script-runtime", "1.4.20"))
                implementation(kotlin("reflect", "1.4.20"))
            }
        }
        val jsTest by getting {
            dependencies {
                implementation(kotlin("test-js"))

                implementation(kotlin("script-runtime", "1.4.20"))
                implementation(kotlin("reflect", "1.4.20"))
            }
        }
        val nativeMain by getting
        val nativeTest by getting
        all {
            languageSettings.enableLanguageFeature("InlineClasses")
        }
    }
}