package cucumber.stepdefs;

import br.edu.facima.forum.model.Animal;
import br.edu.facima.forum.model.Comentario;
import br.edu.facima.forum.model.Usuario;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ComentarStepDefs extends StepDefs{

    private final Animal fofo = new Animal("frajola", "333", "Alto");
    private Comentario comentario;

    @Dado("um animal publicado")
    public void UmAnimalPublicado() throws Exception {
        String gatoJson = converterObjetoEmJson(fofo);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/animais/publicar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gatoJson))
                .andExpect(status().isOk());

    }
    @Quando("alguem achar o animal quiser deixar um comentario {string}")
    public void alguemQuererComentarSobreOAnimal(String comentario) {
        this.comentario = new Comentario(comentario);
    }
    @Entao("deve ser possivel adicionar essa opinião como comentario")
    public void deveSerPossivelAdicionarEssaOpiniãoComoComentario() throws Exception {
        String comentarioJson = comentario.getComentario();

        mockMvc.perform(MockMvcRequestBuilders.post("/api/animais/comentar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(comentarioJson))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
