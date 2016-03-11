package algorithm.bab.util;

/**
 * @author Admin
 * @since 11.10.2015
 */
//@SuppressWarnings("all")
public class StructHWout {

    // Fields

    //private double H;
    private final double HWithoutSum;
    private double[][] M1;
    private double HWithout;
    private final int[][] pNew;
    private final int pathCountNew;
    private int[] mi;
    private int[] mj;

    // Methods

    public StructHWout(double H, double HWithout, double[][] M1,
                                        int[][] pNew, int pathCountNew, int[] mi, int[] mj) {
        //this.H = H;
        this.HWithout = HWithout;
        this.HWithoutSum = H + HWithout;
        this.M1 = M1;
        this.pNew = pNew;
        this.pathCountNew = pathCountNew;
        this.mi = mi;
        this.mj = mj;
    }

    // TODO Deal with this methods
    /*public void setAdditional(int[][] pNew, double[][] M1, int[] mi, int[] mj, int pathCountNew) {
        this.M1 = M1;
        this.pNew = pNew;
        this.mi = mi;
        this.mj = mj;
        this.pathCountNew = pathCountNew;
    }

    public void newStructa(int[] edge, double HWith, double HWithout, double H,
                          int[][] p, int pathCount) {
//        Struct struct = new Struct();
//        this.edge = edge;
        this.HWithout = HWithout;
//        this.H = H;
        this.pathCountNew = pathCount;
        this.HWithoutSum = HWithout + H;
        this.pNew = p;
//        return struct;
    }

    public void newNullStruct(double HWith, double HWithout) {
        this.HWithout = HWithout;
    }

    public void addMiMj(int[] miOld, int[] mjOld) {
//        this.miOld = miOld;
//        this.mjOld = mjOld;
    }*/

    @Override
    public String toString() {
        return "HWout = " + HWithoutSum;//"H = " + H +
    }

    // Getters & Setters


    public double getHWithoutSum() {
        return HWithoutSum;
    }

    public double[][] getM1() {
        return M1;
    }

    public void setM1(double[][] m1) {
        M1 = m1;
    }

    public double getHWithout() {
        return HWithout;
    }

    public void setHWithout(double HWithout) {
        this.HWithout = HWithout;
    }

    public int[][] getpNew() {
        return pNew;
    }

    public int getPathCountNew() {
        return pathCountNew;
    }

    public int[] getMi() {
        return mi;
    }

    public void setMi(int[] mi) {
        this.mi = mi;
    }

    public int[] getMj() {
        return mj;
    }

    public void setMj(int[] mj) {
        this.mj = mj;
    }
}
