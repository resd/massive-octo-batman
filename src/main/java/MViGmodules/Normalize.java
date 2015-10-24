package MViGmodules;

/**
 * @author Admin
 * @since 13.07.15
 */
public class Normalize {
    private double[] minArrI;
    private double[] minArrJ;
    public static final Normalize INSTANCE = new Normalize();

    void normalize(double[][] M) {//todo сразу считать minSum, правда если нужно.
        minArrI = null;
        minArrJ = null;
        minArrI = new double[M.length];
        minArrJ = new double[M.length];
        for (int i = 0; i < M.length; i++) {
            minArrI[i] = Double.MAX_VALUE;
            minArrJ[i] = Double.MAX_VALUE;
        }
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M.length; j++) {
                if (M[i][j] != Double.POSITIVE_INFINITY && M[i][j] < minArrI[i]) {
                    minArrI[i] = M[i][j];
                    if (M[i][j] == 0) {
                        break;
                    }
                }
            }
        }
        for (int i = 0; i < M.length; i++) {
            if (minArrI[i] == 0) {
                continue;
            }
            for (int j = 0; j < M.length; j++) {
                if (M[i][j] != Double.POSITIVE_INFINITY) {
                    M[i][j] -= minArrI[i];
                }
            }
        }
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M.length; j++) {
                if (M[j][i] != Double.POSITIVE_INFINITY && M[j][i] < minArrJ[i]) {
                    minArrJ[i] = M[j][i];
                    if (M[j][i] == 0) {
                        break;
                    }
                }
            }
        }
        for (int i = 0; i < M.length; i++) {
            if (minArrJ[i] == 0) {
                continue;
            }
            for (int j = 0; j < M.length; j++) {
                if (M[j][i] != Double.POSITIVE_INFINITY) {
                    M[j][i] -= minArrJ[i];
                }
            }
        }
    }

    double[] getMinArrI() {
        return minArrI;
    }
//
    double[] getMinArrJ() {
        return minArrJ;
    }
}
