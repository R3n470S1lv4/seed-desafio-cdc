CREATE TABLE  livro (
	livro_id varchar(255) NOT NULL,
	isbn varchar(255) NOT NULL,
	titulo varchar(255) NOT NULL,
	resumo varchar(500) NOT NULL,
	sumario varchar(500) NOT NULL,
  valor numeric NOT NULL,
  numero_paginas int NOT NULL,
  categoria_id varchar(255) NOT NULL,
  autor_id varchar(255) NOT NULL,
  data_publicacao timestamp(6),
	criado_em timestamp(6) NOT NULL,
	CONSTRAINT livro_pkey PRIMARY KEY (livro_id)
);

ALTER TABLE livro ADD CONSTRAINT fk_livro_categoria FOREIGN KEY (categoria_id) REFERENCES categoria (categoria_id);
ALTER TABLE livro ADD CONSTRAINT fk_livro_autor FOREIGN KEY (autor_id) REFERENCES autor (autor_id);
