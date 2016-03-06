package algorithm.bab.util;

/**
 * @author Admin
 * @since 18.02.2016
 */
public class ArrayClass {

    private double[][] a;
    private int originalSize;

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
