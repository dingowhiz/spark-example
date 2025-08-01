# How to Launch MySQL - Complete Guide

## 🚀 Quick Launch Methods

### 1. MySQL Command Line Client
```powershell
# Connect with root user (password required)
& "C:\Program Files\MySQL\MySQL Server 8.4\bin\mysql.exe" -u root -p

# Connect to specific database
& "C:\Program Files\MySQL\MySQL Server 8.4\bin\mysql.exe" -u root -p -D database_name

# Connect with specific host and port
& "C:\Program Files\MySQL\MySQL Server 8.4\bin\mysql.exe" -h localhost -P 3306 -u root -p
```

### 2. MySQL Workbench (GUI)
```powershell
# Launch MySQL Workbench
Start-Process "C:\Program Files\MySQL\MySQL Workbench 8.0 CE\MySQLWorkbench.exe"
```
**Or:** Search "MySQL Workbench" in Windows Start Menu

### 3. Start Menu Launch
- Press `Windows Key`
- Type "MySQL Workbench"
- Click on the application

## 🛠️ MySQL Server Management

### Check if MySQL is Running
```powershell
# Check MySQL processes
Get-Process -Name "mysqld" -ErrorAction SilentlyContinue

# Check if port 3306 is listening
netstat -an | findstr :3306
```

### Start MySQL Server (Manual)
```powershell
# Start MySQL server with configuration file
& "C:\Program Files\MySQL\MySQL Server 8.4\bin\mysqld.exe" --defaults-file="C:\mysql-data\my.ini"
```

### Stop MySQL Server
```powershell
# Graceful shutdown (requires password)
& "C:\Program Files\MySQL\MySQL Server 8.4\bin\mysqladmin.exe" -u root -p shutdown

# Force stop (requires admin privileges)
Get-Process -Name "mysqld" | Stop-Process -Force
```

## 🔧 Windows Service Setup (Recommended)

### Install MySQL as Windows Service
```powershell
# Run PowerShell as Administrator
& "C:\Program Files\MySQL\MySQL Server 8.4\bin\mysqld.exe" --install MySQL84 --defaults-file="C:\mysql-data\my.ini"
```

### Manage MySQL Service
```powershell
# Start service
Start-Service MySQL84

# Stop service
Stop-Service MySQL84

# Set automatic startup
Set-Service -Name MySQL84 -StartupType Automatic

# Check service status
Get-Service MySQL84
```

## 🌐 Connection Parameters

| Parameter | Value | Description |
|-----------|-------|-------------|
| **Host** | `localhost` or `127.0.0.1` | Server address |
| **Port** | `3306` | Default MySQL port |
| **Username** | `root` | Default admin user |
| **Password** | (set during setup) | User password |

## 💻 Common Connection Examples

### Basic Connection
```powershell
mysql -u root -p
```

### Connection with Database
```powershell
mysql -u root -p -D my_database
```

### Connection with All Parameters
```powershell
mysql -h localhost -P 3306 -u root -p
```

### Non-Interactive Connection
```powershell
mysql -u root -p"your_password" -e "SHOW DATABASES;"
```

## 🔍 Verification Commands

Once connected to MySQL, try these commands:

```sql
-- Show MySQL version
SELECT VERSION();

-- Show current user
SELECT USER();

-- Show all databases
SHOW DATABASES;

-- Show current time
SELECT NOW();

-- Show server status
SHOW STATUS LIKE 'Uptime';
```

## 🖥️ MySQL Workbench Setup

1. **Launch Workbench**
2. **Create New Connection:**
   - Connection Name: `Local MySQL`
   - Hostname: `127.0.0.1`
   - Port: `3306`
   - Username: `root`
3. **Test Connection**
4. **Enter Password when prompted**

## 🚨 Troubleshooting

### MySQL Won't Start
```powershell
# Check error logs
Get-Content "C:\mysql-data\mysql-error.log" | Select-Object -Last 20

# Check if port is already in use
netstat -an | findstr :3306

# Verify data directory permissions
Get-Acl "C:\mysql-data"
```

### Connection Issues
- ✅ Verify MySQL server is running
- ✅ Check firewall settings for port 3306
- ✅ Confirm username/password combination
- ✅ Ensure user has proper privileges

### Performance Check
```sql
-- Check active connections
SHOW PROCESSLIST;

-- Check server variables
SHOW VARIABLES LIKE 'max_connections';

-- Check database sizes
SELECT 
    table_schema AS 'Database',
    ROUND(SUM(data_length + index_length) / 1024 / 1024, 2) AS 'Size (MB)'
FROM information_schema.tables 
GROUP BY table_schema;
```

## 🎯 Quick Start Checklist

- [ ] MySQL server is running (check processes)
- [ ] Port 3306 is accessible
- [ ] Root password is set and known
- [ ] MySQL Workbench launches successfully
- [ ] Can connect via command line
- [ ] Can create/access databases

## 📚 Useful Aliases (Optional)

Add these to your PowerShell profile for quick access:

```powershell
# Add to $PROFILE
function mysql { & "C:\Program Files\MySQL\MySQL Server 8.4\bin\mysql.exe" @args }
function mysqldump { & "C:\Program Files\MySQL\MySQL Server 8.4\bin\mysqldump.exe" @args }
function mysqladmin { & "C:\Program Files\MySQL\MySQL Server 8.4\bin\mysqladmin.exe" @args }
```

Your MySQL is ready to use! 🎉
