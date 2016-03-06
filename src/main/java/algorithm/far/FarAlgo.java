package algorithm.far;

import algorithm.util.MethodAction;
import util.C;

/**
 * Created by Admin on 29.03.15.
 */
public class FarAlgo implements MethodAction {

    /*private double[][] M0 = {
            {0, 0, 83, 9, 30, 6, 50},
            {0, 0, 66, 37, 17, 12, 26},
            {29, 1, 0, 19, 0, 12, 5},
            {32, 83, 66, 0, 49, 0, 80},
            {3, 21, 56, 7, 0, 0, 28},
            {0, 85, 8, 42, 89, 0, 0},
            {18, 0, 0, 0, 58, 13, 0}
    };
    private static double[][] M00 = {
            {0, 0, 83, 9, 30, 6, 50},
            {0, 0, 66, 37, 17, 12, 26},
            {29, 1, 0, 19, 0, 12, 5},
            {32, 83, 66, 0, 49, 0, 80},
            {3, 21, 56, 7, 0, 0, 28},
            {0, 85, 8, 42, 89, 0, 0},
            {18, 0, 0, 0, 58, 13, 0}
    };*/
    /*private static double[][] M0 = {
            {0, 1, 10, 10},
            {10, 0, 1, 10},
            {10, 10, 0, 1},
            {1, 10, 10, 0}
    };*/

    /*private double[][] M0 = {
            {0, 0, 83, 8, 30, 6, 50},
            {0, 0, 66, 37, 17, 12, 26},
            {29, 1, 0, 19, 0, 12, 5},
            {32, 883, 66, 0, 49, 0, 80},
            {3, 821, 56, 7, 0, 0, 28},
            {0, 885, 8, 42, 89, 0, 0},
            {18, 880, 0, 0, 58, 13, 0}
    };
    private static double[][] M00 = {
            {0, 0, 83, 8, 30, 6, 50},
            {0, 0, 66, 37, 17, 12, 26},
            {29, 1, 0, 19, 0, 12, 5},
            {32, 883, 66, 0, 49, 0, 80},
            {3, 821, 56, 7, 0, 0, 28},
            {0, 885, 8, 42, 89, 0, 0},
            {18, 880, 0, 0, 58, 13, 0}
    };*/

    private double[][] M0 = {
            { 0,77, 53,74 ,  6},
            {30, 0, 24, 46, 28},
            {78,42,  0,  0, 72},
            {70,90, 30,  0,  8},
            {90, 28,62, 93,  0}
    };
    private static double[][] M00 = {
            { 0,77, 53,74 ,  6},
            {30, 0, 24, 46, 28},
            {78,42,  0,  0, 72},
            {70,90, 30,  0,  8},
            {90, 28,62, 93,  0}
    };

    private int originalsize;
    private int[][] p;
    private long sysTime;
    private double[] maxRow;
    private double[] maxCol;
    private int[] countElCol;
    private int[] countElRow;
    private int[][] idElRow;
    private int[][] idElCol;
    private int countP;

    public FarAlgo(double[][] M0) {
        this.M0 = cloneMatrix(M0);
        originalsize = M0.length;
    }

    public void setM0(double[][] M0) {
        this.M0 = cloneMatrix(M0);
        originalsize = M0.length;
    }

    private void initialize() {  // Метод инициализация переменных
        p = new int[originalsize][2]; // необходимых для
        idElRow = new int[originalsize][2];
        idElCol = new int[originalsize][2];
        countElCol = new int[originalsize];
        countElRow = new int[originalsize];
        maxRow = new double[originalsize];
        maxCol = new double[originalsize];
        double INF = Double.POSITIVE_INFINITY;
        countP = 0;
        for (int i = 0; i < originalsize; i++) {
            M0[i][i] = INF;
            p[i][0] = -1;
            p[i][1] = -1;
            countElCol[i] = 0;
            countElRow[i] = 0;
            maxRow[i] = -Double.MAX_VALUE;
            maxCol[i] = -Double.MAX_VALUE;
        }
    }

