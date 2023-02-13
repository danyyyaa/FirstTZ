import sort.IntegerSort;
import sort.StringSort;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String outFileName = null;
        boolean descending = false;
        boolean integers = false;
        boolean string = false;
        int counter = 0;
        List<String> stringParameters = new ArrayList<>();

        try {
            for (int i = 0; i < 3; i++) {
                counter++;
                if (args[i].equals("-s")) {
                    string = true;
                }
                if (args[i].equals("-i")) {
                    integers = true;
                }
                if (args[i].equals("-d")) {
                    descending = true;
                }
                if (args[i].contains(".txt")) {
                    outFileName = args[i];
                    break;
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Ошибка. Передано недостаточно аргументов");
            System.exit(0);
        }
        String[] parameters = new String[args.length - counter];

        stringParameters.addAll(Arrays.asList(args).subList(counter, args.length - counter));

        boolean integersInAscending = integers && !descending;
        boolean integersInDescending = integers && descending;
        boolean stringInAscending = string && !descending;
        boolean stringInDescending = string && descending;


        int newCounter = 0;
        for (int i = counter; i < args.length; i++) {
            parameters[newCounter] = args[i];
            newCounter++;
        }

        try {
            if (integersInAscending) {
                IntegerSort integerSort = new IntegerSort(parameters, outFileName, true);
                integerSort.run();
            }
            if (integersInDescending) {
                IntegerSort integerSort = new IntegerSort(parameters, outFileName, false);
                integerSort.run();
            }
            if (stringInAscending) {
                StringSort stringSort = new StringSort(parameters, outFileName, true);
                stringSort.run();
            }
            if (stringInDescending) {
                StringSort stringSort = new StringSort(parameters, outFileName, false);
                stringSort.run();
            }
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла");
        }
    }
}