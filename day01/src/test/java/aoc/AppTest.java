package aoc;

import org.junit.Test;

import java.io.IOException;

public class AppTest {
    @Test
    public void verifyNoExceptionThrown() throws IOException {
        App.main(new String[]{"input.txt"});
    }
}
