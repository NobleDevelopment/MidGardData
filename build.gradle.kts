import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.0"
    id("maven")
}

group = "org.nobledev"
version = "1.0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.litote.kmongo:kmongo-coroutine:4.2.7")
    implementation("ch.qos.logback:logback-classic:1.2.1")
}


tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}
