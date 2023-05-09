# language: pt
Funcionalidade: Cadastrar
  Cenario: Cadastrar um aluno
    Dado o nome "Maria Jose"
    E o email "fulano@facima.edu.br"
    E o contato 82912345678
    E a senha "123456"
    Quando os dados forem submetidos
    Entao o aluno deveria ter sido cadastrado com sucesso

