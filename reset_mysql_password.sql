-- MySQL Password Reset Script
USE mysql;
ALTER USER 'root'@'localhost' IDENTIFIED BY 'dundee';
FLUSH PRIVILEGES;
