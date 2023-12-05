package Assignment3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileHandlerTest {

    private static final String TEST_FILE_PATH = "testFile.txt";
    private static final String TEST_CONTENT = "This is a test content.";

    // Set up test data
    @BeforeEach
    void setUp() {
        try {
            // Create the test file with some content
            Files.write(Paths.get(TEST_FILE_PATH), TEST_CONTENT.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testReadFile() {
        FileHandler fileHandler = new FileHandler();

        try {
            String readContent = fileHandler.readFile(TEST_FILE_PATH);
            assertEquals(TEST_CONTENT, readContent);
        } catch (IOException e) {
            e.printStackTrace();
            assertTrue(false, "IOException occurred during read test.");
        }
    }

    @Test
    void testWriteFile() {
        FileHandler fileHandler = new FileHandler();

        try {
            // Modify content and write it back
            String newContent = "Updated content for testing.";
            fileHandler.writeFile(TEST_FILE_PATH, newContent);

            // Read again and compare updated content
            String readContent = fileHandler.readFile(TEST_FILE_PATH);
            assertEquals(newContent, readContent);
        } catch (IOException e) {
            e.printStackTrace();
            assertTrue(false, "IOException occurred during write test.");
        }
    }
}
