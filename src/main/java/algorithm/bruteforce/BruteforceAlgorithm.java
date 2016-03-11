package algorithm.bruteforce;

import algorithm.bab.util.Other;

/**
 * @author Admin
 * @since 08.12.14
 */
public class BruteForceAlgorithm {
    private static int originalSize;
    private static boolean yes = true;
    private static final double[][] example =
            {
                    {
                            0, 90, 80, 40, 100
                    },
                    {
                            60, 0, 40, 50, 70
                    },
                    {
                            50, 30, 0, 60, 20
                    },
                    {
                            10, 70, 20, 0, 50
                    },
                    {
                            20, 40, 50, 20, 0
                    }
            };
    private long sysTime;
    private double minValue = Double.MAX_VALUE;
    private int[] minPath = null;

    public void main(double[][] M0Input) {
        double[][] M0 = Other.cloneMatrix(M0Input);
        yes = true;
        sysTime = System.currentTimeMillis();
        originalSize = M0.length;
        int[] x = new int[originalSize]; // = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
        for (int i = 0; i < originalSize; i++) {
            x[i] = i;
        }
        double path;
        boolean overhead = false;
        while (yes) {
            //System.out.println(c + ": " + Arrays.toString(x));
            path = 0;
            for (int i = 0; i < originalSize - 1; i++) {// Путь пройденный по индексам из x[]
                path += M0[x[i]][x[i + 1]];
                if (path > minValue) {
                    overhead = true;
                    break;
                }
            }
            if (!overhead) {
                path += M0[x[originalSize - 1]][x[0]];
                if (path < minValue) {
                    minValue = path;
                    minPath = x.clone();
                }
            }
            overhead = false;
            next(x);
        }
    }

    public static void main(String[] args) {
        BruteForceAlgorithm bf = new BruteForceAlgorithm();
        bf.main(example);
    }

    private static boolean next(int[] X) {
        int i = originalSize - 2;
        int c;
        //{поиск i}
        while (i > 0 && X[i] > X[i + 1]) i--;
        if (i > 0) {
            int j = i;
            //{поиск j}
            while (j < originalSize - 1 && X[j + 1] > X[i]) j++;
            c = X[i];
            X[i] = X[j];
            X[j] = c;
            /**
             for j:=i+1 to (originalSize+i) div 2 do
             Swap(x[j],x[originalSize-j+i+1]);
             */
            //int lim = div(originalSize + i, 2);
            // {x[i+1]>x[i+2]>...>x[originalSize]};
            /*for (j = i + 1; j < lim; j++) {
                c = x[j];
                x[j] = x[originalSize - j + i];
                x[originalSize - j + i] = c;
            }*/
            for (int t = i + 1; t < originalSize - 1; t++) {
                for (int k = i + 1; k < originalSize - 1; k++) {
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
        StringBuilder str = new StringBuilder("");
        for (int i = 0; i < originalSize - 1; i++) {
            str.append("(").append(minPath[i] + 1).append("-").append(minPath[i + 1] + 1).append(") ");
        }
        str.append("(").append(minPath[originalSize - 1] + 1).append("-").append(minPath[0] + 1).append(") ");
        return str.toString();
    }

    public double getSum() {
        return minValue;
    }

    public long getTime() {
        return System.currentTimeMillis() - sysTime;
    }

}