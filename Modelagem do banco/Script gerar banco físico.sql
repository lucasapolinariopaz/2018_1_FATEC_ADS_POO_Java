CREATE TABLE Cliente
(
    cod_cli integer NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    nome varchar(100) NOT NULL,
    rg varchar(20) NOT NULL,
    cpf varchar(20) NOT NULL,
    endereco varchar(100) NOT NULL,
    num_endereco integer NOT NULL,
    cidade varchar(50) NOT NULL,
    uf varchar(2) NOT NULL,
    telefone varchar(20),
    celular varchar(20),
    email varchar(100),
    UNIQUE(cpf),
    CONSTRAINT pk_cliente PRIMARY KEY(cod_cli)
);

CREATE TABLE Produto
(
    cod_prod integer NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    nome varchar(100) NOT NULL,
    preco numeric NOT NULL,
    categoria varchar(50) NOT NULL,
    quantidade integer NOT NULL,
    CONSTRAINT pk_produto PRIMARY KEY(cod_prod)
);

CREATE TABLE Venda
(
    cod_venda integer NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    data varchar(20) NOT NULL,
    valor numeric NOT NULL,
    forma_pagamento varchar(50) NOT NULL,
    cod_cli integer NOT NULL,
    CONSTRAINT pk_venda PRIMARY KEY(cod_venda),
    CONSTRAINT fk_venda_cliente FOREIGN KEY(cod_cli) REFERENCES Cliente(cod_cli)
);

CREATE TABLE Venda_produto
(
    cod_venda integer NOT NULL,
    cod_prod integer NOT NULL,
    qtd_prod_venda integer NOT NULL,
    CONSTRAINT pk_vp PRIMARY KEY(cod_venda, cod_prod),
    CONSTRAINT fk_vp_venda FOREIGN KEY(cod_venda) REFERENCES Venda(cod_venda),
    CONSTRAINT fk_vp_produto FOREIGN KEY(cod_prod) REFERENCES Produto(cod_prod)
);