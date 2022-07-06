# poo-proj-software-rede-social
Código da disciplina Projeto de Software (Programação Orientada a Objeto)

--------------------------------------------------------------------------------------------------------------------------------------------------------

CODE SMELLS IDENTIFICADOS:
1) Long Method - main()
O método main() foi identificado como long method por possuir muitos if's em sequência, em decorrência do menu funcionar recebendo um valor inteiro para cada diferente 
opção, e assim, cada if seria uma opção selecionada diferente. Não consegui implementar o novo menu, onde separaria o que é feito em cada if em classes e criaria o menu 
utilizando um Strategy Pattern, até porque percebi no decorrer do caminho que, por utilizarem muitas coisas em comum, geraria de imediato uma complexidade maior a ser 
resolvida e novos code smells.

2) Duplicated Code - main()
Na maioria dos if's há código duplicado para realizar o login.
Resolvido com a criação do método fazerLogin() na classe iFace.

3) Duplicated Code e Long Method - Autenticador() e fazerLogin()
Antes da correção 2, foi a autenticação era feita junto com o login e se repetia por vários trechos do código, sendo feito um método Autenticador() na classe Conta, isso 
também fez com que futuramente viesse a ser evitado que o método fazerLogin() na classe iFace viesse a ser um long method.
