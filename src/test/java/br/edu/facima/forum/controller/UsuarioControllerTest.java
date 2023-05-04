package br.edu.facima.forum.controller;

import br.edu.facima.forum.model.Usuario;
import br.edu.facima.forum.services.UsuarioService;
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

    @InjectMocks
    private UsuarioController usuarioController;

    @Nested
    class AoCadastrar {
        @Test
        public void delegarOCadastroParaOService() {
            Usuario usuario = new Usuario();

            usuarioController.cadastrar(usuario);

            verify(usuarioService).cadastrar(same(usuario));
        }
    }
}