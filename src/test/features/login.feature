# language: pt
Funcionalidade: login
  Cenario: logar um usuario com sucesso
    Dado um usuario já cadastrado
    Quando o mesmo tentar logar com os dados corretos
    Entao o usuario deveria ter sido logado com sucesso