    private void maxs(double[][] M) {
        double INF = Double.POSITIVE_INFINITY;
        for (int i = 0; i < originalsize; i++) {   //  Цикл для прохода по строкам
            for (int j = 0; j < originalsize; j++) {   // и столбцам матрицы DDch
                if (M[i][j] != INF && M[i][j] > maxRow[i]) { // и нахождения координат
                    maxRow[i] = M[i][j]; // максимального элемента
                    idElRow[i][0] = i;
                    idElRow[i][1] = j;
                }
                if (M[j][i] != INF && M[j][i] > maxCol[i]) { // и нахождения координат
                    maxCol[i] = M[j][i]; // максимального элемента
                    idElCol[i][0] = j;
                    idElCol[i][1] = i;
                }
            }
        }
    }

    private int[] maxOfMax() {
        double max = -Double.MAX_VALUE;
        int[] edge = null;
        for (int i = 0; i < originalsize; i++) {
            if (maxRow[i] > max) {//
                max = maxRow[i];
                edge = idElRow[i];
            }
            if (maxCol[i] > max) {//
                max = maxCol[i];
                edge = idElCol[i];
            }
        }
        return edge;
    }

    private void newMaxRow(int x, double[][] M) {
        double INF = Double.POSITIVE_INFINITY;
        maxRow[x] = -Double.MAX_VALUE;
        for (int j = 0; j < originalsize; j++) {   //  Цикл для прохода по строкам
            if (M[x][j] != INF && M[x][j] > maxRow[x] && maxRow[x] != -1) { // && countElCol[j] < originalsize - 2
                maxRow[x] = M[x][j]; // и нахождения координат максимального элемента
                idElRow[x][0] = x;
                idElRow[x][1] = j;
            }
        }
    }

    private void newMaxCol(int y, double[][] M) {
        double INF = Double.POSITIVE_INFINITY;
        maxCol[y] = -Double.MAX_VALUE;
        for (int i = 0; i < originalsize; i++) {
            if (M[i][y] != INF && M[i][y] > maxCol[y] && maxCol[y] != -1) { // && countElRow[i] < originalsize - 2
                maxCol[y] = M[i][y]; // и нахождения координат максимального элемента
                idElCol[y][0] = i;
                idElCol[y][1] = y;
            }
        }
    }

    @Override
    public void main() throws NullPointerException{
        sysTime = System.currentTimeMillis();
        double[][] M;
        int d[];
        double INF = Double.POSITIVE_INFINITY;
        initialize();
        M = cloneMatrix(M0);
        maxs(M);
        int limit = originalsize - 2;
        int x;
        int y;
        int temp;
        for (int s = 0; s < (originalsize*originalsize - 2*originalsize); s++){
            d = maxOfMax();
            x = d[0];
            y = d[1];
            temp = checkMaxRow(x, y, limit, M);
            while (temp != Integer.MIN_VALUE){
                temp = checkMaxRow(temp, x, limit, M);// todo add checkMaxCol
                /*if (temp != Integer.MIN_VALUE) {
                    for (int i = 0; i < originalsize; i++)
                        for (int j = 0; j < originalsize; j++) {
                            if (i != j && M[i][j] != INF)
                                checkMaxRow(i, j, limit, M);
                        }
                }*/
            }
            temp = checkMaxCol(x, y, limit, M);
            while (temp != Integer.MIN_VALUE){
                temp = checkMaxCol(y, temp, limit, M);// todo add checkMaxRow
                /*if (temp != Integer.MIN_VALUE) {
                    for (int i = 0; i < originalsize; i++)
                        for (int j = 0; j < originalsize; j++) {
                            if (i != j && M[i][j] != INF)
                                checkMaxRow(i, j, limit, M);
                        }
                }*/
            }
            M[x][y] = INF;// [3, 2] [3, 1] [2, 3] [2, 1] [1, 3] [1, 2]
            if (countElRow[x] <= limit) {
                //maxRow[x] = -1;
                countElRow[x] += 1;
                newMaxRow(x, M);
            }
            if (countElCol[y] <= limit) {
                //maxCol[y] = -1;
                countElCol[y] += 1;
                newMaxCol(y, M);
            }
            temp = checkMaxRow(x, y, limit, M);
            while (temp != Integer.MIN_VALUE){
                temp = checkMaxRow(temp, x, limit, M);// todo add checkMaxCol
                /*if (temp != Integer.MIN_VALUE) {
                    for (int i = 0; i < originalsize; i++)
                        for (int j = 0; j < originalsize; j++) {
                            if (i != j && M[i][j] != INF)
                                checkMaxRow(i, j, limit, M);
                        }
                }*/
            }
            temp = checkMaxCol(x, y, limit, M);
            while (temp != Integer.MIN_VALUE){
                temp = checkMaxCol(y, temp, limit, M);// todo add checkMaxRow
                /*if (temp != Integer.MIN_VALUE) {
                    for (int i = 0; i < originalsize; i++)
                        for (int j = 0; j < originalsize; j++) {
                            if (i != j && M[i][j] != INF)
                                checkMaxRow(i, j, limit, M);
                        }
                }*/
            }
            //C.p(sumEl());
            /*C.p(s + ",  [" + x + ", " + y + "]:");todo uncomment
            C.p(Arrays.toString(countElRow));
            C.p(Arrays.toString(maxRow));
            C.p(Arrays.toString(countElCol));
            C.p(Arrays.toString(maxCol));
            C.p("");*/
            if (countP == originalsize) break;
        }
    }

