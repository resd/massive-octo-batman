package simpleMethod;

/**
 * Created by Admin on 19.12.14.
 */
public class Struct {
    private int id;
    //private int beforId;  // Нужно?
    private int[] edge;
    private double HWith;
    private double HWithout;
    private double H;
    private int[][] p;
    private int[][] pNew;
    private double[][] array;
    private double[][] M1;
    private double HWithSum;
    private double HWithoutSum;

    public boolean isActivatehw() {
        return activatehw;
    }

    public void setActivatehw(boolean activatehw) {
        this.activatehw = activatehw;
    }

    public boolean isActivatehwo() {
        return activatehwo;
    }

    public void setActivatehwo(boolean activatehwo) {
        this.activatehwo = activatehwo;
    }

    private boolean activatehw;
    private boolean activatehwo;
    private int[] mi;
    private int[] mj;


    public void setAll(int id, int[] edge, double HWith, double HWithout, double[][] array, double H,
                       int[][] p, int[] mi, int[] mj) {
        /*this.id = id;
        this.edge = edge;
        this.HWith = HWith;
        this.HWithout = HWithout;
        this.array = array;
        this.H = H;
        this.HWithSum = HWith + H;
        this.HWithoutSum = HWithout + H;
        this.p = p;
        this.mi = mi;
        this.mj = mj;*/
    }

    public void setAdditional(int[][] pNew, double[][] M1, int[] mi, int[] mj) {
        this.M1 = M1;
        this.pNew = pNew;
        this.mi = mi;
        this.mj = mj;
    }

    public double getH() {
        return H;
    }

    public int getId() {
        return id;
    }

    public int[] getEdge() {
        return edge;
    }

    public double getHWith() {
        return HWith;
    }

    public double getHWithout() {
        return HWithout;
    }

    public int[][] getP() {
        return p;
    }

    public double[][] getArray() {
        return array;
    }

    public double getHWithSum() {
        return HWithSum;
    }

    public double getHWithoutSum() {
        return HWithoutSum;
    }

    public int[][] getpNew() {
        return pNew;
    }

    public double[][] getM1() {
        return M1;
    }

    public int[] getMi() {
        return mi;
    }

    public int[] getMj() {
        return mj;
    }

    public Struct(int id, int[] edge, double HWith, double HWithout, double[][] array, double H,
                  int[][] p) {
        this.id = id;
        this.edge = edge;
        this.HWith = HWith;
        this.HWithout = HWithout;
        this.array = array;
        this.H = H;
        this.HWithSum = HWith + H;
        this.HWithoutSum = HWithout + H;
        this.p = p;
    }

}
