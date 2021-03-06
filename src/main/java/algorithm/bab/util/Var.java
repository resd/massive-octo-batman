package algorithm.bab.util;

/**
 * @author Admin
 * @since 14.07.15
 */
public class Var {

    private double[][] array;
//    private double[][] a;
    private double[][] M1;
    private double H;
    private double min;
    private double minLowerBound;
    private double minLeftBound;
    private double minParallel;



    private Var(double[][] array, double h, double[][] m1) {
        this.array = array;
        H = h;
        M1 = m1;
//        this.a = a;
//        originalSize = array.length;
        double M = Double.POSITIVE_INFINITY;
        for (int i = 0, tempIndexLimit = array.length; i < tempIndexLimit; i++) {
            array[i][i] = M;
        }
        minLowerBound = Double.MAX_VALUE;
        minLeftBound = Double.MAX_VALUE;
        minParallel = Double.MAX_VALUE;
        min = Double.MAX_VALUE;
    }

    public Var(Var var) {
        array = Other.cloneMatrix(var.array);
        H = var.H;
        M1 = Other.cloneMatrix(var.M1);
//        a = Other.cloneMatrix(var.a);
//        originalSize = var.originalSize;
        min = var.min;
        minLowerBound = var.minLowerBound;
        minLeftBound = var.minLeftBound;
        minParallel = var.minParallel;
    }

    public double[][] getArray() {
        return array;
    }

    public Var(double[][] array) {
        this(array, -Double.MAX_VALUE, null);
    }

    public void setArray(double[][] array) {
        this.array = array;
    }

    public double[][] getM1() {
        return M1;
    }

    public void setM1(double[][] m1) {
        M1 = m1;
    }

    public double getH() {
        return H;
    }

    public void setH(double h) {
        H = h;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    /*public double[][] getA() {
        return a;
    }*/
/*
    public int getOriginalSize() {
        return originalSize;
    }*/

    public int getArrayLength() {
        return array.length;
    }

    public boolean isArrayNull() {
        return array == null;
    }

    public double getMinLowerBound() {
        return minLowerBound;
    }

    public void setMinLowerBound(double minLowerBound) {
        if (this.minLowerBound > minLowerBound)
            this.minLowerBound = minLowerBound;
    }
//
    public double getMinLeftBound() {
        return minLeftBound;
    }

    public void setMinLeftBound(double minLeftBound) {
        this.minLeftBound = minLeftBound;
    }

    public double getMinParallel() {
        return minParallel;
    }

    public void setMinParallel(double minParallel) {
        this.minParallel = minParallel;
    }
}