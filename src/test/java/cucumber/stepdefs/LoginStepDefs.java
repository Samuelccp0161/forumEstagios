package cucumber.stepdefs;

import br.edu.facima.forum.controller.CadastroController;
import br.edu.facima.forum.model.Usuario;
import br.edu.facima.forum.repository.UsuarioRepository;
import br.edu.facima.forum.services.UsuarioService;
import br.edu.facima.forum.services.UsuarioServiceImpl;
import io.cucumber.java.es.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginStepDefs {

    @Mock
    UsuarioService usuarioService;
    @Autowired
    UsuarioRepository repository;
    @Autowired
    CadastroController cadastro = new CadastroController(usuarioService);
    Usuario usuario;

    @Dado("um usuario já cadastrado")
    public void um_usuario_ja_cadastrado() {
        usuario = new Usuario();
        usuario.setNome("Maria");
        usuario.setSenha("experimento326");
        usuario.setContato(88888888888L);
        usuario.setEmail("juleima@gmail.com");
        usuario.setMatricula(12345678912L);

        cadastro.cadastrar(usuario);
    }
    @Quando("o mesmo tentar logar com os dados corretos")
    public void o_mesmo_tentar_logar_com_os_dados_corretos() {
        assertThat("Maria").isEqualTo(usuario.getNome());
        assertThat("experimento326").isEqualTo(usuario.getSenha());
    }
    @Entao("o usuario deveria ter sido logado com sucesso")
    public void o_usuario_deveria_ter_sido_logado_com_sucesso() {
        assertThat(usuario).isEqualTo(repository.findByEmail(usuario.getEmail()).orElseThrow());
    }
}
