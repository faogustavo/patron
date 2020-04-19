@file:Suppress("UnstableApiUsage")

plugins {
    kotlin("jvm")
}

group = "dev.patron"
version = "1.0"

tasks.named<Jar>("jar") {
    manifest {
        attributes("Automatic-Module-Name" to "dev.patron")
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.3.61")
    implementation("com.squareup:kotlinpoet:1.4.4")

    testImplementation("junit:junit:4.12")
    testImplementation("io.strikt:strikt-core:0.24.0")
    testImplementation("io.mockk:mockk:1.9.3")
    testImplementation(project(":strikt-poet"))
}
