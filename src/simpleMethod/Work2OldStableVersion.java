package simpleMethod;

import common.Methods;

public class Work2OldStableVersion implements Methods{

    /*double[][] M0 = {
            {0, 0, 83, 9, 30, 6, 50},
            {0, 0, 66, 37, 17, 12, 26},
            {29, 1, 0, 19, 0, 12, 5},
            {32, 83, 66, 0, 49, 0, 80},
            {3, 21, 56, 7, 0, 0, 28},
            {0, 85, 8, 42, 89, 0, 0},
            {18, 0, 0, 0, 58, 13, 0}
    };*/

    double[][][] M0ch;
    double[][][] M1ch;
    double[][][] M2ch;
    double[][][] D1ch;
    double[][][] D2ch;
    double[][][] DDch;
    int count;

    boolean saveAdditionalResult;

    int originalsize;
    int[][] p;
    int[] mi;

    int[] mj;
    int[] beforeP;

    public Work2OldStableVersion(double[][] M0) {
        originalsize = M0.length;
    }

    public void initialize() {
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
        M1ch = new double[originalsize - 2][][];
        M2ch = new double[originalsize - 2][][];
        D1ch = new double[originalsize - 2][][];
        D2ch = new double[originalsize - 2][][];
        DDch = new double[originalsize - 2][][];
        beforeP = new int[2];
        saveAdditionalResult = false;
        count = 0;
    }

    public void normalize(double[][] M) {
        double min = Double.MAX_VALUE;

        for (int i = 0; i < M.length; i++) { // Нахождение минимальных значений в каждой строке
            for (int j = 0; j < M.length; j++) {
                if (i != j && M[i][j] < min) {
                    min = M[i][j];
                    if (M[i][j] == 0) {
                        break;
                    }
                }
            }
            if (min != 0) { // Вычитание из каждой строки минимальный элемент
                for (int j = 0; j < M.length; j++) {
                    if (i != j) {
                        M[i][j] -= min;
                    }
                }
            }
            min = Double.MAX_VALUE;
        }

        for (int i = 0; i < M.length; i++) { // Нахождение минимальных значений в каждом столбце
            for (int j = 0; j < M.length; j++) {
                if (i != j && M[j][i] < min) {
                    min = M[j][i];
                    if (M[j][i] == 0) {
                        break;
                    }
                }
            }
            if (min != 0) { // Вычитание из каждого столбца минимальный элемент
                for (int j = 0; j < M.length; j++) {
                    if (i != j) {
                        M[j][i] -= min;
                    }
                }
            }
            min = Double.MAX_VALUE;
        }
    }

    public double[][] solve(double[][] M0) {
        double[][] M1;
        double[][] M2;
        double[][] D1;
        double[][] D2;
        double[][] DD;
        M1 = doMfromM0(M0);
        M2 = doMfromM0(M1);
        D1 = doDfromM(M0, M1);
        D2 = doDfromM(M1, M2);
        DD = doDDfromD(M0, D1, D2);
        /*if (saveAdditionalResult) {
            M0ch[count] = cloneMatrix(M0);
            M1ch[count] = cloneMatrix(M1s);
            M1ch[count] = cloneMatrix(M1s);
            M2ch[count] = cloneMatrix(M2s);
            D1ch[count] = cloneMatrix(D1s);
            D2ch[count] = cloneMatrix(D2s);
            DDch[count] = cloneMatrix(DDs);
            count++;
        }*/
        return DD;
    }

    public double[][] cloneMatrix(double[][] a) {
        double[][] clone = a.clone();

        for (int i = 0; i < a.length; i++) {
            clone[i] = a[i].clone();
        }
        return clone;
    }

    private double[][] doMfromM0(double[][] M0) {
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

    private double[][] doDfromM(double[][] M0, double[][] M1) {
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

    private double[][] doDDfromD(double[][] M0, double[][] D1, double[][] D2) {
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

    private double dpaij(double[][] M0, int di, int dj) {
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

    public int[] getD(double[][] DD, int i) {
        /**
         * Цикл
         *  1. Найти ДД.
         *  2. Найти макс эдемент ДД.
         *  3. Найти соотв. элемент в пути(исходной матрице).
         *  4. Преобразовать матрицу.
         */

        int[] d;
        d = maxForFirstElement(DD);
        /*if (i == 0) {
            d = maxForFirstElement(DDch);
        } else {
            d = max(DDch, beforeP[0], beforeP[1]);//max(DDch);
        }*/
        return d;
    }

    private int[] maxForFirstElement(double[][] DD) {
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

    public void getPath(int[] d, int i) {
        int x = d[0];
        int y = d[1];

        p[i][0] = mi[x];
        p[i][1] = mj[y];

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

    public double[][] doM0(double[][] M0, int di, int dj) {
        M0[dj][di] = 0;

        // Change rows
        M0 = changeJI(M0, di, dj);

        // Create new matrix without row and column
        double[][] M1;
        M1 = setElementsM0toM(M0, dj);

        return M1;
    }

    private double[][] changeJI(double[][] M0, int di, int dj) {
        double temp;
        for (int j = 0; j < M0.length; j++) {
            temp = M0[dj][j];
            M0[dj][j] = M0[di][j];
            M0[di][j] = temp;
        }

        return M0;
    }

    private double[][] setElementsM0toM(double[][] M0, int dj) {
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

    public void computeLastElement() {
        p[originalsize - 2][0] = mi[0];
        p[originalsize - 2][1] = mj[1];
        p[originalsize - 1][0] = mi[1];
        p[originalsize - 1][1] = mj[0];
    }

/*    public void setOriginalsize(int originalsize) {
        this.originalsize = originalsize;
    }*/

    public int[][] getP() {
        return p;
    }

    /*public void setSaveAdditionalResult(boolean saveAdditionalResult) {
        this.saveAdditionalResult = saveAdditionalResult;
    }

    public double[][][] getM0ch() {
        return M0ch;
    }

    *//**
     * @return 3d matrix that contents all M1ch matrix.
     *//*
    public double[][][] getM1ch() {
        return M1ch;
    }

    public double[][][] getM2ch() {
        return M2ch;
    }

    public double[][][] getD1ch() {
        return D1ch;
    }

    public double[][][] getD2ch() {
        return D2ch;
    }

    public double[][][] getDDch() {
        return DDch;
    }*/

 /* public void main(String[] args) {
        double[][] M;
        Work2 w = new Work2();
        M = Arrays.copyOf(w.M0, w.M0.length);

        double[][] DDch;
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
            DDch = solve(M);

            d = getD(DDch, i);
            w.getPath(d, i);
            M = doM0(M, d[0], d[1]);
        }
        computeLastElement();
        StringBuffer str = new StringBuffer("");
        for (int i = 0; i < originalsize; i++) {
            str.append("(").append(p[i][0] + 1).append("-").append(p[i][1] + 1).append(") ");
        }
        Work2 w2 = new Work2();
        int Sum = 0;
        for (int k = 0; k < originalsize; k++) {
            Sum = (int) (Sum + w2.M0[p[k][0]][p[k][1]]);
        }
    }*/

}
