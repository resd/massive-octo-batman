package algorithm.bastrikov;

/**
 * @author Admin
 * @since 19.12.14
 */
interface Methods {
    void initialize();

    void normalize(double[][] M);

    double[][] solve(double[][] M0);

    int[] getD(double[][] DD, int i);

    void getPath(int[] d, int i);

    double[][] doM0(double[][] M0, int di, int dj);

    int[][] getP();

    void computeLastElement();
}
