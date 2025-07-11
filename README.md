## API de cobrança PIX Bancária para Diversos Bancos

### Este projeto fornece uma API genérica para autenticação bancária via OAuth 2.0 para múltiplos bancos. Atualmente, somente os principais bancos do Brasil: Banco do Brasil, Itaú, Santander, Bradesco, mas a estrutura permite facilmente adicionar suporte a outros bancos como Nubank, C6.

### A estrutura do projeto permite adicionar novos bancos rapidamente, sem modificar o código principal da API.

## Stack Tecnológica

- Linguagem: Java 17
- Framework: Spring Boot 3+
- Autenticação: OAuth 2.0 (Client Credentials)
- Cliente HTTP: RestTemplate
- Gerenciamento de Dependências: Maven
- Banco de Dados: Não necessário para autenticação (A ser implementado se necessário)
- Testes: JUnit 5, Mockito, WireMock (A ser implementado)
- Cache: Caffeine (para reutilização de tokens) (A ser implementado)
- Segurança: TLS para bancos que exigem
- Ter a IDE Intellij para importar o projeto.
- Instalar o Insomnia para executar as collections de autenticação e cobrança.

## Estrutura do Projeto

### O projeto segue o padrão de Factory Design, onde cada banco tem sua própria implementação de autenticação, e o AuthFactory seleciona automaticamente o serviço correto.

````
PixApiApplication.java   # Classe principal do Spring Boot
config/                  # Configurações da API
controller/              # Controladores REST
service/                 # Serviços de autenticação
factory/                 # Factory para escolher o serviço correto
repository/              # Repositórios de dados (se necessário no futuro)
dto/                     # DTOs para requisições e respostas
model/                   # Modelos de domínio (se necessário)
exception/               # Tratamento de erros e exceções
````

## Clonar o Repositório

````
git clone https://github.com/vitorsaop/pix-api.git
cd pix-api
````

## Importando Collection do Insomnia

Para facilitar o uso da API, disponibilizamos uma **collection do Insomnia**.

### Passos para importar
1. Abra o **Insomnia**.
2. Vá para **`File > Import`**.
3. Selecione **`Import Data > From File`**.
4. Escolha o arquivo `docs/insomnia/insomnia_collection.json`.
5. Agora você pode testar a API diretamente pelo Insomnia! ?

### Local do arquivo no projeto:  
- `docs/insomnia/insomnia_collection.json`

## Observações sobre Segurança e TLS

- O certificado TLS para Itaú e demais bancos NÃO será armazenado na API.
- O certificado deve ser enviado na requisição ao solicitar autenticação ou geração de cobrança.

