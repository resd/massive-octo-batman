package algorithm.bab.util;

/**
 * @author Admin
 * @since 18.10.2015
 */
public class GeneralStruct {
    
    // Fields

    protected int[] edge;
    protected double H;
//    protected double HWithoutSum;
//    protected double HWithSum;
    private boolean lowerBound;
    private int[][] minP;

    // Constructors

    public GeneralStruct(int[] edge, double H) {
        this.edge = edge;
        this.H = H;
    }

    public GeneralStruct(int[] edge, double H, double HWithout, double HWith) {
        this.edge = edge;
        this.H = H;
//        this.HWithoutSum = H + HWithout;
//        this.HWithSum = H + HWith;
    }

    public GeneralStruct(double H, double HWithout, double HWith) {
        this.H = H;
//        this.HWithoutSum = H + HWithout;
//        this.HWithSum = H + HWith;
    }

    public GeneralStruct(double H, double HWithout, double HWith, boolean lowerBound, int[][] minP) {
        this.H = H;
//        this.HWithoutSum = H + HWithout;
//        this.HWithSum = H + HWith;
        this.lowerBound = lowerBound;
        this.minP = minP;
    }

    // Methods


    public void setAdditional(int[] edge, double H) {
        this.edge = edge;
        this.H = H;
    }

    @Override
    public String toString() {
        return "H = " + H + (isLowerBound() ? " LB" : "");
    }

    // Getters & Setters


    public int[] getEdge() {
        return edge;
    }

    public double getH() {
        return H;
    }

    /*public double getHWithoutSum() {
        return HWithoutSum;
    }

    public double getHWithSum() {
        return HWithSum;
    }*/

    public boolean isLowerBound() {
        return lowerBound;
    }

    public int[][] getMinP() {
        return minP;
    }
}
