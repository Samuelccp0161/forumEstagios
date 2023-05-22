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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ComentarioServiceImplTest {

    @Mock
    ComentarioRepository comentarioRepository;

    @InjectMocks
    private ComentarioServiceImpl comentarioService;

    @Nested
    class aoComentar{
        @Test
        public void deveriaSalvarOComentario(){
            Comentario comentario = new Comentario("Hello");

           comentarioService.comentar(comentario);

           verify(comentarioRepository).save(same(comentario));
        }
        @Test
        public void deveriaComentar(){

        }
    }
}
