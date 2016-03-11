package algorithm.near;

import algorithm.util.DuplicatesMethods;

/**
 * @author Admin
 * @since 06.03.2016
 */
class NearAlgorithmBase {

    int originalSize;
    int[][] p;
    int[] mi;
    int[] mj;
    int[] beforeP;

    void computeLastElement(int[][] p, int[] mi, int[] mj, int originalSize) {
        DuplicatesMethods.computeLastElement(p, mi, mj, originalSize);
    }

    @SuppressWarnings("Duplicates") // Duplicate in algorithm.bastrikov.WorkBase
        // Don't know how to fix this
    void initialize() {  // Метод инициализация переменных
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
    }

    @SuppressWarnings("Duplicates") // Duplicate in algorithm.bastrikov.WorkBase
    // Don't know how to fix this
    void getPath(int[] d, int i) {
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
        mi = DuplicatesMethods.remove(mi, y);
        mj = DuplicatesMethods.remove(mj, y);
    }

    double[][] setElementsM0toM(double[][] M0, int dj) {//  Вычитаем строку и столбец, возвращаем полученную редуцированную матрицу
        return DuplicatesMethods.setElementsM0toM(M0, dj);
    }
}
