package sort;

import util.FileReader;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IntegerSort extends FileReader {
    static String content;
    List<Integer> elements;
    int[] arrayElements;
    String fileName;
    boolean sortStatus;

    public IntegerSort(String[] args, String fileName, boolean sortStatus) {
        try {
            this.sortStatus = sortStatus;
            this.fileName = fileName;

            for (String arg : args) {
                content = FileReader.getString(arg, content);
            }
            String[] lines = content.split(("\\r?\\n"));

            this.elements = new ArrayList<>();

            for (String line : lines) {
                try {
                    this.elements.add((Integer.parseInt(line)));
                } catch (NumberFormatException ignored) {
                }
            }

            arrayElements = new int[elements.size()];

            for (int i = 0; i < arrayElements.length; i++) {
                arrayElements[i] = elements.get(i);
            }
        } catch (NullPointerException e) {
            System.out.println("Ошибка. Не переданы файлы");
            System.exit(0);
        }
    }

    public void run() throws IOException {
        arrayElements = mergeSort(arrayElements);
        FileWriter writer = new FileWriter(fileName);

        List<Integer> list = new ArrayList<>();
        if (!sortStatus) {
            for (int i = arrayElements.length - 1; i > -1; i--) {
                list.add(arrayElements[i]);
            }
            for (int el : list) {
                writer.write(el + System.getProperty("line.separator"));
            }
        } else {
            for (int el : arrayElements) {
                writer.write(el + System.getProperty("line.separator"));
            }
        }
        writer.close();
    }

    private static int[] mergeSort(int[] sortArr) {
        int[] buffer1 = Arrays.copyOf(sortArr, sortArr.length);
        int[] buffer2 = new int[sortArr.length];

        return mergeSortInner(buffer1, buffer2, 0, sortArr.length);
    }

    private static int[] mergeSortInner(int[] buffer1, int[] buffer2, int startIndex, int endIndex) {
        if (startIndex >= endIndex - 1) {
            return buffer1;
        }

        int middle = startIndex + (endIndex - startIndex) / 2;
        int[] sorted1 = mergeSortInner(buffer1, buffer2, startIndex, middle);
        int[] sorted2 = mergeSortInner(buffer1, buffer2, middle, endIndex);

        int index1 = startIndex;
        int index2 = middle;
        int destIndex = startIndex;
        int[] result = sorted1 == buffer1 ? buffer2 : buffer1;
        while (index1 < middle && index2 < endIndex) {
            result[destIndex++] = sorted1[index1] < sorted2[index2] ? sorted1[index1++] : sorted2[index2++];
        }
        while (index1 < middle) {
            result[destIndex++] = sorted1[index1++];
        }
        while (index2 < endIndex) {
            result[destIndex++] = sorted2[index2++];
        }
        return result;
    }
}
