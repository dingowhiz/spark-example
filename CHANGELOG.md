# Changelog

All notable changes to this project will be documented in this file.

## [1.1.0] - 2025-08-01 (Evening Session)

### Added
- **FirstSparkApp.scala** - Enhanced Spark application with statistical analysis
- **SPARK_EXECUTION_LOG.md** - Detailed execution documentation
- **MySQL 8.4.6** - Database server installation and setup
- **MinIO Server & Client** - Object storage with S3 compatibility
- **MYSQL_SETUP.md** - Comprehensive MySQL installation guide
- **MYSQL_LAUNCH_GUIDE.md** - MySQL usage and commands reference
- **MINIO_SETUP_GUIDE.md** - MinIO installation and configuration guide
- **MinIO-Aliases.ps1** - PowerShell aliases for MinIO commands

### Executed Successfully
- **Spark Application Run** - FirstSparkApp completed in 19 seconds
- **Statistical Analysis** - Age calculations (avg: 30.0, total: 150)
- **DataFrame Operations** - Count, show, and aggregation functions
- **Resource Management** - Clean Spark session lifecycle

### Infrastructure Setup
- **MySQL Server**: Running on port 3306 with web console
- **MinIO Object Storage**: Running on ports 9000 (API) and 9001 (Console)
- **Development Environment**: Complete Scala + Spark + Database + Storage stack

### Performance Validated
- **Memory Usage**: 1802.4 MiB available
- **Job Execution**: 4 Spark jobs completed successfully
- **Data Processing**: 5 records with perfect accuracy
- **Java 22 Compatibility**: All module access issues resolved

## [1.0.0] - 2025-08-01

### Added
- Initial Spark DataFrame example application
- Complete Scala 3.4.1 and Apache Spark 3.5.1 integration
- Comprehensive documentation in README.md
- SBT build configuration with Java 22 compatibility
- Java module access permissions for modern JVM versions
- Sample DataFrame operations:
  - Data creation from Scala collections
  - Statistical analysis with describe()
  - Filtering operations with DataFrame DSL
  - Aggregation with groupBy() and count()
- Proper SparkSession lifecycle management
- Windows 11 compatibility testing

### Technical Details
- Spark Version: 3.5.1
- Scala Version: 2.13.12 (for Spark compatibility)
- Java Version: 22.0.1
- SBT Version: 1.10.0
- Platform: Windows 11

### Configuration
- Local cluster execution with all available cores
- Adaptive query execution enabled
- Partition coalescing optimization
- Comprehensive JVM module access grants
- Fork mode enabled for proper JVM options handling

### Dependencies
- spark-core: 3.5.1
- spark-sql: 3.5.1
- spark-mllib: 3.5.1 (provided scope)

### Known Issues
- Hadoop native library warnings on Windows (expected, doesn't affect functionality)
- Requires Java module access permissions for Java 22+ compatibility
