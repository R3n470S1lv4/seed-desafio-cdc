CREATE TABLE  autor (
	autor_id varchar(255) NOT NULL,
	criado_em timestamp(6) NOT NULL,
	descricao varchar(255) NOT NULL,
	nome varchar(255) NOT NULL,
	email varchar(255) NOT NULL,
	CONSTRAINT autor_pkey PRIMARY KEY (autor_id)
);