import com.fasterxml.jackson.databind.ObjectMapper;
import model.Destiny;
import model.Human;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

public class ParsingJson {

    @Test
    void JsonParsingTest() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        try (InputStream is = getClass().getClassLoader().getResourceAsStream("Oleg.json")) {
            assertNotNull(is, "File Oleg.json not found!");

            Human human = mapper.readValue(is, Human.class);
            assertEquals("Human", human.getTitle());

            List<Destiny> destinyList = human.getDestiny();
            assertNotNull(destinyList);
            assertEquals(1, destinyList.size());

            Destiny firstDestiny = destinyList.get(0);
            assertEquals("Oleg", firstDestiny.getName());
            assertEquals(27, firstDestiny.getAge());
            assertEquals("Moscow", firstDestiny.getFrom());

            List<String> hobbies = human.getHobbies();
            assertEquals(Arrays.asList("Reading", "Music", "Car"), hobbies);

        }
    }
}
