package Assignment3;

import java.io.*;
import java.nio.file.*;

public class FileHandler {

    // Reads a file by its path
    public String readFile(String filePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }

    // Writes a file given a path and content (as Strings)
    public void writeFile(String filePath, String content) throws IOException {
        Files.write(Paths.get(filePath), content.getBytes());
    }
}

