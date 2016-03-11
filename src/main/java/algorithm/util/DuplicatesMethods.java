package algorithm.util;

/**
 * @author Admin
 * @since 06.03.2016
 */
public class DuplicatesMethods {

    public static void computeLastElement(int[][] p, int[] mi, int[] mj, int originalSize) {
        if (mj[0] == mi[0] || mj[1] == mi[0]) { // Находим правильное сочетание последнех двух элементов в пути и возвращаем результат
            p[originalSize - 2][0] = mi[1];
            p[originalSize - 2][1] = mi[0];
            p[originalSize - 1][0] = mi[0];
            p[originalSize - 1][1] = mj[1];
        } else {
            p[originalSize - 2][0] = mi[0];
            p[originalSize - 2][1] = mi[1];
            p[originalSize - 1][0] = mi[1];
            p[originalSize - 1][1] = mj[0];
        }
    }

    public static int[] remove(int[] mi, int y) { // Метод для удаления координат текущего элемента из пути
        int[] tmp = new int[mi.length - 1];
        System.arraycopy(mi, 0, tmp, 0, y);
        System.arraycopy(mi, y + 1, tmp, y, mi.length - y - 1);
        return tmp;
    }

    public static double[][] setElementsM0toM(double[][] M0, int dj) {//  Вычитаем строку и столбец, возвращаем полученную редуцированную матрицу
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

}
