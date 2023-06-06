# Projeto Hospital - Sistema de Gerenciamento de Pacientes para Médicos

### Descrição

O Projeto Hospital é um sistema desenvolvido em JavaFX, utilizando JPA (Java Persistence API) e Hibernate para a persistência dos dados. O sistema permite que médicos cadastrem, editem, removam e pesquisem pacientes, além de fornecer uma funcionalidade para avaliação do estado de coma dos pacientes usando a Escala de Coma de Glasgow.

### Requisitos de Sistema

- Java JDK 17 ou superior
- Maven
- MySQL

### Configuração

1. Certifique-se de ter o Maven instalado no seu sistema.
2. Faça o clone do repositório do projeto.
3. Abra o projeto em sua IDE preferida.
4. No arquivo `persistence.xml`, localize as configurações de conexão com o banco de dados e atualize com as informações do banco MySQL chamado "hospital". Exemplo:
  ```xml
   <property name="javax.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/hospital" />
   <property name="javax.persistence.jdbc.user" value="seu_usuario" />
   <property name="javax.persistence.jdbc.password" value="sua_senha" />
   ```
5. Execute o projeto na sua IDE ou use o comando Maven para iniciar a aplicação.
Recursos e Funcionalidades

### Recursos e Funcionalidades

- Autenticação de Médicos: O sistema possui um sistema de login, no qual os médicos podem se registrar passando suas informações, e acessar o sistema.
- Gerenciamento de Pacientes: Os médicos podem cadastrar, editar, remover e pesquisar pacientes.
- Escala de Coma de Glasgow: Cada paciente possui um formulário para avaliação do estado de coma usando a Escala de Coma de Glasgow. O sistema apresenta o resultado dessa avaliação.

### Contribuição

Contribuições são bem-vindas! Se você tiver sugestões, correções de bugs ou melhorias, sinta-se à vontade para abrir uma issue ou enviar um pull request.
