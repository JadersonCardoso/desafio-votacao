CREATE TABLE IF NOT EXISTS TB_ASSOCIADO
(
    associado_id uuid NOT NULL,
    cpf varchar(20) NULL,
    CONSTRAINT TB_ASSOCIADO_PKEY PRIMARY KEY(associado_id)

);

