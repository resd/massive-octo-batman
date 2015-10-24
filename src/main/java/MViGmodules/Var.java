package MViGmodules;

/**
 * @author Admin
 * @since 14.07.15
 */
public class Var {
    private double[][] array;
    private double[][] a;
    private double[][] M1;
    private double H;
    private double min;
    private double minLowerBound;
    private double minLeftBound;

    private int originalSize;

    public Var(double[][] array, double[][] a, double h, double[][] m1) {
        this.array = array;
        H = h;
        M1 = m1;
        this.a = a;
        originalSize = array.length;
        double M = Double.POSITIVE_INFINITY;
        for (int i = 0; i < originalSize; i++) {
            array[i][i] = M;
        }
        minLowerBound = Double.MAX_VALUE;
    }

    public double[][] getArray() {
        return array;
    }

    public Var(double[][] array, double[][] a) {
        this(array, a, -Double.MAX_VALUE, null);
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

    public double[][] getA() {
        return a;
    }

    public int getOriginalSize() {
        return originalSize;
    }

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
}