# MinIO Aliases for PowerShell
# Add these to your PowerShell profile for easy access

# MinIO Server alias
function Start-MinIO {
    param(
        [string]$DataPath = "C:\minio-data",
        [string]$ConsolePort = "9001",
        [string]$ApiPort = "9000"
    )
    & "C:\Users\dingo\AppData\Local\Microsoft\WinGet\Packages\MinIO.Server_Microsoft.Winget.Source_8wekyb3d8bbwe\minio.exe" server $DataPath --console-address ":$ConsolePort" --address ":$ApiPort"
}

# MinIO Client alias
function mc {
    & "C:\Users\dingo\AppData\Local\Microsoft\WinGet\Packages\MinIO.Client_Microsoft.Winget.Source_8wekyb3d8bbwe\mc.exe" @args
}

# MinIO Server alias
function minio {
    & "C:\Users\dingo\AppData\Local\Microsoft\WinGet\Packages\MinIO.Server_Microsoft.Winget.Source_8wekyb3d8bbwe\minio.exe" @args
}

Write-Host "MinIO aliases loaded successfully!" -ForegroundColor Green
Write-Host "Use 'Start-MinIO' to start the server" -ForegroundColor Yellow
Write-Host "Use 'mc' for MinIO client commands" -ForegroundColor Yellow
Write-Host "Use 'minio' for MinIO server commands" -ForegroundColor Yellow
