package br.edu.facima.doapet.controller;

import br.edu.facima.doapet.model.Animal;
import br.edu.facima.doapet.services.AnimalService;
import br.edu.facima.doapet.services.ComentarioService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AnimalControllerTest {
    @Mock
    AnimalService animalService;
    @Mock
    ComentarioService comentarioService;
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
    @Nested
    class AoListar{
        @Test
        public void delegarListarParaOService(){

            animalController.listar();

            verify(animalService).listar();
        }
        @Test
        public void deveriaRetornarOsAnimais(){
            List<Animal> animaisEsperados = new ArrayList<>();
            animaisEsperados.add(null);

            when(animalService.listar()).thenReturn(animaisEsperados);

            List<Animal> animais = animalController.listar();

            assertThat(animais).isEqualTo(animaisEsperados);
        }
        @Test
        public void deveriaDelegarListarComentariosParaOService(){

            animalController.listarComentarios(0L);

            verify(comentarioService).listarComentariosDoAnimal(0L);
        }
    }
}
