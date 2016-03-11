package algorithm.bab.classic.branch;

import algorithm.bab.util.*;
import algorithm.util.DuplicatesMethods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Admin
 * @since 14.07.15
 */
//@SuppressWarnings({"all"})
public class ChoseBranchClassic {
    //private double[][] arrayC;
    private double[] sumOfEachRow;
    private double[] sumOfEachCol;

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
        da.add(sa);
        return sa;
        //count++;
    }

    // Находим max суммы всех нулевых эл-тов по каждой СиС
    protected Map<int[], Double> defineMapEdge(double[][] array) {
        Map<int[], Double> map = new LinkedHashMap<>();
//        double t;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] == 0) {
                    int[] indexes = new int[2];
                    indexes[0] = i;
                    indexes[1] = j;
//                    t = getMinInRow(i, j, array);// todo delete
//                    t = getMinInCollumn(j, i, array);
                    map.put(indexes, getMinInRow(i, j, array) + getMinInCollumn(j, i, array));
                    //array[i][j] = Double.POSITIVE_INFINITY;
                }
            }
        }
        return map;
        //map = sortByValue(map);
        /*Iterator<Map.Entry<Object, Double>> i = map.entrySet().iterator();
        while(i.hasNext()){
            int[] d = (int[]) i.next().getKey();
            System.err.println(Arrays.toString(d)+", "+map.get(d));
        }
        System.err.println("\n");*/
    }

    //получает минимальное значение в строке
    //необходимо для правильной сортировки
    protected double getMinInRow(int row, int j, double[][] array) {
        double minInRow = Integer.MAX_VALUE;

        for (int i = 0; i < array[row].length; i++) {

            if (array[row][i] < minInRow
                    && minInRow != Double.POSITIVE_INFINITY && i != j) {
                minInRow = array[row][i];
            }

        }
        return minInRow;
    }

    protected double getMinInCollumn(int col, int i, double[][] array) {
        double minInRow = Integer.MAX_VALUE;

        for (int j = 0; j < array[col].length; j++) {

            if (array[j][col] < minInRow
                    && minInRow != Double.POSITIVE_INFINITY && j != i) {
                minInRow = array[j][col];
            }

        }

        return minInRow;

    }

    // Находим эл-т соотв-щий max сумме из карты map
    protected ArrayList<int[]> difineEdges(Map <int[], Double> map) {
        double comparator = -Double.MAX_VALUE;
        int[] edge = null;
        ArrayList<int[]> edges = new ArrayList<>();
//        int count = 1;

        // Находим коорд-ты max эл-та
        for (Object entrySet : map.entrySet()) {
            Map.Entry entry = (Map.Entry) entrySet;
            double sum = (double) entry.getValue();
            int[] key1 = (int[]) entry.getKey();

            if (sum > comparator) {
                comparator = sum;
                edge = key1;
            }
        }
        // Добавляем найденный эл-т в edges
        if (edge != null) edges.add(edge);

        // Проверяем, есть ли в map эл-ты с таким же max значением
        for (Object entrySet : map.entrySet()) {
            Map.Entry entry = (Map.Entry) entrySet;
            double sum = (double) entry.getValue();
            int[] key1 = (int[]) entry.getKey();
            if (sum == comparator && key1 != edge) {
                edges.add(key1);
//                count++;
            }
        }
        return edges;
    }

    // Формирует объект Struct только из эл-тов HWithout
    protected Struct chooseHoneLeftOnly(int[] edge, double HWith, Path path, Var var) {
        double HWithout;
        double[][] arrayC;
        StructHW hw;
        GeneralStruct generalStruct;

        arrayC = Other.cloneMatrix(var.getArray());
        var.getArray()[edge[1]][edge[0]] = Double.POSITIVE_INFINITY;
        double[][] m1 = getHWithout(edge, var.getArray());
        HWithout = getSumOfDelta();

        generalStruct = new GeneralStruct(edge, var.getH(), HWithout, HWith);

        arrayC[edge[0]][edge[1]] = Double.POSITIVE_INFINITY;
        Normalize.INSTANCE.normalize(arrayC);
//        sa.setArray(Other.cloneMatrix(arrayC));// если поднять повыше на 2 строчки, то рез-тат меняется на много ответов.
        hw = new StructHW(var.getH(), HWith, Other.cloneMatrix(arrayC), Other.cloneMatrix(path.getP()),
                path.getPathCount(), path.getMi().clone(), path.getMj().clone());
//        sa.addMiMj(path.getMi().clone(), path.getMj().clone());

        //todo System.out.println((H + HWithout) + ",  " + (H + HWith) + "     (" + (mi[edge[0]] + 1) + ", " + (mj[edge[1]] + 1) + ")");
        //System.out.println("");display(var.getArray());System.out.println("");

        path.getPath(edge);
        var.setH(var.getH() + HWithout);
        var.setArray(Other.cloneMatrix(m1));
//        sa.setActivatehwo(true);
        var.setMin(var.getH());
        //da.add(sa);
        //System.out.println("");display(var.getArray());System.out.println("");
        return new Struct(generalStruct, hw);
    }

    protected double[][] getHWithout(int[] edge, double[][] array) {
        double[][] M1;
        //getPathForZero(array, edge);
        //getPathAlternative(array, edge);
        //array[edge[1]][edge[0]] = Double.POSITIVE_INFINITY;
        changeJI(array, edge[0], edge[1]);
        M1 = setElementsM0toM(array, edge[1]);
        for (int i = 0; i < M1.length; i++) {
            if (M1[i][i] != Double.POSITIVE_INFINITY) {
                System.err.println("\n Error wiht array[" + i + "][" + i + "] != INFINITY");
                //throw new RuntimeException();
                //M1ch[i][i] = Double.POSITIVE_INFINITY;
            }
        }
        Normalize.INSTANCE.normalize(M1);
        return M1;
    }

    private double[][] changeJI(double[][] M0, int di, int dj) {
        double temp;
        for (int j = 0; j < M0.length; j++) {
            temp = M0[dj][j];
            M0[dj][j] = M0[di][j];
            M0[di][j] = temp;
        }

        return M0;
    }

    private double[][] setElementsM0toM(double[][] M0, int dj) {
        return DuplicatesMethods.setElementsM0toM(M0, dj);
    }

    //ищет сумму констант приведения
    protected double getSumOfDelta() {
        double sum = 0;
        double[] minArrI = Normalize.INSTANCE.getMinArrI();
        double[] minArrJ = Normalize.INSTANCE.getMinArrJ();

        for (int i = 0; i < minArrI.length; i++) {
            sum += minArrI[i];
            sum += minArrJ[i];
        }

        //System.out.println("H = " + sum);

        return sum;
    }

    public Struct choseLeftOnlyLast(Path path, Var var, ArrayClass arrayClass) {
//        Struct sa = new Struct(new StructHW());
        double HWith;
        double HWithout;
        var.setArray(null);
        var.setM1(null);
        path.getP()[arrayClass.getOriginalSize() - 2][0] = path.getMi()[0];
        path.getP()[arrayClass.getOriginalSize() - 2][1] = path.getMj()[1];
        path.getP()[arrayClass.getOriginalSize() - 1][0] = path.getMi()[1];
        path.getP()[arrayClass.getOriginalSize() - 1][1] = path.getMj()[0];
        double Sum = 0;
        for (int k = 0; k < arrayClass.getOriginalSize(); k++) {
            Sum += arrayClass.getA()[path.getP()[k][0]][path.getP()[k][1]];
        }
        HWithout = Sum - var.getH();
        if (HWithout < 0) System.out.println("Error with Sum - H = " + HWithout);
        var.setH(var.getH() + HWithout);
        var.setMin(var.getH());
        HWith = Double.POSITIVE_INFINITY;
        GeneralStruct generalStruct = new GeneralStruct(var.getH(), HWithout, HWith, true, Other.cloneMatrix(path.getP()));
        var.setMinLowerBound(var.getH());

//        sa.newStruct(null, HWith, HWithout, var.getH(), Other.cloneMatrix(path.getP()), path.getPathCount());
//        sa.setActivatehw(true);
//        sa.setActivatehwo(true);
        return new Struct(generalStruct);
    }

    public Struct chooseBoth(Path path, Var var, ArrayClass arrayClass) {
        Struct sa;

        GeneralStruct generalStruct;

        // Если длина матрицы > 2
        if (var.getArrayLength() > 2) {
            Map <int[], Double> map;// = new HashMap<Object, Double>();
            // Находим max суммы всех нулевых эл-тов по каждой СиС
            map = defineMapEdge(var.getArray());
            // Находим эл-т соотв-щий max сумме из карты map
            ArrayList edges = difineEdges(map);
            // Формирует объект Struct и помечает в нем большее из HWith и HWithout
            //try {
            if (map.size() == 0) {
                return new Struct(true);
            }
            sa = chooseHone((int[])edges.get(0), map.get(edges.get(0)), path, var);
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
            if (HWithout < 0) System.out.println("Error with Sum - H = " + HWithout);
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

    // Формирует объект Struct и помечает в нем большее из HWith и HWithout
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
//        sa.setArray(Other.cloneMatrix(arrayC));// если поднять повыше на 2 строчки, то рез-тат меняется на много ответов.
//        sa.newStruct(edge.clone(), HWith, HWithout, var.getH(), Other.cloneMatrix(path.getP()), path.getPathCount());
//        sa.addMiMj(path.getMi().clone(), path.getMj().clone());

        //todo System.out.println((H + HWithout) + ",  " + (H + HWith) + "     (" + (mi[edge[0]] + 1) + ", " + (mj[edge[1]] + 1) + ")");
        //System.out.println("");display(array);System.out.println("");

        if (HWithout <= HWith) {
            if (HWithout < var.getMinLeftBound()) {
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
            } else return null;
        } else {
            if (HWith < var.getMinLeftBound()) {
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
            } else return null;
        }
        //da.add(sa);
        //System.out.println("");display(array);System.out.println("");
        return sa;
    }

    protected void countSums(double[][] M) {//todo сразу считать minSum, правда если нужно.
        sumOfEachRow = null;
        sumOfEachCol = null;
        sumOfEachRow = new double[M.length];
        sumOfEachCol = new double[M.length];
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M.length; j++) {
                if (M[i][j] != Double.POSITIVE_INFINITY) {
                    sumOfEachRow[i] += M[i][j];
                }
            }
        }
        for (int i = 0; i < M.length; i++) {
            for (double[] aM : M) {
                if (aM[i] != Double.POSITIVE_INFINITY) {
                    sumOfEachCol[i] += aM[i];
                }
            }
        }
    }

    protected double[] getSumOfEachRow() {
        return sumOfEachRow;
    }

    protected double[] getSumOfEachCol() {
        return sumOfEachCol;
    }
}
