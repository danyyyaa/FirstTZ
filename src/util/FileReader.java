package util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileReader {
    private static String readFileContents(String path) throws IOException {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Ошибка. Невозможно прочитать файл. Возможно файл не находится в нужной директории.");
            return null;
        }
    }

    protected static String getString(String path, String content) {
        try {
            content += readFileContents(path) + "\n";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return content;
    }
}
