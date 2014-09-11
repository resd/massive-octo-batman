package common;

import java.util.Arrays;

/**
 * ��������
 * 1.  ��������� ����������� ������ � ���������
 * ��������������� ������� �1 � �2
 * 2.  ��������� ������� D1�7, D2�7 � DD1�7, �������� M1, M2
 * 3.  �������� ������������ �������� �� ������� DD1 (max)
 * � ��������������� �� ����� (i, j) ���������� ���
 * ����� ������������ ����.
 * 4.  ��������� ���������� �����, ��������� ��������������� �� ������,
 * ������� � ������������ ������� �� �������� ������� �0. ������ j,
 * ��������������� ������������ ������������� ��������,
 * ��������� �� �������������� ����� ������ i.
 * 4.1 ����� �������, �������� ������ ����� �������� ���� ������������, � �������
 * ������� ���������� ��������� �� �������. ���� ����� ���������� ������ (�������)
 * �������� ������� ��� ����� � ��������� ������ (�������), ���������� ���������
 * �������� ���������� ������� � ������� ����������� ������� �� ��������������� ������ (�������).
 * 5.  �������� ����������� ���� ���������� (n�2) ����, �������� ������ ������� ����.
 */

/**
 * ��������� � ���������:
 * 1. ������ ��� ����� ��.
 * 2. �� ������ ���� �� ������� � ���������� ����� ��� ������������ ��������.
 * 3. �������� ����� ��� ������.
 * 3.1 � ����� ���������������� ������ ���� ������ ������� ����������.
 * 4. ���-�� ���������� �� �������� ��������� �������� �������� �������.
 * 5. ��������� ���� i <= (n - 2)
 */

public class Work1 {
    // ������� ������
    double[][] M0 = {
            {0, 0, 83, 9, 30, 6, 50},
            {0, 0, 66, 37, 17, 12, 26},
            {29, 1, 0, 19, 0, 12, 5},
            {32, 83, 66, 0, 49, 0, 80},
            {3, 21, 56, 7, 0, 0, 28},
            {0, 85, 8, 42, 89, 0, 0},
            {18, 0, 0, 0, 58, 13, 0}
    };

    double[][] M210 = {
            {0, 10, 10, 3, 10},
            {10, 0, 10, 2, 10},
            {10, 10, 0, 1, 10},
            {10, 10, 5, 0, 10},
            {10, 10, 10, 4, 0}
    };

    // ������ �������� �������
    private static int originalsize;
    // �������������� ����
    public static int[][] p;
    // ����� ��� ����������� �������� ����
    public static int[] mi;
    public static int[] mj;
    
    public static void main(String[] args) {
        double[][] M;
        Work1 w = new Work1();
        M = Arrays.copyOf(w.M0, w.M0.length);

        double[][] DD;
        int d[];
        originalsize = w.M0.length;
        p = new int[originalsize][2];
        mi = new int[originalsize];
        mj = new int[originalsize];

        fillP();
        w.create();
        int n = M.length - 2;
        normalize(M);
        DD = solve(M);
        d = getD(DD, 0);
        w.getPath(d[0], d[1], 0);
        M = doM0(M, d[0], d[1]);
        int r = 1;
        for (int i = 1; i < n; i += 2) {
            normalize(M);
            DD = solve(M);
            p = p;
            p("M");
            out(M);
            p("DD");
            out(DD);
            if (i > (n - 2)) {
                d = getD(DD, i);
                w.getPath(d[0], d[1], i);
                //M = doM0(w.M, d[0], d[1]);
            } else {
                d = getD(DD, i);
                w.getPath(d, r);
                //w.getPath(d[0], d[1], r);
                //w.getPath(d[2], d[3], (r + 1));
                M = doM0(M, d);
                r+=2;
            }
            //p(d[0] +", " +  d[1]);
            //M = doM0(w.M, d[0], d[1]);
            //M = doM0(w.M, d[2], d[3]);
        }
        computeLastElement();
        StringBuffer str = new StringBuffer("");
        for (int i = 0; i < originalsize; i++) {
            str.append("(").append(p[i][0] + 1).append("-").append(p[i][1] + 1).append(") ");
        }
        p(str);
        Work1 w2 = new Work1();
        int Sum = 0;
        for (int k = 0; k < originalsize; k++) {
            //Sum = (int) (Sum + w2.M0[p[k][0]][p[k][1]]);
        }
        //p(Sum);
    }

    public static void fillP() {
        for (int i = 0; i < p.length; i++) {
            p[i][0] = -10;
            p[i][1] = -10;
        }
    }

