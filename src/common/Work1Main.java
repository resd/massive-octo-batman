package common;

import java.util.Arrays;

/**
 * Created by Admin on 07.09.14.
 */
public class Work1Main {

    public Work1Main(double[][] a) {

        double[][] clone = a.clone();

        for (int i = 0; i < a.length; i++) {
            clone[i] = a[i].clone();
        }
        this.a = clone;
        originalsize = a.length;
    }

    private int originalsize;
    private double[][] a;
    private int[][] p;

    Work1OldStableVersion w;
    Work1 w1;

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
        w.p = new int[originalsize][2];
        w.mi = new int[originalsize];
        w.mj = new int[originalsize];

        w.fillP();
        w.create();
        int n = M.length - 2;//2
        for (int i = 0; i < n; i++) {
            w.normalize(M);
            DD = w.solve(M);
            /*p("M");
            out(w.M);
            p("DD");
            out(DD);*/

            d = w.getD(DD, i);
            w.getPath(d, i, M);
            //p(Arrays.deepToString(p));
            //p(a[0] +", " +  a[1]);
            M = w.doM0(M, d[0], d[1]);
        }
        w.computeLastElement();
        p = w.getP();
    }

    public void mainNewMethod() {
        double[][] M;
        sysTime = System.currentTimeMillis();
        w1 = new Work1();
        w1.setM0(a);
        w1.setOriginalsize(a.length);
        M = Arrays.copyOf(a, a.length);

        double[][] DD;
        int d[];
        w1.p = new int[originalsize][2];
        w1.mi = new int[originalsize];
        w1.mj = new int[originalsize];

        w1.fillP();
        w1.create();
        int n = M.length - 2;
        w1.normalize(M);
        DD = w1.solve(M);
        d = w1.getD(DD, 0);
        w1.getPath(d[0], d[1], 0);
        M = w1.doM0(M, d[0], d[1]);
        int r = 1;
        for (int i = 1; i < n; i += 2) {
            w1.normalize(M);
            DD = w1.solve(M);
            p = p;
            w1.p("M");
            w1.out(M);
            w1.p("DD");
            w1.out(DD);
            if (i > (n - 2)) {
                d = w1.getD(DD, i);
                w1.getPath(d[0], d[1], i);
                //M = doM0(w.M, d[0], d[1]);
            } else {
                d = w1.getD(DD, i);
                w1.getPath(d, r);
                //w.getPath(d[0], d[1], r);
                //w.getPath(d[2], d[3], (r + 1));
                M = w1.doM0(M, d);
                r+=2;
            }
            //p(d[0] +", " +  d[1]);
            //M = doM0(w.M, d[0], d[1]);
            //M = doM0(w.M, d[2], d[3]);
        }
        w1.computeLastElement();
        p = w1.getP();
    }

    public String getPath() {
        StringBuffer str = new StringBuffer("");
        for (int i = 0; i < originalsize; i++) {
            str.append("(").append(p[i][0] + 1).append("-").append(p[i][1] + 1).append(") ");
        }
        return str.toString();
    }

    public double getSum(double[][] a) {
        //Work1OldStableVersion w2 = new Work1OldStableVersion();
        //w2.setM0(a);
        double Sum = 0;
        for (int k = 0; k < originalsize; k++) {
            Sum += a[p[k][0]][p[k][1]];
        }
        return Sum;
    }

    public long getTime() {
        return System.currentTimeMillis() - sysTime;
    }

}
