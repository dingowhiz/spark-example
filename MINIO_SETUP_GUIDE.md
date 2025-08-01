# MinIO Installation and Setup Guide

## 🎉 Installation Completed Successfully!

MinIO Server and Client have been successfully installed on your Windows system.

## 📦 What's Installed

- **MinIO Server**: `RELEASE.2025-07-18T21-56-31Z` - High-performance object storage server
- **MinIO Client (mc)**: `RELEASE.2025-07-16T15-35-03Z` - Command-line administration tool
- **Data Directory**: `C:\minio-data\` (created)

## 🚀 Quick Start

### 1. Start MinIO Server
```powershell
# Basic start (uses default credentials)
minio server C:\minio-data

# Start with custom credentials
$env:MINIO_ROOT_USER = "admin"
$env:MINIO_ROOT_PASSWORD = "password123"
minio server C:\minio-data
```

### 2. Access MinIO Console
Once started, MinIO will be available at:
- **Server API**: `http://localhost:9000`
- **Web Console**: `http://localhost:9001` (if available)
- **Default Credentials**: `minioadmin` / `minioadmin`

### 3. Configure MinIO Client
```powershell
# Add local MinIO server as alias
mc alias set local http://localhost:9000 minioadmin minioadmin

# List configured aliases
mc alias list

# Test connection
mc admin info local
```

## 🛠️ Basic Operations

### Server Management
```powershell
# Start MinIO server with console
minio server C:\minio-data --console-address ":9001"

# Start with specific address and port
minio server C:\minio-data --address ":9000" --console-address ":9001"

# Start in background (use Task Scheduler or service)
Start-Process -FilePath "minio" -ArgumentList "server", "C:\minio-data" -WindowStyle Hidden
```

### Bucket Operations
```powershell
# Create a bucket
mc mb local/my-bucket

# List buckets
mc ls local

# Upload file to bucket
mc cp "path\to\file.txt" local/my-bucket/

# Download file from bucket
mc cp local/my-bucket/file.txt "C:\Downloads\"

# List objects in bucket
mc ls local/my-bucket

# Remove bucket (must be empty)
mc rb local/my-bucket
```

### User Management
```powershell
# Create new user
mc admin user add local newuser password123

# Create policy file (save as policy.json)
# {
#   "Version": "2012-10-17",
#   "Statement": [{
#     "Effect": "Allow",
#     "Action": ["s3:*"],
#     "Resource": ["arn:aws:s3:::my-bucket/*"]
#   }]
# }

# Add policy
mc admin policy add local my-policy policy.json

# Assign policy to user
mc admin policy set local my-policy user=newuser
```

## 🔧 Configuration Options

### Environment Variables
```powershell
# Set root credentials
$env:MINIO_ROOT_USER = "your-admin-username"
$env:MINIO_ROOT_PASSWORD = "your-secure-password"

# Set server name and region
$env:MINIO_SERVER_NAME = "MyMinIOServer"
$env:MINIO_REGION = "us-east-1"

# Enable console logging
$env:MINIO_CONSOLE_LOG = "true"
```

### Advanced Configuration
```powershell
# Start with SSL/TLS (requires certificates)
minio server C:\minio-data --certs-dir C:\minio-certs

# Start with specific bind address
minio server C:\minio-data --address "192.168.1.100:9000"

# Start with metrics enabled
minio server C:\minio-data --metrics-addr ":9090"
```

## 🌐 S3 Compatibility

MinIO is fully compatible with Amazon S3 APIs. Use these endpoints:

### Connection Parameters
| Parameter | Value | Description |
|-----------|--------|-------------|
| **Endpoint** | `http://localhost:9000` | MinIO server URL |
| **Access Key** | `minioadmin` (default) | User access key |
| **Secret Key** | `minioadmin` (default) | User secret key |
| **Region** | `us-east-1` (default) | Server region |
| **Path Style** | `true` | Use path-style URLs |

