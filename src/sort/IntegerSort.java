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

            for (int i = 0; i < args.length; i++) {
                this.content = FileReader.getString(args[i], content);
            }
            String[] lines = content.split(("\\r?\\n"));

            this.elements = new ArrayList<>();

            for (int i = 0; i < lines.length; i++) {
                try {
                    this.elements.add((Integer.parseInt(lines[i])));
                } catch (NumberFormatException e) {} // проверка на строку в целочисленных файлах
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
        /*for (int i : arrayElements) {
            System.out.println(i);
        }*/

        List<Integer> list = new ArrayList<>();
        if (!sortStatus) {
            for (int i = arrayElements.length - 1; i > 0; i--) {
                list.add(arrayElements[i]);
            }
            for (int el : list) {
                writer.write(el + System.getProperty("line.separator"));
               // System.out.println(el);
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
        int[] result = mergeSortInner(buffer1, buffer2, 0, sortArr.length);

        return result;
    }

    private static int[] mergeSortInner(int[] buffer1, int[] buffer2, int startIndex, int endIndex) {
        if (startIndex >= endIndex - 1) {
            return buffer1;
        }

        //уже отсортирован
        int middle = startIndex + (endIndex - startIndex) / 2;
        int[] sorted1 = mergeSortInner(buffer1, buffer2, startIndex, middle);
        int[] sorted2 = mergeSortInner(buffer1, buffer2, middle, endIndex);

        //слияние
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
