package cucumber.stepdefs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

public class StepDefs {
    @Autowired protected MockMvc mockMvc;
}
