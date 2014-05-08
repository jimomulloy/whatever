CREATE DATABASE IF NOT EXISTS wtw;
GRANT ALL PRIVILEGES ON wtw.* TO wtw@localhost IDENTIFIED BY 'wtw';

USE wtw;

CREATE TABLE IF NOT EXISTS LOCATION (
  ZIP VARCHAR(255) NOT NULL PRIMARY KEY,
  CITY VARCHAR(255),
  COUNTRY VARCHAR(255),
  REGION VARCHAR(255)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS WEATHER (
  ID INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  DATE TIMESTAMP,
  LOCATION_ZIP VARCHAR(255),
  FOREIGN KEY(LOCATION_ZIP) REFERENCES LOCATION(ZIP)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS WIND (
  ID INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  CHILL VARCHAR(255),
  DIRECTION VARCHAR(255),
  SPEED VARCHAR(255),
  WEATHER_ID INT(4)UNSIGNED NOT NULL,
  FOREIGN KEY (WEATHER_ID) REFERENCES WEATHER(ID)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS ATMOSPHERE (
  ID INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  HUMIDITY VARCHAR(255),
  PRESSURE VARCHAR(255),
  RISING VARCHAR(255),
  VISIBILITY VARCHAR(255),
  WEATHER_ID INT(4) UNSIGNED NOT NULL,
  FOREIGN KEY (WEATHER_ID) REFERENCES WEATHER(ID)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS WEATHERCONDITION (
  ID INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  CODE VARCHAR(255),
  DATE TIMESTAMP,
  TEMP VARCHAR(255),
  TEXT VARCHAR(255),
  WEATHER_ID INT(4) UNSIGNED NOT NULL,
  FOREIGN KEY (WEATHER_ID) REFERENCES WEATHER(ID)
) engine=InnoDB;