package cucumber.stepdefs;

import br.edu.facima.forum.model.Animal;
import io.cucumber.java.Status;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ComentarStepDefs extends StepDefs{

    private final Animal gato = new Animal("frajola", "333", "Alto");
    @Dado("um animal publicado")
    public void UmAnimalPublicado() throws Exception {
        String gatoJson = converterObjetoEmJson(gato);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/animais/publicar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gatoJson))
                .andExpect(status().isOk());

    }
    @Quando("alguem achar o animal {string}")
    public void alguemAcharOAnimal(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Entao("deve ser possivel adicionar essa opinião como comentario")
    public void deveSerPossivelAdicionarEssaOpiniãoComoComentario() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
