plugins {
    kotlin("multiplatform") version "1.5.0"
}

group = "io.github.davidmhewitt"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    mavenLocal()
    maven() { url = uri("/app/maven-local") }
}

kotlin {
    val hostOs = System.getProperty("os.name")
    val isMingwX64 = hostOs.startsWith("Windows")
    val nativeTarget = when {
        //hostOs == "Mac OS X" -> macosX64("native")
        hostOs == "Linux" -> linuxX64("native")
        //isMingwX64 -> mingwX64("native")
        else -> throw GradleException("Host OS is not supported in Kotlin/Native.")
    }

    nativeTarget.apply {
        binaries {
            executable {
                entryPoint = "main"
            }
        }
    }
    sourceSets {
        val nativeMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.0-native-mt")
                implementation("org.gnome.kotlinx-gtk:nativex:0.1.0-alpha")
                implementation("org.gnome.kotlinx-gtk:dsl:0.1.0-alpha")
            }
        }
        val nativeTest by getting
    }
}
