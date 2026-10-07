plugins {
    kotlin("jvm") version "2.4.20"
    id("org.springframework.boot") version "3.3.2"
    id("io.spring.dependency-management") version "1.1.6"
}

group = "org.example.test"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.google.code.gson:gson:2.11.0")
    testImplementation("io.cucumber:cucumber-java:7.20.1")
    testImplementation("io.cucumber:cucumber-junit:7.20.1")

    implementation("io.cucumber:cucumber-java:7.1.0")
    implementation("io.cucumber:cucumber-testng:7.1.0")
    testImplementation("io.cucumber:cucumber-spring:7.1.0")

    implementation("org.springframework.boot:spring-boot-starter")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    implementation("io.appium:java-client:9.3.0")
    testImplementation("org.testng:testng:7.9.0")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.register( name ="runDeviceFarm", type = Test::class) {
    useTestNG {
        suites("src/test/resources/device_farm/deviceFarm.xml")
    }
}

tasks.test {
    systemProperty("cucumber.filter.tags", "cucumber.filter.tags")
    dependsOn(tasks.getByName("runDeviceFarm"))
    useTestNG {
        suites("src/test/resources/parallel_testing.xml")
    }
    mustRunAfter(tasks.getByName("runDeviceFarm"))
}