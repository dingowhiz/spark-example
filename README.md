# Scala Apache Spark Example

A complete example demonstrating Apache Spark DataFrame operations using Scala 3.4.1 and Spark 3.5.1.

## 🚀 Features

- **SparkSession Configuration**: Local cluster setup with adaptive query execution
- **DataFrame Operations**: Creating, filtering, and transforming data
- **Statistical Analysis**: Descriptive statistics and aggregations
- **Data Processing**: Group by operations and filtering
- **Clean Resource Management**: Proper Spark session lifecycle

## 📋 Prerequisites

- **Java 22** or higher
- **Scala 3.4.1** (installed via Coursier)
- **SBT 1.10.0** for build management
- **Windows 11** (tested environment)

## 🛠️ Installation

### 1. Install Scala and SBT
```powershell
# Install Coursier
Invoke-RestMethod -Uri "https://get-coursier.io/coursier-cli-windows.zip" -OutFile "coursier.zip"
Expand-Archive coursier.zip -DestinationPath .
./cs setup
```

### 2. Set Environment Variables
```powershell
$env:SCALA_HOME = "C:\Users\$env:USERNAME\AppData\Local\Coursier\data"
$env:PATH += ";$env:SCALA_HOME\bin"
```

### 3. Clone and Build
```bash
git clone <repository-url>
cd spark-example
sbt compile
```

## 🏃‍♂️ Running the Application

### Quick Start
```bash
sbt run
# Or run specific application
sbt "runMain com.packt.descala.scalaplayground.FirstSparkApp"
```

### Applications Available
1. **SparkExample.scala** - Original demonstration with filtering and groupBy operations
2. **FirstSparkApp.scala** - Enhanced example with statistical analysis

### Execution Results - FirstSparkApp (August 1, 2025)

**✅ Successful Execution Summary:**
- **Total Runtime**: 19 seconds
- **Spark Version**: 3.5.1
- **Java Version**: 22.0.1
- **Records Processed**: 5 employee records
- **Operations**: DataFrame creation, count, show, statistical analysis

**Sample Output:**
```
DataFrame created successfully!
Number of records: 5

Sample data:
+-------+---+--------------+
|   Name|Age|           Job|
+-------+---+--------------+
|  Alice| 25|      Engineer|
|    Bob| 30|Data Scientist|
|Charlie| 35|       Manager|
|  Diana| 28|     Developer|
|    Eve| 32|       Analyst|
+-------+---+--------------+

Age statistics:
+-----------+---------+
|Average_Age|Total_Age|
+-----------+---------+
|       30.0|      150|
+-----------+---------+

✅ Spark session stopped. FirstSparkApp completed!
```

**Performance Metrics:**
- Memory allocated: 1802.4 MiB
- Spark jobs executed: 4
- Data processing time: ~6 seconds
- Resource cleanup: Successful

## 📁 Project Structure

```
spark-example/
├── build.sbt                 # SBT build configuration with Java 22 compatibility
├── project/
│   ├── build.properties      # SBT version specification
│   └── metals.sbt           # Metals IDE support
├── src/
│   └── main/
│       └── scala/
│           ├── SparkExample.scala      # Original Spark DataFrame demo
│           └── FirstSparkApp.scala     # Enhanced statistical analysis demo
├── target/                   # Compiled artifacts (gitignored)
├── README.md                # This documentation
├── CHANGELOG.md             # Version history and updates
├── MYSQL_SETUP.md           # MySQL installation and setup guide
├── MYSQL_LAUNCH_GUIDE.md    # MySQL usage instructions
├── MINIO_SETUP_GUIDE.md     # MinIO object storage setup
└── MinIO-Aliases.ps1        # PowerShell aliases for MinIO
```

## 🔧 Configuration Details

### SBT Configuration (`build.sbt`)
- **Scala Version**: 2.13.12 (Spark compatibility)
- **Spark Version**: 3.5.1
- **Java Compatibility**: Module access permissions for Java 22
- **Fork Settings**: Separate JVM process for proper module handling

### Key Dependencies
```scala
libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "3.5.1",
  "org.apache.spark" %% "spark-sql" % "3.5.1"
)
```

### Java Module Access
The project includes extensive JVM options to handle Java 22 module system restrictions:
```scala
run / javaOptions ++= Seq(
  "--add-opens=java.base/java.lang=ALL-UNNAMED",
  "--add-opens=java.base/java.nio=ALL-UNNAMED",
  "--add-opens=java.base/sun.nio.ch=ALL-UNNAMED",
  // ... additional module opens
)
```

## 💡 Code Walkthrough

### SparkSession Creation
```scala
val spark = SparkSession.builder()
  .appName("Spark Example")
  .master("local[*]")
  .config("spark.sql.adaptive.enabled", "true")
  .config("spark.sql.adaptive.coalescePartitions.enabled", "true")
  .getOrCreate()
```

### DataFrame Operations
```scala
// Create DataFrame from Scala collections
val data = Seq(
  ("Alice", 25, "Engineer"),
  ("Bob", 30, "Data Scientist"),
  ("Charlie", 35, "Manager"),
  ("Diana", 28, "Developer")
)
val df = data.toDF("Name", "Age", "Job")

// Statistical analysis
df.describe("Age").show()

// Filtering and aggregation
df.filter($"Age" > 27).show()
df.groupBy("Job").count().show()
```

## 🐛 Troubleshooting

### Common Issues

1. **Java Module Access Errors**
   - Solution: Ensure `run / fork := true` in `build.sbt`
   - The project includes comprehensive module access permissions

2. **Hadoop Warnings on Windows**
   - Expected behavior: Hadoop native libraries warning is normal
   - Does not affect Spark functionality

3. **Memory Issues**
   - Increase JVM heap: Add `-Xmx4g` to `javaOptions`
   - Adjust Spark executor memory in configuration

### Version Compatibility
- **Scala 3.x**: Use Scala 2.13.x for Spark compatibility
- **Java 22**: Requires module access permissions (included in build.sbt)
- **Spark 3.5.1**: Latest stable version with comprehensive DataFrame API

## 📚 Learning Resources

- [Apache Spark Documentation](https://spark.apache.org/docs/latest/)
- [Scala Documentation](https://docs.scala-lang.org/)
- [SBT Documentation](https://www.scala-sbt.org/documentation.html)

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests if applicable
5. Submit a pull request

## 📄 License

This project is provided as an educational example. Feel free to use and modify for learning purposes.

## 🔗 Related Projects

- [Spark Scala Examples](https://github.com/apache/spark/tree/master/examples/src/main/scala)
- [Coursier Installation Guide](https://get-coursier.io/docs/cli-installation)
- [SBT Getting Started](https://www.scala-sbt.org/1.x/docs/Getting-Started.html)
