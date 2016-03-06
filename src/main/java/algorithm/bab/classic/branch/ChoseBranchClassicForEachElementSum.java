package algorithm.bab.classic.branch;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Admin
 * @since 06.08.15
 * "���� ������������ (�� ������� �������� � ������)"
 */
public class ChoseBranchClassicForEachElementSum extends ChoseBranchClassic {

    @Override
    protected Map<int[], Double> defineMapEdge(double[][] array) {
        Map<int[], Double> map = new LinkedHashMap<>();
        countSums(array);
        double[] sumOfEachRow = getSumOfEachRow();
        double[] sumOfEachCol = getSumOfEachCol();
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] != Double.POSITIVE_INFINITY) {
                    int[] indexes = new int[2];
                    indexes[0] = i;
                    indexes[1] = j;
                    map.put(indexes, sumOfEachRow[i] + sumOfEachCol[j] - 3 * array[i][j]);
                    //array[i][j] = Double.POSITIVE_INFINITY;
                }
            }
        }
        return map;
    }
}
