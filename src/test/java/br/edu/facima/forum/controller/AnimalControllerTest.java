package br.edu.facima.forum.controller;

import br.edu.facima.forum.model.Animal;
import br.edu.facima.forum.model.Comentario;
import br.edu.facima.forum.services.AnimalService;
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
        @Nested
        class AoComentar{
            @Test
            public void deveriaDelegarParaOService(){

                animalController.comentarios();

                verify(animalService).comentarios();
            }
            @Test
            public void deveriaRetornarOsComentarios(){
                List<Comentario> comentariosEsperados = new ArrayList<>();
                comentariosEsperados.add(null);

                when(animalService.comentarios()).thenReturn(comentariosEsperados);

                List<Comentario> comentarios = animalController.comentarios();

                assertThat(comentarios).isEqualTo(comentariosEsperados);
            }
        }
    }
}
