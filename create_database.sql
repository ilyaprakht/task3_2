CREATE DATABASE task3 DEFAULT CHARACTER SET utf8;
CREATE TABLE weather (
  city varchar(100) NOT NULL,
  temp varchar(10) NOT NULL,
  text varchar(10) NOT NULL,
  PRIMARY KEY (city)
) ENGINE=InnoDB;