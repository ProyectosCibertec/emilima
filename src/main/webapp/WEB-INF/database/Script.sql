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
-- Table `emilima`.`file`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `emilima`.`file` ;

CREATE TABLE IF NOT EXISTS `emilima`.`file` (
  `id` VARCHAR(48),
  `filename` TEXT NOT NULL,
  PRIMARY KEY (`id`))
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
  `photo_id` VARCHAR(48) NULL DEFAULT 'c4042c2a-f106-11ec-8ea0-0242ac120002',
  PRIMARY KEY (`username`),
  INDEX `role_idx` (`role_id` ASC) VISIBLE,
  INDEX `photo_idx` (`photo_id` ASC) VISIBLE,
  CONSTRAINT `role`
    FOREIGN KEY (`role_id`)
    REFERENCES `emilima`.`role` (`id`)
    ON DELETE set null
    ON UPDATE set null,
  CONSTRAINT `photo`
    FOREIGN KEY (`photo_id`)
    REFERENCES `emilima`.`file` (`id`)
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
  `file_id` VARCHAR(48) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE,
  INDEX `fk_document_file_idx` (`file_id` ASC) VISIBLE,
  CONSTRAINT `fk_document_file`
    FOREIGN KEY (`file_id`)
    REFERENCES `emilima`.`file` (`id`)
    ON DELETE set null
    ON UPDATE set null)
ENGINE = InnoDB character set = latin1 collate = latin1_spanish_ci;


-- -----------------------------------------------------
-- Table `emilima`.`request_state`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `emilima`.`request_state` ;

CREATE TABLE IF NOT EXISTS `emilima`.`request_state` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
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
  `state_id` INT,
  `user_id` VARCHAR(45),
  `document_id` INT,
  PRIMARY KEY (`id`),
  INDEX `user_id_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_request_document1_idx` (`document_id` ASC) VISIBLE,
  INDEX `fk_request_request_state_idx` (`state_id` ASC) VISIBLE,
  CONSTRAINT `fk_request_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `emilima`.`user` (`username`)
    ON DELETE set null
    ON UPDATE set null,
  CONSTRAINT `fk_request_document`
    FOREIGN KEY (`document_id`)
    REFERENCES `emilima`.`document` (`id`)
    ON DELETE set null
    ON UPDATE set null,
  CONSTRAINT `fk_request_request_state`
    FOREIGN KEY (`state_id`)
    REFERENCES `emilima`.`request_state` (`id`)
    ON DELETE set null
    ON UPDATE set null)
ENGINE = InnoDB character set = latin1 collate = latin1_spanish_ci;


INSERT INTO `request_state`(`name`) VALUES ("PENDIENTE");
INSERT INTO `request_state`(`name`) VALUES ("VALIDADA");
INSERT INTO `request_state`(`name`) VALUES ("AUTORIZADA");
INSERT INTO `request_state`(`name`) VALUES ("ATENDIDA");

INSERT INTO `file`(`id`, `filename`) VALUES ("dca3a58c-ef10-11ec-8ea0-0242ac120002", "ejemplo.pdf");
INSERT INTO `file`(`id`, `filename`) VALUES ("e2d96144-ef10-11ec-8ea0-0242ac120002", "ejemplo.pdf");
INSERT INTO `file`(`id`, `filename`) VALUES ("eb535816-ef10-11ec-8ea0-0242ac120002", "ejemplo.pdf");
INSERT INTO `file`(`id`, `filename`) VALUES ("f0783f8c-ef10-11ec-8ea0-0242ac120002", "ejemplo.pdf");
INSERT INTO `file`(`id`, `filename`) VALUES ("c4042c2a-f106-11ec-8ea0-0242ac120002", "user-photo-default.png");

INSERT INTO `document`(`name`, `description`, `upload_date`, `file_id`) VALUES ("Documento 1", "Este documento contiene información de un inmueble", "2022-03-03", "dca3a58c-ef10-11ec-8ea0-0242ac120002");
INSERT INTO `document`(`name`, `description`, `upload_date`, `file_id`) VALUES ("Documento 2", "Este documento contiene información de un inmueble", "2022-03-03", "e2d96144-ef10-11ec-8ea0-0242ac120002");
INSERT INTO `document`(`name`, `description`, `upload_date`, `file_id`) VALUES ("Documento 3", "Este documento contiene información de un inmueble", "2022-03-03", "eb535816-ef10-11ec-8ea0-0242ac120002");

INSERT INTO `role`(`name`, `description`) VALUES ("Administrador", "Usuario con permisos globales.");
INSERT INTO `role`(`name`, `description`) VALUES ("Unidad orgánica", "Usuario con capacidad de registrar solicitudes de documentación.");
INSERT INTO `role`(`name`, `description`) VALUES ("Técnico", "Usuario con capacidad de administrar solicitudes y documentos.");
INSERT INTO `role`(`name`, `description`) VALUES ("Secretario general", "Usuario con capacidad de autorizar solicitudes y administrar las entidades.");

INSERT INTO `user`(`username`, `password`, `email`, `role_id`) VALUES ("admin", "admin", "admin@emilima.com.pe", 1);
INSERT INTO `user`(`username`, `password`, `email`, `role_id`) VALUES ("admin1", "admin", "admin@emilima.com.pe", 1);
INSERT INTO `user`(`username`, `password`, `email`, `role_id`) VALUES ("user", "admin", "admin@emilima.com.pe", 2);

INSERT INTO `request`(`name`, `description`, `creation_date`, `state_id`, `user_id`, `document_id`) VALUES ("Solicitud de documentación", "Solicitud de documentación", "2022-03-03", 1, "admin", 1);
INSERT INTO `request`(`name`, `description`, `creation_date`, `state_id`, `user_id`, `document_id`) VALUES ("Solicitud de documentación 1", "Solicitud de documentación", "2022-03-03", 2, "admin", 1);
INSERT INTO `request`(`name`, `description`, `creation_date`, `state_id`, `user_id`, `document_id`) VALUES ("Solicitud de documentación 2", "Solicitud de documentación", "2022-03-03", 3, "admin", 1);

SELECT * FROM `document`;
SELECT * FROM `role`;
SELECT * FROM `user`;
SELECT * FROM `request`;
SELECT * FROM `request_state`;
SELECT * FROM `file`;