package algorithm.bab.util;

/**
 * @author Admin
 * @since 13.07.15
 */
public class Normalize {
    private double[] minArrI;
    private double[] minArrJ;
    public static final Normalize INSTANCE = new Normalize();

    @SuppressWarnings("Duplicates")
    public void normalize(double[][] M) {//todo сразу считать minSum, правда если нужно.
        minArrI = null; // TODO Google for same methods realization
        minArrJ = null; // TODO Fix at same time method - algorithm.bastrikov.WorkBase.normalize(double[][] M)
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
            for (double[] aM : M) {
                if (aM[i] != Double.POSITIVE_INFINITY && aM[i] < minArrJ[i]) {
                    minArrJ[i] = aM[i];
                    if (aM[i] == 0) {
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

    //ищет сумму констант приведения
    public double getSumOfDelta() {
        double sum = 0;
        double[] minArrI = getMinArrI();
        double[] minArrJ = getMinArrJ();

        for (int i = 0, tempIndexLimit = minArrI.length; i < tempIndexLimit; i++) {
            sum += minArrI[i];
            sum += minArrJ[i];
        }
        return sum;
    }

    public double[] getMinArrI() {
        return minArrI;
    }
//
public double[] getMinArrJ() {
        return minArrJ;
    }
}
