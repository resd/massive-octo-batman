package algorithm.bastrikov;

import algorithm.util.DuplicatesMethods;

public class Work2OldStableVersion extends WorkBase implements Methods{

    /*double[][] M0 = { // Need to debug
            {0, 0, 83, 9, 30, 6, 50},
            {0, 0, 66, 37, 17, 12, 26},
            {29, 1, 0, 19, 0, 12, 5},
            {32, 83, 66, 0, 49, 0, 80},
            {3, 21, 56, 7, 0, 0, 28},
            {0, 85, 8, 42, 89, 0, 0},
            {18, 0, 0, 0, 58, 13, 0}
    };*/

    /*private double[][][] M0ch;
    private double[][][] M1ch;
    private double[][][] M2ch;
    private double[][][] D1ch;
    private double[][][] D2ch;
    private boolean saveAdditionalResult;
    private double[][][] DDch;*/
    //    private int count;

    public Work2OldStableVersion(double[][] M0) {
        originalSize = M0.length;
    }

    @Override
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

    @Override
    public void getPath(int[] d, int i) {
        int x = d[0];
        int y = d[1];

        p[i][0] = mi[x];
        p[i][1] = mj[y];

        mi[x] = mi[y];

        mi = remove(mi, y);
        mj = remove(mj, y);
    }

    private int[] remove(int[] mi, int y) {
        int[] tmp = new int[mi.length - 1];
        System.arraycopy(mi, 0, tmp, 0, y);
        System.arraycopy(mi, y + 1, tmp, y, mi.length - y - 1);
        return tmp;
    }

    @Override
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
        return DuplicatesMethods.setElementsM0toM(M0, dj);
    }

    @Override
    public void computeLastElement() {
        p[originalSize - 2][0] = mi[0];
        p[originalSize - 2][1] = mj[1];
        p[originalSize - 1][0] = mi[1];
        p[originalSize - 1][1] = mj[0];
    }

    @Override
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
        originalSize = w.M0.length;
        p = new int[originalSize][2];
        mi = new int[originalSize];
        mj = new int[originalSize];

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
        for (int i = 0; i < originalSize; i++) {
            str.append("(").append(p[i][0] + 1).append("-").append(p[i][1] + 1).append(") ");
        }
        Work2 w2 = new Work2();
        int Sum = 0;
        for (int k = 0; k < originalSize; k++) {
            Sum = (int) (Sum + w2.M0[p[k][0]][p[k][1]]);
        }
    }*/

}
