package algorithm.bab.classic;

import algorithm.bab.classic.branch.ChoseBranchClassic;
import algorithm.bab.util.Other;
import algorithm.bab.util.Struct;

/**
 * @author Admin
 * @since 24.10.2015
 */
public class BaBClassicAlgorithm  extends BaBClassicAlgorithmWithFirstLeftBounds {

    // Конструктор

    public BaBClassicAlgorithm(double[][] array) {
        super(array);
    }

    public BaBClassicAlgorithm(double[][] array, ChoseBranchClassic cb) {
        super(array, cb);
    }

    @Override
    protected void initialize() {
        super.initialize();
        needChooseLeftFirst = true;
    }

    protected void solve() {
        // Пока не переберутся все варианты

        while(true) { // checkMin(sa)
            Struct sa = cb.chooseBoth(path, var);
//            System.out.println(sa);

            boolean existNext;// = false
            sa = da.checkSa(sa, var.getMinLowerBound());
//            System.out.println(sa); //      comment
//            if (sa != null) {
//                if (sa.hasStructHW() && sa.getStructHW().getHWithSum() > var.getMinLowerBound() ||
//                        sa.hasStructHWout() && sa.getStructHWout().getHWithoutSum() > var.getMinLowerBound()) {
//                }
//                if (da.isEmpty()) {
//                    if (var.getMinLowerBound() < var.getMin()) {
//                        path.setP(Other.INSTANCE.cloneMatrix(minP));
//                    }
//                    break;
//                } else {
//                    existNext = da.checkMin(path, var);
//
//                }
//            } else {
//                existNext = da.checkMin(path, var);
//            }

            if (sa == null || !sa.getGeneralStruct().isLowerBound()) {
                existNext = da.checkMin(path, var);
                if (sa != null) {
                    if (!existNext && !sa.getGeneralStruct().isLowerBound()) {
                        if (sa.hasStructHWout() && sa.hasStructHW()) { // Если есть и HW и HWo
                            if (sa.getStructHWout().getHWithoutSum() <= sa.getStructHW().getHWithSum()) {
                                sa.setStructHWout(null);
                            } else {
                                sa.setStructHW(null);
                            }
                        } else
                            sa = null;
                    }
                } else {
                    if (!existNext) {
                        da.searchForLowestBound(path, var);
                        break;
                    }
                }
            }

            if (sa != null)
                da.add(sa);

            //sa = null;
            if (var.isArrayNull()) {
                //da.checkLowerBound(sa, var.getMinLowerBound());
                da.searchForLowestBound(path, var);
                if (var.getMinLeftBound() > var.getMin()) { // todo Перенести в choseBrance => ( > 2 ) {} ( else ) { here }
                    var.setMinLeftBound(var.getMin());
                    minP = Other.INSTANCE.cloneMatrix(path.getP());
                }
                existNext = da.checkMin(path, var);
                if (!existNext) {
                    break;
                } else {
                    da.checkDa(var.getMinLeftBound());
                }
            }
            count++;
            sa = null;
        }
        /*System.out.println("countBackWith = " + countBackWith);todo
        System.out.println("countHwith = " + countHwith);
        System.out.println("count = " + count);*/
    }

}
