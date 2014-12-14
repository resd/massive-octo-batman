package common;

import com.deltapackage.ParentFrame;

public class Work1OldStableVersion {

    private ParentFrame frame;

    double[][] M0 = {
            {0, 0, 83, 9, 30, 6, 50},
            {0, 0, 66, 37, 17, 12, 26},
            {29, 1, 0, 19, 0, 12, 5},
            {32, 83, 66, 0, 49, 0, 80},
            {3, 21, 56, 7, 0, 0, 28},
            {0, 85, 8, 42, 89, 0, 0},
            {18, 0, 0, 0, 58, 13, 0}
    };

    static double[][][] M0ch;
    static double[][][] M1;
    static double[][][] M2;
    static double[][][] D1;
    static double[][][] D2;
    static double[][][] DD;
    static int count;

    static boolean saveAdditionalResult;

    static int originalsize;
    static int[][] p;
    static int[] mi;
    static int[] mj;
    static int[] beforeP;

    void initialize() {
        p = new int[originalsize][2];
        mi = new int[originalsize];
        mj = new int[originalsize];
        for (int i = 0; i < originalsize; i++) {
            mi[i] = i;
            mj[i] = i;
            p[i][0] = -1;
            p[i][1] = -1;
        }
        M0ch = new double[originalsize - 2][][];
        M1 = new double[originalsize - 2][][];
        M2 = new double[originalsize - 2][][];
        D1 = new double[originalsize - 2][][];
        D2 = new double[originalsize - 2][][];
        DD = new double[originalsize - 2][][];
        beforeP = new int[2];
        saveAdditionalResult = false;
        count = 0;
    }

