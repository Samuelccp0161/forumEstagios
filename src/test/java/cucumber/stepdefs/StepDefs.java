package cucumber.stepdefs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

public class StepDefs {
    @Autowired protected MockMvc mockMvc;

    private final ObjectWriter jsonWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();

    protected String converterObjetoEmJson(Object obj) {
        try {
            return jsonWriter.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
