package cucumber.stepdefs;

import br.edu.facima.forum.model.Usuario;
import br.edu.facima.forum.services.UsuarioService;
import io.cucumber.java.es.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.mockito.Mock;

public class LoginStepDefs {

    @Mock
    UsuarioService usuarioService;
    Usuario usuario;

    @Dado("um usuario já cadastrado")
    public void um_usuario_ja_cadastrado() {
        usuario = new Usuario();
        usuario.setNome("Maria");
        usuario.setContato(88888888888L);
        usuario.setEmail("juleima@gmail.com");
        usuario.setMatricula(12345678912L);

        usuarioService.cadastrar(usuario);
    }
    @Quando("o mesmo tentar logar com os dados corretos")
    public void o_mesmo_tentar_logar_com_os_dados_corretos() {

        throw new io.cucumber.java.PendingException();
    }
    @Entao("o usuario deveria ter sido logado com sucesso")
    public void o_usuario_deveria_ter_sido_logado_com_sucesso() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
