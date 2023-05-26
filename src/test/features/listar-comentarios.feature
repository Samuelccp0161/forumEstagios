# language: pt
Funcionalidade: Listar comentarios

  Cenario: Listar os comentarios de um mesmo autor
    Dado que existe um usuario cadastrado
    E um animal publicado
    E existem comentarios publicados
    Quando tentar listar os comentarios do usuario
    Entao Todos os comentarios do usuario deverão ser retornados

  Cenario: Listar comentarios de um animal
    Dado um animal publicado
    E existem comentarios publicados
    Quando tentar listar os comentarios do animal
    Entao Todos os comentarios do animal deverão ser retornados