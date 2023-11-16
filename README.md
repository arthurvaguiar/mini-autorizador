# Mini Autorizador

## Descrição

Este é um mini autorizador desenvolvido em Java 11 com **Spring Boot** para simular o processamento de transações de Vale Refeição e Vale Alimentação. O projeto inclui a criação de cartões, consulta de saldo e autorização de transações.

O código foi escrito com o objetivo de ser simples e de fácil entendimento. Foi feito um esforço para minimizar o uso de condicionais e seguir as práticas de Clean Code.

## Começando

### Pré-requisitos

- Java 11
- Maven
- Docker (para MySQL)
- Spring Boot

### Instalação

```bash
git clone https://github.com/arthurvaguiar/mini-autorizador
cd mini-autorizador
mvn install
```

Para o banco de dados MySQL, existe um arquivo docker-compose.yml que pode ser usado para iniciar um contêiner Docker com o MySQL.

## Configuração
### Configuração para rodar localmente a aplicação
Para rodar a aplicação localmente, você precisa configurar o seguinte:
```bash
spring.datasource.url=jdbc:mysql://localhost:3306/miniautorizador?useSSL=false&useTimezone=true&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.driver-class-name=com.mysql.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5Dialect

spring.sql.init.mode=always
spring.sql.init.script-locations=classpath:db/migration/
```
### Configuração para rodar testes de integração
Para rodar os testes de integração, você precisa configurar o seguinte:
```bash

#spring.datasource.url=jdbc:h2:mem:test;MODE=MySQL;
#spring.datasource.driver-class-name=org.h2.Driver
#spring.datasource.username=
#spring.datasource.password=
#spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
#spring.h2.console.enabled=true
#spring.jpa.defer-datasource-initialization=true
#spring.jpa.hibernate.ddl-auto=update
#spring.jpa.hibernate.naming.implicit-strategy=org.hibernate.boot.model.naming.ImplicitNamingStrategyLegacyJpaImpl
#spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
#spring.jpa.properties.hibernate.format_sql=true
#spring.jpa.hibernate.naming-strategy=org.hibernate.cfg.ImprovedNamingStrategy
#spring.sql.init.mode=always
```

## Configuração do Maven
O projeto é construído usando Maven. Aqui estão algumas das principais dependências que o projeto usa:
<ul>
<li>spring-boot-starter-data-jpa: Para o suporte do Spring Data JPA.</li>
<li>spring-boot-starter-web: Para o suporte do Spring MVC e Tomcat.</li>
<li>spring-boot-starter-test: Para o suporte de testes com Spring Boot.</li>
<li>spring-boot-starter-validation: Para o suporte de validação com Spring Boot.</li>
<li>mysql-connector-java: Para conectar ao MySQL.</li>
<li>validation-api: Para a API de validação do Java.</li>
<li>springfox-boot-starter: Para a documentação da API com o Swagger.</li>
<li>lombok: Para reduzir o boilerplate do código Java.</li>
<li>junit-jupiter-api e junit-jupiter-engine: Para escrever testes com JUnit 5.</li>
<li>junit-platform-commons: Para as funcionalidades comuns do JUnit Platform.</li>
<li>h2: Para o banco de dados em memória H2.</li>
<li>junit: Para escrever testes com JUnit 4.</li>
<li>spring-test: Para o suporte de testes com Spring Framework.</li>
<li>O projeto usa Java 11 e é construído com a versão 2.5.4 do Spring Boot.</li>
</ul>

## Uso

O projeto inclui testes unitários e de integração. Para os testes de integração, é utilizado um banco de dados H2.

Existem dois controladores no projeto:
<ul>
  <li>CartaoController: para assuntos relacionados ao cartão (cadastrar e obterSaldo)</li>
  <li>TransacaoController: para realizar uma transação
</li>
</ul>
Os serviços correspondentes são usados para a lógica de negócios.

O projeto utiliza o padrão de projeto Factory para a criação de objetos.

## Modelos

### Cartao

A classe Cartao representa um cartão no sistema. Ela inclui o número do cartão, a senha e o valor atual do cartão.

As principais operações que a classe Cartao pode realizar são:

debitarSaldo(double value): Debita um valor específico do saldo do cartão.
isCardValid(String numeroCartao, String numeroCartaoRequest): Verifica se um cartão é válido.

### Transacao

A classe Transacao representa uma transação no sistema. Ela inclui a senha do cartão, o valor da transação e o cartão associado à transação.

## Serviços

Os serviços no projeto lidam com a lógica de negócios. Eles são usados pelos controladores para realizar operações específicas, como realizar uma transação ou criar um novo cartão.

### CartaoService

O serviço CartaoService implementa a interface ICartaoService e lida com as operações relacionadas aos cartões. Ele usa a CartaoFactory para criar novos cartões e o CartaoRepository para salvar e recuperar cartões do banco de dados.

As principais operações que o CartaoService pode realizar são:

criarOuRetornarExistente(CartaoDto cartaoRequest): Cria um novo cartão ou retorna um existente.
obterSaldoDoCartao(String numeroCartao): Obtém o saldo de um cartão específico.
findByNumeroCartao(String numeroCartao): Encontra um cartão pelo número do cartão.

### TransacaoService

O serviço TransacaoService implementa a interface ITransacaoService e lida com as operações relacionadas às transações do cartão. Ele usa o CartaoService para encontrar um cartão e o TransacaoRepository para salvar as transações no banco de dados.

As principais operações que o TransacaoService pode realizar são:

realizarTransacao(CartaoRequestDto cartaoDto): Realiza uma transação em um cartão específico.
salvarEProcessarTransacao(Cartao cartao, double valor): Salva e processa uma transação.
mapper(Cartao cartao, double valor): Mapeia uma transação.

## Utilitários

### SecurityUtils

A classe SecurityUtils fornece métodos utilitários para lidar com a segurança das senhas dos usuários.

As principais operações que a SecurityUtils pode realizar são:

hashPassword(String password): Cria um hash da senha do usuário usando o algoritmo SHA-256.
isPasswordValid(String storedPassword, String inputPassword): Verifica se a senha fornecida pelo usuário é válida comparando-a com a senha armazenada.

## CartaoFactory

A classe CartaoFactory é usada para criar novos objetos Cartao.

As principais operações que a CartaoFactory pode realizar são:

criarCartao(String numeroCartao, String senha): Cria um novo objeto Cartao.

