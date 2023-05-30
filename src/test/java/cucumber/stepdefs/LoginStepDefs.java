package cucumber.stepdefs;

import br.edu.facima.doapet.model.Usuario;
import io.cucumber.java.es.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class LoginStepDefs extends StepDefs{

    public static final String EMAIL = "juleima@gmail.com";
    public static final String SENHA = "experimento626";
    Usuario usuario;

    @Dado("um usuario já cadastrado")
    public void um_usuario_ja_cadastrado() throws Exception {
        usuario = new Usuario();
        usuario.setNome("Maria");
        usuario.setSenha(SENHA);
        usuario.setContato(88888888888L);
        usuario.setEmail(EMAIL);

        cadastrarUsuario(usuario);
    }

    MvcResult resultadoDaRequisicao;

    @Quando("o mesmo tentar logar com os dados corretos")
    public void o_mesmo_tentar_logar_com_os_dados_corretos() throws Exception {
        String loginJson = converterObjetoEmJson(usuario);

        var requestParaLogar = post("/api/usuario/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(loginJson);

        resultadoDaRequisicao = mockMvc.perform(requestParaLogar)
                .andDo(print())  // Descomentar essa linha se quiser que printe o request e o response
                .andReturn();
    }

    @Entao("o usuario deveria ter sido logado com sucesso")
    public void o_usuario_deveria_ter_sido_logado_com_sucesso() {
        int statusResposta = resultadoDaRequisicao.getResponse().getStatus();
        assertThat(statusResposta).isEqualTo(HttpStatus.OK.value());
    }
}
