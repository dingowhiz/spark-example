@echo off
echo Starting MySQL password reset process...

REM Stop MySQL service first
echo Stopping MySQL service...
net stop MySQL84

REM Navigate to MySQL bin directory
cd /d "C:\Program Files\MySQL\MySQL Server 8.4\bin"

echo Starting MySQL in safe mode...
start "MySQL Safe Mode" mysqld --skip-grant-tables --skip-networking

echo Waiting for MySQL to start...
timeout /t 10

echo Executing password reset...
mysql -u root < "C:\DATA\SCALA\spark-example\reset_mysql_password.sql"

echo Password reset completed!
echo Stopping MySQL safe mode...
taskkill /f /im mysqld.exe

echo Starting MySQL service normally...
net start MySQL84

echo Password has been changed to: dundee
echo You can now connect using: mysql -u root -p
pause
