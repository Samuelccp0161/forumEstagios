# language: pt
Funcionalidade: Comentar

  Cenario: Adicionar comentarios
    Dado que existe um usuario cadastrado
    E um animal publicado
    Quando o usuario tentar deixar o comentario "fofinho"
    Entao este comentario deveria ter sido salvo
    E o comentario salvo deveria informar quem foi autor
    E deveria informar qual o animal comentado