package MViGmodules;

/**
 * @author Admin
 * @since 19.12.14
 */
@SuppressWarnings("all")
public class Struct {
    private StructHW structHW;
    private StructHWout structHWout;
    private GeneralStruct generalStruct;

    public Struct() {
    }

    public Struct(StructHW structHW) {
        this.structHW = structHW;
    }

    public Struct(StructHWout structHWout) {
        this.structHWout = structHWout;
    }

    public Struct(GeneralStruct generalStruct) {
        this.generalStruct = generalStruct;
    }

    public Struct(StructHW structHW, StructHWout structHWout) {
        this.structHW = structHW;
        this.structHWout = structHWout;
    }

    public Struct(GeneralStruct generalStruct, StructHWout structHWout) {
        this.generalStruct = generalStruct;
        this.structHWout = structHWout;
    }

    public Struct(GeneralStruct generalStruct, StructHW structHW) {
        this.generalStruct = generalStruct;
        this.structHW = structHW;
    }

    private int[] edge;
    private double H;
    private double HWithoutSum;
    private double HWithSum;
    private boolean activatehwo;
    private boolean activatehw;
    private double[][] array;
    private double[][] M1;
    private double HWith;
    private double HWithout;
    private int[][] p;
    private int pathCount;
    private int[] miOld;
    private int[] mjOld;
    private int[][] pNew;
    private int pathCountNew;
    private int[] mi;
    private int[] mj;

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
//        Struct struct = new Struct();
        this.edge = edge;
        this.HWith = HWith;
        this.HWithout = HWithout;
        this.H = H;
        this.pathCount = pathCount;
        this.HWithSum = HWith + H;
        this.HWithoutSum = HWithout + H;
        this.p = p;
//        return struct;
    }

    public void newNullStruct(double HWith, double HWithout) {
        this.HWith = HWith;
        this.HWithout = HWithout;
        this.activatehw = true;
        this.activatehwo = true;
    }

    public void addMiMj(int[] miOld, int[] mjOld) {
        this.miOld = miOld;
        this.mjOld = mjOld;
    }

    @Override
    public String toString() {
        return "H = " + H + ", HWout = " + HWithoutSum + (activatehwo == true ? " +" : "") + ", HW = " + HWithSum + (activatehw == true ? " +" : "");
    }
}
