package cucumber.stepdefs;

import br.edu.facima.forum.model.Usuario;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class StepDefs {
    @Autowired protected MockMvc mockMvc;

    private final ObjectWriter jsonWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();

    protected String converterObjetoEmJson(Object obj) {
        try {
            return jsonWriter.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    protected void cadastrarUsuario(Usuario usuario) throws Exception {
        String cadastroJson = converterObjetoEmJson(usuario);

        var requestParaEnviarCadastro = post("/api/usuario/cadastrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(cadastroJson);

        mockMvc.perform(requestParaEnviarCadastro)
                .andDo(print())  // Descomentar essa linha se quiser que printe o request e o response
                .andExpect(status().isOk());
    }

    protected void logarUsuario(Usuario usuario) throws Exception {
        String loginJson = converterObjetoEmJson(usuario);

        var requestParaLogar = post("/api/usuario/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(loginJson);

        mockMvc.perform(requestParaLogar)
                .andDo(print());
    }
}
