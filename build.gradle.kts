plugins {
    id("org.springframework.boot") version "3.3.3"
    id("io.spring.dependency-management") version "1.1.1"
    `java-library`
    `maven-publish`
}

repositories {
    mavenCentral()
}

dependencies {
    api(libs.org.springframework.boot.spring.boot.starter.web)
    api(libs.org.springframework.boot.spring.boot.starter.data.jpa)
    compileOnly(libs.org.projectlombok.lombok)
    api(libs.org.springframework.boot.spring.boot.starter.security)
    runtimeOnly(libs.org.springframework.boot.spring.boot.devtools)
    runtimeOnly(libs.com.microsoft.sqlserver.mssql.jdbc)
    testImplementation(libs.org.springframework.boot.spring.boot.starter.test)
    compileOnly(libs.org.springframework.boot.spring.boot.starter.tomcat)
}

group = "com.product"
version = "0.0.1-SNAPSHOT"
description = "Test_CRUD_Product"
java.sourceCompatibility = JavaVersion.VERSION_17

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.withType<Javadoc> {
    options.encoding = "UTF-8"
}
