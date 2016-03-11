package algorithm.bastrikov;

import algorithm.util.DuplicatesMethods;

import static algorithm.util.DuplicatesMethods.remove;

public class Work1OldStableVersion extends WorkBase implements Methods {


    /*double[][] M0 = { // Need to test
            {0, 0, 83, 9, 30, 6, 50},
            {0, 0, 66, 37, 17, 12, 26},
            {29, 1, 0, 19, 0, 12, 5},
            {32, 83, 66, 0, 49, 0, 80},
            {3, 21, 56, 7, 0, 0, 28},
            {0, 85, 8, 42, 89, 0, 0},
            {18, 0, 0, 0, 58, 13, 0}
    };*/
    /*private double[][][] M0ch; // TODO Deal with this
    private double[][][] M1ch;
    private double[][][] M2ch;
    private double[][][] D1ch;
    private double[][][] D2ch;
    private double[][][] DDch;
    private boolean saveAdditionalResult;*/

    public Work1OldStableVersion(double[][] M0) {
        originalSize = M0.length;
    }

    /*private double[][] doMfromM0(double[][] M0) {// Метод для вычисление матрицы М
        double[][] M = new double[M0.length][M0.length];
        double alfa = 0.005;
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M.length; j++) {
                if (i != j)
                    M[i][j] = M0[i][j] - alfa * dpaij(M0, i, j);
            }
        }
        return M;
    }

    private double[][] doDfromM(double[][] M0, double[][] M1) {// Метод для вычисление матрицы D
        double[][] D = new double[M0.length][M0.length];
        for (int i = 0; i < D.length; i++) {
            for (int j = 0; j < D.length; j++) {
                if (i != j)
                    D[i][j] = dpaij(M1, i, j) - dpaij(M0, i, j);
            }
        }
        return D;
    }

    private double[][] doDDfromD(double[][] M0, double[][] D1, double[][] D2) { // Метод для вычисление матрицы приведений DDch
        double[][] DD = new double[D1.length][D1.length];
        for (int i = 0; i < DD.length; i++) {
            for (int j = 0; j < DD.length; j++) {
                if (i != j && M0[i][j] == 0)
                    DD[i][j] = D2[i][j] - D1[i][j];
            }
        }
        return DD;
    }*/

    @Override
    public int[] getD(double[][] DD, int i) {
        int[] d;
        if (i == 0) {
            d = maxForFirstElement(DD); // Метод для получение максимального элемента для первой матрицы DDch
        } else {
            d = max(DD, beforeP[0], beforeP[1]);// Метод для получение максимального элемента для последующих матриц DDch
        }
        return d;
    }

    private int[] maxForFirstElement(double[][] DD) {
        double max = -Double.MAX_VALUE;
        int di = 0, dj = 0;

        for (int i = 0; i < DD.length; i++) {   //  Цикл для прохода по строкам
            for (int j = 0; j < DD.length; j++) {   // и столбцам матрицы DDch
                if (i != j && DD[i][j] > max) { // и нахождения координат
                    max = DD[i][j]; // максимального элемента
                    di = i;
                    dj = j;
                }
            }
        }
        return new int[]{di, dj};
    }

    @SuppressWarnings("ConstantConditions")
    private int[] max(double[][] DD, int ni, int nj) {
        double maxValue1 = -Double.MAX_VALUE;
        double maxValue2 = -Double.MAX_VALUE;
        int di = 0, dj = 0;

        for (int i = 0; i < DD.length; i++) {
            if (i != nj && DD[i][nj] > maxValue1) {// Поиск максимального элемента в строке
                maxValue1 = DD[i][nj];
                di = i;
            }
            if (i != ni && DD[ni][i] > maxValue2) {// Поиск максимального элемента в столбце
                maxValue2 = DD[ni][i];
                dj = i;
            }
        }

        return (maxValue1 >= maxValue2) ? new int[]{di, nj} : new int[]{ni, dj}; // Возврат максимального элемента
    }

    @Override
    @SuppressWarnings("Duplicates") /** Duplicate in
     @path algorithm.near.NearAlgorithmBase
     */
    // Don't know how to fix this
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

    @Override
    public double[][] doM0(double[][] M0, int i, int j) {
        M0[j][i] = 0; // Обнуление j-го и i-го элемента для сохранения гамильтонова цикла
        M0 = changeJI(M0, i, j); // Смена i-й строки и j-го столбца местами
        return setElementsM0toM(M0, j);// Новая матрица без j-й строки и j-го столбца
    }

    private double[][] changeJI(double[][] M0, int di, int dj) {// Меняем строку со столбцом
        double temp;
        for (int j = 0; j < M0.length; j++) {
            temp = M0[dj][j];
            M0[dj][j] = M0[di][j];
            M0[di][j] = temp;
        }

        return M0;
    }

    private double[][] setElementsM0toM(double[][] M0, int dj) {//  Вычитаем строку и столбец, возвращаем полученную редуцированную матрицу
        return DuplicatesMethods.setElementsM0toM(M0, dj);
    }

    @Override
    public void computeLastElement() {
        DuplicatesMethods.computeLastElement(p, mi, mj, originalSize);
    }


    @Override
    public int[][] getP() {
        return p;
    }

    /*public void setSaveAdditionalResult(boolean saveAdditionalResult) {
        Work1OldStableVersion.saveAdditionalResult = saveAdditionalResult;
    }

    public double[][][] getM0ch() {
        return M0ch;
    }

    public void setOriginalsize(int originalSize) {
        Work1OldStableVersion.originalSize = originalSize;
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

    /**
     * Цикл
     *  1. Найти ДД.
     *  2. Найти макс эдемент ДД.
     *  3. Найти соотв. элемент в пути(исходной матрице).
     *  4. Преобразовать матрицу.
     */
}
