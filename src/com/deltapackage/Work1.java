/**
 * Created by Admin on 03.04.14.
 */

package com.deltapackage;

public class Work1 {

    // Коэф. для расчетов
    static private double     alfa = 0.005;
    static private double[][] M0;

    public static double[][] getM0() {
        return M0;
    }

    public static void setM0(double[][] M0) {
        Work1.M0 = M0;
    }

    public static void main(String[] args){
       
        // Реалиация алгоритма
        for (int i = 0; i < 1; i++) {

        }
        out(solve(M0, 0));
        // Вывод результатов
        p("\n");
    }

    /*
    Input - M0
    Output -
     */

    private static double[][] solve(double[][] M0, int ddx) {
        int di;
        int dj;
        int[] d;
        double[][] M1;// = new double[7][7];
        double[][] M2;// = new double[7][7];
        double[][] D1;// = new double[7][7];
        double[][] D2;// = new double[7][7];
        double[][] DD1 = null;// = new double[7][7];
        //for (int i = 0; i < M0.length - 3; i++){//todo
        //for (int i = 0; i < 1; i++) {
        M1 = doMtoM(M0, ddx);
        M2 = doMtoM(M1, ddx);
        D1 = doDfromM(M0, M1, ddx);
        D2 = doDfromM(M1, M2, ddx);
        d = doDDfromD(M0, D1, D2, ddx);
        di = d[0];
        dj = d[1];
        M0 = doM0(M0, di, dj, ddx);
        //}
        //Нужно возвратить матрицу на 1 порядок меньше.
        return M0;
    }


    private static double[][] doMtoM(double[][] M0, int ddx){
        double[][] M = new double[M0.length - ddx][M0.length - ddx];
        for (int i = 0; i < M.length; i++){
            for (int j = 0; j < M.length; j++){
                M[i][j] = M0[i][j] - alfa * dpaij(M0, i, j, ddx);
            }
        }
        return M;
    }

    private static double[][] doDfromM(double[][] M0, double[][] M1, int ddx){
        double[][] D = new double[M0.length - ddx][M0.length - ddx];
        for (int i = 0; i < D.length; i++){
            for (int j = 0; j < D.length; j++){
                D[i][j] = dpaij(M1, i, j, ddx) - dpaij(M0, i, j, ddx);
            }
        }
        return D;
    }

    private static int[] doDDfromD(double[][] M0, double[][] D1, double[][] D2, int ddx){
        double[][] DD = new double[D1.length - ddx][D1.length - ddx];
        // Получаем матрицу DD
        for (int i = 0; i < DD.length; i++){
            for (int j = 0; j < DD.length; j++){
                if (M0[i][j] == 0)
                    DD[i][j] = D2[i][j] - D1[i][j];
            }
        }
        // Находим максимальный элемент DD
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
        return new int[]{di, dj};
    }

    private static double[][] doM0(double[][] M0, int di, int dj, int ddx){
        /*for (int ){//todo
        }*/
        /*
        0 0 0
        0 1 2
        1 2 3
        ->
        0 0
        0 1
         */

        double[][] M1 = new double[M0.length - 1][M0.length - 1];
        int ki = 0;
        int kj = 0;
        for (int i = 0; i < M0.length; i++){
            if (i == di){
                ki++;
                continue;
            }
            for (int j = 0; j < M0.length; j++){
                if (j == dj){
                    kj++;
                    continue;
                } else{
                    M1[i - ki][j - kj] = M0[i][j];
                }
            }
        }
        return M1;
    }

    private static double dpaij(double[][] M0 , int di, int dj, int ddx){
        double dpaij;
        double zaik = 0;
        double zakj = 0;
        double zajk = 0;
        double zaki = 0;
        double aij = 0;
        double aji = 0;
        for (int k = 0; k < M0.length - ddx; k++){
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

    private static void p(String s){
        System.out.println(s);
    }
    private static void p(int s){
        System.out.println(s);
    }
    private static void p(double s){
        System.out.println(s);
    }
    private static void out(double[][] M) {
        if (M == null){
            return;
        }
        /*for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M.length; j++) {
                if (i == j){
                    System.out.print(" " + " ");
                } else{
                    System.out.print(M[i][j] + " ");
                    if (j == M.length - 1)
                        System.out.println("");
                }
            }
        }*/
        //Output out  = new Output();
        //out.setM0(M0);
        
        
    }
}
