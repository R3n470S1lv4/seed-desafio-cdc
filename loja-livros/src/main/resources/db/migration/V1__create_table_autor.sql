CREATE TABLE  autor (
	criado_em timestamp(6) NULL,
	descricao varchar(255) NULL,
	id varchar(255) NOT NULL,
	nome varchar(255) NULL,
	email varchar(255) NULL,
	CONSTRAINT autor_pkey PRIMARY KEY (id)
);