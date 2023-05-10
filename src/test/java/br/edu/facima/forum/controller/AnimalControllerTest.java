package br.edu.facima.forum.controller;

import br.edu.facima.forum.model.Animal;
import br.edu.facima.forum.services.AnimalService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AnimalControllerTest {
    @Mock
    AnimalService animalService;
    @InjectMocks
    AnimalController animalController;

    @Nested
    class AoPublicar{
        @Test
        public void delegarPublicarParaOService(){
            Animal animal = new Animal();

            animalController.publicar(animal);

            verify(animalService).publicar(same(animal));
        }
    }
}