    public static double[][] normalize(double[][] M) {
        double[] minArrI = new double[M.length];
        double[] minArrJ = new double[M.length];
        for (int i = 0; i < M.length; i++) {
            minArrI[i] = Double.MAX_VALUE;
            minArrJ[i] = Double.MAX_VALUE;
        }
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M.length; j++) {
                if (i != j && M[i][j] < minArrI[i]) {
                    minArrI[i] = M[i][j];
                    if (M[i][j] == 0) {
                        break;
                    }
                }
            }
        }
        for (int i = 0; i < M.length; i++) {
            if (minArrI[i] == 0) {
                continue;
            }
            for (int j = 0; j < M.length; j++) {
                if (i != j) {
                    M[i][j] -= minArrI[i];
                }
            }
        }
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M.length; j++) {
                if (i != j && M[j][i] < minArrJ[i]) {
                    minArrJ[i] = M[j][i];
                    if (M[j][i] == 0) {
                        break;
                    }
                }
            }
        }
        for (int i = 0; i < M.length; i++) {
            if (minArrJ[i] == 0) {
                continue;
            }
            for (int j = 0; j < M.length; j++) {
                if (i != j) {
                    M[j][i] -= minArrJ[i];
                }
            }
        }
        return M;
    }

    public static double[][] solve(double[][] M0) {
        double[][] M1s;
        double[][] M2s;
        double[][] D1s;
        double[][] D2s;
        double[][] DDs;
        M1s = doMfromM0(M0);
        M2s = doMfromM0(M1s);
        D1s = doDfromM(M0, M1s);
        D2s = doDfromM(M1s, M2s);
        DDs = doDDfromD(M0, D1s, D2s);
        if (saveAdditionalResult) {
            M0ch[count] = cloneMatrix(M0);
            M1[count] = cloneMatrix(M1s);
            M1[count] = cloneMatrix(M1s);
            M2[count] = cloneMatrix(M2s);
            D1[count] = cloneMatrix(D1s);
            D2[count] = cloneMatrix(D2s);
            DD[count] = cloneMatrix(DDs);
            count++;
        }
        return DDs;
    }

    private static double[][] cloneMatrix(double[][] a) {
        double[][] clone = a.clone();

        for (int i = 0; i < a.length; i++) {
            clone[i] = a[i].clone();
        }
        return clone;
    }

    private static double[][] doMfromM0(double[][] M0) {
        double[][] M = new double[M0.length][M0.length];
        double alfa = 0.005;
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M.length; j++) {
                if (i == j)
                    M[i][j] = 0;
                M[i][j] = M0[i][j] - alfa * dpaij(M0, i, j);
            }
        }
        return M;
    }

    private static double[][] doDfromM(double[][] M0, double[][] M1) {
        double[][] D = new double[M0.length][M0.length];
        for (int i = 0; i < D.length; i++) {
            for (int j = 0; j < D.length; j++) {
                if (i == j)
                    D[i][j] = 0;
                D[i][j] = dpaij(M1, i, j) - dpaij(M0, i, j);
            }
        }
        return D;
    }

    private static double[][] doDDfromD(double[][] M0, double[][] D1, double[][] D2) {
        double[][] DD = new double[D1.length][D1.length];
        for (int i = 0; i < DD.length; i++) {
            for (int j = 0; j < DD.length; j++) {
                if (i == j)
                    DD[i][j] = 0;
                else if (M0[i][j] == 0)
                    DD[i][j] = D2[i][j] - D1[i][j];
            }
        }
        return DD;
    }

    private static double dpaij(double[][] M0, int di, int dj) {
        double dpaij;
        double zaik = 0;
        double zakj = 0;
        double zajk = 0;
        double zaki = 0;
        double aij;
        double aji;
        for (int k = 0; k < M0.length; k++) {
            zaik += M0[di][k];
            zakj += M0[k][dj];
            zajk += M0[dj][k];
            zaki += M0[k][di];
        }
        aij = M0[di][dj];
        aji = M0[dj][di];
        dpaij = zaik + zakj - zajk - zaki - 3. * aij + 3. * aji;
        return dpaij;
    }

    public static int[] getD(double[][] DD, int i) {
        /**
         * Цикл
         *  1. Найти ДД.
         *  2. Найти макс эдемент ДД.
         *  3. Найти соотв. элемент в пути(исходной матрице).
         *  4. Преобразовать матрицу.
         */
        int[] d;
        if (i == 0) {
            d = maxForFirstElement(DD);
        } else {
            d = max(DD, beforeP[0], beforeP[1]);//max(DD);
        }
        return d;
    }

    private static int[] maxForFirstElement(double[][] DD) {
        double max = -Double.MAX_VALUE;
        int di = 0, dj = 0;

        for (int i = 0; i < DD.length; i++) {
            for (int j = 0; j < DD.length; j++) {
                if (i != j && DD[i][j] > max) {
                    max = DD[i][j];
                    di = i;
                    dj = j;
                }
            }
        }
        return new int[]{di, dj};
    }

    private static int[] max(double[][] DD, int ni, int nj) {
        double maxValue1 = -Double.MAX_VALUE;
        double maxValue2 = -Double.MAX_VALUE;
        int di = 0, dj = 0;

        for (int i = 0; i < DD.length; i++) {
            if (i != nj && DD[i][nj] > maxValue1) {
                maxValue1 = DD[i][nj];
                di = i;
            }
        }

        for (int j = 0; j < DD.length; j++) {
            if (j != ni && DD[ni][j] > maxValue2) {
                maxValue2 = DD[ni][j];
                dj = j;
            }
        }

        if (maxValue1 == maxValue2) {
            return new int[]{di, nj};
        }

        return (maxValue1 > maxValue2) ? new int[]{di, nj} : new int[]{ni, dj};
    }

    void getPath(int[] d, int i) {
        int x = d[0];
        int y = d[1];

        p[i][0] = mi[x];
        p[i][1] = mj[y];

        if (x <= y) {
            beforeP[0] = x;
            beforeP[1] = x;
        } else {
            beforeP[1] = x - 1;
            beforeP[0] = x - 1;

        }

        mi[x] = mi[y];

        mi = remove(mi, y);
        mj = remove(mj, y);
    }

    int[] remove(int[] mi, int y) {
        int[] tmp = new int[mi.length - 1];
        System.arraycopy(mi, 0, tmp, 0, y);
        System.arraycopy(mi, y + 1, tmp, y, mi.length - y - 1);
        return tmp;
    }

    public static double[][] doM0(double[][] M0, int di, int dj) {
        M0[dj][di] = 0;

        // Change rows
        M0 = changeJI(M0, di, dj);

        // Create new matrix without row and column
        double[][] M1;
        M1 = setElementsM0toM(M0, dj);

        return M1;
    }

    private static double[][] changeJI(double[][] M0, int di, int dj) {
        double temp;
        for (int j = 0; j < M0.length; j++) {
            temp = M0[dj][j];
            M0[dj][j] = M0[di][j];
            M0[di][j] = temp;
        }

        return M0;
    }

    private static double[][] setElementsM0toM(double[][] M0, int dj) {
        double[][] M1 = new double[M0.length - 1][M0.length - 1];
        int ki = 0;
        int kj;
        for (int i = 0; i < M0.length; i++) {
            if (i == dj) {
                ki++;
                continue;
            }
            kj = 0;
            for (int j = 0; j < M0.length; j++) {
                if (j == dj) {
                    kj++;
                } else {
                    M1[i - ki][j - kj] = M0[i][j];
                }
            }
        }
        return M1;
    }

    public static void computeLastElement() {
        if (mj[0] == mi[0] || mj[1] == mi[0]) {
            p[originalsize - 2][0] = mi[1];
            p[originalsize - 2][1] = mi[0];
            p[originalsize - 1][0] = mi[0];
            p[originalsize - 1][1] = mj[1];
        } else {
            p[originalsize - 2][0] = mi[0];
            p[originalsize - 2][1] = mi[1];
            p[originalsize - 1][0] = mi[1];
            p[originalsize - 1][1] = mj[0];
        }
    }

    public static void setOriginalsize(int originalsize) {
        Work1OldStableVersion.originalsize = originalsize;
    }

    public void setM0(double[][] M0) {
        this.M0 = M0;
    }

    public static int[][] getP() {
        return p;
    }

    public static void setSaveAdditionalResult(boolean saveAdditionalResult) {
        Work1OldStableVersion.saveAdditionalResult = saveAdditionalResult;
    }

    public double[][][] getM0ch() {
        return M0ch;
    }

    /**
     * @return 3d matrix that contents all M1 matrix.
     */
    public double[][][] getM1() {
        return M1;
    }

    public double[][][] getM2() {
        return M2;
    }

    public double[][][] getD1() {
        return D1;
    }

    public double[][][] getD2() {
        return D2;
    }

    public double[][][] getDD() {
        return DD;
    }

 /* public static void main(String[] args) {
        double[][] M;
        Work1OldStableVersion w = new Work1OldStableVersion();
        M = Arrays.copyOf(w.M0, w.M0.length);

        double[][] DD;
        int d[];
        originalsize = w.M0.length;
        p = new int[originalsize][2];
        mi = new int[originalsize];
        mj = new int[originalsize];

        fillP();
        w.initialize();
        int n = M.length - 2;//2
        for (int i = 0; i < n; i++) {
            normalize(M);
            DD = solve(M);

            d = getD(DD, i);
            w.getPath(d, i);
            M = doM0(M, d[0], d[1]);
        }
        computeLastElement();
        StringBuffer str = new StringBuffer("");
        for (int i = 0; i < originalsize; i++) {
            str.append("(").append(p[i][0] + 1).append("-").append(p[i][1] + 1).append(") ");
        }
        Work1OldStableVersion w2 = new Work1OldStableVersion();
        int Sum = 0;
        for (int k = 0; k < originalsize; k++) {
            Sum = (int) (Sum + w2.M0[p[k][0]][p[k][1]]);
        }
    }*/

}
