package br.edu.facima.forum.services.impl;

import br.edu.facima.forum.model.Animal;
import br.edu.facima.forum.model.Comentario;
import br.edu.facima.forum.repository.AnimalRepository;
import br.edu.facima.forum.repository.ComentarioRepository;
import br.edu.facima.forum.services.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnimalServiceImpl implements AnimalService {
    private final AnimalRepository animalRepository;

    public AnimalServiceImpl(AnimalRepository animalRepository){
        this.animalRepository = animalRepository;
    }

    @Override
    public void publicar(Animal animal) {
        animalRepository.save(animal);
    }

    @Override
    public List<Animal> listar() {
        return animalRepository.findAll();
    }

}
