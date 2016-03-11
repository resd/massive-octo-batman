package algorithm.bab.parallel_with_back.branch;

import algorithm.bab.classic.branch.ChoseBranchClassic;
import algorithm.bab.parallel_with_back.BaBParallelWithBack;
import algorithm.bab.util.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Admin
 * @since 11.11.2015
 */
//@SuppressWarnings({"all"})
public class ChoseBranchParallel extends ChoseBranchClassic {

    public Struct chooseBoth(Path path, Var var, BaBParallelWithBack parallel, ArrayClass arrayClass) {
        Struct sa;

        GeneralStruct generalStruct;

        // ���� ����� ������� > 2
        if (var.getArrayLength() > 2) {
            Map<int[], Double> map;// = new HashMap<Object, Double>();
            // ������� max ����� ���� ������� ��-��� �� ������ ���
            map = defineMapEdge(var.getArray());
            // ������� ��-� �����-��� max ����� �� ����� map
            ArrayList<int[]> edges = difineEdges(map);
            // ��������� ������ Struct � �������� � ��� ������� �� HWith � HWithout
            //try {

            if (map.size() == 0) {
                //return new Struct(true);
                int[] tempEdge = new int[]{0, 1};
                edges.add(tempEdge);
                map.put(tempEdge, Double.POSITIVE_INFINITY);
            } else {
                if (parallel.isEdgesNotEmpty() && parallel.checkForEdges(edges.get(0))) {
                    return null;
                } else {
                    parallel.addEdge(edges.get(0));
                }
            }
            sa = chooseHone(edges.get(0), map.get(edges.get(0)), path, var);
            /*} catch (Exception e) {
                e.printStackTrace();
            }*/
        } else {
            double HWith;
            double HWithout;
            double Sum = 0;
            var.setArray(null);
            var.setM1(null);
            path.getP()[arrayClass.getOriginalSize() - 2][0] = path.getMi()[0];
            path.getP()[arrayClass.getOriginalSize() - 2][1] = path.getMj()[1];
            path.getP()[arrayClass.getOriginalSize() - 1][0] = path.getMi()[1];
            path.getP()[arrayClass.getOriginalSize() - 1][1] = path.getMj()[0];
            for (int k = 0; k < arrayClass.getOriginalSize(); k++) {
                Sum += arrayClass.getA()[path.getP()[k][0]][path.getP()[k][1]];
            }
            HWithout = Sum - var.getH();
            if (HWithout < 0) System.out.println("Error with (Sum - H) = " + HWithout);
            var.setH(var.getH() + HWithout);
            var.setMin(var.getH());
            HWith = Double.POSITIVE_INFINITY;
            generalStruct = new GeneralStruct(var.getH(), HWithout, HWith, true, Other.cloneMatrix(path.getP()));
            var.setMinLowerBound(var.getH());
            /*structHW = new StructHW(var.getH(), HWith, null, Other.cloneMatrix(path.getP()), path.getPathCount(),
                    path.getMi().clone(), path.getMj().clone());*/
            sa = new Struct(generalStruct);
//            sa.setActivatehw(true);
        }
//        if (sa.getM1() == null) {
//            sa.setAdditional(Other.cloneMatrix(path.getP()), Other.cloneMatrix(var.getM1()), path.getMi().clone(), path.getMj().clone(), path.getPathCount());
//        }
        return sa;
    }

    @Override
    public Struct choseLeftOnly(DA da, Path path, Var var) {
        Struct sa;
        Map <int[], Double> map = new HashMap<>();
        // Очистка матриц
        map.clear();
        // Находим max суммы всех нулевых эл-тов по каждой СиС
        map = defineMapEdge(var.getArray());
        // Находим эл-т соотв-щий max сумме из карты map
        ArrayList edges = difineEdges(map);
        // Формирует объект Struct только из эл-тов HWithout
        sa = chooseHoneLeftOnly((int[]) edges.get(0), map.get(edges.get(0)), path, var);
//        if (sa.getM1() == null) {
//            sa.setAdditional(Other.cloneMatrix(path.getP()), Other.cloneMatrix(M1), path.getMi().clone(), path.getMj().clone(), path.getPathCount());
//        }
        return sa;
        //count++;
    }

