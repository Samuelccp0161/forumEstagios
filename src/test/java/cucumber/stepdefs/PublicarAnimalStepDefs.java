package cucumber.stepdefs;

import br.edu.facima.forum.model.Animal;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class PublicarAnimalStepDefs extends StepDefs{

    Animal animal = new Animal();

    @Dado("o nome do animal {string}")
    public void dado_o_nome_do_animal(String nome) {
        animal.setNome("sr puffins");
    }

    @Dado("o telefone para contato {string}")
    public void dado_o_telefone(String telefone) {
        animal.setTelefone("82912345678");
    }

    @Dado("a descrição do pet sendo {string}")
    public void dado_a_descricao_do_pet(String descricao) {
        animal.setDescricao("pequeno filhote a procura de um lar");
    }


    private MvcResult resultadoDaRequisicao;

    @Quando("os dados forem submetidos corretamente")
    public void tendo_os_dados_submetidos_corretamente() throws Exception {
        String publicacaoJson = converterObjetoEmJson(animal);

        var requestDaPublicacao = MockMvcRequestBuilders.post("/api/animais/publicar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(publicacaoJson);

        resultadoDaRequisicao = mockMvc.perform(requestDaPublicacao)
//                .andExpect(status().isOk());
                .andReturn();
    }

    @Entao("o animal deveria ter sido publicado")
    public void publique_o_pet_para_adocao() {
        int status = resultadoDaRequisicao.getResponse().getStatus();

        assertThat(status).isEqualTo(HttpStatus.OK.value());
    }
}
