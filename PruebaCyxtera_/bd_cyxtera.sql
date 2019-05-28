	
-- SCHEMA: operaciones

-- DROP SCHEMA operaciones ;

CREATE SCHEMA operaciones
    AUTHORIZATION postgres;

COMMENT ON SCHEMA operaciones
    IS 'Auditoria de operaciones realizadas';


-- Table: operaciones."Auditoria"

-- DROP TABLE operaciones."Auditoria";

CREATE TABLE operaciones."Auditoria"
(
    "idAuditoria" serial NOT NULL,
    "Usuario" character(200) COLLATE pg_catalog."default" NOT NULL,
    "Transaccion" character(1) COLLATE pg_catalog."default" NOT NULL,
    "FechaCreacion" date NOT NULL,
    "Servicio" character(200) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT "pkAuditoria" PRIMARY KEY ("idAuditoria")
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE operaciones."Auditoria"
    OWNER to postgres;
	


