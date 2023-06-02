package br.edu.facima.doapet.controller;

import br.edu.facima.doapet.exceptions.UsuarioJaExistenteException;
import br.edu.facima.doapet.model.Comentario;
import br.edu.facima.doapet.model.Usuario;
import br.edu.facima.doapet.services.ComentarioService;
import br.edu.facima.doapet.services.UsuarioService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UsuarioControllerTest {

    @Mock
    private UsuarioService usuarioService;

    @Mock
    private ComentarioService comentarioService;

    @InjectMocks
    private UsuarioController usuarioController;
    @Nested
    class AoCadastrar {
        @Test
        public void delegarOCadastroParaOService() throws UsuarioJaExistenteException {
            Usuario usuario = new Usuario();

            usuarioController.cadastrar(usuario);

            verify(usuarioService).cadastrar(same(usuario));
        }
    }
    @Nested
    class AoLogar {
        @Test
        public void delegarOLoginParaOService(){
          var usuario = new Usuario();
          usuario.setEmail("54321");
          usuario.setSenha("12345");

            usuarioController.logar(usuario);

            verify(usuarioService).logar(usuario.getEmail(), usuario.getSenha());
        }
    }
    @Nested
    class AoComentar{
        @Test
        public void delegarOComentar(){
            Comentario comentario = new Comentario();

            usuarioController.comentar(comentario);

            verify(comentarioService).comentar(same(comentario));
        }
    }
    @Nested
    class AoListarComentarios{
        @Test
        public void deveriaDelegarParaOListarComentarios(){
            usuarioController.listarComentariosDoUsuario("");

            verify(comentarioService).listarComentariosDoUsuario("");
        }
    }
    @Nested
    class AoDeletarComentario{
        @Test
        public void deveriaDelegarParaODeletarComentario(){
            usuarioController.deletar(1L);

            verify(comentarioService).deletar(1L);
        }
    }
}
