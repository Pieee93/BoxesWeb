-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema boxesweb
-- -----------------------------------------------------
-- Schema per BoxesWeb
DROP SCHEMA IF EXISTS `boxesweb` ;

-- -----------------------------------------------------
-- Schema boxesweb
--
-- Schema per BoxesWeb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `boxesweb` DEFAULT CHARACTER SET utf8 ;
USE `boxesweb` ;

-- -----------------------------------------------------
-- Table `boxesweb`.`Utente`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `boxesweb`.`Utente` ;

CREATE TABLE IF NOT EXISTS `boxesweb`.`Utente` (
  `idUtente` INT NOT NULL AUTO_INCREMENT,
  `nomeUtente` VARCHAR(45) NOT NULL,
  `cognomeUtente` VARCHAR(45) NOT NULL,
  `emailUtente` VARCHAR(45) NOT NULL,
  `isAdmin` TINYINT ZEROFILL NOT NULL,
  `indirizzo` VARCHAR(45) NOT NULL,
  `città_provincia` VARCHAR(45) NOT NULL,
  `cap` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idUtente`),
  UNIQUE INDEX `idUtente_UNIQUE` (`idUtente` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `boxesweb`.`Ordine`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `boxesweb`.`Ordine` ;

CREATE TABLE IF NOT EXISTS `boxesweb`.`Ordine` (
  `idOrdine` INT NOT NULL AUTO_INCREMENT,
  `ammonatre` DECIMAL(6,2) NOT NULL,
  `dataCreazione` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `numeroConfermaOridne` VARCHAR(45) NOT NULL,
  `Utente_idUtente` INT NOT NULL,
  PRIMARY KEY (`idOrdine`, `Utente_idUtente`),
  UNIQUE INDEX `idOrdine_UNIQUE` (`idOrdine` ASC),
  INDEX `fk_Ordine_Utente1_idx` (`Utente_idUtente` ASC),
  CONSTRAINT `fk_Ordine_Utente1`
    FOREIGN KEY (`Utente_idUtente`)
    REFERENCES `boxesweb`.`Utente` (`idUtente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `boxesweb`.`Box`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `boxesweb`.`Box` ;

CREATE TABLE IF NOT EXISTS `boxesweb`.`Box` (
  `idBox` INT NOT NULL AUTO_INCREMENT,
  `tipoDiBox` VARCHAR(45) NOT NULL,
  `numeroProdotti` INT NOT NULL,
  `nomeBox` VARCHAR(45) NOT NULL,
  `punteggioBox` DECIMAL(5,1) NOT NULL,
  `descrizioneBox` MEDIUMTEXT NOT NULL,
  `prezzoBox` DECIMAL(5,2) NOT NULL,
  PRIMARY KEY (`idBox`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `boxesweb`.`Prodotto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `boxesweb`.`Prodotto` ;

CREATE TABLE IF NOT EXISTS `boxesweb`.`Prodotto` (
  `idProdotto` INT NOT NULL AUTO_INCREMENT,
  `nomeProdotto` VARCHAR(45) NOT NULL,
  `punteggio` DECIMAL(5,2) NOT NULL,
  `prezzo` DECIMAL(5,2) NOT NULL,
  `descrizioneProdotto` MEDIUMTEXT NOT NULL,
  `Box_idBox` INT NOT NULL,
  PRIMARY KEY (`idProdotto`, `Box_idBox`),
  INDEX `fk_Prodotto_Box1_idx` (`Box_idBox` ASC),
  CONSTRAINT `fk_Prodotto_Box1`
    FOREIGN KEY (`Box_idBox`)
    REFERENCES `boxesweb`.`Box` (`idBox`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `boxesweb`.`Prodotto_has_Box`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `boxesweb`.`Prodotto_has_Box` ;

CREATE TABLE IF NOT EXISTS `boxesweb`.`Prodotto_has_Box` (
  `Prodotto_idProdotto` INT NOT NULL,
  `Box_idBox` INT NOT NULL,
  PRIMARY KEY (`Prodotto_idProdotto`, `Box_idBox`),
  INDEX `fk_Prodotto_has_Box_Box1_idx` (`Box_idBox` ASC),
  INDEX `fk_Prodotto_has_Box_Prodotto_idx` (`Prodotto_idProdotto` ASC),
  CONSTRAINT `fk_Prodotto_has_Box_Prodotto`
    FOREIGN KEY (`Prodotto_idProdotto`)
    REFERENCES `boxesweb`.`Prodotto` (`idProdotto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Prodotto_has_Box_Box1`
    FOREIGN KEY (`Box_idBox`)
    REFERENCES `boxesweb`.`Box` (`idBox`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `boxesweb`.`Prodotti_in_Box`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `boxesweb`.`Prodotti_in_Box` ;

CREATE TABLE IF NOT EXISTS `boxesweb`.`Prodotti_in_Box` (
  `Box_idBox` INT NOT NULL,
  `Prodotto_idProdotto` INT NOT NULL,
  PRIMARY KEY (`Box_idBox`, `Prodotto_idProdotto`),
  INDEX `fk_Box_has_Prodotto_Prodotto1_idx` (`Prodotto_idProdotto` ASC),
  INDEX `fk_Box_has_Prodotto_Box1_idx` (`Box_idBox` ASC),
  CONSTRAINT `fk_Box_has_Prodotto_Box1`
    FOREIGN KEY (`Box_idBox`)
    REFERENCES `boxesweb`.`Box` (`idBox`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Box_has_Prodotto_Prodotto1`
    FOREIGN KEY (`Prodotto_idProdotto`)
    REFERENCES `boxesweb`.`Prodotto` (`idProdotto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `boxesweb`.`Box_ordinate`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `boxesweb`.`Box_ordinate` ;

CREATE TABLE IF NOT EXISTS `boxesweb`.`Box_ordinate` (
  `Ordine_idOrdine` INT NOT NULL,
  `Box_idBox` INT NOT NULL,
  PRIMARY KEY (`Ordine_idOrdine`, `Box_idBox`),
  INDEX `fk_Ordine_has_Box_Box1_idx` (`Box_idBox` ASC),
  INDEX `fk_Ordine_has_Box_Ordine1_idx` (`Ordine_idOrdine` ASC),
  CONSTRAINT `fk_Box_ordinate_Ordine1`
    FOREIGN KEY (`Ordine_idOrdine`)
    REFERENCES `boxesweb`.`Ordine` (`idOrdine`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Box_ordinate_Box1`
    FOREIGN KEY (`Box_idBox`)
    REFERENCES `boxesweb`.`Box` (`idBox`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
