CREATE DATABASE vitalsport;
CREATE USER 'vitalsport'@'localhost' IDENTIFIED BY 'vitalsport';
grant all privileges on vitalsport.* to 'vitalsport'@'localhost';