# poo-proj-software-rede-social
Código da disciplina Projeto de Software (Programação Orientada a Objeto)

--------------------------------------------------------------------------------------------------------------------------------------------------------

CODE SMELLS IDENTIFICADOS:
1) Long Method - main()
O método main() foi identificado como long method por possuir muitos if's em sequência, em decorrência do menu funcionar recebendo um valor inteiro para cada diferente opção, e assim, cada if seria uma opção selecionada diferente.

2) Duplicated Code - main()

Na maioria dos if's há código duplicado para realizar o login e sua devida autenticação.

Resolvido com a criação do método Autenticador() na classe Conta.
