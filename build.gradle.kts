plugins {
    kotlin("jvm") version "2.4.20"
    id("org.springframework.boot") version "3.3.2"
    id("io.spring.dependency-management") version "1.1.6"
    kotlin("plugin.spring") version "2.4.20"
}

group = "org.example.test"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val cucumber = "7.20.1"

dependencies {
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.google.code.gson:gson:2.11.0")
    testImplementation("io.cucumber:cucumber-java:$cucumber")
    testImplementation("io.cucumber:cucumber-junit:$cucumber")

    implementation("io.cucumber:cucumber-java:$cucumber")
    implementation("io.cucumber:cucumber-testng:$cucumber")
    testImplementation("io.cucumber:cucumber-spring:$cucumber")

    implementation("org.springframework.boot:spring-boot-starter")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    implementation("io.appium:java-client:9.3.0")
    testImplementation("org.testng:testng:7.9.0")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation(kotlin("test"))
}

tasks.register( name ="runDeviceFarm", type = Test::class) {
    description = "This task is for getting available devices target for testing"

    (findProperty("testTags") as String?)?.let {
        systemProperty("TEST_TAGS", it)
    }

    useTestNG {
        suites("src/test/resources/device_farm/deviceFarm.xml")
    }
}

/*Command:
./gradlew clean test -PtestTags="@login" --info

run task test dan masukan scenario yang ingin di test contoh @login atau multiple @login,@bebas
 */
tasks.test {
    dependsOn(tasks.getByName("runDeviceFarm"))
    useTestNG {
        suites("src/test/resources/parallel_testing.xml")
    }
    mustRunAfter(tasks.getByName("runDeviceFarm"))
}