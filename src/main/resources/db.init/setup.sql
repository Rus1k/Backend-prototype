CREATE DATABASE prototype;
CREATE USER 'prototype'@'localhost' IDENTIFIED BY 'prototype';
grant all privileges on prototype.* to 'prototype'@'localhost';