# language: pt
Funcionalidade: Cadastrar
  Cenario: Cadastrar um aluno
    Dado o nome "Maria Jose"
    E o email "fulano@facima.edu.br"
    E o numero da matricula sendo 12345678911
    E o contato 82912345678
    Quando os dados forem submetidos
    Entao o cadastro foi realizado com sucesso

