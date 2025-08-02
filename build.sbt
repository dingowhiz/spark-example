// Scala Apache Spark Example Project Configuration
// Builds a complete Spark application with Java 22 compatibility

name := "SparkExample"
version := "1.0"

// Use Scala 2.13.x for Spark compatibility (Spark doesn't fully support Scala 3.x yet)
scalaVersion := "2.13.12"

// Set main class for running
Compile / mainClass := Some("com.packt.descala.scalaplayground.FirstSparkApp")

// Apache Spark version - latest stable release
val sparkVersion = "3.5.1"

// Enable forking to run in separate JVM process
// This allows JVM options to take effect properly
run / fork := true

// JVM options to resolve Java module access issues with Java 22+
// Apache Spark needs access to internal Java APIs that are restricted by the module system
run / javaOptions ++= Seq(
  "--add-opens=java.base/java.lang=ALL-UNNAMED",           // Core language features
  "--add-opens=java.base/java.lang.invoke=ALL-UNNAMED",    // Method handles
  "--add-opens=java.base/java.lang.reflect=ALL-UNNAMED",   // Reflection API
  "--add-opens=java.base/java.io=ALL-UNNAMED",             // I/O operations
  "--add-opens=java.base/java.net=ALL-UNNAMED",            // Network operations
  "--add-opens=java.base/java.nio=ALL-UNNAMED",            // Non-blocking I/O
  "--add-opens=java.base/java.util=ALL-UNNAMED",           // Utilities
  "--add-opens=java.base/java.util.concurrent=ALL-UNNAMED", // Concurrency
  "--add-opens=java.base/java.util.concurrent.atomic=ALL-UNNAMED", // Atomic operations
  "--add-opens=java.base/sun.nio.ch=ALL-UNNAMED",          // NIO channels (critical for Spark)
  "--add-opens=java.base/sun.nio.cs=ALL-UNNAMED",          // Character sets
  "--add-opens=java.base/sun.security.action=ALL-UNNAMED", // Security actions
  "--add-opens=java.base/sun.util.calendar=ALL-UNNAMED"    // Calendar utilities
)

// Apache Spark dependencies
libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion,      // Core Spark functionality
  "org.apache.spark" %% "spark-sql" % sparkVersion,       // SQL and DataFrame API
  "org.apache.spark" %% "spark-mllib" % sparkVersion % "provided", // Machine learning (optional)
  "mysql" % "mysql-connector-java" % "8.0.33"            // MySQL JDBC driver
)

// Exclude conflicting Hadoop dependencies to avoid version conflicts
excludeDependencies ++= Seq(
  ExclusionRule("org.apache.hadoop", "hadoop-client"),
  ExclusionRule("org.apache.hadoop", "hadoop-yarn-client")
)

// Assembly plugin configuration for creating executable JAR files
ThisBuild / assemblyMergeStrategy := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard  // Ignore META-INF conflicts
  case x => MergeStrategy.first                                // Use first occurrence for other conflicts
}
