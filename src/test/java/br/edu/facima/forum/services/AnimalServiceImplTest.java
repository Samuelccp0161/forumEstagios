package br.edu.facima.forum.services;

import br.edu.facima.forum.model.Animal;
import br.edu.facima.forum.model.Comentario;
import br.edu.facima.forum.repository.AnimalRepository;
import br.edu.facima.forum.repository.ComentarioRepository;
import br.edu.facima.forum.services.impl.AnimalServiceImpl;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
            Animal passaro = new Animal("zezé", "8825", "pequeno");
            Animal coruja = new Animal("arquimedes", "336", "silencioso");
            List<Animal> animaisEsperados = adicionarDoisAnimaisALista(passaro, coruja);

            animalService.publicar(passaro);
            animalService.publicar(coruja);

            List<Animal> animais = animalService.listar();

            assertEquals(animaisEsperados, animais);
        }

        private List<Animal> adicionarDoisAnimaisALista(Animal passaro, Animal coruja) {
            List<Animal> animaisEsperados = new ArrayList<>();
            animaisEsperados.add(passaro);
            animaisEsperados.add(coruja);
            return animaisEsperados;
        }
        @Nested
        class AoComentar{
            @Test
            public void deveriaSalvarOComentario(){
                Comentario comentario = new Comentario("hey");

                animalService.comentar(comentario);

//                when(comentarioRepository.findByComentario(comentario.getComentario()))
//                        .thenReturn(Optional.of(comentario.getComentario()));

                verify(comentarioRepository).save(comentario);
            }
            @Test
            public void deveriaComentar(){
                Comentario comentario = new Comentario("HeyOh");

                animalService.comentar(comentario);

                when(comentarioRepository.findByComentario(comentario.getComentario()))
                        .thenReturn(Optional.of(comentario.getComentario()));

                assertEquals(Optional.of(new Comentario("HeyOh").getComentario()),
                        comentarioRepository.findByComentario(comentario.getComentario()));
            }
        }
    }
}