package topPhrases;

import com.google.common.collect.Multisets;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author Yury Khodanitcky
 * @see TopPhrases
 */
public class TopPhrasesTest {

    private TopPhrases topPhrases;

    @Before
    public void initiate() {
        this.topPhrases = new TopPhrases();
    }

    @Test
    public void test() throws IOException {
        List<Path> path = topPhrases.getPath(new File("example.txt"), 500);
        assertEquals(path.size(), 97);
        assertEquals(Multisets.copyHighestCountFirst(topPhrases.getTop(path)).count("Джадак"), 341);
    }

    @Test
    public void test1() throws IOException {
        List<Path> path = topPhrases.getPath(new File("example.txt"), 200);
        assertEquals(path.size(), 244);
        assertEquals(Multisets.copyHighestCountFirst(topPhrases.getTop(path)).count("Республикой"), 3);
    }

    @Test
    public void test2() throws IOException {
        List<Path> path = topPhrases.getPath(new File("example.txt"), 50);
        assertEquals(path.size(), 978);
        assertEquals(Multisets.copyHighestCountFirst(topPhrases.getTop(path)).count("микрофон"), 5);
    }

}
