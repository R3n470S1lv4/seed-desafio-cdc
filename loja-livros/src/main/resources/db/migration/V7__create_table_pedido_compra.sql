CREATE TABLE pedido_compra (
	pedido_compra_id varchar(255) NOT NULL,
	compra_id  varchar(255) NOT NULL,
	CONSTRAINT pedido_compra_pkey PRIMARY KEY (pedido_compra_id)
);

ALTER TABLE pedido_compra ADD CONSTRAINT fk_pedido_compra_compra FOREIGN KEY (compra_id) REFERENCES compra (compra_id);