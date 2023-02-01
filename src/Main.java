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
        List<Integer> listContent = new ArrayList<>();

        content = getString("C:\\Users\\hp\\projects\\TZ\\src\\in1.txt", content);
        content = getString("C:\\Users\\hp\\projects\\TZ\\src\\in2.txt", content);
        content = getString("C:\\Users\\hp\\projects\\TZ\\src\\in3.txt", content);


        String[] lines = content.split(("\\r?\\n"));
        int[] elements = new int[lines.length];

        for (int i = 0; i < elements.length; i++) {
            elements[i] = (Integer.parseInt(lines[i]));
        }

        for (int el : elements) {
            System.out.println(el);
        }
        mergeSort(elements);
        System.out.println("-----------------");

        for (int el : elements) {
            System.out.println(el);
        }

        //mergeSort(elements);

    }
    public static String getString(String path, String content) {
        FileReader fileReader = new FileReader();
        content += fileReader.readFileContents(path) + "\n";
        return content;
    }

    public static void mergeSort(int[] sortArr) {
        int j;
        //сортировку начинаем со второго элемента, т.к. считается, что первый элемент уже отсортирован
        for (int i = 1; i < sortArr.length; i++) {
            //сохраняем ссылку на индекс предыдущего элемента
            int swap = sortArr[i];
            for (j = i; j > 0 && swap < sortArr[j - 1]; j--) {
                //элементы отсортированного сегмента перемещаем вперёд, если они больше элемента для вставки
                sortArr[j] = sortArr[j - 1];
            }
            sortArr[j] = swap;
        }
    }


}