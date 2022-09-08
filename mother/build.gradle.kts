kotlin {
    sourceSets {
        val jvmExample by getting {
            dependencies {
                implementation(projects.daughter)
            }
        }
    }
}