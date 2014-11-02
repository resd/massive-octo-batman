
package common;

import com.deltapackage.ParentFrame;
import java.util.Arrays;

/**
 * Created by Admin on 07.09.14.
 */
public class Work1Main {

    private ParentFrame frame;

    public ParentFrame getFrame() {
        return frame;
    }

    public void setFrame(ParentFrame frame) {
        this.frame = frame;
    }
    
    public Work1Main() {
    }

    public Work1Main(double[][] a) {
        setM0(a);
    }

    private int originalsize;
    private double[][] a;
    private int[][] p;

    Work1OldStableVersion w;

    long sysTime;

    public void main() {
        double[][] M;
        sysTime = System.currentTimeMillis();
        w = new Work1OldStableVersion();
        w.setM0(a);
        w.setOriginalsize(a.length);
        M = Arrays.copyOf(a, a.length);

        double[][] DD;
        int d[];

        w.initialize();
        int n = M.length - 2;
        for (int i = 0; i < n; i++) {
            w.normalize(M);
            DD = w.solve(M);
            d = w.getD(DD, i);
            w.getPath(d, i);
            M = w.doM0(M, d[0], d[1]);
        }
        w.computeLastElement();
        p = w.getP();
    }

    public double[][][] getM0ch() {
        return w.getM0ch();
    }

    /**
     *
     * @return 3d matrix that contents all M1 matrix.
     */
    public double[][][] getM1() {
        return w.getM1();
    }

    public double[][][] getM2() {
        return w.getM2();
    }

    public double[][][] getD1() {
        return w.getD1();
    }

    public double[][][] getD2() {
        return w.getD2();
    }

    public double[][][] getDD() {
        return w.getDD();
    }

    public double[][] getM0ch(int count) {
        return w.getM0ch()[count];
    }

    public double[][] getM1(int count) {
        return w.getM1()[count];
    }

    public double[][] getM2(int count) {
        return w.getM2()[count];
    }

    public double[][] getD1(int count) {
        return w.getD1()[count];
    }

    public double[][] getD2(int count) {
        return w.getD2()[count];
    }

    public double[][] getDD(int count) {
        return w.getDD()[count];
    }

    public String getPath() {
        StringBuffer str = new StringBuffer("");
        for (int i = 0; i < originalsize; i++) {
            str.append("(").append(p[i][0] + 1).append("-").append(p[i][1] + 1).append(") ");
        }
        return str.toString();
    }

    public double getSum(double[][] a) {
        double Sum = 0;
        for (int k = 0; k < originalsize; k++) {
            Sum += a[p[k][0]][p[k][1]];
        }
        return Sum;
    }

    public long getTime() {
        return System.currentTimeMillis() - sysTime;
    }

    public void setM0(double[][] a) {
        double[][] clone = a.clone();

        for (int i = 0; i < a.length; i++) {
            clone[i] = a[i].clone();
        }
        this.a = clone;
        originalsize = a.length;
    }

}
