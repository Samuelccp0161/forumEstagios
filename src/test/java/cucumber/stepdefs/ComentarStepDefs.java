package cucumber.stepdefs;

import br.edu.facima.forum.model.Animal;
import br.edu.facima.forum.model.Comentario;
import br.edu.facima.forum.model.Usuario;
import io.cucumber.java.PendingException;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ComentarStepDefs extends StepDefs{

    private final Animal fofo = new Animal("frajola", "333", "Alto");
    private Comentario comentario;

    @Dado("que existe um usuario logado")
    public void queExisteUmUsuarioLogado() {
        throw new PendingException();
    }

    @Dado("existe um animal publicado")
    public void UmAnimalPublicado() throws Exception {
        String gatoJson = converterObjetoEmJson(fofo);

        mockMvc.perform(post("/api/animais/publicar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gatoJson))
                .andExpect(status().isOk());

    }

    @Quando("o usuario tentar deixar o comentario {string}")
    public void alguemQuererComentarSobreOAnimal(String comentario) throws Exception {
        this.comentario = new Comentario(comentario);

        String comentarioJson = this.comentario.getComentario();


        mockMvc.perform(post("/api/animais/comentar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(comentarioJson))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Entao("este comentario deveria ter sido salvo")
    public void este_comentario_deveria_ter_sido_salvo() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    
    @Entao("o comentario salvo deveria informar quem foi autor")
    public void o_comentario_salvo_deveria_informar_quem_foi_autor() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Entao("deveria informar qual o animal comentado")
    public void deveria_informar_qual_o_animal_comentado() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
