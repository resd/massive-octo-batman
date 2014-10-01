package common;

import com.deltapackage.ParentFrame;
import java.util.Arrays;


//TODO Может ли быть так, что в матрице DD не находится max элемент и из-за этого и выводит exception

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

    double[][] M01 = {
            {0, 222, 83, 9, 30, 6, 50},
            {0, 0, 66, 37, 17, 12, 26},
            {29, 1, 0, 19, 0, 12, 5},
            {32, 83, 66, 0, 49, 13, 80},
            {3, 21, 56, 7, 0, 0, 28},
            {0, 85, 8, 42, 89, 0, 120},//120
            {18, 0, 0, 0, 58, 13, 0}
    };

    static double[][][] M0ch;
    static double[][][] M1;
    static double[][][] M2;
    static double[][][] D1;
    static double[][][] D2;
    static double[][][] DD;
    static int count;

    static int originalsize;
    static int[][] p;
    static int[] mi;
    static int[] mj;
    static int[] beforeP = new int[2];

    public static void main(String[] args) {
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
        w.create();
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
            if (p[i][0] == p[i][1]) {
                p("\nWEQRQWERASDFXCVXBHKLFDSKGSDKFGKDSGDSGDSG\n");
            }
            str.append("(").append(p[i][0] + 1).append("-").append(p[i][1] + 1).append(") ");
        }
        p(str);
        Work1OldStableVersion w2 = new Work1OldStableVersion();
        int Sum = 0;
        for (int k = 0; k < originalsize; k++) {
            Sum = (int) (Sum + w2.M0[p[k][0]][p[k][1]]);
        }
        p(Sum);
        
    }

    public static void fillP() {
        for (int i = 0; i < p.length; i++) {
            p[i][0] = -10;
            p[i][1] = -10;
        }
    }

    void create() {
        for (int i = 0; i < originalsize; i++) {
            mi[i] = i;
            mj[i] = i;
        }

        M0ch = new double[originalsize - 2][][];
        M1 = new double[originalsize - 2][][];
        M2 = new double[originalsize - 2][][];
        D1 = new double[originalsize - 2][][];
        D2 = new double[originalsize - 2][][];
        DD = new double[originalsize - 2][][];
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
                if (i != j && M[j][i] < minArrI[j]) {
                    minArrI[j] = M[j][i];
                }
            }
        }
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M.length; j++) {//todo sokratit' v n raz
                if (i != j) {
                    M[j][i] -= minArrI[j];
                }
            }
        }
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M.length; j++) {
                if (i != j && M[i][j] < minArrJ[j]) {
                    minArrJ[j] = M[i][j];
                }
            }
        }
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M.length; j++) {//todo sokratit' v n raz
                if (i != j) {
                    M[i][j] -= minArrJ[j];
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

        M0ch[count] = cloneMatrix(M0);
        M1[count] = cloneMatrix(M1s);
        M1[count] = cloneMatrix(M1s);
        M2[count] = cloneMatrix(M2s);
        D1[count] = cloneMatrix(D1s);
        D2[count] = cloneMatrix(D2s);
        DD[count] = cloneMatrix(DDs);
        count++;
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
        int[] d;
        if (i == 0) {
            d = max(DD);
        } else {
            d = maxNo0(DD, beforeP[0], beforeP[1]);//maxNo0(DD);
            p("(" + (d[0] + 1) + " " + (d[1] + 1) + ")");
            if (!checkMax(d[0], d[1])) {//todo По идее, нужно удалить. И проверить работает ли после.
                p("BEFORE CHECK: " + d[0] + ", " + d[1]);
                d = max(DD, d[0], d[1]);
                p("AFTER CHECK: " + d[0] + ", " + d[1]);
            }
        }

        //p(checkMax(d[0], d[1]));
        return d;
    }

    private static int[] max(double[][] DD) {
        double max = Double.MIN_VALUE;
        int di = 0, dj = 0;

        for (int i = 0; i < DD.length; i++) {
            for (int j = 0; j < DD.length; j++) {
                if (DD[i][j] > max) {
                    max = DD[i][j];
                    di = i;
                    dj = j;
                }
            }
        }
        return new int[]{di, dj};
    }

    private static int[] maxNo0(double[][] DD, int ni, int nj) {

        int di = 0, dj = 0;

        /*if (ni == -1)
            ni = 0;
        if (nj == -1) {
            nj = 0;
        }*/

        double maxValue1 = Double.MIN_VALUE;
        double maxValue2 = Double.MIN_VALUE;

        try {
            for (int i = 0; i < DD.length; i++) {
                if (DD[i][nj] != 0 && DD[i][nj] > maxValue1) {
                    maxValue1 = DD[i][nj];
                    di = i;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        for (int j = 0; j < DD.length; j++) {
            if (DD[ni][j] != 0 && DD[ni][j] > maxValue2) {
                maxValue2 = DD[ni][j];
                dj = j;
            }
        }

        return (maxValue1 > maxValue2) ? new int[]{di, nj} : new int[]{ni, dj};
    }

    private static boolean checkMax(int i, int j) {
        int a = mi[i];
        int b = mj[j];
        p(a + ", " + b);
        for (int t = 0; t < p.length; t++) {
            if (p[t][0] == a || p[t][0] == b || p[t][1] == b || p[t][1] == a) {
                return true;
            }
        }
        return false;
    }

    private static int[] max(double[][] DD, int ni, int nj) {
        double max = Double.MIN_VALUE;
        int di = 0, dj = 0;
/*
Пройтись дэбагерром и посмотреть значения beforeP и понять почему выбивает эксепшен.
 */

        for (int i = 0; i < DD.length; i++) {
            for (int j = 0; j < DD.length; j++) {
                if (DD[i][j] != 0 && DD[i][j] > max && !(i == ni && j == nj)) {
                    max = DD[i][j];
                    di = i;
                    dj = j;
                }
            }
        }
        return new int[]{di, dj};
    }

    void getPath(int[] d, int i) {
        int x = d[0];
        int y = d[1];

//(6-7) (4-6) (5-4) (7-3) (3-2) (2-1) (1-5)
        p[i][0] = mi[x];//7(6)
        p[i][1] = mj[y];//2(1)
        if (x <= y) {
            beforeP[1] = x;//mi[x]?
        }
        //else if (x == 0)
        //    beforeP[1] = x;//mi[x]?
        else
            beforeP[1] = x - 1;//mi[x]?

        mi[x] = mi[y];//2

        if (x <= y)
            beforeP[0] = x;//mj[y]
            //else if (x != 0)
            //    beforeP[1] = x;//mi[x]?
        else
            beforeP[0] = x - 1;//mj[y]

        mi = remove(mi, y);
        mj = remove(mj, y);
        //p(Arrays.deepToString(p));
    }

    int[] remove(int[] mi, int y) {
        int[] tmp = new int[mi.length - 1];
        System.arraycopy(mi, 0, tmp, 0, y);
        System.arraycopy(mi, y + 1, tmp, y, mi.length - y - 1);
        return tmp;
    }

    public static double[][] doM0(double[][] M0, int di, int dj) {
        // Change rows
        M0 = changeJI(M0, di, dj);

        // Create new matrix without row and column
        double[][] M1;
        M1 = setElementsM0toM(M0, dj, dj);//di

        for (int i = 0; i < M1.length; i++) {
            M1[i][i] = 0;
        }

        boolean minusi, minusj;
        int coi = 0;
        minusi = false;
        minusj = false;
        for (int i = 0; i < M1.length; i++) {
            if (minusi || minusj) {
                coi = i - 1;
                break;
            }
            minusj = true;
            minusi = true;
            for (int j = 0; j < M1.length; j++) {
                if (i != j && M1[i][j] == 0) {
                    minusi = false;
                }
                if (i != j && M1[j][i] == 0) {
                    minusj = false;
                }

            }
        }
        if (minusi)
            doVuch(M1, coi, true, false);
        if (minusj)
            doVuch(M1, coi, false, true);
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

    private static double[][] setElementsM0toM(double[][] M0, int dj, int dj2) {
        double[][] M1 = new double[M0.length - 1][M0.length - 1];
        out(M0);
        int ki = 0;
        int kj;
        for (int i = 0; i < M0.length; i++) {
            if (i == dj || i == dj2) {
                ki++;
                continue;
            }
            kj = 0;
            for (int j = 0; j < M0.length; j++) {
                if (j == dj || j == dj2) {
                    kj++;
                } else {
                    M1[i - ki][j - kj] = M0[i][j];
                }
            }
        }
        return M1;
    }
/*
int length = M0.length - 1;
        double[][] M1 = new double[length][length];
        for (int i = 0; i < dj; i++) {
            System.arraycopy(M0[i], 0, M1[i], 0, dj);
            System.arraycopy(M0[i], dj + 1, M1[i], dj, length - dj);
        }
        for (int i = dj + 1; i < length; i++) {
            System.arraycopy(M0[i], 0, M1[i - 1], 0, dj);
            System.arraycopy(M0[i], dj + 1, M1[i - 1], dj, length - dj);
        }
 */
    private static double[][] doVuch(double[][] M1, int coi, boolean minusi, boolean minusj) {
        double min = Double.MAX_VALUE;

        for (int j = 0; j < M1.length; j++) {
            if (j != coi && minusi && M1[coi][j] < min) {
                min = M1[coi][j];
            }
            if (j != coi && minusj && M1[j][coi] < min) {
                min = M1[j][coi];
            }
        }
        for (int i = 0; i < M1.length; i++) {
            if (minusi && i != coi) {
                M1[coi][i] -= min;
            }
            if (minusj && i != coi) {
                M1[i][coi] -= min;
            }
        }
        return M1;
    }

    public static void computeLastElement() {
        p("");
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

    public double[][][] getM0ch() {
        return M0ch;
    }

    /**
     *
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

    private static void p(Object s) {
        System.out.println(s + "");
    }

    private static void out(double[][] M) {
        if (M == null) {
            return;
        }
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M.length; j++) {
                if (i == j) {
                    System.out.print("0" + " ");
                } else {
                    System.out.print(round(M[i][j]) + " ");
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

    private static double round(double a) {
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
        int t1 = 0;
        String s1 = s.substring(0, t);
        t++;
        t1 = (s.length() - t) < 5 ? s.length() : (t + 5);
        String s2 = s.substring(t, t1);
        //System.out.println(s1 + "s1");
        //System.out.println(s2 + "s2");
        s = s1 + '.' + s2;
        b = Double.valueOf(s);
        return b;
    }

}
