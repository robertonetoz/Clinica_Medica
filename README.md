🎓 **CLINICA MÉDICA | DESENVOLVIMENTO WEB BACK-END** 🎓

***
###
O projeto é uma aplicação ***Spring Boot*** para a administração de uma clínica, focada na gestão de convênios e médicos, até o momento! (Podendo sofrer novas alterações e implementações de outros recursos) 
A aplicação está organizada em pacotes que seguem uma arquitetura típica de camadas, facilitando a manutenção e a escalabilidade do sistema. 📌

📖 Livro utilizado como base do projeto: **Java para Web - Desenvolvimento de Aplicações | William Pereira Alves**

***
📜 **FUNCIONALIDADES** 📜

🩺 **Gestão de Convênios**: permite criar, ler, atualizar e excluir convênios. 
###
🩺 **Gestão de Médicos**: permite criar, ler, atualizar e excluir médicos.


***
**Estrutura do Projeto**
###
🖥️ *Models*: contém as classes de modelo que representam as entidades do banco de dados. 
###
🖥️ *Dtos*: contém os Data Transfer Objects usados para transferir dados entre as camadas da aplicação. 
###
🖥️ *Repositories*: contém as interfaces de repositório que interagem com o banco de dados. 
###
🖥️ *Services*: contém a lógica de negócios. 
###
🖥️ *Controllers*: contém os controladores REST que expõem as APIs.
###

❗❗❗❗❗❗❗❗ **AVISOS** ❗❗❗❗❗❗❗❗ 

✅ A versão do JDK deve ser utilizado na 17, a versão 22 apresenta as vezes incompatibilidade, o que pode ser resolvido verificando a versão do projeto e configurar para 17;
✅ Os pacotes "resources" de cada modulo devem ser configurados manualmente com base no seu usuário que você utiliza para acessar o banco de dados MySQL (o padrão costuma ser "root", mas se você personalizou, não esqueça de verificar este detalhe !). Sua senha do MySQL também deverá ser verificada!

 Antes de rodar o projeto, é necessário verificar se o JDK está em uma versão compatível. O ideal é utilizar a versão 17.
