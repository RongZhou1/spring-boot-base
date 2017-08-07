package apitest;

import com.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,classes = Application.class)
public class HelloControllerApiTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldSayAnotherHelloWorld() {
        String result = restTemplate.getForObject("/hello", String.class);
        assertEquals("hello", result);
    }

}