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
    testCompile("junit:junit:4.12")
}