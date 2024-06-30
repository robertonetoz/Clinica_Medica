🎓 **CLINICA MÉDICA | DESENVOLVIMENTO WEB BACK-END** 🎓

***
###

Nosso projeto visa desenvolver uma aplicação de gerenciamento de uma clínica médica, composta por três módulos principais: administrativo, agendamento e atendimento. Utilizaremos a linguagem de programação Java e a IDE IntelliJ IDEA para o desenvolvimento do backend da aplicação. O objetivo é criar uma infraestrutura eficiente para gerenciar todos os aspectos operacionais de uma clínica médica.


A aplicação está organizada em pacotes que seguem uma arquitetura típica de camadas, facilitando a manutenção e a escalabilidade do sistema. 📌


📖 Livro utilizado como base do projeto: **Java para Web - Desenvolvimento de Aplicações | William Pereira Alves**


***
**Ferramentas Utilizadas**

💻 **IDE**: IntelliJ IDEA
###
☕ **Linguagem de Programação**: Java
###
🎲 **Banco de Dados**: MySQL
###
🧰 **Ferramenta de Teste de API**: Postman


***
**Estrutura do Projeto**
###
🖥️ Models: o diretório Models contém as classes de modelo que representam as entidades do banco de dados. Estas classes definem a estrutura e os relacionamentos dos dados que serão armazenados e manipulados pela aplicação.
###
🖥️ Dtos: o diretório DTOs contém os Data Transfer Objects, que são utilizados para transferir dados entre as camadas da aplicação. Os DTOs ajudam a isolar a lógica de negócio dos detalhes de persistência, proporcionando uma maneira limpa e segura de transferir dados.
###
🖥️ Repositories: o diretório Repositories contém as interfaces de repositório que interagem com o banco de dados. Estas interfaces são responsáveis por definir os métodos para operações CRUD (Create, Read, Update, Delete) e outras consultas específicas que a aplicação necessitará.
###
🖥️ Services: o diretório Services contém a lógica de negócios da aplicação. Aqui é onde as regras de negócio são implementadas, utilizando os repositórios para acesso aos dados e aplicando a lógica necessária para cumprir os requisitos do sistema.
###
🖥️ Controllers: o diretório Controllers contém os controladores REST que expõem as APIs. Os controladores são responsáveis por receber as requisições HTTP, chamar os serviços apropriados e retornar as respostas adequadas ao cliente.
###


***
❗❗❗❗❗❗❗❗ **AVISOS** ❗❗❗❗❗❗❗❗ 

✅ A versão do JDK deve ser utilizado na 17, a versão 22 apresenta as vezes incompatibilidade, o que pode ser resolvido verificando a versão do projeto e configurar para 17;

✅ É necessário criar um banco de dados no MySQL e passar a "usá-lo";

✅ Os pacotes "resources" de cada modulo devem ser configurados manualmente com base no seu usuário que você utiliza para acessar o banco de dados MySQL (o padrão costuma ser "root", mas se você personalizou, não esqueça de verificar este detalhe !). Sua senha e o nome do banco de dados que você define no MySQL também devem ser verificadas!

 Antes de rodar o projeto, é necessário verificar se o JDK está em uma versão compatível. O ideal é utilizar a versão 17.
