package br.edu.facima.forum.services.impl;

import br.edu.facima.forum.model.Animal;
import br.edu.facima.forum.repository.AnimalRepository;
import br.edu.facima.forum.services.AnimalService;
import org.springframework.stereotype.Service;

@Service
public class AnimalServiceImpl implements AnimalService {
    private final AnimalRepository animalRepository;

    public AnimalServiceImpl(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @Override
    public void publicar(Animal animal) {
        animalRepository.save(animal);
    }
}
