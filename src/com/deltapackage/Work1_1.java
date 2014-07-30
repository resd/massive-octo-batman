package com.deltapackage;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *��������
 1.  ��������� ����������� ������ � ���������
    ��������������� ������� �1 � �2
 2.  ��������� ������� D1�7, D2�7 � DD1�7, �������� M1, M2
 3.  �������� ������������ �������� �� ������� DD1 (max)
    � ��������������� �� ����� (i, j) ���������� ���
    ����� ������������ ����.
 4.  ��������� ���������� �����, ��������� ��������������� �� ������,
    ������� � ������������ ������� �� �������� ������� �0. ������ j,
    ��������������� ������������ ������������� ��������,
    ��������� �� �������������� ����� ������ i.
 4.1 ����� �������, �������� ������ ����� �������� ���� ������������, � �������
    ������� ���������� ��������� �� �������. ���� ����� ���������� ������ (�������)
    �������� ������� ��� ����� � ��������� ������ (�������), ���������� ���������
    �������� ���������� ������� � ������� ����������� ������� �� ��������������� ������ (�������).
 5.  �������� ����������� ���� ���������� (n�3) ����, �������� ������ ������� ����.
 */

 /*
 1. ��� �����, ���� � ������� DD ����� 2 ������������ ��������?
 - �������� ������ �� ���, ������� ��������� �� ����� ��� � ���������� ������������ ���������.
 2. � ������� � ������ ������� 7 ������. ���? ���� �������� (n - 3) = 5.
 2.1 ����� (5�2) � (3�5) �������� �������
    � �����, ���������� ������, �� ����� ���-
    ���������. ����� �������, ������� ������
    ����������� ������ ������ 7 �������. ???
 - ��������� 2 ����� �������� �� ���� ��������� ������.
 3. ������ � �������, � ��������� ������� �� ������ ������� �� 1?
 - � �� �������. ����.
  */
public class Work1_1 {
    // ������� ������
    double[][] M0 ={
            {0, 0, 83, 9, 30, 6, 50},
            {0, 0, 66, 37, 17, 12, 26},
            {29, 1, 0, 19, 0, 12, 5},
            {32, 83, 66, 0, 49, 0, 80},
            {3, 21, 56, 7, 0, 0, 28},
            {0, 85, 8, 42, 89, 0, 0},
            {18, 0, 0, 0, 58, 13, 0}
    };
    static ArrayList<Integer> arrI = new ArrayList<Integer>();
    static int[][] res;
    static ArrayList<Integer> arrJ = new ArrayList<Integer>();
    double[][] M;

    public static void main(String[] args){
        Work1_1 w = new Work1_1();
        Work1_1 w2 = new Work1_1();
        res = new int[w.M0.length - 2][2];
        w.M = Arrays.copyOf(w.M0, w.M0.length);
        int n = w.M.length - 3;
        int d[];

        for (int i = 0; i < n; i++) {
            d = solve(w.M);
            getRes(d, i);
            w.M = doM0(w.M, d[0], d[1]);
        }
        out(w.M);
        d = solve(w.M);
        getRes(d, n);
        w.M = doM0(w.M, d[0], d[1]);
        out(w.M);
        for (int i = 0; i < arrJ.size(); i++) {
            //p((arrI.get(i)+1) + " = arrI, arrJ = "+(arrJ.get(i)+1));
        }
        //p(Work1.M0[6][0]);
        double a = 0;
        for (int i = 0; i < res.length ; i++) {
            p("M0["+res[i][0]+"]["+res[i][1]+"] = " + w2.M0[res[i][0]][res[i][1]]);
                a += w2.M0[res[i][0]][res[i][1]];
                //p((res[i][0]+1) + " = I, J = "+(res[i][1]+1));
        }
        p(a+" = a");
    }

