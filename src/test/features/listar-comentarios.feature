# language: pt
Funcionalidade: Listar comentarios

  Cenario: Listar os comentarios de um mesmo autor
    Dado que existe um usuario logado
    E existem comentarios publicados
    Quando tentar listar os comentarios
    Entao Todos os comentarios do mesmo deverão ser retornados

  Cenario: Listar comentarios de um animal
    Dado um animal publicado
    E existem comentarios publicados
    Quando tentar listar os comentarios do animal
    Entao Todos os comentarios do mesmo deverão ser retornados