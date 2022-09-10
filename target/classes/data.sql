CREATE TABLE CLIENTES (
    ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    NOME VARCHAR(100)
);

CREATE TABLE PRODUTOS (
    ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    DESCRICAO VARCHAR(100),
    PRECO_UNITARIO NUMERIC(20,2)
);

CREATE TABLE PEDIDOS (
    ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    CLIENTE_ID INTEGER REFERENCES CLIENTES (ID),
    DATA_PEDIDO TIMESTAMP,
    TOTAL NUMERIC(20,2)
);

CREATE TABLE ITENS_PEDIDOS (
    ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    PEDIDO_ID INTEGER REFERENCES PEDIDOS (ID),
    PRODUTO_ID INTEGER REFERENCES PRODUTOS (ID),
    QUANTIDADE INTEGER
);