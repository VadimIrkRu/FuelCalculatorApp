import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExampleTest {

    @Test
    void simpleTest() {
        int expected = 2;
        int actual = 1 + 1;
        assertEquals(expected, actual, "1 + 1 должно быть 2");
    }
}
