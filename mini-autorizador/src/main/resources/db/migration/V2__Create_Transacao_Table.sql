CREATE TABLE IF NOT EXISTS transacao (
                                         id INT AUTO_INCREMENT PRIMARY KEY,
                                         numero_cartao VARCHAR(16) NOT NULL,
                                         senha_cartao VARCHAR(255) NOT NULL,
                                         valor DOUBLE NOT NULL,
                                         data_transacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                         CONSTRAINT fk_cartao FOREIGN KEY (numero_cartao) REFERENCES cartao(numero_cartao)
);