    private int checkMaxRow(int x, int y, int limit, double[][] M) {
        double INF = Double.POSITIVE_INFINITY;
        if (countElRow[x] >= limit && maxRow[x] != -1){
            maxRow[x] = -1;
            if (maxCol[y] != -1)
                newMaxCol(y, M);
            //countElCol[y] += 1;
            for (int k = 0; k < originalsize; k++) {
                if (M[x][k] != INF) {
                    M[x][k] = INF;
                    if (checkP(k, x, limit)) {
                        k = findMinRow(x, k);
                    }
                    countElRow[x] = limit + 1;
                    getPath(new int[]{x, k}, countP);
                    countP++;
                    maxCol[k] = -1;
                    countElCol[k] = limit + 1;
                    if (M[k][x] != INF) {
                        if (countElRow[k] == limit && maxRow[k] != -1) {
                            M[k][x] = INF;
                            int q = findMinRow(x, k);
                            M[q][x] = M0[q][x];
                            checkMaxCol(k, x, limit, M);// todo check
                        } else {
                            M[k][x] = INF;
                            countElRow[k] += 1;// todo need?
                            countElCol[x] += 1;// todo need?
                            if (maxRow[k] != -1) newMaxRow(k, M);
                            if (maxCol[x] != -1) newMaxCol(x, M);
                        }
                    }
                    for (int t = 0; t < originalsize; t++) {
                        if (M[t][k] != INF) {
                            if (countElRow[t] == limit && maxRow[t] != -1) {
                                int q = findMinRow(t, k);//todo переместить нажу, убрать 1 переменную
                                M[t][q] = M0[t][q];
                                M[t][k] = INF;
                                checkMaxRow(t, k, limit, M);// todo может вынести в массив temp[i++] и запилить цикл for(.. < temp.length..) в while(temp!=...)
                            } else {
                                M[t][k] = INF;
                                newMaxRow(t, M);
                                countElRow[t] += 1;
                            }
                        }
                    }
                    return k;
                }
            }

        }
        return Integer.MIN_VALUE;
    }

    private boolean checkP(int k, int x, int limit) {
        if (countP < limit + 1) {
            int w = k;
            for (int i = 0; i < p.length; i++) {
                for (int j = 0; j < p.length; j++) {
                    if (p[j][0] == w) {
                        if (p[j][1] == x) {
                            return true;
                        }
                        w = p[j][1];
                        break;
                    }
                }
            }
        }
        return false;
    }

    private int findMinRow(int t, int k) {
        double min = Double.MAX_VALUE;
        int x = -1;
        for (int i = 0; i < originalsize; i++) {
            if (i != t && i != k && M0[t][i] < min && maxCol[i] != -1) {
                min = M0[t][i];
                x = i;
            }
        }
        return x;
    }

    private int findMinCol(int t, int k) {
        double min = Double.MAX_VALUE;
        int y = -1;
        for (int j = 0; j < originalsize; j++) {
            if (j != k && j != t && M0[j][k] < min && maxRow[j] != -1) {
                min = M0[j][k];
                y = j;
            }
        }
        return y;
    }

