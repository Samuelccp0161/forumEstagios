package cucumber.stepdefs;

import br.edu.facima.forum.model.PublicarAnimal;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.multipart.support.RequestPartServletServerHttpRequest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PublicarAnimalStepDefs extends StepDefs{
    PublicarAnimal publicar = new PublicarAnimal();

    @Dado("o nome (S*)")
    public void dado_o_nome_do_animal(String nome) {
        publicar.setNome("sr puffins");
        assertThat(nome).isEqualTo(publicar.getNome());
    }
    @Dado("o telefone {long}")
    public void dado_o_telefone(Long telefone) {
        publicar.setTelefone(82912345678L);

        assertThat(telefone).isEqualTo(publicar.getTelefone());
    }
    @Dado("o endereço {string}")
    public void dado_o_endereco(String endereco) {
        publicar.setEndereco("cachoeirinha");

        assertThat(endereco).isEqualTo(publicar.getEndereco());
    }

    @Dado("a descrição do pet sendo {string}")
    public void dado_a_descricao_do_pet(String descricao) {
        publicar.setDescricao("pequeno filhote a procura de um lar");

        assertThat(descricao).isEqualTo(publicar.getDescricao());
    }
    @Quando("os dados forem submetidos corretamente")
    public void tendo_os_dados_submetidos_corretamente() throws Exception {
        String publicacaoJson = converterObjetoEmJson(publicar);

        var requestDaPublicacao = MockMvcRequestBuilders.post("/api/publicar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(publicacaoJson);

        mockMvc.perform(requestDaPublicacao)
                .andExpect(status().isOk());
    }
    @Entao("publique o pet para adocao")
    public void publique_o_pet_para_adocao() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
