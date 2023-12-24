CREATE TABLE pagamento (
	pagamento_id varchar(255) NOT NULL,
  nome varchar(255) NOT NULL,
  sobrenome varchar(255) NOT NULL,
  email varchar(255) NOT NULL,
  numero_telefone varchar(10) NOT NULL,
  tipo_documento smallint NOT NULL,
  documento varchar(18) NOT NULL,
  logradouro varchar(255) NOT NULL,
  bairro varchar(255) NOT NULL,
  cidade varchar(255) NOT NULL,
  estado_id varchar(255),
  pais_id varchar(255) NOT NULL,
  cep varchar(8) NOT NULL,
  complemento varchar(255) NOT NULL,
	CONSTRAINT pagamento_pkey PRIMARY KEY (pagamento_id)
);

ALTER TABLE pagamento ADD CONSTRAINT fk_pagamento_pais FOREIGN KEY (pais_id) REFERENCES pais (pais_id);
ALTER TABLE pagamento ADD CONSTRAINT fk_pagamento_estado FOREIGN KEY (estado_id) REFERENCES estado (estado_id);
