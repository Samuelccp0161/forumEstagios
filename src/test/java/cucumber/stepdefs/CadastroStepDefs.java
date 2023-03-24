package cucumber.stepdefs;
import br.edu.facima.forum.cadastro.Cadastro;
import io.cucumber.java.es.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@AutoConfigureMockMvc
public class CadastroStepDefs extends StepDefs{
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
    @Quando("os dados forem submetidos")
    public void os_dados_forem_submetidos() {
//        mockMvc.perform(post("/api/cadastrar")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(cadastro))

        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Entao("o cadastro foi realizado com sucesso")
    public void o_cadastro_foi_realizado_comn_sucesso() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