### AWS SDK Configuration
```powershell
# For AWS CLI
aws configure set aws_access_key_id minioadmin
aws configure set aws_secret_access_key minioadmin
aws configure set default.region us-east-1

# Test with AWS CLI
aws --endpoint-url http://localhost:9000 s3 ls
```

## 🔗 Integration Examples

### Scala/Spark Integration
Add to your `build.sbt`:
```scala
libraryDependencies ++= Seq(
  "com.amazonaws" % "aws-java-sdk-s3" % "1.12.500",
  "org.apache.hadoop" % "hadoop-aws" % "3.3.4"
)
```

Example Spark configuration:
```scala
val spark = SparkSession.builder()
  .appName("MinIO Example")
  .config("spark.hadoop.fs.s3a.endpoint", "http://localhost:9000")
  .config("spark.hadoop.fs.s3a.access.key", "minioadmin")
  .config("spark.hadoop.fs.s3a.secret.key", "minioadmin")
  .config("spark.hadoop.fs.s3a.path.style.access", "true")
  .config("spark.hadoop.fs.s3a.impl", "org.apache.hadoop.fs.s3a.S3AFileSystem")
  .getOrCreate()

// Read from MinIO
val df = spark.read.parquet("s3a://my-bucket/data.parquet")
```

### Python Integration
```python
from minio import Minio

# Create client
client = Minio(
    "localhost:9000",
    access_key="minioadmin",
    secret_key="minioadmin",
    secure=False
)

# List buckets
buckets = client.list_buckets()
for bucket in buckets:
    print(bucket.name)
```

## 🔍 Monitoring and Management

### Health Check
```powershell
# Check server health
mc admin info local

# Check server configuration
mc admin config get local

# Monitor server performance
mc admin prometheus metrics local
```

### Backup and Restore
```powershell
# Mirror data between MinIO instances
mc mirror local/source-bucket remote/target-bucket

# Backup bucket to local filesystem
mc mirror local/my-bucket C:\backups\my-bucket

# Restore from backup
mc mirror C:\backups\my-bucket local/restored-bucket
```

## 🚨 Troubleshooting

### Common Issues

1. **Port Already in Use**
   ```powershell
   # Check what's using port 9000
   netstat -an | findstr :9000
   
   # Use different port
   minio server C:\minio-data --address ":9002"
   ```

2. **Permission Errors**
   ```powershell
   # Check data directory permissions
   Get-Acl C:\minio-data
   
   # Fix permissions if needed
   icacls C:\minio-data /grant Everyone:F /T
   ```

3. **Connection Refused**
   - Ensure MinIO server is running
   - Check firewall settings
   - Verify endpoint URL is correct

### Log Analysis
```powershell
# MinIO logs are printed to console by default
# To save logs to file:
minio server C:\minio-data > C:\minio-logs\minio.log 2>&1
```

## 🎯 Production Considerations

### Security
- Change default credentials immediately
- Use HTTPS in production
- Implement proper IAM policies
- Regular security updates

### Performance
- Use SSD storage for better performance
- Configure appropriate server resources
- Monitor memory and CPU usage
- Implement load balancing for high availability

### Windows Service Setup
```powershell
# Create Windows service (requires admin rights)
sc create MinIOService binPath= "minio server C:\minio-data" start= auto
sc start MinIOService
```

## 📚 Useful Resources

- [MinIO Documentation](https://docs.min.io/)
- [MinIO Client Guide](https://docs.min.io/docs/minio-client-quickstart-guide.html)
- [S3 API Compatibility](https://docs.min.io/docs/minio-s3-compatibility.html)
- [MinIO SDK](https://docs.min.io/docs/minio-sdk-quickstart-guide.html)

## 🎯 Quick Start Checklist

- [ ] Start MinIO server
- [ ] Access web console at http://localhost:9001
- [ ] Configure MinIO client with `mc alias set`
- [ ] Create first bucket
- [ ] Upload test file
- [ ] Configure application integration
- [ ] Set up monitoring (optional)
- [ ] Configure backup strategy (optional)

Your MinIO installation is complete and ready for object storage operations! 🚀
