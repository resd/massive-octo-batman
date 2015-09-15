package MViGmodules;

/**
 * Created by Admin on 19.12.14.
 */
@SuppressWarnings("all")
public class Struct {
    private int[] edge;
    private double HWith;
    private double HWithout;
    private double H;
    private int pathCount;
    private int[][] p;
    private int[][] pNew;
    private double[][] array;
    private double[][] M1;
    private double HWithSum;
    private double HWithoutSum;
    private int[] miOld;
    private int[] mjOld;
    private int pathCountNew;

    public int getPathCountNew() {
        return pathCountNew;
    }

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

    public void setAdditional(int[][] pNew, double[][] M1, int[] mi, int[] mj, int pathCountNew) {
        this.M1 = M1;
        this.pNew = pNew;
        this.mi = mi;
        this.mj = mj;
        this.pathCountNew = pathCountNew;
    }

    public double getH() {
        return H;
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

    public int getPathCount() {
        return pathCount;
    }

    public int[] getMiOld() {
        return miOld;
    }

    public int[] getMjOld() {
        return mjOld;
    }

    public void setArray(double[][] array) {
        this.array = array;
    }

    public void newStruct(int[] edge, double HWith, double HWithout, double H,
                  int[][] p, int pathCount) {
        this.edge = edge;
        this.HWith = HWith;
        this.HWithout = HWithout;
        this.H = H;
        this.pathCount = pathCount;
        this.HWithSum = HWith + H;
        this.HWithoutSum = HWithout + H;
        this.p = p;
    }

    public void addMiMj(int[] miOld, int[] mjOld) {
        this.miOld = miOld;
        this.mjOld = mjOld;
    }
}
