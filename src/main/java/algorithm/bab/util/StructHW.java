package algorithm.bab.util;

/**
 * @author Admin
 * @since 11.10.2015
 */
@SuppressWarnings("all")
public class StructHW {

    // Fields

    //private double H;
    private double HWithSum;
    private double[][] array;
    private double HWith;
    private int[][] p;
    private int pathCount;
    private int[] miOld;
    private int[] mjOld;

    // Methods

    public StructHW(double H, double HWith, double[][] array,
                                        int[][] p, int pathCount, int[] miOld, int[] mjOld) {
//        hw.edge = edge;
        //this.H = H;
        this.HWith = HWith;
        this.HWithSum = H + HWith;
        this.array = array;
        this.p = p;
        this.pathCount = pathCount;
        this.miOld = miOld;
        this.mjOld = mjOld;
    }

    public void newStructa(int[] edge, double HWith, double HWithout, double H,
                          int[][] p, int pathCount) {
//        Struct struct = new Struct();
//        this.edge = edge;
        this.HWith = HWith;
//        this.H = H;
        this.pathCount = pathCount;
        this.HWithSum = HWith + H;
        this.p = p;
//        return struct;
    }

    public void newNullStruct(double HWith, double HWithout) {
        this.HWith = HWith;
    }

    public void addMiMj(int[] miOld, int[] mjOld) {
        this.miOld = miOld;
        this.mjOld = mjOld;
    }

    @Override
    public String toString() {
        return "HW = " + HWithSum;//"H = " + H +
    }

    // Getters & Setters


    public double getHWithSum() {
        return HWithSum;
    }

    public void setHWithSum(double HWithSum) {
        this.HWithSum = HWithSum;
    }

    public double[][] getArray() {
        return array;
    }

    public void setArray(double[][] array) {
        this.array = array;
    }

    public double getHWith() {
        return HWith;
    }

    public void setHWith(double HWith) {
        this.HWith = HWith;
    }

    public int[][] getP() {
        return p;
    }

    public void setP(int[][] p) {
        this.p = p;
    }

    public int getPathCount() {
        return pathCount;
    }

    public void setPathCount(int pathCount) {
        this.pathCount = pathCount;
    }

    public int[] getMiOld() {
        return miOld;
    }

    public void setMiOld(int[] miOld) {
        this.miOld = miOld;
    }

    public int[] getMjOld() {
        return mjOld;
    }

    public void setMjOld(int[] mjOld) {
        this.mjOld = mjOld;
    }
}
