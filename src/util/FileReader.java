package util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileReader {
    protected static String getString(String path, String content) {
        try {
            String fileData = Files.readString(Path.of(path));
            String[] fileLine = fileData.split("\\r?\\n");
            for (String line : fileLine) {
                content += line + "\n";
            }
            if (content.contains("null")) {
                StringBuilder sb = new StringBuilder(content);
                content = sb.delete(0,4).toString();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return content;
    }
}
