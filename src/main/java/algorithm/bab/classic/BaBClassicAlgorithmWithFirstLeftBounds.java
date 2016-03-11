package algorithm.bab.classic;

import algorithm.util.MethodAction;

/**
 * @author Admin
 * @since 24.10.2015
 */
public class BaBClassicAlgorithmWithFirstLeftBounds extends BaBClassicAlgorithm implements MethodAction {

    // Constructor

    public BaBClassicAlgorithmWithFirstLeftBounds(double[][] array) {
        super(array);
        needChooseLeftFirst = true;
    }

    // May be need
    /*public BaBClassicAlgorithmWithFirstLeftBounds(double[][] array, ChoseBranchClassic cb) {
        super(array, cb);
        needChooseLeftFirst = true;
    }*/

}
