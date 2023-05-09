package cucumber.stepdefs;

import br.edu.facima.forum.controller.UsuarioController;
import br.edu.facima.forum.model.Usuario;
import br.edu.facima.forum.repository.UsuarioRepository;
import br.edu.facima.forum.services.UsuarioService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.es.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class LoginStepDefs extends StepDefs{

    public static final String EMAIL = "juleima@gmail.com";
    public static final String SENHA = "experimento626";

//    @Autowired
//    UsuarioController cadastro;
//    @Autowired
//    UsuarioRepository repository;
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

    private void cadastrarUsuario(Usuario usuario) throws Exception {
        String cadastroJson = converterObjetoEmJson(usuario);

        var requestParaEnviarCadastro = post("/api/cadastrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(cadastroJson);

        mockMvc.perform(requestParaEnviarCadastro)
                .andDo(print())  // Descomentar essa linha se quiser que printe o request e o response
                .andExpect(status().isOk());
    }

    MvcResult resultadoDaRequisicao;

    @Quando("o mesmo tentar logar com os dados corretos")
    public void o_mesmo_tentar_logar_com_os_dados_corretos() throws Exception {
        String loginJson = converterObjetoEmJson(usuario);

        var requestParaLogar = post("/api/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(loginJson);

        resultadoDaRequisicao = mockMvc.perform(requestParaLogar)
                .andDo(print())  // Descomentar essa linha se quiser que printe o request e o response
                .andReturn();

//        if (repository.findByEmail(usuario.getEmail()).orElseThrow().toString().equals(loginJson)){
//            resultadoDaRequisicao.getResponse().setStatus(200);
//        }

    }

    @Entao("o usuario deveria ter sido logado com sucesso")
    public void o_usuario_deveria_ter_sido_logado_com_sucesso() {
        int statusResposta = resultadoDaRequisicao.getResponse().getStatus();
        assertThat(statusResposta).isEqualTo(HttpStatus.OK.value());
    }
}
