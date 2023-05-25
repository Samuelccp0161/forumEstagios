package br.edu.facima.forum.services;

import br.edu.facima.forum.model.Comentario;
import br.edu.facima.forum.repository.ComentarioRepository;
import br.edu.facima.forum.services.impl.ComentarioServiceImpl;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ComentarioServiceImplTest {

    @Mock
    ComentarioRepository comentarioRepository;

    @InjectMocks
    private ComentarioServiceImpl comentarioService;

    @Nested
    class AoComentar{
        @Test
        public void deveriaSalvarOComentario(){
            Comentario comentario = new Comentario();

           comentarioService.comentar(comentario);

           verify(comentarioRepository).save(same(comentario));
        }
    }
    @Nested
    class AoBuscar{
        @Test
        public void deveriaBuscarUmComentario(){
            Comentario comentario = new Comentario();
        }
    }

    @Nested
    class AoListarComentarios{
        @Test
        public void deveriaListarComentariosDeUmUsuario(){
            List<Comentario> comentariosEsperados = new ArrayList<>();
            String email = "jj@";

            when(comentarioRepository.findByAutorEmail(email)).thenReturn(comentariosEsperados);

            List<Comentario> comentarios = comentarioService.listarComentariosDoUsuario(email);

            assertThat(comentarios).isEqualTo(comentariosEsperados);
        }
    }
}
