package algorithm.bab.classic;

import algorithm.bab.classic.branch.ChoseBranchClassic;

/**
 * @author Admin
 * @since 24.10.2015
 */
public class BaBClassicAlgorithmWithFirstLeftBounds extends BaBClassicAlgorithm {

    // Конструктор

    public BaBClassicAlgorithmWithFirstLeftBounds(double[][] array) {
        super(array);
        needChooseLeftFirst = true;
    }

    public BaBClassicAlgorithmWithFirstLeftBounds(double[][] array, ChoseBranchClassic cb) {
        super(array, cb);
        needChooseLeftFirst = true;
    }

}
