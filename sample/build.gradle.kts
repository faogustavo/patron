plugins {
    kotlin("jvm")
}

group = "dev.patron.sample"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.3.61")
    implementation(project(":patron"))
    implementation("com.squareup:kotlinpoet:1.4.4")
    testCompile("junit:junit:4.12")
}