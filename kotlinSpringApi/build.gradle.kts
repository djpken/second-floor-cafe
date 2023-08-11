import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    war
    id("org.springframework.boot") version "3.1.0"
    id("io.spring.dependency-management") version "1.1.2"
    kotlin("jvm") version "1.9.0"
    kotlin("plugin.spring") version "1.9.0"
}
group = "com.erp"
version = "0.0.1-SNAPSHOT"
val springBootVersion = "3.1.0"
val kotlinVersion = "1.9.0"
val myBatisPlusVersion = "3.5.3.1"
java {
    sourceCompatibility = JavaVersion.VERSION_18
}
repositories {
    mavenCentral()
}
dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web:$springBootVersion")
    implementation("org.springframework.boot:spring-boot-devtools:$springBootVersion")
    implementation("com.baomidou:mybatis-plus-boot-starter:$myBatisPlusVersion")
    implementation("org.mariadb.jdbc:mariadb-java-client:3.1.0")
    implementation("com.baomidou:mybatis-plus-generator:$myBatisPlusVersion")
    implementation("org.springframework.boot:spring-boot-starter-security:$springBootVersion")
    implementation("org.springframework.boot:spring-boot-starter-data-redis:$springBootVersion")
    implementation("com.github.xiaoymin:knife4j-openapi3-jakarta-spring-boot-starter:4.1.0")
    implementation("cn.hutool:hutool-all:5.8.20")
    runtimeOnly("org.jetbrains.kotlin:kotlin-reflect:1.9.0")
    compileOnly("org.projectlombok:lombok:1.18.24")
    testImplementation("org.springframework.boot:spring-boot-starter-test:$springBootVersion")
    testImplementation("com.baomidou:mybatis-plus-boot-starter-test:$myBatisPlusVersion")
}
tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}
tasks.withType<JavaExec> {
    systemProperty("file.encoding", "utf-8")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "18"
    }
}
tasks.withType<Test> {
    useJUnitPlatform()
}