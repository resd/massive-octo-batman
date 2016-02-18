package algorithm.bab.parallel.branch;

import algorithm.bab.classic.branch.ChoseBranchClassic;
import algorithm.bab.util.*;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author Admin
 * @since 11.11.2015
 */
public class ChoseBranchParallel extends ChoseBranchClassic {

    public Struct chooseBoth(Path path, Var var, Var varRef) {
        Struct sa;

        GeneralStruct generalStruct;

        // ���� ����� ������� > 2
        if (var.getArrayLength() > 2) {
            Map map;// = new HashMap<Object, Double>();
            // ������� max ����� ���� ������� ��-��� �� ������ ���
            map = defineMapEdge(var.getArray());
            // ������� ��-� �����-��� max ����� �� ����� map
            ArrayList edges = difineEdges(map);
            // ��������� ������ Struct � �������� � ��� ������� �� HWith � HWithout
            //try {
            if (map.size() == 0) {
                return new Struct(true);
            }
            sa = chooseHone((int[]) edges.get(0), (double) map.get(edges.get(0)), path, var, varRef);
            /*} catch (Exception e) {
                e.printStackTrace();
            }*/
        } else {
            double HWith;
            double HWithout;
            double Sum = 0;
            var.setArray(null);
            var.setM1(null);
            path.getP()[var.getOriginalSize() - 2][0] = path.getMi()[0];
            path.getP()[var.getOriginalSize() - 2][1] = path.getMj()[1];
            path.getP()[var.getOriginalSize() - 1][0] = path.getMi()[1];
            path.getP()[var.getOriginalSize() - 1][1] = path.getMj()[0];
            for (int k = 0; k < var.getOriginalSize(); k++) {
                Sum += var.getA()[path.getP()[k][0]][path.getP()[k][1]];
            }
            HWithout = Sum - var.getH();
            if (HWithout < 0) System.out.println("Error with (Sum - H) = " + HWithout);
            var.setH(var.getH() + HWithout);
            var.setMin(var.getH());
            HWith = Double.POSITIVE_INFINITY;
            generalStruct = new GeneralStruct(var.getH(), HWithout, HWith, true, Other.INSTANCE.cloneMatrix(path.getP()));
            var.setMinLowerBound(var.getH());
            /*structHW = new StructHW(var.getH(), HWith, null, Other.INSTANCE.cloneMatrix(path.getP()), path.getPathCount(),
                    path.getMi().clone(), path.getMj().clone());*/
            sa = new Struct(generalStruct);
//            sa.setActivatehw(true);
        }
//        if (sa.getM1() == null) {
//            sa.setAdditional(Other.INSTANCE.cloneMatrix(path.getP()), Other.INSTANCE.cloneMatrix(var.getM1()), path.getMi().clone(), path.getMj().clone(), path.getPathCount());
//        }
        return sa;
    }

    // ��������� ������ Struct � �������� � ��� ������� �� HWith � HWithout
    protected Struct chooseHone(int[] edge, double HWith, Path path, Var var, Var varRef) {
        double[][] arrayC;
        Struct sa;
        GeneralStruct generalStruct;
        StructHW structHW = null;
        StructHWout structHWout = null;

        double HWithout;

        arrayC = Other.INSTANCE.cloneMatrix(var.getArray());
        var.getArray()[edge[1]][edge[0]] = Double.POSITIVE_INFINITY;
        var.setM1(getHWithout(edge, var.getArray()));
        HWithout = getSumOfDelta();

        arrayC[edge[0]][edge[1]] = Double.POSITIVE_INFINITY;
        Normalize.INSTANCE.normalize(arrayC);
//        sa.setArray(Other.INSTANCE.cloneMatrix(arrayC));// ���� ������� ������ �� 2 �������, �� ���-��� �������� �� ����� �������.
//        sa.newStruct(edge.clone(), HWith, HWithout, var.getH(), Other.INSTANCE.cloneMatrix(path.getP()), path.getPathCount());
//        sa.addMiMj(path.getMi().clone(), path.getMj().clone());

        //todo System.out.println((H + HWithout) + ",  " + (H + HWith) + "     (" + (mi[edge[0]] + 1) + ", " + (mj[edge[1]] + 1) + ")");
        //System.out.println("");display(array);System.out.println("");

        if (HWithout <= HWith) {
            if (HWithout < var.getMinParallel()) {
                generalStruct = new GeneralStruct(edge, var.getH(), HWithout, HWith);
                if (HWith < var.getMinLeftBound()) {
                    structHW = new StructHW(var.getH(), HWith, Other.INSTANCE.cloneMatrix(arrayC), Other.INSTANCE.cloneMatrix(path.getP()),
                            path.getPathCount(), path.getMi().clone(), path.getMj().clone());
                }

                path.getPath(edge);

                structHWout = new StructHWout(var.getH(), HWithout, Other.INSTANCE.cloneMatrix(var.getM1()),
                        Other.INSTANCE.cloneMatrix(path.getP()), path.getPathCount(), path.getMi().clone(), path.getMj().clone());
                sa = new Struct(generalStruct, structHWout, structHW);
                var.setH(var.getH() + HWithout);
                var.setArray(Other.INSTANCE.cloneMatrix(var.getM1()));
                var.setMin(var.getH());
                var.setMinParallel(HWithout);
            } else return null;
        } else {
            if (HWith < var.getMinParallel()) {
                generalStruct = new GeneralStruct(edge, var.getH(), HWithout, HWith);
                structHW = new StructHW(var.getH(), HWith, Other.INSTANCE.cloneMatrix(arrayC), Other.INSTANCE.cloneMatrix(path.getP()),
                        path.getPathCount(), path.getMi().clone(), path.getMj().clone());

                int x = edge[0];
                int y = edge[1];
                int[][] pC = Other.INSTANCE.cloneMatrix(path.getP());
                int[] miC = path.getMi().clone();
                int[] mjC = path.getMj().clone();
                pC[path.getPathCount()][0] = miC[x];
                pC[path.getPathCount()][1] = mjC[y];
                miC[x] = miC[y];
                miC = path.remove(miC, y);
                mjC = path.remove(mjC, y);
                if (HWithout < var.getMinLeftBound()) {
                    structHWout = new StructHWout(var.getH(), HWithout, Other.INSTANCE.cloneMatrix(var.getM1()), Other.INSTANCE.cloneMatrix(pC),
                            path.getPathCount() + 1, miC.clone(), mjC.clone());
                }

                var.setH(var.getH() + HWith);
                var.setMin(var.getH());
                var.setArray(arrayC);
//            sa.setAdditional(Other.INSTANCE.cloneMatrix(pC), Other.INSTANCE.cloneMatrix(var.getM1()), miC.clone(), mjC.clone(), path.getPathCount() + 1);
                sa = new Struct(generalStruct, structHWout, structHW);
                var.setMinParallel(HWithout);
            } else return null;
        }
        //da.add(sa);
        //System.out.println("");display(array);System.out.println("");
        return sa;
    }
}
