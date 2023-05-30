package br.edu.facima.doapet.services;

import br.edu.facima.doapet.exceptions.UsuarioJaExistenteException;
import br.edu.facima.doapet.model.Usuario;
import br.edu.facima.doapet.repository.UsuarioRepository;
import br.edu.facima.doapet.services.impl.UsuarioServiceImpl;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.assertEquals;
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
            Usuario usuario = new Usuario();
            usuario.setEmail("g@gmail.com");

            when(usuarioRepository.findByEmail("g@gmail.com")).thenReturn(Optional.of(usuario));

            assertThatThrownBy(() -> usuarioService.cadastrar(usuario))
                    .isInstanceOf(UsuarioJaExistenteException.class)
                    .hasMessage("Usuario já existe");
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
