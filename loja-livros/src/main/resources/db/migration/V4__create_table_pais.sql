CREATE TABLE pais (
	pais_id varchar(255) NOT NULL,
  nome varchar(255) NOT NULL,
	CONSTRAINT pais_pkey PRIMARY KEY (pais_id)
);

ALTER TABLE pais ADD CONSTRAINT uk_pais_nome UNIQUE (nome);