package simpleMethod;

/**
 * Created by Admin on 02.04.15.
 */
public class NearAlgoEveryDot {
    private double[][] M0 = {
            {0, 0, 83, 9, 30, 6, 50},
            {0, 0, 66, 37, 17, 12, 26},
            {29, 1, 0, 19, 0, 12, 5},
            {32, 83, 66, 0, 49, 0, 80},
            {3, 21, 56, 7, 0, 0, 28},
            {0, 85, 8, 42, 89, 0, 0},
            {18, 0, 0, 0, 58, 13, 0}
    };
    private int originalsize;
    private int[][] p;
    private int[] mi;
    private int[] mj;
    private int[] beforeP;
    private long sysTime;
    private double[] sumArr;
    private int[][][] pArr;
    private int idMax;

    public NearAlgoEveryDot(double[][] M0) {
        this.M0 = cloneMatrix(M0);
        originalsize = M0.length;
    }

    public void setM0(double[][] M0) {
        this.M0 = cloneMatrix(M0);
        originalsize = M0.length;
    }

    private void initialize() {  // Метод инициализация переменных
        p = new int[originalsize][2]; // необходимых для
        mi = new int[originalsize];   // расчетов
        mj = new int[originalsize];
        for (int i = 0; i < originalsize; i++) {
            mi[i] = i;
            mj[i] = i;
            p[i][0] = -1;
            p[i][1] = -1;
        }
        beforeP = new int[2];
    }

    private int[] min(double[][] DD, int ni) {
        double minValue = Double.MAX_VALUE;
        int dj = 0;

        for (int j = 0; j < DD.length; j++) {
            if (j != ni && DD[ni][j] < minValue) {// Поиск максимального элемента в строке
                minValue = DD[ni][j];
                dj = j;
            }
        }

        return new int[]{ni, dj}; // Возврат максимального элемента
    }

    private void getPath(int[] d, int i) {
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

    private double[][] doM0(double[][] M0, int i, int j) {
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
        double[][] M1 = new double[M0.length - 1][M0.length - 1]; // todo попробовать перерилить это через systemarraycopy()
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

    private void computeLastElement() {
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



    private int[][] getP() {
        return p;
    }

    public void go() {
        sysTime = System.currentTimeMillis();
        int randomNumX = -1;
        double[][] M;
        int d[];
        int countSum = 0;
        sumArr = new double[originalsize*originalsize - originalsize];
        pArr = new int[originalsize*originalsize - originalsize][originalsize][2];

        for (int count = 0; count < originalsize; count++) {
            for (int c = 0; c < originalsize; c++) {
                if (c == count) continue;
                initialize();
                d = new int[]{count, c};
                M = cloneMatrix(M0);
                int n = M.length - 2;
                for (int i = 0; i < n; i++) {
                    if (i != 0) {
                        d = min(M, randomNumX);
                    }
                    getPath(d, i);
                    M = doM0(M, d[0], d[1]);
                    randomNumX = beforeP[1];
                }
                computeLastElement();                     // Нахождение двух последних элементов пути
                sumArr[countSum] = getSumLoop(M0);
                pArr[countSum] = cloneMatrix(p);
                countSum++;
            }
        }
    }


    /*public static void main(String[] args) {
        NearAlgoEveryDot na = new NearAlgoEveryDot(M0);
        na.go();
        double sum = na.getSum(M0);
        C.p("\nPath: " + na.getPath() + "\nSum = " + sum + ",  Time: " + na.getTime());
    }*/

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
        p = cloneMatrix(pArr[idMax]);
        for (int i = 0; i < originalsize; i++) {
            str.append("(").append(p[i][0] + 1).append("-").append(p[i][1] + 1).append(") ");
        }
        return str.toString();
    }

    private double getSumLoop(double[][] a) {
        double Sum = 0;
        for (int k = 0; k < originalsize; k++) {
            Sum += a[p[k][0]][p[k][1]];
        }
        return Sum;
    }

    public double getSum(double[][] a) {
        double minValue = Double.MAX_VALUE;

        for (int i = 0; i < sumArr.length; i++) {
            if (sumArr[i] < minValue) {// Поиск максимального элемента в строке
                minValue = sumArr[i];
                idMax = i;
            }
        }
        return minValue;
    }

    public long getTime() {
        return System.currentTimeMillis() - sysTime;
    }
}
