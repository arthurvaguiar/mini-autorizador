CREATE SCHEMA `MINIAUTORIZADOR`;

DROP TABLE transacao;

DROP TABLE CARTAO;

CREATE TABLE CARTAO
(
    id            BIGINT NOT NULL AUTO_INCREMENT,
    numero_cartao VARCHAR(255),
    senha         VARCHAR(255),
    valor         DOUBLE NOT NULL,
    PRIMARY KEY (id)
);



CREATE TABLE IF NOT EXISTS `transacao`
(
    id            INT AUTO_INCREMENT PRIMARY KEY,
    cartao_id VARCHAR(16)  NOT NULL,
    senha_cartao  VARCHAR(255) NOT NULL,
    valor         DOUBLE       NOT NULL,
    FOREIGN KEY (cartao_id) REFERENCES cartao (id)
);


