# Projeto de CCH de Banco de Dados II
Repositório destinado aos arquivos do projeto de estudo da matéria de Banco de Dados II

## Requisitos

- JDK 20
- PostgresSQL

## Como Executar

1. Clone este repositório para o seu ambiente local.
2. Abra o arquivo `Criar Tabelas.sql` da pasta SQL em seu PGAdmin e execute-o.
    - Este arquivo irá criar o banco de dados e as tabelas necessárias para o funcionamento do projeto.
2. Abra o arquivo `Login.sql` da pasta SQL em seu PGAdmin e modifique-o para conter suas informações de login.
    - O nome do funcionário cadastrado deve ser exatamente igual ao login que você utiliza para logar no banco de dados.
    - A senha do funcionário cadastrado deve ser exatamente igual à senha que você utiliza para logar no banco de dados.
    - A função do funcionário cadastrado deve ser "Gerente", para você acessar as funcionalidades de gerente, qualquer outra apenas terá acesso as funcionalidades de um funcionário comum.
3. Abra os outros arquivos da pasta SQL em seu PGAdmin e execute-os um a um.
4. Abra o projeto em sua IDE Java de preferência (recomendamos o Apache NetBeans).
5. Para rodar o projeto, abra a classe `LojaVirtualApplication.java` e execute-a.
6. As informações de login utilizadas para acessar o sistema, são as mesmas cadastradas na tabela funcionário, no passo 2.
7. O backup será realizado na pasta `C:\Backup`, para modificar, apenas reescreva o novo caminho na variável outputPath da classe `BackupService.java`.

## Funcionalidades

<b>1. Painel Tela Inicial</b>
   - Permite o login no sistema.
   - Direciona o usuário à tela de login.

<b>2. Painel Login</b>
   - Autentica o usuário (funcionário ou gerente) com base nas credenciais fornecidas.
   - Redireciona para a tela principal ou de gerente conforme o nível de acesso. 

<b>3. Painel Gerente</b>
   - Permite a pesquisa de funcionários e fornecedores.
   - Possibilita a realização de backups do sistema.
   - Concede privilégios a outros funcionários. 

<b>4. Painel Funcionário</b>
   - Permite a busca e seleção de produtos.
   - Funciona como um cadastro de novos funcionários. 

<b>5. Painel Cadastro Produto</b>
   - Permite o cadastro de novos produtos no banco de dados.
   - Registra informações como nome, valor, quantidade e fornecedor do produto. 

<b>6. Painel Venda Funcionário</b>
   - Funcionalidades relacionadas à venda de produtos.
   - Permite a realização e gerenciamento de vendas.

