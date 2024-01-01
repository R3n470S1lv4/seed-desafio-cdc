CREATE TABLE item_pedido_compra (
	item_pedido_compra_id varchar(255) NOT NULL,
	pedido_compra_id varchar(255) NOT NULL,
	livro_id varchar(255) NOT NULL,
	quantidade numeric(15,2) NOT NULL,
	CONSTRAINT item_pedido_compra_pkey PRIMARY KEY (item_pedido_compra_id)
);

ALTER TABLE item_pedido_compra ADD CONSTRAINT fk_item_pedido_compra_livro FOREIGN KEY (livro_id) REFERENCES livro (livro_id);
ALTER TABLE item_pedido_compra ADD CONSTRAINT fk_item_pedido_compra_pedido_compra FOREIGN KEY (pedido_compra_id) REFERENCES pedido_compra (pedido_compra_id);