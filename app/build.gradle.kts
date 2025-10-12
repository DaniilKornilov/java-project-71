plugins {
    id("application")
    id("checkstyle")
    id("com.github.ben-manes.versions") version "0.53.0"
    id("org.sonarqube") version "6.3.1.5724"
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

application {
    mainClass.set("hexlet.code.App")
}

checkstyle {
    toolVersion = "11.1.0"
    configFile = file("$rootDir/config/checkstyle.xml")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("info.picocli:picocli:4.7.7")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.20.0")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.20.0")
    implementation("commons-io:commons-io:2.20.0")

    testImplementation("org.junit.jupiter:junit-jupiter:5.11.0")
}

sonar {
    properties {
        property("sonar.projectKey", "DaniilKornilov_java-project-71")
        property("sonar.organization", "daniilkornilov")
    }
}

tasks.test {
    useJUnitPlatform()
}
