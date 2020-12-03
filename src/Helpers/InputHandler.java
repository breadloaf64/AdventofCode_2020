package Helpers;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class InputHandler {

    public static ArrayList<String> get(String fileName) throws IOException {
        final Charset ENCODING = StandardCharsets.UTF_8;
        Path path = Paths.get(fileName);
        return (ArrayList<String>) Files.readAllLines(path, ENCODING);
    }
}
