package br.edu.facima.forum.services;

import br.edu.facima.forum.controller.UsuarioController;
import br.edu.facima.forum.model.Usuario;
import br.edu.facima.forum.repository.UsuarioRepository;
import org.junit.Ignore;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class UsuarioServiceImplTest {
    public static final String EMAIL = "gg@gmail.com";
    public static final String SENHA = "a123";
    @Mock
    UsuarioRepository usuarioRepository;

    @InjectMocks
    UsuarioServiceImpl usuarioService;

    @Nested
    class AoCadastrar {
        @Test
        void comDadosValidosDeveriaSalvarUsuarioNoRepositorio() {
            Usuario usuario = new Usuario();

            usuarioService.cadastrar(usuario);

            verify(usuarioRepository).save(usuario);
        }

        @Test
        void comUsuarioJaExistenteDeveriaLancarExcecao() {

        }
    }
    @Nested
    class AoTentarLogar {
        @Test
        public void comOsDadosCadastradosDeveriaLogarComSucesso(){
            Usuario usuario = new Usuario();
            usuario.setEmail(EMAIL);
            usuario.setSenha(SENHA);

            when(usuarioRepository.findByEmail(EMAIL)).thenReturn(Optional.of(usuario));

            usuarioService.logar(EMAIL, SENHA);

            assertThat(usuarioService.getUsuarioLogado()).contains(usuario);
        }
    }
}