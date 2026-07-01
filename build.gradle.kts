plugins {
	kotlin("plugin.spring") version "2.3.21" apply false
	id("org.springframework.boot") version "4.1.0" apply false
	id("io.spring.dependency-management") version "1.1.7" apply false
	id("com.google.protobuf") version "0.9.6" apply false
	kotlin("jvm") version "2.4.0"
}

group = "com.chipmong"
version = "0.0.1-SNAPSHOT"

allprojects {
	group = "com.chipmong"
	version = "0.0.1-SNAPSHOT"

	repositories {
		mavenCentral()
	}
}

subprojects {
	apply(plugin = "org.jetbrains.kotlin.jvm")
	apply(plugin = "org.jetbrains.kotlin.plugin.spring")
	apply(plugin = "org.springframework.boot")
	apply(plugin = "io.spring.dependency-management")
	apply(plugin = "com.google.protobuf")

	// Use configure<T> to target the extensions explicitly
	configure<org.gradle.api.plugins.JavaPluginExtension> {
		toolchain {
			languageVersion.set(JavaLanguageVersion.of(17))
		}
	}

	configure<org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension> {
		jvmToolchain(17)
	}

	// Use string literals "implementation" and "testImplementation"
	dependencies {
		"implementation"("org.jetbrains.kotlin:kotlin-reflect")
		"implementation"("tools.jackson.module:jackson-module-kotlin")

		"implementation"("io.grpc:grpc-stub")
		"implementation"("io.grpc:grpc-protobuf")

		"implementation"("org.springframework.boot:spring-boot-starter-grpc-server")
		"implementation"("org.springframework.boot:spring-boot-starter-grpc-client")

		// Replaced kotlin("test") shorthand with its explicit coordinate string
		"testImplementation"("org.jetbrains.kotlin:kotlin-test")
	}

	tasks.withType<Test> {
		useJUnitPlatform()
	}
}
dependencies {
	implementation(kotlin("stdlib-jdk8"))
}
repositories {
	mavenCentral()
}