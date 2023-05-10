# language: pt
Funcionalidade: Publicar
  Cenario: Publicar um animal
    Dado o nome do animal "sr puffins"
    E o telefone para contato "82912345678"
    E a descrição do pet sendo "pequeno filhote a procura de um lar"
    Quando os dados forem submetidos corretamente
    Entao o animal deveria ter sido publicado