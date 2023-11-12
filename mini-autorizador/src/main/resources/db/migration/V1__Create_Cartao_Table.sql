CREATE TABLE IF NOT EXISTS cartao (
                                      id INT AUTO_INCREMENT PRIMARY KEY,
                                      numero_cartao VARCHAR(16) NOT NULL,
                                      senha VARCHAR(255) NOT NULL,
                                      saldo DOUBLE NOT NULL
);