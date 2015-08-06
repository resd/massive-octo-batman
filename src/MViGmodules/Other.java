package MViGmodules;

import java.util.ArrayList;

/**
 * Created by Admin on 14.07.15.
 */
public class Other {

    public static final Other INSTANCE = new Other();

    public double[][] cloneMatrix(double[][] a) {
        if (a == null) return null;
        double[][] clone = a.clone();

        for (int i = 0; i < a.length; i++) {
            clone[i] = a[i].clone();
        }
        return clone;
    }

    public int[][] cloneMatrix(int[][] a) {
        int[][] clone = a.clone();

        for (int i = 0; i < a.length; i++) {
            clone[i] = a[i].clone();
        }
        return clone;
    }

    //выводит значения массива списка в консоль
    private void display(ArrayList<Double> collection, String name) {

        System.out.println(name);

        for (Double element : collection) {
            System.out.print(element + " \t");
        }
        System.out.println("");

    }

    //выводит значения массива на экран
    private void display(double[][] array1) {
        for (int i = 0; i < array1.length; i++) {
            for (int j = 0; j < array1[i].length; j++) {
                if (array1[i][j] == Double.POSITIVE_INFINITY) {
                    System.out.print("M\t\t\t");
                } else
                    System.out.print(array1[i][j] + "\t\t\t");
            }
            System.out.println("");
        }
    }
}