    private static void getRes(int[] d, int k){
        int di;
        int dj;
        di = d[0];
        dj = d[1];
        //p("(" + (di+1)+", "+ (dj+1)+")");
        if (di < dj) {
            arrI.add(di);
            arrJ.add(di);
        }
        else {
            arrI.add(dj);
            arrJ.add(dj);
        }
        // �.�. �������� ������ ������.
        if (k != 0){
        for (int i = 0; i < arrI.size() - 1; i++) {
            if (arrI.get(i) <= di) {
                di++;
            }
        }
            for (int i = 0; i < arrJ.size() - 1; i++) {
            if (arrJ.get(i) <= dj) {
                dj++;
            }
        }
        }
        p("(" + (di + 1) + ", " + (dj + 1) + ")");
        res[k][0] = di;
        res[k][1] = dj;
        //return res;
        /*
        �������
        1�4�6�7�3�5�2�1
        (6, 7)
        (7, 3) <- (6, 3)
        (2, 1) == (2, 1)
        (1, 4) <- (2, 4)
        (5, 2)
        (3, 5)

        (1, 4)? -
        (3, 5)? -
        (5, 2)? -
        wrong ?(2, 3) ?= (2,4)


        (6, 7)(6, 3)(3, 5)(2, 1) - Output

        (6, 7) 7 -> 6;
(7, 3)  (6, 3) 3 -> 6;
(4, 6)  (3, 5)
        (2, 1)
(2, 4)  (1, 2)
             ����� (5�2) � (3�5) �������� �������
            � �����, ���������� ������, �� ����� ���-
            ���������
         */
    }

    private static int[] solve(double[][] M0) {
        double[][] M1;
        double[][] M2;
        double[][] D1;
        double[][] D2;
        double[][] DD;
        // ������� ����������� ��� �������� �������
        M1 = doMtoM(M0);
        M2 = doMtoM(M1);
        D1 = doDfromM(M0, M1);
        D2 = doDfromM(M1, M2);
        DD = doDDfromD(M0, D1, D2);
        //out(DD);
        // ���������� ���������� ������������� �������� ������� DD
        return max(DD);
    }

    private static double[][] doMtoM(double[][] M0){
        double[][] M = new double[M0.length][M0.length];
        double alfa = 0.005;
        for (int i = 0; i < M.length; i++){
            for (int j = 0; j < M.length; j++){
                M[i][j] = M0[i][j] - alfa * dpaij(M0, i, j);
            }
        }
        return M;
    }

    private static double[][] doDfromM(double[][] M0, double[][] M1){
        double[][] D = new double[M0.length][M0.length];
        for (int i = 0; i < D.length; i++){
            for (int j = 0; j < D.length; j++){
                D[i][j] = dpaij(M1, i, j) - dpaij(M0, i, j);
            }
        }
        return D;
    }

    private static double[][] doDDfromD(double[][] M0, double[][] D1, double[][] D2){
        double[][] DD = new double[D1.length][D1.length];
        // �������� ������� DD
        for (int i = 0; i < DD.length; i++){
            for (int j = 0; j < DD.length; j++){
                if (M0[i][j] == 0)
                    DD[i][j] = D2[i][j] - D1[i][j];
            }
        }
        return DD;
    }

    private static int[] max(double[][] DD) {
        // ������� ������������ ������� DD
        double max = DD[0][0];
        int di = 0, dj = 0;
        for (int i = 0; i < DD.length; i++){
            for (int j = 0; j < DD.length; j++){
                if (DD[i][j] > max){
                    max = DD[i][j];
                    di = i;
                    dj = j;
                }
            }
        }
        // ���������� ���������� ������������� ��������
        return new int[]{di, dj};
    }

    private static double[][] doM0(double[][] M0, int di, int dj){
        M0 = changeJI(M0, di, dj);
        double[][] M1 = new double[M0.length - 1][M0.length - 1];

        // ���������� � ������� M1 �������� M0 ��� di � dj
        M1 = setElementsM0toM1(M0, M1, dj, dj);//di

        // ������ ������� ������ j �� ������ i
        //M1 = changeJIBefore(M1, di, dj);

        // �������� ������� ���������
        for (int i = 0; i < M1.length; i++) {
            M1[i][i] = 0;
        }

        // ���� � ������ (�������) ������� ��� �������� �� �������, �� �������� ��� ������ (�������)
        // �� ����������� �������
        boolean minusi, minusj;// �������� �� ���� � ������� � ��������.
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
        // ���� ���� ��������� ������, �� �������� �� ��� ����������� �������
        if (minusi)
            doVuch(M1, coi, minusi, false);
        if (minusj)
            doVuch(M1, coi, false, minusj);
        return M1;
    }

