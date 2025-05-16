tasks.register<Jar>("fatJar") {
    archiveClassifier.set("all") // This will name the JAR as yourproject-all.jar
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE

    manifest {
        attributes["Main-Class"] = "MainKt" // Replace with your main class if needed
    }

    from(sourceSets.main.get().output)

    // Include all runtime dependencies
    from({
        configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) }
    })
}
