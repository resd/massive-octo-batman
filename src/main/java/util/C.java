package util;

import java.util.ArrayList;

/**
 * @author Admin
 * @since 23.06.14
 */
public class C {

    @SuppressWarnings("unused") // Can need for some test
    public static void f(String s, Object... o) {
        System.out.format(s, o);
    }

    public static void p(Object s) {
        System.out.println(s + "");
    }

    public static void out(double[][] M) {
        if (M == null) {
            return;
        }
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M.length; j++) {
                if (i == j) {
                    System.out.print("-" + "  ");
                } else {
                    System.out.print(round(M[i][j]) + "  ");
                    if (j == (M.length - 1)) {
                        System.out.println("");
                    }
                }
            }
        }
        System.out.println("");
    }

    @SuppressWarnings("unused") // Can need for some test
    private static void out(int[][] XXX) {
        if (XXX == null) {
            return;
        }
        for (int[] aXXX : XXX) {
            for (int j = 0; j < 2; j++) {
                if (j == 0)
                    System.out.print("(" + (aXXX[j] + 1) + " ");
                else {
                    System.out.println("" + (aXXX[j] + 1) + ")");
                }
            }
        }
        System.out.println("");
    }

    public static double round(double a) {
        if (a == 0)
            return 0;
        double b;
        String s = String.valueOf(a);
        char[] ch;
        int t = 0;
        ch = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            if (ch[i] == '.') {
                t = i;
                break;
            }
        }
        int t1;
        String s1 = s.substring(0, t);
        t++;
        t1 = (s.length() - t) < 5 ? s.length() : (t + 5);
        String s2 = s.substring(t, t1);
        s = s1 + '.' + s2;
        b = Double.valueOf(s);
        return b;
    }


    //������� �������� ������� �� �����
    //выводит значения массива на экран
    // TODO ask if can know encoding by compare this two strings
    @SuppressWarnings("unused") // Can need for some test
    private static void displayAnother(double[][] array1) {
        for (double[] anArray1 : array1) {
            for (double anAnArray1 : anArray1) {
                if (anAnArray1 == Double.POSITIVE_INFINITY) {
                    System.out.print("M\t\t\t");
                } else
                    System.out.print(anAnArray1 + "\t\t\t");
            }
            System.out.println("");
        }
    }

    //выводит значения массива списка в консоль
    @SuppressWarnings("unused") // Can need for some test
    private void display(ArrayList<Double> collection, String name) {

        System.out.println(name);

        for (Double element : collection) {
            System.out.print(element + " \t");
        }
        System.out.println("");

    }
}
