import org.gradle.api.JavaVersion.VERSION_21

plugins {
    java
    alias(libs.plugins.paperweight.userdev)
}

allprojects {
    apply(plugin = "java-library")
    apply(plugin = rootProject.libs.plugins.paperweight.userdev.get().pluginId)

    group = "de.ole101.i18n"
    version = "1.0.0"
    description = "A small library for simplifying internationalization in Minecraft plugins"

    repositories {
        mavenCentral()
        maven("https://oss.sonatype.org/content/groups/public/")
        maven("https://jitpack.io")
    }

    dependencies {
        implementation(rootProject.libs.lombok)
        annotationProcessor(rootProject.libs.lombok)
        implementation(rootProject.libs.guice)
        implementation(rootProject.libs.annotations)

        paperweight.paperDevBundle("1.21.1-R0.1-SNAPSHOT")
    }

    tasks {
        withType<JavaCompile>().configureEach {
            sourceCompatibility = VERSION_21.toString()
            targetCompatibility = VERSION_21.toString()
            options.encoding = "UTF-8"
        }

        test {
            useJUnitPlatform()
        }

        named<Jar>("jar") {
            archiveFileName.set("${rootProject.name}-${project.name}-${project.version}.jar")
        }
    }
}
