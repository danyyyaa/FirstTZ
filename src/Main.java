import java.io.FileWriter;
import java.io.IOException;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        /*for (int i = 0; i < args.length; i++) {
            mergeSort(args[i]);
        }*/

        String content = "";
        content = getString("C:\\Users\\hp\\projects\\TZ\\src\\in1.txt", content);
        content = getString("C:\\Users\\hp\\projects\\TZ\\src\\in2.txt", content);
        content = getString("C:\\Users\\hp\\projects\\TZ\\src\\in3.txt", content);

        String[] lines = content.split(("\\r?\\n"));

        for (String line : lines) {
            System.out.println(line);
        }


        List<Integer> elements = new ArrayList<>();

        for (int i = 0; i < lines.length; i++) {
            try {
                elements.add((Integer.parseInt(lines[i])));
            } catch (NumberFormatException e) {
                //System.out.println("fsdg");
            }
        }
        int[] arrayElements = new int[elements.size()];

        for (int i = 0; i < arrayElements.length; i++) {
            arrayElements[i] = elements.get(i);
        }

        arrayElements = mergeSort(arrayElements);
        System.out.println("-----------------");

        for (int el : arrayElements) {
            System.out.println(el);
        }


        /*String[] lines = content.split(("\\r?\\n"));
        int[] elements = new int[lines.length];


        for (int i = 0; i < elements.length; i++) {
            elements[i] = (Integer.parseInt(lines[i]));
        }

        for (int el : elements) {
            System.out.println(el);
        }
        System.out.println("-----------------");

        elements = mergeSort(elements);

        for (int el : elements) {
            System.out.println(el);
        }*/
        }


    public static String getString(String path, String content) {
        FileReader fileReader = new FileReader();
        content += fileReader.readFileContents(path) + "\n";
        return content;
    }

    public static int[] mergeSort(int[] sortArr) {
        int[] buffer1 = Arrays.copyOf(sortArr, sortArr.length);
        int[] buffer2 = new int[sortArr.length];
        int[] result = mergeSortInner(buffer1, buffer2, 0, sortArr.length);

        return result;
    }

    public static int[] mergeSortInner(int[] buffer1, int[] buffer2, int startIndex, int endIndex) {
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