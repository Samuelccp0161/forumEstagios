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

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
            Animal passaro = new Animal("zezé", "8825", "pequeno");
            Animal coruja = new Animal("arquimedes", "336", "silencioso");
            List<Animal> animaisEsperados = new ArrayList<>();
            animaisEsperados.add(passaro);
            animaisEsperados.add(coruja);

            animalService.publicar(passaro);
            animalService.publicar(coruja);

            List<Animal> animais = animalService.listar();

            System.out.println(animais);
            assertEquals(animaisEsperados, animais);
        }
    }
}