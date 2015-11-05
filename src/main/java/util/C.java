package util;

/**
 * Created by Admin on 23.06.14.
 */
public class C {

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

    private static void out(int[][] XXX) {
        if (XXX == null) {
            return;
        }
        for (int i = 0; i < XXX.length; i++) {
            for (int j = 0; j < 2; j++) {
                if (j == 0)
                    System.out.print("(" + (XXX[i][j] + 1) + " ");
                else {
                    System.out.println("" + (XXX[i][j] + 1) + ")");
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


    //выводит значения массива на экран
    private static void displayAnother(double[][] array1) {
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