    private static double[][] setElementsM0toM1(double[][] M0, double[][] M1, int di, int dj){
        int ki = 0;
        int kj = 0;
        // ���������� � ������� M1 �������� M0 ��� di � dj
        for (int i = 0; i < M0.length; i++){
            if (i == di){
                ki++;
                continue;
            }
            kj = 0;
            for (int j = 0; j < M0.length; j++){
                if (j == dj){
                    kj++;
                } else{
                    M1[i - ki][j - kj] = M0[i][j];
                }
            }
        }
        return M1;
    }

    private static double[][] changeJI(double[][] M0, int di, int dj){
        double temp;
        for (int j = 0; j < M0.length; j++) {
            temp = M0[dj][j];
            M0[dj][j] = M0[di][j];
            M0[di][j] = temp;
        }
        return M0;
    }

    private static double[][] changeJIBefore(double[][] M1, int di, int dj){
        double temp;
        int tdj = dj;
        if (dj > di) {
            tdj = di;
        }
        if (dj < di)
            for (int i = 0; i < Math.abs(di - dj) - 1; i++) {
                for (int j = 0; j < M1.length; j++) {
                    temp = M1[tdj + i][j];
                    M1[tdj + i][j] = M1[tdj + i + 1][j];
                    M1[tdj + i + 1][j] = temp;
                }
            }
        else
            for (int i = 0; i > -(Math.abs(di - dj) - 1); i--) {
                for (int j = 0; j < M1.length; j++) {
                    temp = M1[tdj + i][j];
                    M1[tdj + i][j] = M1[tdj + i + 1][j];
                    M1[tdj + i + 1][j] = temp;
                }
            }
        return M1;
    }

    private static double[][] doVuch(double[][] M1, int coi, boolean minusi, boolean minusj){
        // �������� ����������� �������
        double min = 0;
        if (minusi){
            min = coi != 0 ? M1[coi][0] : M1[coi][1];
        }
        if (minusj)
            min = coi != 0 ? M1[0][coi] : M1[1][coi];
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

    // ������� ��� �������������� ������� � ����������� ����
    // �.�. � ������ � ������ ������ � �������
    private static double[][] doVuch(double[][] M){
        // ��������� ����������
        double[] minArrI = new double[M.length];
        double[] minArrJ = new double[M.length];
        minArrI[0] = M[0][1];
        minArrJ[0] = M[1][0];
        for (int i = 1; i < M.length; i++) {
            minArrI[i] = M[i][0];
            minArrJ[i] = M[0][i];
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

    // ���������� ������� ��� ��������
    private static double dpaij(double[][] M0 , int di, int dj){
        double dpaij;
        double zaik = 0;
        double zakj = 0;
        double zajk = 0;
        double zaki = 0;
        double aij = 0;
        double aji = 0;
        for (int k = 0; k < M0.length; k++){
            zaik += M0[di][k];
            zakj += M0[k][dj];
            zajk += M0[dj][k];
            zaki += M0[k][di];
        }
        aij = M0[di][dj];
        aji = M0[dj][di];
        dpaij = zaik + zakj - zajk - zaki - 3 * aij + 3 * aji;
        return dpaij;
    }

    private static void p(Object s){
        System.out.println(s+"");
    }
    private static void out(double[][] M) {
        if (M == null){
            return;
        }
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M.length; j++) {
               // if (i == j){
                    //System.out.print(" " + " ");
                //} else{
                    System.out.print(round(M[i][j]) + " ");
                    if (j == M.length - 1)
                        System.out.println("");
                //}
            }
        }
    }

    private static double round(double a){
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