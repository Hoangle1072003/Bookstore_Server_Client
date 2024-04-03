/*
 * This file was generated by the Gradle 'init' task.
 */

plugins {
    `java-library`
    `maven-publish`
}

repositories {
    mavenLocal()
    maven {
        url = uri("https://repo.maven.apache.org/maven2/")
    }
}

dependencies {
    api(libs.net.sf.jasperreports.jasperreports)
    api(libs.org.springframework.security.spring.security.core)
    api(libs.org.mindrot.jbcrypt)
    api(libs.org.jfree.jfreechart)
    api(libs.org.apache.logging.log4j.log4j.core)
    api(libs.org.apache.poi.poi)
    api(libs.org.apache.poi.poi.ooxml)
    api(libs.org.apache.maven.plugins.maven.jar.plugin)
    api(libs.com.toedter.jcalendar)
    api(libs.org.apache.commons.commons.lang3)
    api(libs.org.kordamp.ikonli.ikonli.swing)
    api(libs.org.kordamp.ikonli.ikonli.materialdesign.pack)
    api(libs.com.itextpdf.itextpdf)
    // https://mvnrepository.com/artifact/org.hibernate/hibernate-core
    implementation("org.hibernate:hibernate-core:6.4.4.Final")
// https://mvnrepository.com/artifact/org.hibernate.orm/hibernate-testing
    testImplementation("org.hibernate.orm:hibernate-testing:6.4.4.Final")
// https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-api
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.1")
// https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-engine
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.10.1")
// https://mvnrepository.com/artifact/com.microsoft.sqlserver/mssql-jdbc
    implementation("com.microsoft.sqlserver:mssql-jdbc:12.3.0.jre20-preview")
// https://mvnrepository.com/artifact/org.projectlombok/lombok
    compileOnly("org.projectlombok:lombok:1.18.24")
}

group = "vn.N10_PTUD_v1"
version = "0.0.1-SNAPSHOT"
description = "QuanLyHieuSach"
java.sourceCompatibility = JavaVersion.VERSION_1_8

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}

tasks.withType<JavaCompile>() {
    options.encoding = "UTF-8"
}

tasks.withType<Javadoc>() {
    options.encoding = "UTF-8"
}
