/*********SCRIPT di creazione tabelle******************/
CREATE TABLE IF NOT EXISTS `testcontech`.`fattura`
(
    `id`          int          NOT NULL AUTO_INCREMENT,
    `descrizione` varchar(255) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `testcontech`.`prodotto`
(
    `id`          int          NOT NULL AUTO_INCREMENT,
    `descrizione` varchar(255) NOT NULL,
    `prezzo`      double       NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `testcontech`.`fattura_prodotti`
(
    `id_fattura`  INT NOT NULL,
    `id_prodotto` INT NOT NULL,
    INDEX `fk_fattura_prodotti_fattura_idx` (`id_fattura` ASC) VISIBLE,
    INDEX `fk_fattura_prodotti_prodotto_idx` (`id_prodotto` ASC) VISIBLE,
    CONSTRAINT `fk_fattura_prodotti_fattura`
        FOREIGN KEY (`id_fattura`)
            REFERENCES `testcontech`.`fattura` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_fattura_prodotti_prodotto`
        FOREIGN KEY (`id_prodotto`)
            REFERENCES `testcontech`.`prodotto` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

/*
**************************************************************************************************************************
**************************************************************************************************************************
**************************************************************************************************************************
*/

/*********SP:p_crud_fattura******************/
USE `testcontech`;
DROP procedure IF EXISTS `testcontech`.p_crud_fattura;

DELIMITER $$
create procedure testcontech.p_crud_fattura(IN in_type_op varchar(50), IN in_id int,
                                            IN in_descrizione varchar(255))
BEGIN


    declare CONST_OP_CREATE varchar(50) default 'create';
    declare CONST_OP_UPDATE varchar(50) default 'edit_by_id';
    declare CONST_OP_DELETE varchar(50) default 'delete';

    case
        when CONST_OP_CREATE = in_type_op
            then insert into testcontech.fattura (descrizione)
                 values (in_descrizione);

        when CONST_OP_UPDATE = in_type_op
            then update testcontech.fattura f
                 set f.descrizione = in_descrizione
                 where f.id = in_id;

        when CONST_OP_DELETE = in_type_op
            then delete
                 from testcontech.fattura_prodotti f
                 where f.id_fattura = in_id;

                 delete
                 from testcontech.fattura f
                 where f.id = in_id;

        end case;


END
$$

/*
**************************************************************************************************************************
**************************************************************************************************************************
**************************************************************************************************************************
*/

/*********SP:p_get_fattura******************/
USE `testcontech`;
DROP procedure IF EXISTS `testcontech`.p_get_fattura;

DELIMITER $$
create procedure testcontech.p_get_fattura(IN in_type_op varchar(50), IN in_id int,
                                           IN in_descrizione varchar(255))
BEGIN

    declare CONST_OP_GET_ALL varchar(50) default 'get_all';
    declare CONST_OP_GET_BY_ID varchar(50) default 'get_by_id';
    declare CONST_OP_GET_BY_DESCRIZIONE varchar(50) default 'get_by_descrizione';

    case
        when CONST_OP_GET_ALL = in_type_op
            then select *
                 from testcontech.fattura f;

        when CONST_OP_GET_BY_ID = in_type_op
            then select *
                 from testcontech.fattura f
                 where f.id = in_id;

        when CONST_OP_GET_BY_DESCRIZIONE = in_type_op
            then select *
                 from testcontech.fattura f
                 where f.descrizione like in_descrizione;

        end case;


END
$$

/*
**************************************************************************************************************************
**************************************************************************************************************************
**************************************************************************************************************************
*/

/*********SP:p_crud_prodotto******************/
USE `testcontech`;
DROP procedure IF EXISTS `testcontech`.p_crud_prodotto;

DELIMITER $$
create procedure testcontech.p_crud_prodotto(IN in_type_op varchar(50), IN in_id int,
                                             IN in_descrizione varchar(255), IN in_prezzo double)
BEGIN


    declare CONST_OP_CREATE varchar(50) default 'create';
    declare CONST_OP_UPDATE varchar(50) default 'edit_by_id';
    declare CONST_OP_DELETE varchar(50) default 'delete';

    case
        when CONST_OP_CREATE = in_type_op
            then insert into testcontech.prodotto (descrizione, prezzo)
                 values (in_descrizione, in_prezzo);

        when CONST_OP_UPDATE = in_type_op
            then update testcontech.prodotto f
                 set f.descrizione = in_descrizione,
                     f.prezzo      = in_prezzo
                 where f.id = in_id;

        when CONST_OP_DELETE = in_type_op
            then delete
                 from testcontech.prodotto f
                 where f.id = in_id;

        end case;


END
$$

/*
**************************************************************************************************************************
**************************************************************************************************************************
**************************************************************************************************************************
*/

/*********SP:p_get_prodotto******************/
USE `testcontech`;
DROP procedure IF EXISTS `testcontech`.p_get_prodotto;

DELIMITER $$
create procedure testcontech.p_get_prodotto(IN in_type_op varchar(50), IN in_id int,
                                            IN in_descrizione varchar(255), IN in_prezzo double)
BEGIN

    declare CONST_OP_GET_ALL varchar(50) default 'get_all';
    declare CONST_OP_GET_BY_ID varchar(50) default 'get_by_id';
    declare CONST_OP_GET_BY_DESCRIZIONE varchar(50) default 'get_by_descrizione';

    case
        when CONST_OP_GET_ALL = in_type_op
            then select *
                 from testcontech.prodotto f;

        when CONST_OP_GET_BY_ID = in_type_op
            then select *
                 from testcontech.prodotto f
                 where f.id = in_id;

        when CONST_OP_GET_BY_DESCRIZIONE = in_type_op
            then select *
                 from testcontech.prodotto f
                 where f.descrizione like in_descrizione;

        end case;


END
$$

/*
**************************************************************************************************************************
**************************************************************************************************************************
**************************************************************************************************************************
*/

/*********SP:p_crud_fattura_prodotti******************/
USE `testcontech`;
DROP procedure IF EXISTS `testcontech`.p_crud_fattura_prodotti;

DELIMITER $$
create procedure testcontech.p_crud_fattura_prodotti(IN in_type_op varchar(50), IN in_id_fattura int,
                                                     IN in_id_prodotto int)
BEGIN


    declare CONST_OP_CLEAR varchar(50) default 'clear_products';
    declare CONST_OP_EDIT_PRODUCTS varchar(50) default 'edit_products';

    case
        when CONST_OP_CLEAR = in_type_op
            then delete
                 from testcontech.fattura_prodotti fp
                 where fp.id_fattura = in_id_fattura;

        when CONST_OP_EDIT_PRODUCTS = in_type_op
            then insert into testcontech.fattura_prodotti (id_fattura, id_prodotto)
                 values (in_id_fattura, in_id_prodotto);

        end case;


END
$$

/*
**************************************************************************************************************************
**************************************************************************************************************************
**************************************************************************************************************************
*/

/*********SP:p_get_fattura_prodotti******************/
USE `testcontech`;
DROP procedure IF EXISTS `testcontech`.p_get_fattura_prodotti;

DELIMITER $$
create procedure testcontech.p_get_fattura_prodotti(IN in_type_op varchar(50), IN in_id int)
BEGIN


    declare CONST_OP_GET_BY_ID_FATTURA varchar(50) default 'get_by_id_id_fattura';

    case
        when CONST_OP_GET_BY_ID_FATTURA = in_type_op
            then select *
                 from testcontech.fattura_prodotti fp
                 where fp.id_fattura = in_id;

        end case;


END
$$

/*
**************************************************************************************************************************
**************************************************************************************************************************
**************************************************************************************************************************
*/