package algorithm.near;

import algorithm.util.MethodAction;

/**
 * @author Admin
 * @since 02.04.15
 */
//@SuppressWarnings("all")
public class NearAlgorithmEveryDot extends NearAlgorithmBase implements MethodAction {
    private double[][] M0 = {
            {0, 0, 83, 9, 30, 6, 50},
            {0, 0, 66, 37, 17, 12, 26},
            {29, 1, 0, 19, 0, 12, 5},
            {32, 83, 66, 0, 49, 0, 80},
            {3, 21, 56, 7, 0, 0, 28},
            {0, 85, 8, 42, 89, 0, 0},
            {18, 0, 0, 0, 58, 13, 0}
    };
    private long sysTime;
    private double[] sumArr;
    private int[][][] pArr;
    private int idMax;

    public NearAlgorithmEveryDot(double[][] M0) {
        this.M0 = cloneMatrix(M0);
        originalSize = M0.length;
    }

    public void setM0(double[][] M0) {
        this.M0 = cloneMatrix(M0);
        originalSize = M0.length;
    }

    private int[] min(double[][] DD, int ni) {
        double minValue = Double.MAX_VALUE;
        int dj = 0;

        for (int j = 0; j < DD.length; j++) {
            if (j != ni && DD[ni][j] < minValue) {// Поиск максимального элемента в строке
                minValue = DD[ni][j];
                dj = j;
            }
        }

        return new int[]{ni, dj}; // Возврат максимального элемента
    }

    private double[][] doM0(double[][] M0, int i, int j) {
        M0[j][i] = 0; // Обнуление j-го и i-го элемента для сохранения гамильтонова цикла
        M0 = changeJI(M0, i, j); // Смена i-й строки и j-го столбца местами
        return setElementsM0toM(M0, j);// Новая матрица без j-й строки и j-го столбца
    }

    private double[][] changeJI(double[][] M0, int di, int dj) {// Меняем строку со столбцом
        double temp;
        for (int j = 0; j < M0.length; j++) {
            temp = M0[dj][j];
            M0[dj][j] = M0[di][j];
            M0[di][j] = temp;
        }

        return M0;
    }

    @Override
    public void main() {
        sysTime = System.currentTimeMillis();
        int randomNumX = -1;
        double[][] M;
        int d[];
        int countSum = 0;
        sumArr = new double[originalSize * originalSize - originalSize];
        pArr = new int[originalSize * originalSize - originalSize][originalSize][2];

        for (int count = 0; count < originalSize; count++) {
            for (int c = 0; c < originalSize; c++) {
                if (c == count) continue;
                initialize();
                d = new int[]{count, c};
                M = cloneMatrix(M0);
                int n = M.length - 2;
                for (int i = 0; i < n; i++) {
                    if (i != 0) {
                        d = min(M, randomNumX);
                    }
                    getPath(d, i);
                    M = doM0(M, d[0], d[1]);
                    randomNumX = beforeP[1];
                }
                computeLastElement(p, mi, mj, originalSize); // Нахождение двух последних элементов пути
                sumArr[countSum] = getSumLoop(M0);
                pArr[countSum] = cloneMatrix(p);
                countSum++;
            }
        }
    }


    /*public static void main(String[] args) {
        NearAlgoEveryDot na = new NearAlgoEveryDot(M0);
        na.main();
        double sum = na.getSum(M0);
        C.p("\nPath: " + na.getPath() + "\nSum = " + sum + ",  Time: " + na.getTime());
    }*/

    private static double[][] cloneMatrix(double[][] a) { // Метод для копирования матриц
        double[][] clone = a.clone();
        for (int i = 0; i < a.length; i++) {
            clone[i] = a[i].clone();
        }
        return clone;
    }

    private int[][] cloneMatrix(int[][] a) {
        int[][] clone = a.clone();

        for (int i = 0; i < a.length; i++) {
            clone[i] = a[i].clone();
        }
        return clone;
    }

    public String getPath() {
        StringBuilder str = new StringBuilder("");
        p = cloneMatrix(pArr[idMax]);
        for (int i = 0; i < originalSize; i++) {
            str.append("(").append(p[i][0] + 1).append("-").append(p[i][1] + 1).append(") ");
        }
        return str.toString();
    }

    private double getSumLoop(double[][] a) {
        double Sum = 0;
        for (int k = 0; k < originalSize; k++) {
            Sum += a[p[k][0]][p[k][1]];
        }
        return Sum;
    }

    @Override
    public double getSum(double[][] a) {
        double minValue = Double.MAX_VALUE;

        for (int i = 0; i < sumArr.length; i++) {
            if (sumArr[i] < minValue) {// Поиск максимального элемента в строке
                minValue = sumArr[i];
                idMax = i;
            }
        }
        return minValue;
    }

    @Override
    public long getTime() {
        return System.currentTimeMillis() - sysTime;
    }
}
