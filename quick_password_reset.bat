@echo off
echo Resetting MySQL password to 'dundee'...

REM Stop MySQL service
net stop MySQL84

REM Start MySQL in safe mode
cd /d "C:\Program Files\MySQL\MySQL Server 8.4\bin"
start "MySQL Safe Mode" mysqld --skip-grant-tables --skip-networking

REM Wait for startup
timeout /t 10

REM Reset password
echo ALTER USER 'root'@'localhost' IDENTIFIED BY 'dundee'; FLUSH PRIVILEGES; | mysql -u root

REM Stop safe mode
taskkill /f /im mysqld.exe

REM Start normal service
net start MySQL84

echo Password reset to 'dundee' complete!
pause
