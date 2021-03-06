package algorithm.bastrikov;

import algorithm.bab.util.Other;
import algorithm.util.MethodAction;

/**
 * @author Admin
 * @since 07.09.14
 */
public class Work1Main implements MethodAction {

    private double[][] M0 = {
            {0, 0, 83, 9, 30, 6, 50},
            {0, 0, 66, 37, 17, 12, 26},
            {29, 1, 0, 19, 0, 12, 5},
            {32, 83, 66, 0, 49, 0, 80},
            {3, 21, 56, 7, 0, 0, 28},
            {0, 85, 8, 42, 89, 0, 0},
            {18, 0, 0, 0, 58, 13, 0}
    };

    @SuppressWarnings("unused")
    public Work1Main(double[][] a, int idMethod) {
        setM0(a, idMethod);
    }

    private int originalSize;
    private int[][] p;

    private Methods w;

    private long sysTime;

    /**
     * Основной метод программы
     */
    @Override
    public void main() {
        sysTime = System.currentTimeMillis();
        double[][] M;
        M = Other.cloneMatrix(M0);

        double[][] DD;
        int d[];
        w.initialize();

        int n = M.length - 2;
        for (int i = 0; i < n; i++) {
            w.normalize(M);                 // Приведение матрицы к нормальной форме
            DD = w.solve(M);                // Вычисление матрицы DDch
            d = w.getD(DD, i);              // Получение максимального элемента матрицы DDch
            w.getPath(d, i);                // Нахождение соответствия между макс. элем-том и путем
            // в исходной матрице
            M = w.doM0(M, d[0], d[1]);      // Вычитание максимального эле-та из матрицы M,
            // получение приведенной матрицы
        }
        w.computeLastElement();                     // Нахождение двух последних элементов пути
        p = w.getP();
    }

  /*  public double[][][] getM0ch() {
        return w.getM0ch();
    }

    */

    /**
     * @return 3d matrix that contents all M1ch matrix.
     *//*
    public double[][][] getM1ch() {
        return w.getM1ch();
    }

    public double[][][] getM2ch() {
        return w.getM2ch();
    }

    public double[][][] getD1ch() {
        return w.getD1ch();
    }

    public double[][][] getD2ch() {
        return w.getD2ch();
    }

    public double[][][] getDDch() {
        return w.getDDch();
    }

    public double[][] getM0ch(int count) {
        return w.getM0ch()[count];
    }

    public double[][] getM1ch(int count) {
        return w.getM1ch()[count];
    }

    public double[][] getM2ch(int count) {
        return w.getM2ch()[count];
    }

    public double[][] getD1ch(int count) {
        return w.getD1ch()[count];
    }

    public double[][] getD2ch(int count) {
        return w.getD2ch()[count];
    }

    public double[][] getDDch(int count) {
        return w.getDDch()[count];
    }
*/
    public String getPath() {
        StringBuilder str = new StringBuilder("");
        for (int i = 0; i < originalSize; i++) {
            str.append("(").append(p[i][0] + 1).append("-").append(p[i][1] + 1).append(") ");
        }
        return str.toString();
    }

    @Override
    public double getSum(double[][] a) {
        double Sum = 0;
        for (int k = 0; k < originalSize; k++) {
            Sum += a[p[k][0]][p[k][1]];
        }
        return Sum;
    }

    @Override
    public long getTime() {
        return System.currentTimeMillis() - sysTime;
    }

    public void setM0(double[][] a, int idMethod) {
        double[][] clone = a.clone();

        for (int i = 0; i < a.length; i++) {
            clone[i] = a[i].clone();
        }
        this.M0 = clone;
        originalSize = a.length;

        if (idMethod == 1) {
            w = new Work1OldStableVersion(Other.cloneMatrix(M0));
        } else {
            w = new Work2OldStableVersion(Other.cloneMatrix(M0));
        }
    }

}
