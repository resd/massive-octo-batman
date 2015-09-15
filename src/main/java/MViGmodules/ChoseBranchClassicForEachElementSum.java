package MViGmodules;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Admin on 06.08.15.
 */
public class ChoseBranchClassicForEachElementSum extends ChoseBranchClassic {

    @Override
    protected Map defineMapEdge(double[][] array) {
        Map map = new LinkedHashMap<Object, Object>();
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
