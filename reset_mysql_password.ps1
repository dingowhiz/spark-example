# MySQL Password Reset PowerShell Script
Write-Host "Starting MySQL password reset process..." -ForegroundColor Green

# Navigate to MySQL bin directory
Set-Location "C:\Program Files\MySQL\MySQL Server 8.4\bin"

# Start MySQL in safe mode (skip password validation)
Write-Host "Starting MySQL in safe mode..." -ForegroundColor Yellow
Start-Process mysqld -ArgumentList "--skip-grant-tables", "--skip-networking" -WindowStyle Hidden

# Wait for MySQL to start
Start-Sleep -Seconds 10

try {
    # Execute password reset
    Write-Host "Executing password reset..." -ForegroundColor Yellow
    $resetSQL = @"
USE mysql;
ALTER USER 'root'@'localhost' IDENTIFIED BY 'newpassword123';
FLUSH PRIVILEGES;
"@
    
    $resetSQL | mysql -u root
    Write-Host "Password reset completed successfully!" -ForegroundColor Green
    
} catch {
    Write-Host "Error during password reset: $($_.Exception.Message)" -ForegroundColor Red
}

# Stop MySQL safe mode
Write-Host "Stopping MySQL safe mode..." -ForegroundColor Yellow
Get-Process mysqld -ErrorAction SilentlyContinue | Stop-Process -Force

# Start MySQL service normally
Write-Host "Starting MySQL service normally..." -ForegroundColor Yellow
Start-Service MySQL84

Write-Host "`nPassword Reset Complete!" -ForegroundColor Green
Write-Host "New password: newpassword123" -ForegroundColor Cyan
Write-Host "Test connection with: mysql -u root -p" -ForegroundColor Cyan
