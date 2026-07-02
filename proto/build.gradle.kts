plugins {
    kotlin("jvm")
    id("com.google.protobuf")
}

group = "com.chipmong"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}


tasks.test {
    useJUnitPlatform()
}