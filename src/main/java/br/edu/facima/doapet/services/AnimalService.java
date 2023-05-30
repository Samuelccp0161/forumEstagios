package br.edu.facima.doapet.services;

import br.edu.facima.doapet.model.Animal;

import java.util.List;

public interface AnimalService {
    void publicar(Animal animal);
    List<Animal> listar();
}
