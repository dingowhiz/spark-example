package com.packt.descala.scalaplayground

import org.apache.spark.sql.SparkSession

object ReadTable extends App {
    private val session = SparkSession
        .builder()
        .appName("Spark JDBC Example")
        .master("local[*]")
        .config("spark.driver.host", "localhost")
        .config("spark.driver.bindAddress", "127.0.0.1")
        .config("spark.sql.adaptive.enabled", "false")
        .getOrCreate()

    private val airportsDF = session.read
        .format("jdbc")
        .option("url", "jdbc:mysql://localhost:3306/mysql")
        .option("dbtable", "airports")
        .option("user", "root")
        .option("password", "dundee")
        .load()
    
    airportsDF.show()
    
    // Stop Spark session
    session.stop()
}