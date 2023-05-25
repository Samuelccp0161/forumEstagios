package cucumber.stepdefs;

import br.edu.facima.forum.model.Animal;
import br.edu.facima.forum.model.Comentario;
import br.edu.facima.forum.repository.AnimalRepository;
import br.edu.facima.forum.repository.ComentarioRepository;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;

public class DeletarComentario extends StepDefs{
    @Autowired
    AnimalRepository animalRepository;
    @Autowired
    ComentarioRepository comentarioRepository;
    Comentario comentario;
    Animal animal = new Animal("miu","82965", "mimimimi");
    @Dado("um animal publicado")
    public void um_animal_publicado() throws Exception {
        String animalJson = converterObjetoEmJson(animal);
        fazerUmaChamadaPost("/api/animais/publicar", animalJson);
        animal = animalRepository.findOne(Example.of(animal)).orElseThrow();
    }
    @Dado("Um usuario comentanto")
    public void um_usuario_comentanto() throws Exception {
        criarUsuarioELogar("meimei", "meimei@gmail.com", "1235", 153L);

        comentario = new Comentario(usuarioLogado.getEmail(), animal.getId(), "nojento");

        String comentarioJson = converterObjetoEmJson(comentario);

        fazerUmaChamadaPost("/api/usuario/comentar", comentarioJson);
    }
    @Quando("o usuario quiser deletar o comentario")
    public void o_usuario_quiser_deletar_o_comentario() throws Exception {
        String comentarioDeletado = converterObjetoEmJson(comentario);

        fazerUmaChamadaPost("/api/usuario/deletar", comentarioDeletado);
    }
    @Entao("o comentario Deveria ser deletado com sucesso")
    public void o_comentario_deveria_ser_deletado_com_sucesso() {
        try{
            assertEquals(comentario, comentarioRepository.findOne(Example.of(comentario)).orElseThrow());
        } catch (NoSuchElementException e){
            assertEquals("empty value", e.getMessage());
        }
    }
}
