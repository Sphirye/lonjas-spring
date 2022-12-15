import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

plugins {
	id("org.springframework.boot") version "2.7.3"
	id("io.spring.dependency-management") version "1.0.13.RELEASE"
	kotlin("jvm") version "1.6.21"
	kotlin("plugin.spring") version "1.6.21"
	kotlin("plugin.jpa") version "1.6.21"
	kotlin("kapt") version "1.5.10"
}

group = "com.sphirye.lonjas"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
	maven("https://jitpack.io")
}

dependencies {
	// CORE
	implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-jdbc")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf:2.7.3")

	// DATABASE
	runtimeOnly("com.h2database:h2")
	runtimeOnly("mysql:mysql-connector-java")
	implementation("org.mariadb.jdbc:mariadb-java-client:3.0.6")
	implementation("org.hibernate:hibernate-jpamodelgen")
	kapt("org.hibernate:hibernate-jpamodelgen")

	// KOTLIN
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

	// RETROFIT
	implementation("com.squareup.retrofit2:retrofit:2.9.0")
	implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")
	implementation("com.squareup.retrofit2:converter-gson:2.9.0")
	implementation(platform("com.squareup.okhttp3:okhttp-bom:4.9.0"))
	implementation("com.squareup.okhttp3:okhttp")
	implementation("com.squareup.okhttp3:logging-interceptor")


	// SECURITY
	implementation("org.springframework.boot:spring-boot-starter-security")
	runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.5")
	runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.5")
	implementation("io.jsonwebtoken:jjwt-api:0.11.5")
	implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")

	// TEST
	implementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
	}

	testImplementation("org.springframework.boot:spring-boot-starter-test")
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