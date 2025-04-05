import com.fasterxml.jackson.databind.ObjectMapper;
import model.Human;
import org.junit.jupiter.api.Test;

import java.io.InputStream;


import static org.junit.jupiter.api.Assertions.*;

public class ParsingJson {
    ObjectMapper mapper = new ObjectMapper();

    @Test
    void JsonParsingTest() throws Exception {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("Oleg.json")) {
            assertNotNull(is, "Файл Oleg.json не найден!");

            ObjectMapper mapper = new ObjectMapper();
            Human human = mapper.readValue(is, Human.class);

            assertEquals("Human", human.getTitle());
            assertEquals("Oleg", human.getDestiny());
            assertEquals("Reading", human.getHobbies());



        }
    }
}
