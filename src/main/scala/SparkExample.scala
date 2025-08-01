import org.apache.spark.sql.SparkSession

/**
 * Comprehensive Apache Spark example demonstrating DataFrame operations.
 * 
 * This application showcases:
 * - SparkSession configuration with local cluster
 * - DataFrame creation from Scala collections
 * - Statistical analysis and descriptive statistics
 * - Data filtering and aggregation operations
 * - Proper resource cleanup
 * 
 * @author Scala Spark Example
 * @version 1.0
 */
object SparkExample extends App {
  
  // Create Spark session with optimized configuration
  val spark = SparkSession.builder()
    .appName("Spark Example")
    .master("local[*]") // Run locally with all available cores
    .config("spark.sql.adaptive.enabled", "true") // Enable adaptive query execution
    .config("spark.sql.adaptive.coalescePartitions.enabled", "true") // Optimize partition coalescing
    .getOrCreate()
  
  // Import implicits for DataFrame DSL operations
  import spark.implicits._
  
  println("🚀 Spark Session Created Successfully!")
  println(s"Spark Version: ${spark.version}")
  println(s"Scala Version: ${scala.util.Properties.versionString}")
  
  // Create sample data: Employee information with name, age, and job title
  val data = Seq(
    ("Alice", 25, "Engineer"),
    ("Bob", 30, "Data Scientist"),
    ("Charlie", 35, "Manager"),
    ("Diana", 28, "Developer")
  )
  
  // Convert Scala sequence to Spark DataFrame
  val df = data.toDF("Name", "Age", "Job")
  
  println("\n📊 Sample DataFrame:")
  df.show()
  
  // Generate descriptive statistics for numerical columns
  println("\n📈 DataFrame Statistics:")
  df.describe("Age").show()
  
  // Demonstrate filtering operations using DataFrame DSL
  println("\n🔍 People over 27:")
  df.filter($"Age" > 27).show()
  
  // Demonstrate aggregation operations with groupBy
  println("\n👥 Count by Job:")
  df.groupBy("Job").count().show()
  
  // Clean up resources - always stop Spark session
  spark.stop()
  println("\n✅ Spark session stopped. Example completed!")
}
