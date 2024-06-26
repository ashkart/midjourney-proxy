plugins {
    java
    `maven-publish`
    idea
    id("io.freefair.lombok") version "8.6"
}

repositories {
    mavenLocal()
    maven {
        url = uri("https://repo.maven.apache.org/maven2/")
    }
}

tasks.jar {
    manifest.attributes["Main-Class"] = "com.github.novicezk.midjourney.ProxyApplication"
    val dependencies = configurations
        .runtimeClasspath
        .get()
        .map(::zipTree) // OR .map { zipTree(it) }
    from(dependencies)
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web:2.6.15")
    implementation("org.springframework.boot:spring-boot-starter-data-redis:2.6.15")
    implementation("org.springframework:spring-websocket:5.3.27")
    implementation("cn.hutool:hutool-core:5.8.26")
    implementation("cn.hutool:hutool-cache:5.8.26")
    implementation("cn.hutool:hutool-crypto:5.8.26")
    implementation("org.json:json:20240303")
    implementation("net.dv8tion:JDA:5.0.0-beta.21")
    implementation("com.unfbx:chatgpt-java:1.1.5")
    implementation("eu.maxschuster:dataurl:2.0.0")
    implementation("com.github.xiaoymin:knife4j-openapi2-spring-boot-starter:4.1.0")
    implementation("eu.bitwalker:UserAgentUtils:1.21")
    implementation("org.springframework.boot:spring-boot-configuration-processor:2.6.15")
    implementation("org.projectlombok:lombok:1.18.26")
}

group = "com.github.novicezk"
version = "2.6.2"
description = "midjourney-proxy"
java.sourceCompatibility = JavaVersion.VERSION_17

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}

tasks.withType<JavaCompile>() {
    options.encoding = "UTF-8"
}
