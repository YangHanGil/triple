-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema TRIPLE
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema TRIPLE
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `TRIPLE` DEFAULT CHARACTER SET utf8 ;
USE `TRIPLE` ;

-- -----------------------------------------------------
-- Table `TRIPLE`.`T_USER`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TRIPLE`.`T_USER` (
  `F_SEQ` INT NOT NULL AUTO_INCREMENT,
  `F_ID` VARCHAR(200) NOT NULL,
  `F_PASS` VARCHAR(200) NOT NULL,
  `F_NAME` VARCHAR(45) NOT NULL,
  `F_REG_TIME` DATETIME NOT NULL,
  `F_STATUS` VARCHAR(2) NOT NULL,
  PRIMARY KEY (`F_SEQ`),
  UNIQUE INDEX `F_ID_UNIQUE` (`F_ID` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TRIPLE`.`T_PLACE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TRIPLE`.`T_PLACE` (
  `F_SEQ` INT NOT NULL AUTO_INCREMENT,
  `F_PLACE` VARCHAR(200) NOT NULL,
  `F_ADD` VARCHAR(200) NOT NULL,
  `F_REG_TIME` DATETIME NOT NULL,
  `F_STATUS` VARCHAR(2) NOT NULL,
  PRIMARY KEY (`F_SEQ`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TRIPLE`.`T_REVIEW`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TRIPLE`.`T_REVIEW` (
  `F_SEQ` INT NOT NULL AUTO_INCREMENT,
  `F_ID` VARCHAR(200) NOT NULL,
  `F_PLACE_SEQ` INT NOT NULL,
  `F_CONTENT` VARCHAR(500) NOT NULL,
  `F_PHOTO` VARCHAR(1000) NOT NULL,
  `F_REG_TIME` DATETIME NOT NULL,
  `F_STATUS` VARCHAR(2) NOT NULL,
  PRIMARY KEY (`F_SEQ`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TRIPLE`.`T_MILEGE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TRIPLE`.`T_MILEGE` (
  `F_SEQ` INT NOT NULL AUTO_INCREMENT,
  `F_REVIEW_SEQ` INT NOT NULL,
  `F_MILEGE` INT NOT NULL,
  `F_STATUS` VARCHAR(2) NOT NULL,
  `F_TOTAL` INT NOT NULL,
  `F_REG_TIME` DATETIME NOT NULL,
  PRIMARY KEY (`F_SEQ`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
