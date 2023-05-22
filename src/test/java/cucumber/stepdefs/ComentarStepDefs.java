package cucumber.stepdefs;

import br.edu.facima.forum.model.Animal;
import br.edu.facima.forum.model.Comentario;
import br.edu.facima.forum.model.Usuario;
import br.edu.facima.forum.repository.AnimalRepository;
import br.edu.facima.forum.repository.ComentarioRepository;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.MediaType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ComentarStepDefs extends StepDefs{

    @Autowired
    ComentarioRepository comentarioRepository;

    @Autowired
    AnimalRepository animalRepository;

    private Animal animal = new Animal("frajola", "333", "Alto");
    private Comentario comentario;
    private Comentario comentarioConsultado;
    private Usuario usuarioLogado;

    @Dado("que existe um usuario logado")
    public void queExisteUmUsuarioLogado() throws Exception {
        usuarioLogado = new Usuario("João", "gg@gmail.com", "333");
        usuarioLogado.setContato(8199809099L);
        cadastrarUsuario(usuarioLogado);
        logarUsuario(usuarioLogado);
    }

    @Dado("existe um animal publicado")
    public void UmAnimalPublicado() throws Exception {
        String gatoJson = converterObjetoEmJson(animal);

        mockMvc.perform(post("/api/animais/publicar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gatoJson))
                .andExpect(status().isOk());

        animal = animalRepository.findOne(Example.of(animal)).orElseThrow();
    }

    @Quando("o usuario tentar deixar o comentario {string}")
    public void alguemQuererComentarSobreOAnimal(String comentario) throws Exception {
        this.comentario = new Comentario(comentario);

        String comentarioJson = converterObjetoEmJson(this.comentario);

        mockMvc.perform(post("/api/usuario/comentar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(comentarioJson))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Entao("este comentario deveria ter sido salvo")
    public void este_comentario_deveria_ter_sido_salvo() {
        comentarioConsultado = comentarioRepository.findOne(Example.of(comentario)).orElseThrow();
    }
    
    @Entao("o comentario salvo deveria informar quem foi autor")
    public void o_comentario_salvo_deveria_informar_quem_foi_autor() {
        assertThat(comentarioConsultado.getAutorEmail()).isEqualTo(usuarioLogado.getEmail());
    }

    @Entao("deveria informar qual o animal comentado")
    public void deveria_informar_qual_o_animal_comentado() {
        assertThat(comentarioConsultado.getAnimalId()).isEqualTo(animal.getId());

    }
}
