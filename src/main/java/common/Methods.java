package common;

/**
 * Created by Admin on 19.12.14.
 */
public interface Methods {
    void initialize();
    public void normalize(double[][] M);
    public double[][] solve(double[][] M0);
    public int[] getD(double[][] DD, int i);
    void getPath(int[] d, int i);
    public double[][] doM0(double[][] M0, int di, int dj);
    public int[][] getP();
    public void computeLastElement();
}
