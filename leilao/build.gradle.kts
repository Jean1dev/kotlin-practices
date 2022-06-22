import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.6.6"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.6.21"
	kotlin("plugin.spring") version "1.6.21"
}

group = "com.app"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web:2.7.0")
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.springdoc:springdoc-openapi-ui:1.6.9")
	implementation("org.springframework.boot:spring-boot-starter-validation:2.7.0")
	// https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-mongodb
	implementation("org.springframework.boot:spring-boot-starter-data-mongodb:2.7.0")
	// https://mvnrepository.com/artifact/org.springframework.cloud/spring-cloud-starter-openfeign
	implementation("org.springframework.cloud:spring-cloud-starter-openfeign:3.1.3")
	// https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-webflux
	implementation("org.springframework.boot:spring-boot-starter-webflux:2.7.0")


	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("com.ninja-squad:springmockk:3.1.1")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