    // ��������� ������ Struct � �������� � ��� ������� �� HWith � HWithout
    @Override
    protected Struct chooseHone(int[] edge, double HWith, Path path, Var var) {
        double[][] arrayC;
        Struct sa;
        GeneralStruct generalStruct;
        StructHW structHW = null;
        StructHWout structHWout = null;

        double HWithout;

        arrayC = Other.cloneMatrix(var.getArray());
        var.getArray()[edge[1]][edge[0]] = Double.POSITIVE_INFINITY;
        var.setM1(getHWithout(edge, var.getArray()));
        HWithout = getSumOfDelta();

        arrayC[edge[0]][edge[1]] = Double.POSITIVE_INFINITY;
        Normalize.INSTANCE.normalize(arrayC);
//        sa.setArray(Other.cloneMatrix(arrayC));// ���� ������� ������ �� 2 �������, �� ���-��� �������� �� ����� �������.
//        sa.newStruct(edge.clone(), HWith, HWithout, var.getH(), Other.cloneMatrix(path.getP()), path.getPathCount());
//        sa.addMiMj(path.getMi().clone(), path.getMj().clone());

        //todo System.out.println((H + HWithout) + ",  " + (H + HWith) + "     (" + (mi[edge[0]] + 1) + ", " + (mj[edge[1]] + 1) + ")");
        //System.out.println("");display(array);System.out.println("");

        if (HWithout <= HWith) {
            if (HWithout < var.getMinParallel() || Double.isInfinite(HWithout)) {
                generalStruct = new GeneralStruct(edge, var.getH(), HWithout, HWith);
                if (HWith < var.getMinLeftBound()) {
                    structHW = new StructHW(var.getH(), HWith, Other.cloneMatrix(arrayC), Other.cloneMatrix(path.getP()),
                            path.getPathCount(), path.getMi().clone(), path.getMj().clone());
                }

                path.getPath(edge);

                structHWout = new StructHWout(var.getH(), HWithout, Other.cloneMatrix(var.getM1()),
                        Other.cloneMatrix(path.getP()), path.getPathCount(), path.getMi().clone(), path.getMj().clone());
                sa = new Struct(generalStruct, structHWout, structHW);
                var.setH(var.getH() + HWithout);
                var.setArray(Other.cloneMatrix(var.getM1()));
                var.setMin(var.getH());
                var.setMinParallel(HWithout);
            } else return null;
        } else {
            if (HWith < var.getMinParallel() || Double.isInfinite(HWith)) {
                generalStruct = new GeneralStruct(edge, var.getH(), HWithout, HWith);
                structHW = new StructHW(var.getH(), HWith, Other.cloneMatrix(arrayC), Other.cloneMatrix(path.getP()),
                        path.getPathCount(), path.getMi().clone(), path.getMj().clone());

                int x = edge[0];
                int y = edge[1];
                int[][] pC = Other.cloneMatrix(path.getP());
                int[] miC = path.getMi().clone();
                int[] mjC = path.getMj().clone();
                pC[path.getPathCount()][0] = miC[x];
                pC[path.getPathCount()][1] = mjC[y];
                miC[x] = miC[y];
                miC = path.remove(miC, y);
                mjC = path.remove(mjC, y);
                if (HWithout < var.getMinLeftBound()) {
                    structHWout = new StructHWout(var.getH(), HWithout, Other.cloneMatrix(var.getM1()), Other.cloneMatrix(pC),
                            path.getPathCount() + 1, miC.clone(), mjC.clone());
                }

                var.setH(var.getH() + HWith);
                var.setMin(var.getH());
                var.setArray(arrayC);
//            sa.setAdditional(Other.cloneMatrix(pC), Other.cloneMatrix(var.getM1()), miC.clone(), mjC.clone(), path.getPathCount() + 1);
                sa = new Struct(generalStruct, structHWout, structHW);
                var.setMinParallel(HWithout);
            } else return null;
        }
        //da.add(sa);
        //System.out.println("");display(array);System.out.println("");
        return sa;
    }
}
