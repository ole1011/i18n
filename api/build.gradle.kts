plugins {
    alias(libs.plugins.maven.publish)
}

publishing {
    repositories {
        maven {
            name = "ole101"
            val releasesRepoUrl = "https://repo.ole101.de/releases"
            val snapshotsRepoUrl = "https://repo.ole101.de/snapshots"
            url = uri(if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl)
            credentials(PasswordCredentials::class)
            authentication {
                create<BasicAuthentication>("basic")
            }
        }
    }
    publications {
        create<MavenPublication>("maven") {
            groupId = "de.ole101.i18n"
            artifactId = "i18n-api"
            version = project.version as String
            artifact(tasks.named("jar").get().outputs.files.singleFile)
        }
    }
}
