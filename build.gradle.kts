plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.apache.tomcat.embed:tomcat-embed-core:8.5.42")
    implementation("org.apache.tomcat.embed:tomcat-embed-jasper:8.5.42")

    implementation("javax.servlet:jstl:1.2")
    implementation("javax.servlet:javax.servlet-api:4.0.1")
    implementation("org.passay:passay:1.6.1")

    implementation("org.reflections:reflections:0.9.12")

    implementation("ch.qos.logback:logback-classic:1.2.3")
    implementation("org.assertj:assertj-core:3.23.1")


    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}