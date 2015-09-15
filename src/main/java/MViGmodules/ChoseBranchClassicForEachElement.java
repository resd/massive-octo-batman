package MViGmodules;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Admin on 04.08.15.
 */
public class ChoseBranchClassicForEachElement extends ChoseBranchClassic {

    @Override
    protected Map defineMapEdge(double[][] array) {
        Map map = new LinkedHashMap<Object, Object>();
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] != Double.POSITIVE_INFINITY) {
                    int[] indexes = new int[2];
                    indexes[0] = i;
                    indexes[1] = j;
                    map.put(indexes, getMinInRow(i, j, array) + getMinInCollumn(j, i, array) - 3 * array[i][j]);
                    //array[i][j] = Double.POSITIVE_INFINITY;
                }
            }
        }
        return map;
    }
}
