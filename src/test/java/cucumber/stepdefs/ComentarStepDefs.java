package cucumber.stepdefs;

import br.edu.facima.forum.model.Animal;
import br.edu.facima.forum.model.Comentario;
import br.edu.facima.forum.model.Usuario;
import br.edu.facima.forum.repository.AnimalRepository;
import br.edu.facima.forum.repository.ComentarioRepository;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.test.web.servlet.MvcResult;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ComentarStepDefs extends StepDefs{
    @Autowired
    ComentarioRepository comentarioRepository;
    @Autowired
    AnimalRepository animalRepository;
    
    @Before
    public void afterCenario() {
        esvaziarContexto();
    }

    private final Usuario usuario = new Usuario("João", "gg@gmail.com", "333", 8199809099L);

    @Dado("que existe um usuario cadastrado")
    public void queExisteUmUsuario() throws Exception {
        cadastrarUsuario(usuario);
    }

    private Animal animal = new Animal("frajola", "333", "Alto");
    
    @Dado("um animal publicado")
    public void UmAnimalPublicado() throws Exception {
        cadastrarAnimal(animal);

        animal = animalRepository.findOne(Example.of(animal)).orElseThrow();
    }

    private Comentario comentarioSalvo;
    
    @Quando("o usuario tentar deixar o comentario {string}")
    public void alguemQuererComentarSobreOAnimal(String mensagemComentario) throws Exception {
        comentarioSalvo = new Comentario(usuario.getEmail(),animal.getId(),mensagemComentario);

        publicarComentario(comentarioSalvo);
    }

    private void publicarComentario(Comentario comentario) throws Exception {
        String comentarioJson = converterObjetoEmJson(comentario);
        System.out.println(comentarioJson);
        fazerUmaChamadaPost("/api/usuario/comentar", comentarioJson);
    }

    @Entao("este comentario deveria ter sido salvo")
    public void este_comentario_deveria_ter_sido_salvo() {
        comentarioSalvo = comentarioRepository.findOne(Example.of(comentarioSalvo)).orElseThrow();
    }
    
    @Entao("o comentario salvo deveria informar quem foi autor")
    public void o_comentario_salvo_deveria_informar_quem_foi_autor() {
        assertThat(comentarioSalvo.getAutorEmail()).isEqualTo(usuario.getEmail());
    }

    @Entao("deveria informar qual o animal comentado")
    public void deveria_informar_qual_o_animal_comentado() {
        assertThat(comentarioSalvo.getAnimalId()).isEqualTo(animal.getId());
    }

    private Comentario[] comentariosPublicados;
    
    @Dado("existem comentarios publicados")
    public void existemComentariosPublicados() throws Exception {
        Comentario comentario1 = new Comentario(usuario.getEmail(), animal.getId(), "hey");
        Comentario comentario2 = new Comentario(usuario.getEmail(), 19L, "hi");
        Comentario comentario3 = new Comentario(usuario.getEmail() + "sdaj", animal.getId(), "hii");

        comentariosPublicados = new Comentario[] {comentario1, comentario2, comentario3};

        publicarComentario(comentario1);
        publicarComentario(comentario2);
        publicarComentario(comentario3);
    }
    private MvcResult resultadoDaRequisicao;
    @Quando("tentar listar os comentarios do usuario")
    public void tentarListarOsComentariosDoUsuario() throws Exception {
        resultadoDaRequisicao = mockMvc.perform(get("/api/usuario/comentarios")
                        .content(usuario.getEmail())
                ).andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Quando("tentar listar os comentarios do animal")
    public void tentar_listar_os_comentarios_do_animal() throws Exception {
        resultadoDaRequisicao = mockMvc.perform(get("/api/animais/" + animal.getId() + "/comentarios"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }
    
    @Entao("Todos os comentarios do {} deverão ser retornados")
    public void todosOsComentariosDeverãoSerRetornados(String entity) throws UnsupportedEncodingException {
        List<Comentario> comentariosEsperados = switch (entity) {
            case "usuario" -> getComentariosDoUsuario();
            case "animal" -> getComentariosDoAnimal();
            default -> new ArrayList<>();
        };

        String responseBody = resultadoDaRequisicao.getResponse().getContentAsString();

        Comentario[] comentarios = converterJson(responseBody, Comentario[].class);
        
        assertThat(comentarios).hasSameElementsAs(comentariosEsperados);
    }

    private List<Comentario> getComentariosDoAnimal() {
        return Arrays.stream(comentariosPublicados)
                .filter(c -> c.getAnimalId().equals(animal.getId()))
                .collect(Collectors.toList());
    }

    private List<Comentario> getComentariosDoUsuario() {
        return Arrays.stream(comentariosPublicados)
                .filter(c -> c.getAutorEmail().equals(usuario.getEmail()))
                .collect(Collectors.toList());
    }
}
