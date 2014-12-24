package common;

public class Work1OldStableVersion implements Methods{

    /*double[][] M0 = {
            {0, 0, 83, 9, 30, 6, 50},
            {0, 0, 66, 37, 17, 12, 26},
            {29, 1, 0, 19, 0, 12, 5},
            {32, 83, 66, 0, 49, 0, 80},
            {3, 21, 56, 7, 0, 0, 28},
            {0, 85, 8, 42, 89, 0, 0},
            {18, 0, 0, 0, 58, 13, 0}
    };*/

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

    public Work1OldStableVersion(double[][] M0) {
        originalsize = M0.length;
    }

    public void initialize() {  // Метод инициализация переменных
        p = new int[originalsize][2]; // необходимых для
        mi = new int[originalsize];   // расчетов
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

    public void normalize(double[][] M) { // Приведение матрицы к нормальному виду
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

    public double[][] solve(double[][] M0) {// Метод вычисления матрицы приведений DD
        double[][] M1s; //  Объявление необходимых для расчета переменных
        double[][] M2s;
        double[][] D1s;
        double[][] D2s;
        double[][] DDs;
        M1s = doMfromM0(M0); // Методы для расчета
        M2s = doMfromM0(M1s);
        D1s = doDfromM(M0, M1s);
        D2s = doDfromM(M1s, M2s);
        DDs = doDDfromD(M0, D1s, D2s);
        return DDs; // Возврат DD
    }

    static double[][] cloneMatrix(double[][] a) { // Метод для копирования матриц
        double[][] clone = a.clone();
        for (int i = 0; i < a.length; i++) {
            clone[i] = a[i].clone();
        }
        return clone;
    }

    private static double[][] doMfromM0(double[][] M0) {// Метод для вычисление матрицы М
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

    private static double[][] doDfromM(double[][] M0, double[][] M1) {// Метод для вычисление матрицы D
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

    private static double[][] doDDfromD(double[][] M0, double[][] D1, double[][] D2) { // Метод для вычисление матрицы приведений DD
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

    private static double dpaij(double[][] M0, int di, int dj) {// Метод для вычисление оценки ветви
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
        int[] d;
        if (i == 0) {
            d = maxForFirstElement(DD); // Метод для получение максимального элемента для первой матрицы DD
        } else {
            d = max(DD, beforeP[0], beforeP[1]);// Метод для получение максимального элемента для последующих матриц DD
        }
        return d;
    }

    private static int[] maxForFirstElement(double[][] DD) {
        double max = -Double.MAX_VALUE;
        int di = 0, dj = 0;

        for (int i = 0; i < DD.length; i++) {   //  Цикл для прохода по строкам
            for (int j = 0; j < DD.length; j++) {   // и столбцам матрицы DD
                if (i != j && DD[i][j] > max) { // и нахождения координат
                    max = DD[i][j]; // максимального элемента
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
            if (i != nj && DD[i][nj] > maxValue1) {// Поиск максимального элемента в строке
                maxValue1 = DD[i][nj];
                di = i;
            }
            if (i != ni && DD[ni][i] > maxValue2) {// Поиск максимального элемента в строке
                maxValue2 = DD[ni][i];
                dj = i;
            }
        }

        return (maxValue1 >= maxValue2) ? new int[]{di, nj} : new int[]{ni, dj}; // Возврат максимального элемента
    }

    public void getPath(int[] d, int i) {
        int x = d[0]; // Координаты максимального элемента
        int y = d[1];

        p[i][0] = mi[x];// Соответствие по данным координатам пути в исходной матрице
        p[i][1] = mj[y];

        if (x <= y) {// Сохранение координат текущего максимального элемента в редуцированной матрице
            beforeP[0] = x;
            beforeP[1] = x;
        } else {
            beforeP[1] = x - 1;
            beforeP[0] = x - 1;

        }

        mi[x] = mi[y];// Необходимые приведения
        mi = remove(mi, y);
        mj = remove(mj, y);
    }

    int[] remove(int[] mi, int y) { // Метод для удаления координат текущего элемента из пути
        int[] tmp = new int[mi.length - 1];
        System.arraycopy(mi, 0, tmp, 0, y);
        System.arraycopy(mi, y + 1, tmp, y, mi.length - y - 1);
        return tmp;
    }

    public double[][] doM0(double[][] M0, int i, int j) {
        M0[j][i] = 0; // Обнуление j-го и i-го элемента для сохранения гамильтонова цикла
        M0 = changeJI(M0, i, j); // Смена i-й строки и j-го столбца местами
        return setElementsM0toM(M0, j);// Новая матрица без j-й строки и j-го столбца
    }

    private static double[][] changeJI(double[][] M0, int di, int dj) {// Меняем строку со столбцом
        double temp;
        for (int j = 0; j < M0.length; j++) {
            temp = M0[dj][j];
            M0[dj][j] = M0[di][j];
            M0[di][j] = temp;
        }

        return M0;
    }

    private static double[][] setElementsM0toM(double[][] M0, int dj) {//  Вычитаем строку и столбец, возвращаем полученную редуцированную матрицу
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
        if (mj[0] == mi[0] || mj[1] == mi[0]) { // Находим правильное сочетание последнех двух элементов в пути и возвращаем результат
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



    public int[][] getP() {
        return p;
    }

    /*public static void setSaveAdditionalResult(boolean saveAdditionalResult) {
        Work1OldStableVersion.saveAdditionalResult = saveAdditionalResult;
    }

    public double[][][] getM0ch() {
        return M0ch;
    }

    public static void setOriginalsize(int originalsize) {
        Work1OldStableVersion.originalsize = originalsize;
    }

    *//**
     * @return 3d matrix that contents all M1 matrix.
     *//*
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
    }*/

    /**
     * Цикл
     *  1. Найти ДД.
     *  2. Найти макс эдемент ДД.
     *  3. Найти соотв. элемент в пути(исходной матрице).
     *  4. Преобразовать матрицу.
     */
}
