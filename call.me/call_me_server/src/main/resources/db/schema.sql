CREATE DATABASE IF NOT EXISTS `call_me`
CHARACTER SET 'utf8'
COLLATE 'utf8_general_ci';

CREATE TABLE `call_me`.`ATTACHMENT`(
  `id` INTEGER(11) UNSIGNED NOT NULL AUTO_INCREMENT UNIQUE,
  `uploaded` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `file` BLOB NOT NULL,
  `deletion_date` DATETIME
) ENGINE=InnoDB CHARACTER SET=utf8 COLLATE utf8_bin;


CREATE TABLE `call_me`.`PHONE` (
  `id` INTEGER(11) UNSIGNED NOT NULL AUTO_INCREMENT UNIQUE,
  `countyCode` INTEGER(3) NOT NULL,
  `operatorCode` INTEGER(2) NOT NULL,
  `phoneNumber` INTEGER(7) NOT NULL,
  `comment` VARCHAR(256) NOT NULL,
  `deletion_date` DATETIME
) ENGINE=InnoDB CHARACTER SET=utf8 COLLATE utf8_bin;


CREATE TABLE `call_me`.`CONTACT` (
  `id` INTEGER(11) UNSIGNED NOT NULL AUTO_INCREMENT UNIQUE,
  `firstName` VARCHAR(25) NOT NULL,
  `secondName` VARCHAR(25) NOT NULL,
  `surname` VARCHAR(25) NOT NULL,
  `birthday` DATE NOT NULL,
  `gender` ENUM('', '1', '2'),
  `citizenship` VARCHAR(256) NOT NULL,
  `materialStatus` ENUM('MARRIED', 'SINGLE', 'DIVORCED', 'WIDOWED'),
  `website` VARCHAR(256) NOT NULL,
  `job` VARCHAR(256) NOT NULL,
  `country` VARCHAR(11) NOT NULL,
  `city` VARCHAR(11) NOT NULL,
  `street` VARCHAR(56) NOT NULL,
  `zipCode` INTEGER(9) NOT NULL,
  `apartment` VARCHAR(256) NOT NULL,
  `deletion_date` DATETIME

) ENGINE=InnoDB CHARACTER SET=utf8 COLLATE utf8_bin;


ALTER TABLE `call_me`.`ATTACHMENT` ADD COLUMN `contact_id` INT(11) UNSIGNED NOT NULL  AFTER `file` ,
ADD CONSTRAINT `contact_attachment_fk`
FOREIGN KEY (`contact_id`)
REFERENCES `call_me`.`CONTACT` (`id`)
ON DELETE CASCADE
ON UPDATE RESTRICT
, ADD INDEX `contact_attachment_fk_idx` (`contact_id` ASC);


ALTER TABLE `call_me`.`PHONE` ADD COLUMN `contact_id` INT(11) UNSIGNED NOT NULL  AFTER `phoneNumber` ,
ADD CONSTRAINT `contact_phone_fk`
FOREIGN KEY (`contact_id`)
REFERENCES `call_me`.`CONTACT` (`id` )
ON DELETE CASCADE
ON UPDATE RESTRICT
, ADD INDEX `contact_phone_fk_idx` (`contact_id` ASC);
