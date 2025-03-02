## API de cobran�a PIX Banc�ria para Diversos Bancos

### Este projeto fornece uma API gen�rica para autentica��o banc�ria via OAuth 2.0 para m�ltiplos bancos. Atualmente, somente o Banco do Brasil est� implementado, mas a estrutura permite facilmente adicionar suporte a outros bancos como Ita�, Santander, Bradesco, C6, etc..

### A estrutura do projeto permite adicionar novos bancos rapidamente, sem modificar o c�digo principal da API.

## Stack Tecnol�gica

- Linguagem: Java 17
- Framework: Spring Boot 3+
- Autentica��o: OAuth 2.0 (Client Credentials)
- Cliente HTTP: RestTemplate
- Gerenciamento de Depend�ncias: Maven
- Banco de Dados: N�o necess�rio para autentica��o (A ser implementado se necess�rio)
- Testes: JUnit 5, Mockito, WireMock (A ser implementado)
- Cache: Caffeine (para reutiliza��o de tokens) (A ser implementado)
- Seguran�a: TLS para bancos que exigem
- Ter a IDE Intellij para importar o projeto.
- Instalar o Insomnia para executar as collections de autentica��o e cobran�a.

## Estrutura do Projeto

### O projeto segue o padr�o de Factory Design, onde cada banco tem sua pr�pria implementa��o de autentica��o, e o AuthFactory seleciona automaticamente o servi�o correto.

PixApiApplication.java   # Classe principal do Spring Boot
config/                  # Configura��es da API
controller/              # Controladores REST
service/                 # Servi�os de autentica��o
factory/                 # Factory para escolher o servi�o correto
repository/              # Reposit�rios de dados (se necess�rio no futuro)
dto/                     # DTOs para requisi��es e respostas
model/                   # Modelos de dom�nio (se necess�rio)
exception/               # Tratamento de erros e exce��es

## Clonar o Reposit�rio

git clone https://github.com/vitorsaop/pix-api.git
cd pix-api

## Importando Collection do Insomnia

Para facilitar o uso da API, disponibilizamos uma **collection do Insomnia**.

### Passos para importar
1. Abra o **Insomnia**.
2. V� para **`File > Import`**.
3. Selecione **`Import Data > From File`**.
4. Escolha o arquivo `docs/insomnia/insomnia_collection.json`.
5. Agora voc� pode testar a API diretamente pelo Insomnia! ?

? **Local do arquivo no projeto:**  
- `docs/insomnia/insomnia_collection.json`

## Observa��es sobre Seguran�a e TLS

- O certificado TLS para Ita� e demais bancos N�O ser� armazenado na API.
- O certificado deve ser enviado na requisi��o ao solicitar autentica��o ou gera��o de cobran�a.

