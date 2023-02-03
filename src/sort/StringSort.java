package sort;

import util.FileReader;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StringSort {
    String[] args;
    String content;
    String fileName;
    boolean sortStatus;
    List<String> elements = new ArrayList<>();


    public StringSort(String[] args, String fileName, boolean sortStatus) {
        this.args = args;
        this.fileName = fileName;
        this.sortStatus = sortStatus;

        for (int i = 0; i < args.length; i++) {
            this.content = getString(args[i], content);
        }
        String[] lines = content.split(("\\r?\\n"));
        for (int i = 0; i < lines.length; i++) {
            elements.add(lines[i]);
        }
    }

    public String getString(String path, String content) {
        FileReader fileReader = new FileReader();
        content += fileReader.readFileContents(path) + "\n";
        return content;
    }

    public void run() throws IOException {
        elements.sort(String.CASE_INSENSITIVE_ORDER);

        FileWriter writer = new FileWriter(fileName);
        if (sortStatus) {
            for (int i = elements.size() - 1; i > 0; i--) {
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
