import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileReader {
    public static String readFileContents(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Ошибка. Невозможно прочитать файл. Возможно файл не находится в нужной директории.");
            return null;
        }
    }
}
