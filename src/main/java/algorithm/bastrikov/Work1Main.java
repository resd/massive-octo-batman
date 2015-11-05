
package algorithm.bastrikov;

/**
 * Created by Admin on 07.09.14.
 */
public class Work1Main {

    double[][] M0 = {
            {0, 0, 83, 9, 30, 6, 50},
            {0, 0, 66, 37, 17, 12, 26},
            {29, 1, 0, 19, 0, 12, 5},
            {32, 83, 66, 0, 49, 0, 80},
            {3, 21, 56, 7, 0, 0, 28},
            {0, 85, 8, 42, 89, 0, 0},
            {18, 0, 0, 0, 58, 13, 0}
    };

    public Work1Main(double[][] a, int idMethod) {
        setM0(a);
        this.idMethod = idMethod;
    }

    public Work1Main(int idMethod) {
        this.idMethod = idMethod;
    }

    private int idMethod;
    private int originalsize;
    private int[][] p;

    private Methods w;

    private long sysTime;

    /**
     * Основной метод программы
     */
    public void main() {
        sysTime = System.currentTimeMillis();
        double[][] M;
        M = Work1OldStableVersion.cloneMatrix(M0);

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
        StringBuffer str = new StringBuffer("");
        for (int i = 0; i < originalsize; i++) {
            str.append("(").append(p[i][0] + 1).append("-").append(p[i][1] + 1).append(") ");
        }
        return str.toString();
    }

    public double getSum(double[][] a) {
        double Sum = 0;
        for (int k = 0; k < originalsize; k++) {
            Sum += a[p[k][0]][p[k][1]];
        }
        return Sum;
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

        if (idMethod == 1) {
            w = new Work1OldStableVersion(Work1OldStableVersion.cloneMatrix(M0));
        } else {
            w = new Work2OldStableVersion(Work1OldStableVersion.cloneMatrix(M0));
        }
    }

}
