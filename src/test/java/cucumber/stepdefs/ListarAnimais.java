package cucumber.stepdefs;

import br.edu.facima.forum.model.Animal;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import java.io.UnsupportedEncodingException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ListarAnimais extends StepDefs{

    private final Animal gato = new Animal("Fran", "829886645", "Muito Birrenta");
    private final Animal cachorro = new Animal("Bart", "666666666", "Doido");


    @Dado("que já existem animais cadastrados")
    public void DeveriaTerAnimaisCadastrados() throws Exception {
        String gatoJson = converterObjetoEmJson(gato);

        mockMvc.perform(post("/api/animais/publicar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gatoJson)
                ).andExpect(status().isOk());

        String dogJson = converterObjetoEmJson(cachorro);

        mockMvc.perform(post("/api/animais/publicar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(dogJson)
                ).andExpect(status().isOk());
    }

    private MvcResult resultadoDaRequisicao;

    @Quando("tentar listar os animais")
    public void tentarListarOsAnimais() throws Exception {
        resultadoDaRequisicao = mockMvc.perform(get("/api/animais"))
                .andExpect(status().isOk())
                .andReturn();
    }
    @Entao("Todos os animais deverão ser retornados")
    public void todosOsAnimaisDeveraoSerRetornados() throws UnsupportedEncodingException {
        String responseBody = resultadoDaRequisicao.getResponse().getContentAsString();

        assertThat(responseBody).containsSubsequence(
                gato.getNome(),
                gato.getTelefone(),
                gato.getDescricao()
        );
        assertThat(responseBody).containsSubsequence(
                cachorro.getNome(),
                cachorro.getTelefone(),
                cachorro.getDescricao()
        );
    }

}
