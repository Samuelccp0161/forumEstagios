# language: pt
Funcionalidade: Publicar
  Cenario: Publicar um animal
    Dado o nome "sr puffins"
    E o telefone 82912345678
    E o endereço "cachoeirinha"
    E a descrição do pet sendo "pequeno filhote a procura de um lar"
    Quando os dados forem submetidos corretamente
    Entao publique o pet para adocao