package common;

import java.util.Arrays;

/**
 * Created by Admin on 08.12.14.
 */
public class BruteforceAlgo {
    static int[] x;// = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
    static int originalsize;
    static boolean yes = true;
    static double[][] M0 = {
            {0, 0, 83, 9, 30, 6, 50}, // 1
            {0, 0, 66, 37, 17, 12, 26}, // 2
            {29, 1, 0, 19, 0, 12, 5},  // 3
            {32, 83, 66, 0, 49, 0, 80}, // 4
            {3, 21, 56, 7, 0, 0, 28}, // 5
            {0, 85, 8, 42, 89, 0, 0},  // 6
            {18, 0, 0, 0, 58, 13, 0}   // 7
    };
    long sysTime;
    double minValue = Double.MAX_VALUE;
    int[] minPath = null;

    public void main(double[][] M0) {
        sysTime = System.currentTimeMillis();
        originalsize = M0.length;
        x = new int[originalsize];
        for (int i = 0; i < originalsize; i++) {
            x[i] = i;
        }
        double path;
        while (yes) {
            //System.out.println(c + ": " + Arrays.toString(x));
            path = 0;
            for (int i = 0; i < originalsize - 1; i++) {// Путь пройденный по индексам из x[]
                path += M0[x[i]][x[i + 1]];
            }
            path += M0[x[originalsize - 1]][x[0]];
            if (path < minValue) {
                minValue = path;
                minPath = x.clone();
            }
            next(x);
        }
        System.out.println(minValue + ": " + Arrays.toString(minPath));
    }

    public static void main(String[] args) {
        originalsize = M0.length;
        x = new int[originalsize];
        for (int i = 0; i < originalsize; i++) {
            x[i] = i;
        }
        double minValue = Double.MAX_VALUE;
        int[] minPath = null;
        double path;
        while (yes) {
            //System.out.println(c + ": " + Arrays.toString(x));
            path = 0;
            for (int i = 0; i < originalsize - 1; i++) {// Путь пройденный по индексам из x[]
                path += M0[x[i]][x[i + 1]];
            }
            path += M0[x[originalsize - 1]][x[0]];
            if (path < minValue) {
                minValue = path;
                minPath = x.clone();
            }
            next(x);
        }
        System.out.println(minValue + ": " + Arrays.toString(minPath));
    }

    private static boolean next(int[] X) {
        int i = originalsize - 2;
        int c;
        //{поиск i}
        while (i > 0 && X[i] > X[i + 1]) i--;
        if (i > 0) {
            int j = i;
            //{поиск j}
            while (j < originalsize - 1 && X[j + 1] > X[i]) j++;
            c = X[i];
            X[i] = X[j];
            X[j] = c;
            /**
             for j:=i+1 to (originalsize+i) div 2 do
             Swap(x[j],x[originalsize-j+i+1]);
             */
            //int lim = div(originalsize + i, 2);
            // {x[i+1]>x[i+2]>...>x[originalsize]};
            /*for (j = i + 1; j < lim; j++) {
                c = x[j];
                x[j] = x[originalsize - j + i];
                x[originalsize - j + i] = c;
            }*/
            for (int t = i + 1; t < originalsize - 1; t++) {
                for (int k = i + 1; k < originalsize - 1; k++) {
                    if (X[k] > X[k + 1]) {
                        c = X[k];
                        X[k] = X[k + 1];
                        X[k + 1] = c;
                    }
                }
            }
            yes = true;
        } else yes = false;
        return yes;
    }

    public String getPath() {
        return Arrays.toString(minPath);
    }

    public double getSum(double[][] a) {
        return minValue;
    }

    public long getTime() {
        return System.currentTimeMillis() - sysTime;
    }

    public void setM0(double[][] a) {
        double[][] clone = a.clone();

        for (int i = 0; i < a.length; i++) {
            clone[i] = a[i].clone();
        }
        this.M0 = clone;
        originalsize = a.length;
    }
}