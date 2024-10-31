import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    alias(libs.plugins.shadow)
}

dependencies {
    implementation(project(":api"))
}

tasks {
    named<ShadowJar>("shadowJar") {
        archiveFileName.set("${rootProject.name}-${project.name}-${project.version}-all.jar")
    }

    build {
        dependsOn(named("shadowJar"))
    }
}
