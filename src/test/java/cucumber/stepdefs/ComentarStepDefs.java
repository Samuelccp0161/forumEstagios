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
import org.springframework.test.web.servlet.MvcResult;

import java.io.UnsupportedEncodingException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
    private Comentario[] comentariosPublicados;

    @Dado("que existe um usuario logado")
    public void queExisteUmUsuarioLogado() throws Exception {
        cadastrarUsuarioELogar("João", "gg@gmail.com", "333", 8199809099L);
    }
    @Dado("existe um animal publicado")
    public void UmAnimalPublicado() throws Exception {
        String gatoJson = converterObjetoEmJson(animal);

        fazerUmaChamadaPost("/api/animais/publicar", gatoJson);

        animal = animalRepository.findOne(Example.of(animal)).orElseThrow();
    }

    @Quando("o usuario tentar deixar o comentario {string}")
    public void alguemQuererComentarSobreOAnimal(String mensagemComentario) throws Exception {
        comentario = new Comentario(usuarioLogado.getEmail(),animal.getId(),mensagemComentario);

        publicarComentario(comentario);
    }

    private void publicarComentario(Comentario comentario) throws Exception {
        String comentarioJson = converterObjetoEmJson(comentario);

        fazerUmaChamadaPost("/api/usuario/comentar", comentarioJson);
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


    @Dado("existem comentarios publicados")
    public void existemComentariosPublicados() throws Exception {
        Comentario comentario1 = new Comentario(usuarioLogado.getEmail(), 1L, "hey");
        Comentario comentario2 = new Comentario(usuarioLogado.getEmail(), 2L, "hi");
        Comentario comentario3 = new Comentario(usuarioLogado.getEmail() + "sdaj", 4L, "hii");

        comentariosPublicados = new Comentario[] {comentario1, comentario2, comentario3};

        publicarComentario(comentario1);
        publicarComentario(comentario2);
        publicarComentario(comentario3);
    }
    private MvcResult resultadoDaRequisicao;
    @Quando("tentar listar os comentarios")
    public void tentarListarOsComentarios() throws Exception {
        resultadoDaRequisicao = mockMvc.perform(get("/api/usuario/comentarios")
                        .content(usuarioLogado.getEmail())
                ).andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Entao("Todos os comentarios do mesmo deverão ser retornados")
    public void todosOsComentariosDesteUsuarioDeverãoSerRetornados() throws UnsupportedEncodingException {
        String responseBody = resultadoDaRequisicao.getResponse().getContentAsString();

        assertThat(responseBody).containsSubsequence(
                comentariosPublicados[0].getComentario(),
                comentariosPublicados[0].getAutorEmail(),
                comentariosPublicados[0].getAnimalId().toString()
        );
        assertThat(responseBody).containsSubsequence(
                comentariosPublicados[1].getComentario(),
                comentariosPublicados[1].getAutorEmail(),
                comentariosPublicados[1].getAnimalId().toString()
        );
        assertThat(responseBody).doesNotContain(
                comentariosPublicados[2].getComentario(),
                comentariosPublicados[2].getAutorEmail(),
                comentariosPublicados[2].getAnimalId().toString()
        );
    }
}
