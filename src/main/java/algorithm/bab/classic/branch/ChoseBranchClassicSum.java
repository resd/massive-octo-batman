package algorithm.bab.classic.branch;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Admin
 * @since 06.08.15
 * "ћ¬и√ классический (с суммой)"
 */
@SuppressWarnings("all")
public class ChoseBranchClassicSum extends ChoseBranchClassic {

    @Override
    protected Map defineMapEdge(double[][] array) {
        Map<int[], Double> map = new LinkedHashMap<>();
        countSums(array);
        double[] sumOfEachRow = getSumOfEachRow();
        double[] sumOfEachCol = getSumOfEachCol();
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] == 0) {
                    int[] indexes = new int[2];
                    indexes[0] = i;
                    indexes[1] = j;
                    map.put(indexes, sumOfEachRow[i] + sumOfEachCol[j]);
                }
            }
        }
        return map;
    }

    double[] getSumOfEachRow(double[][] M) {
        double[] sumOfEachRow = new double[M.length];
        for (int i = 0; i < M.length; i++) {
            sumOfEachRow[i] = Double.MAX_VALUE;
        }
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M.length; j++) {
                if (M[i][j] != Double.POSITIVE_INFINITY) {
                    sumOfEachRow[i] += M[i][j];
                }
            }
        }
        return sumOfEachRow;
    }

    double[] getSumOfEachCol(double[][] M) {
        double[] sumOfEachCol = new double[M.length];
        for (int i = 0; i < M.length; i++) {
            sumOfEachCol[i] = Double.MAX_VALUE;
        }
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M.length; j++) {
                if (M[j][i] != Double.POSITIVE_INFINITY) {
                    sumOfEachCol[i] += M[j][i];
                }
            }
        }
        return sumOfEachCol;
    }
}
