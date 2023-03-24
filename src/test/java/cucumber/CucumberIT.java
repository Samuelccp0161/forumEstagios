package cucumber;

import br.edu.facima.forum.ForumApplication;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty", "json:target/cucumber.json"}, features = "src/test/features")
@CucumberContextConfiguration
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = ForumApplication.class, loader = SpringBootContextLoader.class)
public class CucumberIT {
}
