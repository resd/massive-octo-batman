package algorithm.bab.classic.branch;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Admin
 * @since 06.08.15
 * "МВиГ классический (по каждому элементу со смежными)"
 */
public class ChoseBranchClassicForEachElementWithRelated extends ChoseBranchClassic {

    @Override
    protected Map<int[], Double> defineMapEdge(double[][] array) {
        Map<int[], Double> map = new LinkedHashMap<>();
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] != Double.POSITIVE_INFINITY) {
                    int[] indexes = new int[2];
                    indexes[0] = i;
                    indexes[1] = j;
                    //?aik + ?akj – 3aij
                    //?aik + ?akj – ?ajk – ?aki – 3aij + 3aji
                    map.put(indexes, getMinInRow(i, j, array) + getMinInCollumn(j, i, array)
                            - getMinInRow(j, i, array) - getMinInCollumn(i, j, array)
                            - 3 * array[i][j] + 3 * array[j][i]); 
                    //array[i][j] = Double.POSITIVE_INFINITY;
                }
            }
        }
        return map;
    }
}