    private int checkMaxCol(int x, int y, int limit, double[][] M) {
        double INF = Double.POSITIVE_INFINITY;
        if (countElCol[y] >= limit && maxCol[y] != -1) {
            maxCol[y] = -1;// todo need to comment this 2 line ?
            if (maxRow[x] != -1)
                newMaxRow(x, M);
            for (int k = 0; k < originalsize; k++) {
                if (M[k][y] != INF) {
                    M[k][y] = INF;
                    if (checkP(y, k, limit)) {
                        int q = findMinCol(k, y);
                        if (q != -1) k = q;
                    }
                    countElCol[y] = limit + 1;
                    getPath(new int[]{k, y}, countP);
                    countP++;
                    maxRow[k] = -1;
                    countElRow[k] = limit + 1;
                    if (M[y][k] != INF) {
                        if (countElCol[k] == limit && maxCol[k] != -1){
                            M[y][k] = INF;
                            int q = findMinRow(x, k);
                            M[q][k] = M0[q][k];
                            checkMaxRow(y, k, limit, M);// todo check
                        }
                        else {
                            M[y][k] = INF;
                            countElCol[k] += 1;
                            countElRow[y] += 1;
                            newMaxRow(y, M);
                            newMaxCol(k, M);
                        }
                    }
                    for (int t = 0; t < originalsize; t++) {
                        if (M[k][t] != INF) {
                            if (countElCol[t] == limit && maxCol[t] != -1) {
                                int q = findMinCol(k, t);
                                M[k][t] = INF;
                                M[q][t] = M0[q][t]; // todo Error
                                checkMaxCol(k, t, limit, M);//todo check k t
                            } else {
                                M[k][t] = INF;
                                newMaxCol(t, M);
                                countElCol[t] += 1;
                            }
                        }
                    }
                    return k;
                }

            }
        }
        return Integer.MIN_VALUE;
    }

    public static void main(String[] args) {
        FarAlgo fa = new FarAlgo(M00);
        fa.main();
        C.p("\nPath: " + fa.getPath() + "\nSum = " + fa.getSum(M00) + ",  Time: " + fa.getTime());
    }

    private void getPath(int[] d, int i) {
        int x = d[0];
        int y = d[1];
        p[i][0] = x;
        p[i][1] = y;
    }

    private int[][] getP() {
        return p;
    }

    static double[][] cloneMatrix(double[][] a) { // Метод для копирования матриц
        double[][] clone = a.clone();
        for (int i = 0; i < a.length; i++) {
            clone[i] = a[i].clone();
        }
        return clone;
    }

    public int[][] cloneMatrix(int[][] a) {
        int[][] clone = a.clone();

        for (int i = 0; i < a.length; i++) {
            clone[i] = a[i].clone();
        }
        return clone;
    }

    public String getPath() {
        StringBuffer str = new StringBuffer("");
        for (int i = 0; i < originalsize; i++) {
            str.append("(").append(p[i][0] + 1).append("-").append(p[i][1] + 1).append(") ");
        }
        return str.toString();
    }

    @Override
    public double getSum(double[][] a) {
        double Sum = 0;
        for (int k = 0; k < originalsize; k++) {
            Sum += a[p[k][0]][p[k][1]];
        }
        return Sum;
    }

    @Override
    public long getTime() {
        return System.currentTimeMillis() - sysTime;
    }
}

/*if (countElRow[x] >= limit){
                flagContinue = true;
                maxRow[x] = -1;
                M[y][x] = INF;
                countElRow[y] += 1;
                countElCol[x] += 1;
                newMaxRow(y, M);
                newMaxCol(x, M);
                for (int k = 0; k < originalsize; k++) {
                    if (M[x][k] != INF) {
                        getPath(new int[]{x, k}, countP);
                        countP++;
                        newMaxCol(k, M);
                        maxCol[k] = -1;
                        countElCol[k] = limit;
                        for (int t = 0; t < originalsize; t++) {
                            if (M[t][k] != INF) {
                                M[t][k] = INF;
                                countElRow[t] += 1;
                            }
                        }
                        break;
                    }
                }
            }
            if (countElCol[y] >= limit) {
                flagContinue = true;
                maxCol[y] = -1;
                M[y][x] = INF;
                countElRow[y] += 1;
                countElCol[x] += 1;
                newMaxRow(y, M);
                newMaxCol(x, M);
                for (int k = 0; k < originalsize; k++) {
                    if (M[k][y] != INF) {
                        getPath(new int[]{k, y}, countP);
                        countP++;
                        newMaxRow(k, M);
                        maxRow[k] = -1;
                        countElRow[k] = limit;
                        for (int t = 0; t < originalsize; t++) {
                            if (M[k][t] != INF) {
                                M[k][t] = INF;
                                countElCol[t] += 1;

                            }
                        }
                        break;
                    }

                }
            }
            if (flagContinue) {
                M[x][y] = INF;
                flagContinue = false;
                continue;
            }*/