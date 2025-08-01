package com.packt.descala.scalaplayground

import org.apache.spark.sql.{
    DataFrame,
    Dataset,
    Row,
    SparkSession
}
import org.apache.spark.sql.functions.{avg, sum}

// Employee case class definition
case class Employee(
    empID: Int, 
    firstName: String, 
    lastName: String, 
    age: Int, 
    job: String, 
    salary: Double, 
    department: String)

object FirstSparkApp extends App{
// Reduce Spark logging verbosity
import org.apache.log4j.{Level, Logger}
Logger.getLogger("org").setLevel(Level.WARN)
Logger.getLogger("akka").setLevel(Level.WARN)

val spark: SparkSession = SparkSession
    .builder()
    .appName("First Spark App")
    .master("local[1]")
    .getOrCreate()
import spark.implicits._

// Data location configuration
val dataLocation = "C:/DATA/SCALA/spark-example/tmp"
println(s"Data location: $dataLocation")

// Create sample Employee data using the case class
val employeeData = Seq(
    Employee(300, "Alice", "Smith",25, "Engineer", 75000.0, "Engineering"),
    Employee(205, "Bob", "Segal", 30, "Data Scientist", 85000.0, "Analytics"),
    Employee(909, "Charlie", "Sheen", 35, "Manager", 95000.0, "Management"),
    Employee(303, "Diana", "Ross", 28, "Developer", 70000.0, "Engineering"),
    Employee(204, "Eve", "Garcia", 32, "Analyst", 65000.0, "Analytics"),
    Employee(308, "Frank", "Sinatra", 29, "DevOps Engineer", 80000.0, "Engineering"),
    Employee(908, "Grace", "Kelly", 31, "Product Manager", 90000.0, "Management")
)

val df: DataFrame = employeeData.toDF()

println("Employee DataFrame created successfully!")
println(s"Number of employee records: ${df.count()}")
println("\nEmployee data:")
df.show()

println("\nAge statistics:")
df.select(avg($"age").alias("Average_Age"), sum($"age").alias("Total_Age")).show()

println("\nSalary statistics:")
df.select(avg($"salary").alias("Average_Salary"), sum($"salary").alias("Total_Salary")).show()

println("\nDepartment distribution:")
df.groupBy("department").count().show()

// Display final summary
println("\n=== EMPLOYEE DATA SUMMARY ===")
println("=" * 50)
println(s"Total Employees: ${df.count()}")
println(s"Departments: ${df.select("department").distinct().count()}")
println(s"Average Age: ${df.agg(avg($"age")).collect()(0)(0)}")
val avgSalary = df.agg(avg($"salary")).collect()(0)(0).asInstanceOf[Double]
println(f"Average Salary: $$${avgSalary}%.2f")
println("=" * 50)

// Stop Spark session
spark.stop()
println("\nSpark session stopped. FirstSparkApp completed!")
}
