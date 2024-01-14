CREATE TABLE cupom_desconto (
	cupom_desconto_id varchar(255) NOT NULL,
	codigo varchar(25) NOT NULL,
	percentual numeric(2) NOT NULL,
	validade date NOT NULL,
	CONSTRAINT cupom_desconto_pkey PRIMARY KEY (cupom_desconto_id)
);

ALTER TABLE cupom_desconto ADD CONSTRAINT uk_cupom_desconto_codigo UNIQUE (codigo);