package sort;

import util.FileReader;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringSort extends FileReader {
    String[] args;
    String content;
    String fileName;
    boolean sortStatus;
    List<String> elements = new ArrayList<>();

    public StringSort(String[] args, String fileName, boolean sortStatus) {
        this.args = args;
        this.fileName = fileName;
        this.sortStatus = sortStatus;

        for (String arg : args) {
            this.content = FileReader.getString(arg, content);
        }
        assert content != null;
        String[] lines = content.split(("\\r?\\n"));
        elements.addAll(Arrays.asList(lines));
    }

    public void run() throws IOException {
        elements.sort(String.CASE_INSENSITIVE_ORDER);

        FileWriter writer = new FileWriter(fileName);
        if (sortStatus) {
            for (int i = elements.size() - 1; i > -1; i--) {
                writer.write(elements.get(i).trim() + System.getProperty("line.separator"));
            }
        } else {
            for (String s : elements) {
                writer.write(s.trim() + System.getProperty("line.separator"));
            }
        }
        writer.close();
    }
}
