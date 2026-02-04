plugins {
	java
	id("org.springframework.boot") version "4.0.2"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.springboot"
version = "0.0.1-SNAPSHOT"
description = "Demo project for Spring Boot to Azure"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {

	// ✅ Web (REST controllers)
	implementation("org.springframework.boot:spring-boot-starter-web")

	// ✅ JPA (FIXES Entity, JpaRepository, User)
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")

	// ✅ Validation (NotBlank, Email, Pattern)
	implementation("org.springframework.boot:spring-boot-starter-validation")

	// ✅ SQL Server Driver
	runtimeOnly("com.microsoft.sqlserver:mssql-jdbc")

	// ✅ Lombok (FIXES getters/setters)
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")

	// Optional (you can remove for now)
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")

	// Tests
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
