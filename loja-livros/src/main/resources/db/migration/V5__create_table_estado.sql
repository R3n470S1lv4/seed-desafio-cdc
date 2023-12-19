CREATE TABLE estado (
	estado_id varchar(255) NOT NULL,
  nome varchar(255) NOT NULL,
  pais_id varchar(255) NOT NULL,
	CONSTRAINT estado_pkey PRIMARY KEY (estado_id)
);

ALTER TABLE estado ADD CONSTRAINT fk_estado_pais FOREIGN KEY (pais_id) REFERENCES pais (pais_id);
ALTER TABLE estado ADD CONSTRAINT uk_estado_nome UNIQUE (nome);