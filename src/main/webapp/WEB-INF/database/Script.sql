-- -----------------------------------------------------
-- Schema emilima
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `emilima`;
CREATE SCHEMA IF NOT EXISTS `emilima` DEFAULT CHARACTER SET utf8 ;
USE `emilima` ;

-- -----------------------------------------------------
-- Table `emilima`.`role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `emilima`.`role` ;

CREATE TABLE IF NOT EXISTS `emilima`.`role` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` TEXT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB character set = latin1 collate = latin1_spanish_ci;


-- -----------------------------------------------------
-- Table `emilima`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `emilima`.`user` ;

CREATE TABLE IF NOT EXISTS `emilima`.`user` (
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `email` VARCHAR(100) NULL,
  `role_id` INT NULL,
  PRIMARY KEY (`username`),
  INDEX `role_idx` (`role_id` ASC) VISIBLE,
  CONSTRAINT `role`
    FOREIGN KEY (`role_id`)
    REFERENCES `emilima`.`role` (`id`)
    ON DELETE set null
    ON UPDATE set null)
ENGINE = InnoDB character set = latin1 collate = latin1_spanish_ci;


-- -----------------------------------------------------
-- Table `emilima`.`request`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `emilima`.`request` ;

CREATE TABLE IF NOT EXISTS `emilima`.`request` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` TEXT NULL,
  `creation_date` DATETIME NULL,
  `state` CHAR(1) NULL,
  `user_id` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  INDEX `user_id_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `emilima`.`user` (`username`)
    ON DELETE set null
    ON UPDATE set null)
ENGINE = InnoDB character set = latin1 collate = latin1_spanish_ci;


-- -----------------------------------------------------
-- Table `emilima`.`document`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `emilima`.`document` ;

CREATE TABLE IF NOT EXISTS `emilima`.`document` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` TEXT NULL,
  `upload_date` DATETIME NULL,
  `request_id` INT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE,
  INDEX `request_id_idx` (`request_id` ASC) VISIBLE,
  CONSTRAINT `request_id`
    FOREIGN KEY (`request_id`)
    REFERENCES `emilima`.`request` (`id`)
    ON DELETE set null
    ON UPDATE set null)
ENGINE = InnoDB character set = latin1 collate = latin1_spanish_ci;

INSERT INTO document(`name`, `description`, `upload_date`, `request_id`) VALUES ("asdf", "asdf", "2022-03-03", NULL);
INSERT INTO document(`name`, `description`, `upload_date`, `request_id`) VALUES ("asdfa", "asdf", "2022-03-03", NULL);

SELECT * FROM document;