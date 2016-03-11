package algorithm.bab.util;

/**
 * @author Admin
 * @since 14.07.15
 */
public class Other {

    public static double[][] cloneMatrix(double[][] a) {
        if (a == null) return null;
        double[][] clone = a.clone();

        for (int i = 0, tempIndexLimit = a.length; i < tempIndexLimit; i++) {
            clone[i] = a[i].clone();
        }
        return clone;
    }

    public static int[][] cloneMatrix(int[][] a) {
        if (a == null) return null;
        int[][] clone = a.clone();

        for (int i = 0, tempIndexLimit = a.length; i < tempIndexLimit; i++) {
            clone[i] = a[i].clone();
        }
        return clone;
    }

}