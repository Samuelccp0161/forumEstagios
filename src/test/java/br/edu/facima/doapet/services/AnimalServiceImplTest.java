package br.edu.facima.doapet.services;

import br.edu.facima.doapet.model.Animal;
import br.edu.facima.doapet.repository.AnimalRepository;
import br.edu.facima.doapet.repository.ComentarioRepository;
import br.edu.facima.doapet.services.impl.AnimalServiceImpl;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AnimalServiceImplTest {

    @Mock
    private AnimalRepository animalRepository;

    @Mock
    private ComentarioRepository comentarioRepository;

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
            List<Animal> animaisEsperados = new ArrayList<>();
            animaisEsperados.add(new Animal("zezé", "8825", "pequeno"));
            animaisEsperados.add(new Animal("arquimedes", "336", "silencioso"));

            when(animalRepository.findAll()).thenReturn(animaisEsperados);

            List<Animal> animais = animalService.listar();

            assertEquals(animaisEsperados, animais);
        }

        private List<Animal> adicionarDoisAnimaisALista(Animal passaro, Animal coruja) {
            List<Animal> animaisEsperados = new ArrayList<>();
            animaisEsperados.add(passaro);
            animaisEsperados.add(coruja);
            return animaisEsperados;
        }
    }
}
