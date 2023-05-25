package br.edu.facima.forum.services;

import br.edu.facima.forum.model.Animal;
import br.edu.facima.forum.model.Comentario;

import java.util.List;

public interface AnimalService {

    void publicar(Animal animal);

    List<Animal> listar();

}