    public void setOriginalsize(int originalsize) {
        Work1.originalsize = originalsize;
    }

    void create() {
        for (int i = 0; i < originalsize; i++) {
            mi[i] = i;
            mj[i] = i;
        }
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

    public static int[] getD(double[][] DD, int i) {
        int[] d;
        int[] dd = new int[4];

        d = max(DD);
        dd[0] = d[0];
        dd[1] = d[1];

        d = max(DD, d[0], d[1]);
        dd[2] = d[0];
        dd[3] = d[1];
        //p("(" + (d[0] +1)  + " " + (d[1] +1) + ")");
        /*if (i != 0){
            if (!checkMax(d[0], d[1])) {
                *//*p("M0");
                out(M);
                p("\nDD");
                out(DD);*//*
                p("BEFORE CHECK: " + d[0] + ", " + d[1]);
                d = max(DD, d[0], d[1]);
                p("AFTER CHECK: " + d[0] + ", " + d[1]);
            }
        }*/
        //p(checkMax(d[0], d[1]));
        return dd;
    }

    private static int[] max(double[][] DD) {
        // ������� ������������ ������� DD
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
        // ���������� ���������� ������������� ��������
        return new int[]{di, dj};
    }

    private static int[] max(double[][] DD, int ni, int nj) {
        // ������� ������������ ������� DD ��� ����� ni � nj
        double max = Double.MIN_VALUE;

        int di = 0, dj = 0;
        for (int i = 0; i < DD.length; i++) {
            for (int j = 0; j < DD.length; j++) {
                if (DD[i][j] != 0 && DD[i][j] > max && !(i == ni && j == nj)) {
                    max = DD[i][j];
                    di = i;
                    dj = j;
                }
            }
        }
        // ���������� ���������� ������������� ��������
        return new int[]{di, dj};
    }

    void getPath(int[] dd, int i) {
        int x = dd[0];
        int y = dd[1];
        int x1 = dd[2];
        int y1 = dd[3];
        p[i][0] = mi[x];
        p[i][1] = mj[y];

        mi[x] = mi[y];

        i++;
        p[i][0] = mi[x1];
        p[i][1] = mj[y1];

        mi[x1] = mi[y1];

        //(6-7) (7-3) (4-6) (2-1) (-9--9) (1-3) (3-2)
        if (y > y1) {
            mi = remove(mi, y);
            mj = remove(mj, y);
            mi = remove(mi, y1);
            mj = remove(mj, y1);
        } else if (y < y1) {
            mi = remove(mi, y1);
            mj = remove(mj, y1);
            mi = remove(mi, y);
            mj = remove(mj, y);
        } else {
            mi = remove(mi, y);
            mj = remove(mj, y);
            //??
        }
        //p(Arrays.deepToString(p));
    }

    void getPath(int x, int y, int i) {
        p[i][0] = mi[x];
        p[i][1] = mj[y];

        mi[x] = mi[y];
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

    public static double[][] solve(double[][] M0) {
        double[][] M1;
        double[][] M2;
        double[][] D1;
        double[][] D2;
        double[][] DD;
        // ������� ����������� ��� �������� �������
        M1 = doMfromM0(M0);
        M2 = doMfromM0(M1);
        D1 = doDfromM(M0, M1);
        D2 = doDfromM(M1, M2);
        DD = doDDfromD(M0, D1, D2);
        /*p("M1");
        out(M1);
        p("M2");
        out(M2);
        p("D1");
        out(D1);
        p("D2");
        out(D2);*/
        //p("DD");
        //out(DD);
        // ���������� ������� DD
        return DD;
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

    public static double[][] doM0(double[][] M0, int[] dd) {
        int di = dd[0];
        int dj = dd[1];
        int di2 = dd[2];
        int dj2 = dd[3];

        // ������ ������� ������ j �� ������ i todo
        if (dj > dj2) {
            M0 = changeJI(M0, di, dj);
            M0 = changeJI(M0, di2, dj2);
        }
        else if (dj2 < dj) {
            M0 = changeJI(M0, di, dj);
            M0 = changeJI(M0, di2, dj2);
        } else {
            M0 = changeJI(M0, di, dj);
            M0 = changeJI(M0, di2, dj2);
        }
        double[][] M = new double[M0.length - 2][M0.length - 2];

        // ���������� � ������� M �������� M0 ��� di � dj
        M = setElementsM0toM(M0, M, dj, dj2);//di

        // �������� ������� ���������
        for (int i = 0; i < M.length; i++) {
            M[i][i] = 0;
        }

        // ���� � ������ (�������) ������� ��� �������� �� �������, �� �������� ��� ������ (�������)
        // �� ����������� �������
        boolean minusi, minusj;// �������� �� ���� � ������� � ��������.
        int coi = 0;
        minusi = false;
        minusj = false;
        for (int i = 0; i < M.length; i++) {
            // ���� ����� ������� ������ (�������) �������� �������� ������ ������, ��
            // ������� �� �����, �������� �������������� ����� ������ (�������).
            if (minusi || minusj) {
                coi = i - 1;
                break;
            }
            minusj = true;
            minusi = true;
            for (int j = 0; j < M.length; j++) {
                if (i != j && M[i][j] == 0) {
                    minusi = false;
                }
                if (i != j && M[j][i] == 0) {
                    minusj = false;
                }

            }
        }
        // ���� ���� ��������� ������, �� �������� �� ��� ����������� �������
        if (minusi)
            doVuch(M, coi, true, false);
        if (minusj)
            doVuch(M, coi, false, true);
        return M;
    }

    public static double[][] doM0(double[][] M0, int di, int dj) {
        M0 = changeJI(M0, di, dj);
        double[][] M1 = new double[M0.length - 1][M0.length - 1];

        M1 = setElementsM0toM(M0, M1, dj, dj);//di

        for (int i = 0; i < M1.length; i++) {
            M1[i][i] = 0;
        }

        boolean minusi, minusj;
        int coi = 0;
        minusi = false;
        minusj = false;
        for (int i = 0; i < M1.length; i++) {
            // ���� ����� ������� ������ (�������) �������� �������� ������ ������, ��
            // ������� �� �����, �������� �������������� ����� ������ (�������).
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

    private static double[][] setElementsM0toM(double[][] M0, double[][] M1, int dj, int dj2) {
        int ki = 0;
        int kj;
        // ���������� � ������� M1 �������� M0 ��� di � dj
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

    private static double[][] changeJI(double[][] M0, int di, int dj) {
        double temp;
        for (int j = 0; j < M0.length; j++) {
            temp = M0[dj][j];
            M0[dj][j] = M0[di][j];
            M0[di][j] = temp;
        }
        return M0;
    }

    // ������� ��� �������������� ������� � ����������� ����
    // �.�. � ������ � ������ ������ � �������
    public static double[][] normalize(double[][] M) {
        // ��������� ����������
        double[] minArrI = new double[M.length];
        double[] minArrJ = new double[M.length];
        for (int i = 0; i < M.length; i++) {
            minArrI[i] = Double.MAX_VALUE;
            minArrJ[i] = Double.MAX_VALUE;
        }
        /* [i][j] - �������; [j][i] - ������. */ // j - ���������� ����������
        // ������� ����������� �������� � ����� ������
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M.length; j++) {
                if (i != j && M[j][i] < minArrI[j]) {
                    minArrI[j] = M[j][i];
                }
            }
        }
        // �������� �� ������ ������ ����������� ������� ��� ��������� ����.
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M.length; j++) {
                if (i != j) {
                    M[j][i] -= minArrI[j];
                }
            }
        }
        // ������� ����������� �������� � ����� �������
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M.length; j++) {
                if (i != j && M[i][j] < minArrJ[j]) {
                    minArrJ[j] = M[i][j];
                }
            }
        }
        // �������� �� ������� ������� ����������� ������� ��� ��������� ����.
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M.length; j++) {
                if (i != j) {
                    M[i][j] -= minArrJ[j];
                }
            }
        }
        return M;
    }

    // ������� ��� �������������� ������� � ����������� ����
    // �.�. � ������ � ������ ������ � �������
    private static double[][] doVuch(double[][] M1, int coi, boolean minusi, boolean minusj) {
        // �������� ����������� �������
        double min = Double.MAX_VALUE;

        for (int j = 0; j < M1.length; j++) {
            if (j != coi && minusi && M1[coi][j] < min) {
                min = M1[coi][j];
            }
            if (j != coi && minusj && M1[j][coi] < min) {
                min = M1[j][coi];
            }
        }
        // �������� �� ������ (�������) ����������� ������� ��� ��������� ����.
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

    // ���������� ������� ��� ��������
    private static double dpaij(double[][] M0, int di, int dj) {
        double dpaij;
        double zaik = 0;
        double zakj = 0;
        double zajk = 0;
        double zaki = 0;
        double aij = 0;
        double aji = 0;
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

    public void setM0(double[][] M0) {
        this.M0 = M0;
    }

    public static int[][] getP() {
        return p;
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
                    System.out.print("0" + " ");
                } else {
                    System.out.format("%1.2f ", M[i][j]);
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

}