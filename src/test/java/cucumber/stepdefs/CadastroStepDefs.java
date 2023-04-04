package cucumber.stepdefs;
import br.edu.facima.forum.cadastro.Cadastro;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import io.cucumber.java.es.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@AutoConfigureMockMvc
public class CadastroStepDefs extends StepDefs{
    private final ObjectWriter jsonWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
    Cadastro cadastro = new Cadastro();

    @Dado("o nome {string}")
    public void testNome(String nome){
        cadastro.setNome(nome);
        assertEquals("Maria Jose", cadastro.getNome());
    }

    @Dado("o email {string}")
    public void o_email(String email) {
        cadastro.setEmail(email);
        assertEquals("fulano@facima.edu.br", cadastro.getEmail());
    }

    @Dado("o numero da matricula sendo {long}")
    public void o_ra_sendo(long matricula) {
        cadastro.setMatricula(matricula);
        assertEquals(Long.parseLong("12345678911"), cadastro.getMatricula());
    }

    @Dado("o contato {long}")
    public void o_contato(Long contato) {
        cadastro.setContato(contato);
        assertEquals(Long.parseLong("82912345678"),cadastro.getContato());
    }

    private MvcResult resultadoDoCadastro;

    @Quando("os dados forem submetidos")
    public void os_dados_forem_submetidos() throws Exception {
        String cadastroJson = jsonWriter.writeValueAsString(cadastro);

        var requestParaEnviarCadastro = post("/api/cadastrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(cadastroJson);

        resultadoDoCadastro = mockMvc.perform(requestParaEnviarCadastro)
                .andDo(print())  // Descomentar essa linha se quiser que printe o request e o response
                .andReturn();

    }

    @Entao("o cadastro foi realizado com sucesso")
    public void o_cadastro_foi_realizado_comn_sucesso() {
        int status = resultadoDoCadastro.getResponse().getStatus();
        HttpStatus statusDaResposta = HttpStatus.resolve(status);

        assertThat(statusDaResposta).isEqualTo(HttpStatus.OK);
    }
}
