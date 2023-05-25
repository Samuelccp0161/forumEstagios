package cucumber.stepdefs;

import br.edu.facima.forum.model.Animal;
import br.edu.facima.forum.model.Comentario;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;

public class DeletarComentario extends StepDefs{
    Comentario comentario;
    Animal animal = new Animal();
    @Dado("um animal publicado")
    public void um_animal_publicado() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Dado("Um usuario comentanto")
    public void um_usuario_comentanto() throws Exception {
        criarUsuarioELogar("meimei", "meimei@gmail.com", "1235", 153L);
    }
    @Entao("o comentario Deveria ser deletado com sucesso")
    public void o_comentario_deveria_ser_deletado_com_sucesso() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
