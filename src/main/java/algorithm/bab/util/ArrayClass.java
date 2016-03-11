package algorithm.bab.util;

/**
 * @author Admin
 * @since 18.02.2016
 */
public class ArrayClass {

    private final double[][] a;
    private final int originalSize;

    public ArrayClass(double[][] a) {
        this.a = a;
        originalSize = a.length;
    }

    public double[][] getA() {
        return a;
    }

    public int getOriginalSize() {
        return originalSize;
    }
}
