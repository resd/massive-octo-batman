package algorithm.bab.parallel;

import algorithm.bab.classic.BaBClassicAlgorithm;
import algorithm.bab.parallel.branch.*;
import algorithm.bab.util.DA;
import algorithm.bab.util.Path;
import algorithm.bab.util.Var;
import algorithm.util.MethodAction;

/**
 * @author Admin
 * @since 26.10.2015
 */
//@SuppressWarnings("all")
//@SuppressWarnings({"unused", "Duplicates"})
public class BaBParallel extends BaBClassicAlgorithm implements MethodAction {

    protected ChoseBranchParallelSum cbSum;
    protected ChoseBranchParallelForEachElement cbForEachElement;
    protected ChoseBranchParallelForEachElementSum cbForEachElementSum;
    protected ChoseBranchParallelForEachElementWithRelated cbForEachElementWithRelated;
    protected ChoseBranchParallelForEachElementWithRelatedSum cbForEachElementWithRelatedSum;
    private ChoseBranchParallel cbp;

    public BaBParallel(double[][] array) {
        super(array);
    }

    @Override
    protected void initialize() {
        // ���������� ����������

        path = new Path(arrayClass);
        da = new DA();
        cbp = new ChoseBranchParallel();
        cbSum = new ChoseBranchParallelSum();
        cbForEachElement = new ChoseBranchParallelForEachElement();
        cbForEachElementSum = new ChoseBranchParallelForEachElementSum();
        cbForEachElementWithRelated = new ChoseBranchParallelForEachElementWithRelated();
        cbForEachElementWithRelatedSum = new ChoseBranchParallelForEachElementWithRelatedSum();
        minP = new int[originalSize][2];
    }

    @Override
    protected void solve() {
        int cBSize = 6;
        Var[] varCopy = new Var[cBSize];
        Path[] pathCopy = new Path[cBSize];

        // ���� �� ����������� ��� ��������

        do {
            var.setMinParallel(Double.MAX_VALUE);
            for (int i = 0; i < cBSize; i++) {
                varCopy[i] = new Var(var);
                pathCopy[i] = new Path(path);
            }
            cbp.                            chooseBoth(pathCopy[0], varCopy[0], var, arrayClass);
//            varCopy = new Var(var);
//            pathCopy = new Path(path);
            cbSum.                          chooseBoth(pathCopy[1], varCopy[1], var, arrayClass);
//            varCopy = new Var(var);
//            pathCopy = new Path(path);
            cbForEachElement.               chooseBoth(pathCopy[2], varCopy[2], var, arrayClass);
//            varCopy = new Var(var);
//            pathCopy = new Path(path);
            cbForEachElementSum.            chooseBoth(pathCopy[3], varCopy[3], var, arrayClass);
//            varCopy = new Var(var);
//            pathCopy = new Path(path);
            cbForEachElementWithRelated.    chooseBoth(pathCopy[4], varCopy[4], var, arrayClass);
//            varCopy = new Var(var);
//            pathCopy = new Path(path);
            cbForEachElementWithRelatedSum. chooseBoth(pathCopy[5], varCopy[5], var, arrayClass);

            double minCB = Double.MAX_VALUE;
            int minCI = 0;
            for (int i = 0; i < cBSize; i++) {
                if (varCopy[i].getMinParallel() < minCB) {
                    minCB = varCopy[i].getMinParallel();
                    minCI = i;
                }
            }
            var  = new Var(varCopy[minCI]);
            path = new Path(pathCopy[minCI]);
        } while(!varCopy[0].isArrayNull());

        double minSum = Double.MAX_VALUE;
        double tempSum;
        for (int i = 0; i < cBSize; i++) {
            tempSum = getSum(arrayClass.getA(), pathCopy[i]);
            if (tempSum < minSum) {
                minSum = tempSum;
                path = pathCopy[i];
            }
        }

            /*boolean existNext;// = false
            sa = da.checkSa(sa, var.getMinLowerBound());

            if (sa == null || !sa.getGeneralStruct().isLowerBound()) {
                existNext = da.checkMin(path, var);
                if (sa != null) {
                    if (!existNext) {
                        if (sa.hasStructHWout() && sa.hasStructHW()) { // ���� ���� � HW � HWo
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


            if (var.isArrayNull()) {
                //da.checkLowerBound(sa, var.getMinLowerBound());
                da.searchForLowestBound(path, var);
                if (var.getMinLeftBound() > var.getMin()) { // todo ��������� � choseBrance => ( > 2 ) {} ( else ) { here }
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
        */


        /*System.out.println("countBackWith = " + countBackWith);todo
        System.out.println("countHwith = " + countHwith);
        System.out.println("count = " + count);*/
    }

    public double getSum(double[][] a, Path path) {
        double sum = 0;
        int[][] p = path.getP();
        for (int k = 0; k < originalSize; k++) {
            sum += a[p[k][0]][p[k][1]];
        }
        return sum;
    }
}
