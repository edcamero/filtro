DROP TABLE IF EXISTS USUARIO;
DROP TABLE IF EXISTS TIPO_USUARIO;
DROP TABLE IF EXISTS TECNICO;
DROP TABLE IF EXISTS CUSTOMER;
DROP TABLE IF EXISTS DIVICE;
DROP TABLE IF EXISTS SPARK_PLUG;
DROP TABLE IF EXISTS SPARE;
DROP TABLE IF EXISTS TYPE_SPARE;

CREATE TABLE TIPO_USUARIO(
    tius_id SERIAL,
    tius_name VARCHAR(40) NOT NULL,
    tius_descripcion VARCHAR(100) NOT NULL,
	createAt timestamp DEFAULT now(),
	updateAt timestamp DEFAULT now(),
    CONSTRAINT tipo_usuario_pk PRIMARY KEY(tius_id)
);


CREATE TABLE USUARIO
(
    user_id SERIAL,
    user_name VARCHAR(40) NOT NULL UNIQUE,
    user_password VARCHAR(40) NOT NULL,
	tius_id INT NOT NULL,
    user_status BOOLEAN NOT NULL DEFAULT TRUE,
	createAt timestamp DEFAULT now(),
	updateAt timestamp DEFAULT now(),
    CONSTRAINT usuario_pk PRIMARY KEY
        ( user_id ),
	CONSTRAINT user_tius_fk foreign key(tius_id) references TIPO_USUARIO(tius_id)
 );

CREATE TABLE TECNICO
(
    tecn_id SERIAL ,
    tecn_document VARCHAR(12) NOT NULL UNIQUE,
    tecn_name VARCHAR(40) NOT NULL,
    tecn_telephone VARCHAR(12) NOT NULL,
    tecn_status BOOLEAN
    NOT NULL DEFAULT TRUE,
	createAt timestamp DEFAULT now(),
	updateAt timestamp DEFAULT now(),
    CONSTRAINT tecnico_pk PRIMARY KEY
        (tecn_id)
);

CREATE TABLE  CUSTOMER
(
    cust_id SERIAL ,
    cust_document VARCHAR(12) NOT NULL UNIQUE,
    cust_name VARCHAR(50) NOT NULL,
    cust_telephone_one VARCHAR(12) NOT NULL,
    cust_telephone_two VARCHAR(12) NOT NULL,
    cust_address VARCHAR(12) NOT NULL,
    cust_email VARCHAR(50) NOT NULL,
    cust_status BOOLEAN
    NOT NULL DEFAULT TRUE,	
	createAt timestamp DEFAULT now(),
	updateAt timestamp DEFAULT now(),
    CONSTRAINT customer_pk PRIMARY KEY
        (cust_id)
);

CREATE TABLE  DIVICE
(
    divi_id SERIAL ,
    divi_material VARCHAR(12) NOT NULL ,
    divi_model VARCHAR(20) NOT NULL,
    divi_name VARCHAR(20) NOT NULL,
    divi_cost INTEGER NOT NULL,
    divi_price INTEGER NOT NULL,
    divi_available BOOLEAN NOT NULL,
    divi_status BOOLEAN
    NOT NULL DEFAULT TRUE,
	createAt timestamp DEFAULT now(),
	updateAt timestamp DEFAULT now(),
    CONSTRAINT divice_pk PRIMARY KEY
        (divi_id)
);
CREATE TABLE TYPE_SPARE
(
    tysp_id SERIAL,
    tysp_name VARCHAR NOT NULL UNIQUE,
	createAt timestamp DEFAULT now(),
	updateAt timestamp DEFAULT now(),
    CONSTRAINT type_spare_pk PRIMARY KEY 
        (tysp_id)
);
CREATE TABLE  SPARE
(
    spar_id SERIAL ,
    spar_name VARCHAR(100) NOT NULL,
    spar_cost INTEGER NOT NULL DEFAULT 0,
    spar_price_without_iva INTEGER NOT NULL,
	iva INTEGER NOT NULL,
	spar_price_with_iva INTEGER NOT NULL,
    tysp_id INTEGER NOT NULL,
    spar_status BOOLEAN
    NOT NULL DEFAULT TRUE,
	createAt timestamp DEFAULT now(),
	updateAt timestamp DEFAULT now(),
    CONSTRAINT spare_pk PRIMARY KEY
        (spar_id),
    CONSTRAINT spar_type FOREIGN KEY (tysp_id ) REFERENCES TYPE_SPARE(tysp_id)
);
CREATE TABLE SPARK_PLUG
(
    sppl_id SERIAL,
    spar_id INTEGER NOT NULL,
    sppl_useful_life INTEGER NOT NULL,
	createAt timestamp DEFAULT now(),
	updateAt timestamp DEFAULT now(),
    CONSTRAINT spark_plug_pk PRIMARY KEY
        (sppl_id),
    CONSTRAINT sppl_spar_fk FOREIGN KEY (spar_id) REFERENCES SPARE(spar_id)
);

insert into TIPO_USUARIO  (tius_name,    tius_descripcion) values ('admin','administrados del sistema');
insert into TIPO_USUARIO  (tius_name,    tius_descripcion) values ('secretaria','operaciones basicas');
insert into USUARIO (user_name,user_password,tius_id) values ('admin','0cfc5b81354c34dca4122586d754e813',1);
insert into USUARIO (user_name,user_password,tius_id) values ('secretaria','e10adc3949ba59abbe56e057f20f883e',2);
insert into TYPE_SPARE ( tysp_name)values ('ABRAZADERA'),('BUJIA'),('ELECTRICO'),('OTROS');
--copy spare (spar_name,spar_cost,spar_price_without_iva,iva,spar_price_with_iva,tysp_id) from 
