package br.edu.facima.forum.services;

import br.edu.facima.forum.model.Animal;
import br.edu.facima.forum.repository.AnimalRepository;
import br.edu.facima.forum.services.impl.AnimalServiceImpl;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AnimalServiceImplTest {

    @Mock
    private AnimalRepository animalRepository;

    @InjectMocks
    private AnimalServiceImpl animalService;

    @Nested
    class AoPublicar {
        @Test
        void oAnimalDeveriaSerSalvo(){
            Animal animal = new Animal();

            animalService.publicar(animal);

            verify(animalRepository).save(animal);
        }
    }
    @Nested
    class AoListar{
        @Test
        public void DeveriaListarOsAnimais(){

        }
    }
}