package algorithm.bab;

import algorithm.bab.util.DA;
import algorithm.bab.util.Path;
import algorithm.bab.util.Struct;
import algorithm.bab.util.Var;

/**
 * @author Admin
 * @since 06.03.2016
 */
public class Bab {

    public static boolean isEndByNoMoreSmallerBounds(Struct structForCheckToExitOfWhile,
                                                     DA da,
                                                     Path path,
                                                     Var var) {
        boolean existNext;

        if (structForCheckToExitOfWhile == null || !structForCheckToExitOfWhile.getGeneralStruct().isLowerBound()) {
            existNext = da.checkMin(path, var);

            if (structForCheckToExitOfWhile != null) {

                if (!existNext) {

                    if (structForCheckToExitOfWhile.hasStructHWout() && structForCheckToExitOfWhile.hasStructHW()) { // Если есть и HW и HWo

                        if (structForCheckToExitOfWhile.getStructHWout().getHWithoutSum() <= structForCheckToExitOfWhile.getStructHW().getHWithSum()) {

                            structForCheckToExitOfWhile.setStructHWout(null);

                        } else {

                            structForCheckToExitOfWhile.setStructHW(null);

                        }

                    } else

                        //noinspection UnusedAssignment
                        structForCheckToExitOfWhile = null; // probably need to avoid memory leak

                } // end of if

            } else {

                if (!existNext) {

                    da.searchForLowestBound(path, var);
                    return true;

                }  // end of if

            }

        } // end of if
        return false;
    }

}
