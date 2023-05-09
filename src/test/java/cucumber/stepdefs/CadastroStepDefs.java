package cucumber.stepdefs;

import br.edu.facima.forum.model.Usuario;
import br.edu.facima.forum.repository.UsuarioRepository;
import br.edu.facima.forum.services.UsuarioServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import io.cucumber.java.es.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class CadastroStepDefs extends StepDefs {
    @Autowired
    private UsuarioRepository usuarioRepository;

    Usuario usuario = new Usuario();

    @Dado("o nome {string}")
    public void adicionando_um_nome(String nome){
        usuario.setNome(nome);
    }

    @Dado("o email {string}")
    public void adicionando_o_email(String email) {
        usuario.setEmail(email);
    }

    @Dado("o contato {long}")
    public void adicionando_o_contato(Long contato) {
        usuario.setContato(contato);
    }

    @Dado("a senha {string}")
    public void criando_senha(String senha){
        usuario.setSenha(senha);
    }

    @Quando("os dados forem submetidos")
    public void os_dados_forem_submetidos() throws Exception {
        String cadastroJson = converterObjetoEmJson(usuario);

        var requestParaEnviarCadastro = post("/api/cadastrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(cadastroJson);

        mockMvc.perform(requestParaEnviarCadastro)
                .andDo(print())  // Descomentar essa linha se quiser que printe o request e o response
                .andExpect(status().isOk());

    }

    @Entao("o aluno deveria ter sido cadastrado com sucesso")
    public void oAlunoDeveriaTerSidoCadastradoComSucesso() throws JsonProcessingException {
        Usuario usuarioConsultado = usuarioRepository.findByEmail(usuario.getEmail()).orElseThrow();
        System.out.println(usuarioConsultado);
        assertThat(usuarioConsultado).isEqualTo(usuario);
    }
}
