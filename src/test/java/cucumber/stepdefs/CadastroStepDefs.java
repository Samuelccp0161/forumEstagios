package cucumber.stepdefs;

import br.edu.facima.forum.model.Usuario;
import br.edu.facima.forum.repository.UsuarioRepository;
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
public class CadastroStepDefs extends StepDefs{
    @Autowired
    private UsuarioRepository usuarioRepository;

    private final ObjectWriter jsonWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
    Usuario usuario = new Usuario();

    @Dado("o nome {string}")
    public void testNome(String nome){
        usuario.setNome(nome);
    }

    @Dado("o email {string}")
    public void o_email(String email) {
        usuario.setEmail(email);
    }

    @Dado("o numero da matricula sendo {long}")
    public void o_ra_sendo(long matricula) {
        usuario.setMatricula(matricula);
    }

    @Dado("o contato {long}")
    public void o_contato(Long contato) {
        usuario.setContato(contato);
    }

    @Quando("os dados forem submetidos")
    public void os_dados_forem_submetidos() throws Exception {
        String cadastroJson = jsonWriter.writeValueAsString(usuario);

        var requestParaEnviarCadastro = post("/api/cadastrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(cadastroJson);

        mockMvc.perform(requestParaEnviarCadastro)
                .andDo(print())  // Descomentar essa linha se quiser que printe o request e o response
                .andExpect(status().isOk());

    }

    @Entao("o aluno deveria ter sido cadastrado com sucesso")
    public void oAlunoDeveriaTerSidoCadastradoComSucesso() {
        Usuario usuarioConsultado = usuarioRepository.findByEmail(usuario.getEmail()).orElseThrow();
        assertThat(usuarioConsultado).isEqualTo(usuario);
    }
}
