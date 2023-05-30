package cucumber.stepdefs;

import br.edu.facima.doapet.model.Animal;
import br.edu.facima.doapet.model.Usuario;
import br.edu.facima.doapet.repository.AnimalRepository;
import br.edu.facima.doapet.repository.ComentarioRepository;
import br.edu.facima.doapet.repository.UsuarioRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class StepDefs {
    @Autowired protected MockMvc mockMvc;

    @Autowired ComentarioRepository comentarioRepository;
    @Autowired AnimalRepository animalRepository;
    @Autowired UsuarioRepository usuarioRepository;
    private final ObjectWriter jsonWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
    private final ObjectReader jsonReader = new ObjectMapper().configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true).reader();

    protected String converterObjetoEmJson(Object obj) {
        try {
            return jsonWriter.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    protected <T> T converterJson(String json, Class<T> type) {
        try {
            return jsonReader.readValue(json, type);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
//    protected <T> List<T> converterJsonArray(String json, Class<T> type) {
//        try {
//            return jsonReader.readValue(json);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }


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

    protected void fazerUmaChamadaPost(String url, String conteudoJson) throws Exception {
        mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(conteudoJson))
                .andExpect(status().isOk());
    }

    protected void esvaziarContexto() {
        comentarioRepository.deleteAll();
        usuarioRepository.deleteAll();
        animalRepository.deleteAll();
    }

    protected void cadastrarAnimal(Animal animal) throws Exception {
        String json = converterObjetoEmJson(animal);

        fazerUmaChamadaPost("/api/animais/publicar", json);
    }

}
