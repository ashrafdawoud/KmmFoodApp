buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    val  sql_delight_version="1.5.3"
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
        classpath("org.jetbrains.kotlin:kotlin-serialization:1.6.10")
        classpath("com.android.tools.build:gradle:7.1.0-alpha08")
        classpath("com.squareup.sqldelight:gradle-plugin:$sql_delight_version")

    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}