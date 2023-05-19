package cucumber.stepdefs;

import br.edu.facima.forum.model.Comentario;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;

public class DeletarComentario extends StepDefs{

    private Comentario comentario;

    @Dado("Um comentario impertinente tal qual {string}")
    public void DadoUmComentarioNegativo(String comentarioNegativo) {
        this.comentario = new Comentario(comentarioNegativo);
    }

    @Quando("alguem quiser apagar o mesmo")
    public void quandoQuiseremApagarOmesmo() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Entao("Deveria ser deletado com sucesso")
    public void deveriaSerDeletado() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
