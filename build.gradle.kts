import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinMultiplatformPluginWrapper

plugins {
    kotlin("multiplatform") version "1.7.20-RC" apply false
}

allprojects {
    repositories {
        mavenCentral()
    }
}

subprojects {
    apply<KotlinMultiplatformPluginWrapper>()
    configure<KotlinMultiplatformExtension> {
        jvm {
            compilations {
                val main by getting
                val example by creating {
                    defaultSourceSet {
                        dependsOn(main.defaultSourceSet)
                    }

                    task<JavaExec>("runJvmExample") {
                        classpath = output.classesDirs + compileDependencyFiles
                        mainClass.set("${project.name}.example.MainKt")
                    }
                }
            }
        }
    }
}