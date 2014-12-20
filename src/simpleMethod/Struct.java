package simpleMethod;

import java.util.Map;

/**
 * Created by Admin on 19.12.14.
 */
public class Struct {
    private int id;
    private int beforId;
    private Map map;// Нужно?
    private int[] edge;
    private double HWith;
    private double HWithout;
    private int[][] p;
    private double[][] array;
    private double HWithSum;
    private double HWithoutSum;
    private boolean activate;


    public void setAll(int id, Map map, int[] edge, double HWith, double HWithout, double[][] array, double HWithSum,
                      double HWithoutSum, int[][] p, boolean activate) {
        this.id = id;
        this.map = map;
        this.edge = edge;
        this.HWith = HWith;
        this.HWithout = HWithout;
        this.array = array;
        this.HWithSum = HWithSum;
        this.HWithoutSum = HWithoutSum;
        this.activate = activate;
        this.p = p;
    }

    public int getId() {
        return id;
    }

    public Map getMap() {
        return map;
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

    public boolean isActivate() {
        return activate;
    }

    public Struct() {}

}
