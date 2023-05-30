package cucumber.stepdefs;

import br.edu.facima.doapet.model.Animal;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.springframework.test.web.servlet.MvcResult;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ListarAnimais extends StepDefs{
    private Animal[] animaisPublicados;


    @Dado("que já existem animais cadastrados")
    public void DeveriaTerAnimaisCadastrados() throws Exception {
        Animal gato = new Animal("Fran", "829886645", "Muito Birrenta");
        Animal cachorro = new Animal("Bart", "666666666", "Doido");

        animaisPublicados = new Animal[] {gato, cachorro};

        for (var animal : animaisPublicados) {
            cadastrarAnimal(animal);
        }
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
        System.out.println(responseBody);
        System.out.println();
        System.out.println(Arrays.toString(animaisPublicados));
        var animais = converterJson(responseBody, Animal[].class);

        assertThat(animais).hasSameElementsAs(Arrays.asList(animaisPublicados));
    }

}
