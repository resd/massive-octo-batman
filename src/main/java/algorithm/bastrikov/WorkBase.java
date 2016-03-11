package algorithm.bastrikov;

/**
 * @author Admin
 * @since 07.03.2016
 */
class WorkBase implements Methods {

    int originalSize;
    int[][] p;
    int[] mi;
    int[] mj;
    int[] beforeP;

    @Override
    @SuppressWarnings("Duplicates") // Duplicate in this method
    // Don't know how to fix this
    public void initialize() {  // Метод инициализация переменных
        p = new int[originalSize][2]; // необходимых для
        mi = new int[originalSize];   // расчетов
        mj = new int[originalSize];
        for (int i = 0; i < originalSize; i++) {
            mi[i] = i;
            mj[i] = i;
            p[i][0] = -1;
            p[i][1] = -1;
        }
        beforeP = new int[2];
        /*M0ch = new double[originalSize - 2][][];
        M1ch = new double[originalSize - 2][][];
        M2ch = new double[originalSize - 2][][];
        D1ch = new double[originalSize - 2][][];
        D2ch = new double[originalSize - 2][][];
        DDch = new double[originalSize - 2][][];
        saveAdditionalResult = false;*/
        //        count = 0;
    }

    @Override
    public double[][] solve(double[][] M0) {// Метод вычисления матрицы приведений DDch
        double[][] M1; //  Объявление необходимых для расчета переменных
        double[][] M2;
        double[][] D1;
        double[][] D2;
        double[][] DD;
        M1 = doMfromM0(M0);              // Методы для расчета
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
        return DD; // Возврат DD
    }

    @Override
    public int[] getD(double[][] DD, int i) {
        return null;
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


    @Override
    public void getPath(int[] d, int i) {
    }

    @Override
    public double[][] doM0(double[][] M0, int di, int dj) {
        return null;
    }

    @Override
    public int[][] getP() {
        return null;
    }

    @Override
    public void computeLastElement() {
    }

    private double dpaij(double[][] M0, int di, int dj) {// Метод для вычисление оценки ветви
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

    @Override
    @SuppressWarnings("Duplicates") // Duplicate in this method
    // Don't know how to fix this
    public void normalize(double[][] M) { // TODO Google for same methods realization
        double min = Double.MAX_VALUE;
        // TODO Fix at same time method - algorithm.bab.util.Normalize.normalize(double[][] M)
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
}
