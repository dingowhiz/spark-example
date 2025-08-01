# MySQL Installation and Setup Guide

## 🎉 Installation Completed Successfully!

MySQL 8.4.6 and MySQL Workbench 8.0.43 have been installed on your Windows system.

## 📍 Installation Locations

- **MySQL Server**: `C:\Program Files\MySQL\MySQL Server 8.4\`
- **MySQL Workbench**: `C:\Program Files\MySQL\MySQL Workbench 8.0 CE\`
- **Data Directory**: `C:\mysql-data\`
- **Configuration File**: `C:\mysql-data\my.ini`

## 🛠️ Current Status

✅ MySQL Server 8.4.6 installed  
✅ MySQL Workbench 8.0.43 installed  
✅ Data directory initialized  
✅ Configuration file created  
⚠️ MySQL server is running but requires initial setup  

## 🔧 Next Steps for Complete Setup

### 1. Set Root Password
```powershell
# Connect to MySQL and set a password
& "C:\Program Files\MySQL\MySQL Server 8.4\bin\mysql.exe" -u root
```

Once connected, run these SQL commands:
```sql
ALTER USER 'root'@'localhost' IDENTIFIED BY 'your_password_here';
FLUSH PRIVILEGES;
EXIT;
```

### 2. Create a Development Database
```sql
CREATE DATABASE development;
CREATE USER 'dev_user'@'localhost' IDENTIFIED BY 'dev_password';
GRANT ALL PRIVILEGES ON development.* TO 'dev_user'@'localhost';
FLUSH PRIVILEGES;
```

### 3. Test Connection
```powershell
# Test with new password
& "C:\Program Files\MySQL\MySQL Server 8.4\bin\mysql.exe" -u root -p -e "SHOW DATABASES;"
```

## 🔐 Security Configuration

Run the MySQL security script to secure your installation:
```powershell
& "C:\Program Files\MySQL\MySQL Server 8.4\bin\mysql_secure_installation.exe"
```

This will help you:
- Set root password
- Remove anonymous users
- Disable remote root login
- Remove test database
- Reload privilege tables

## 🚀 Starting and Stopping MySQL

### Manual Start/Stop
```powershell
# Start MySQL Server
& "C:\Program Files\MySQL\MySQL Server 8.4\bin\mysqld.exe" --defaults-file="C:\mysql-data\my.ini"

# Stop MySQL (requires admin privileges)
& "C:\Program Files\MySQL\MySQL Server 8.4\bin\mysqladmin.exe" -u root -p shutdown
```

### Install as Windows Service (Recommended)
```powershell
# Run as Administrator
& "C:\Program Files\MySQL\MySQL Server 8.4\bin\mysqld.exe" --install MySQL84 --defaults-file="C:\mysql-data\my.ini"

# Start service
Start-Service MySQL84

# Set to start automatically
Set-Service -Name MySQL84 -StartupType Automatic
```

## 🔍 Useful Commands

### MySQL Client Connection
```powershell
# Connect to MySQL
& "C:\Program Files\MySQL\MySQL Server 8.4\bin\mysql.exe" -u root -p

# Connect to specific database
& "C:\Program Files\MySQL\MySQL Server 8.4\bin\mysql.exe" -u root -p -D database_name
```

### Database Operations
```sql
-- Show all databases
SHOW DATABASES;

-- Create new database
CREATE DATABASE my_project;

-- Use a database
USE my_project;

-- Show tables
SHOW TABLES;

-- Show current user
SELECT USER();

-- Show MySQL version
SELECT VERSION();
```

### Import/Export Data
```powershell
# Export database
& "C:\Program Files\MySQL\MySQL Server 8.4\bin\mysqldump.exe" -u root -p database_name > backup.sql

# Import database
& "C:\Program Files\MySQL\MySQL Server 8.4\bin\mysql.exe" -u root -p database_name < backup.sql
```

## 🖥️ MySQL Workbench

Launch MySQL Workbench for a graphical interface:
- **Start Menu**: Search for "MySQL Workbench"
- **Default Connection**: localhost:3306
- **Username**: root
- **Password**: (set during initial setup)

## 📊 Integration with Scala/Spark

To use MySQL with your Scala Spark projects, add this dependency to `build.sbt`:

```scala
libraryDependencies += "mysql" % "mysql-connector-java" % "8.4.0"
```

Example Spark connection:
```scala
val df = spark.read
  .format("jdbc")
  .option("url", "jdbc:mysql://localhost:3306/database_name")
  .option("dbtable", "table_name")
  .option("user", "username")
  .option("password", "password")
  .option("driver", "com.mysql.cj.jdbc.Driver")
  .load()
```

## 🐛 Troubleshooting

### Common Issues

1. **Connection Refused**
   - Check if MySQL service is running: `Get-Service MySQL*`
   - Verify port 3306 is not blocked by firewall

2. **Access Denied**
   - Ensure correct username/password
   - Check user privileges: `SHOW GRANTS FOR 'username'@'localhost';`

3. **Can't Connect to Server**
   - Verify MySQL is listening: `netstat -an | findstr :3306`
   - Check error log: `C:\mysql-data\mysql-error.log`

### Log Files
- **Error Log**: `C:\mysql-data\mysql-error.log`
- **General Log**: `C:\mysql-data\mysql-general.log`

## 📚 Additional Resources

- [MySQL Documentation](https://dev.mysql.com/doc/)
- [MySQL Workbench Manual](https://dev.mysql.com/doc/workbench/en/)
- [MySQL Performance Tuning](https://dev.mysql.com/doc/refman/8.4/en/optimization.html)

## 🎯 Quick Start Checklist

- [ ] Set root password
- [ ] Create development database
- [ ] Install MySQL as Windows service
- [ ] Test connection with MySQL Workbench
- [ ] Configure firewall if needed
- [ ] Create application-specific users
- [ ] Set up regular backups

Your MySQL installation is ready for development! 🚀
