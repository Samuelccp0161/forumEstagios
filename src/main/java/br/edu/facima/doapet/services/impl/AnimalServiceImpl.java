package br.edu.facima.doapet.services.impl;

import br.edu.facima.doapet.model.Animal;
import br.edu.facima.doapet.repository.AnimalRepository;
import br.edu.facima.doapet.services.AnimalService;
import org.springframework.stereotype.Service;

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
