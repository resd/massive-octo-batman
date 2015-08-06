package MViGmodules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Admin on 14.07.15.
 */
@SuppressWarnings({"unused", "all"})
public class ChoseBranchClassic extends ChoseBranch{
    protected double[][] arrayC;
    protected double[][] M1;

    /*public ChoseBranchClassic(DA da) {
    this.da = da;
    }*/

    public void choseLeftOnly(DA da, Path path, Var var) {
        Struct sa;
        ArrayList mins = new ArrayList();;
        Map map = new HashMap<Object, Double>();
        // Очистка матриц todo del
        mins.clear();
        map.clear();
        // Находим max суммы всех нулевых эл-тов по каждой СиС
        map = defineMapEdge(var.getArray());
        // Находим эл-т соотв-щий max сумме из карты map
        ArrayList edges = difineEdges(map);
        // Формирует объект Struct только из эл-тов HWithout
        sa = chooseHoneLeftOnly((int[]) edges.get(0), (double) map.get((int[]) edges.get(0)), path, var);
        if (sa.getM1() == null) {
            sa.setAdditional(Other.INSTANCE.cloneMatrix(path.getP()), Other.INSTANCE.cloneMatrix(M1), path.getMi().clone(), path.getMj().clone(), path.getPathCount());
        }
        da.add(sa);
        sa = null;
        //count++;
    }

    // Находим max суммы всех нулевых эл-тов по каждой СиС
    protected Map defineMapEdge(double[][] array) {

        Map map = new LinkedHashMap<Object, Object>();

        double t;
        for (int i = 0; i < array.length; i++) {

            for (int j = 0; j < array[i].length; j++) {

                if (array[i][j] == 0) {

                    int[] indexes = new int[2];
                    indexes[0] = i;
                    indexes[1] = j;
                    t = getMinInRow(i, j, array);// todo delete
                    t = getMinInCollumn(j, i, array);
                    map.put(indexes, getMinInRow(i, j, array) + getMinInCollumn(j, i, array));
                    //array[i][j] = Double.POSITIVE_INFINITY;

                }
            }
        }
        //map = sortByValue(map);

        /*Iterator<Map.Entry<Object, Double>> i = map.entrySet().iterator();
        while(i.hasNext()){
            int[] d = (int[]) i.next().getKey();
            System.err.println(Arrays.toString(d)+", "+map.get(d));
        }
        System.err.println("\n");*/
        return map;
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
    protected ArrayList difineEdges(Map map) {
        double comparator = -Double.MAX_VALUE;
        int[] edge = new int[2];
        ArrayList edges = new ArrayList();
        int count = 1;

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
        edges.add(edge);
        // Проверяем, есть ли в map эл-ты с таким же max значением
        for (Object entrySet : map.entrySet()) {
            Map.Entry entry = (Map.Entry) entrySet;
            double sum = (double) entry.getValue();
            int[] key1 = (int[]) entry.getKey();
            if (sum == comparator && key1 != edge) {
                edges.add(key1);
                count++;
            }
        }
        return edges;
    }

    // Формирует объект Struct только из эл-тов HWithout
    protected Struct chooseHoneLeftOnly(int[] edge, double HWith, Path path, Var var) {
        double HWithout;
        Struct sa = new Struct();

        arrayC = Other.INSTANCE.cloneMatrix(var.getArray());
        var.getArray()[edge[1]][edge[0]] = Double.POSITIVE_INFINITY;
        M1 = getHWithout(edge, HWith, var.getArray());
        HWithout = getSumOfDelta();

        arrayC[edge[0]][edge[1]] = Double.POSITIVE_INFINITY;
        Normalize.INSTANCE.normalize(arrayC);
        sa.setArray(Other.INSTANCE.cloneMatrix(arrayC));// если поднять повыше на 2 строчки, то рез-тат меняется на много ответов.
        sa.newStruct(edge.clone(), HWith, HWithout, var.getH(), Other.INSTANCE.cloneMatrix(path.getP()), path.getPathCount());
        sa.addMiMj(path.getMi().clone(), path.getMj().clone());

        //todo System.out.println((H + HWithout) + ",  " + (H + HWith) + "     (" + (mi[edge[0]] + 1) + ", " + (mj[edge[1]] + 1) + ")");
        //System.out.println("");display(var.getArray());System.out.println("");

        path.getPath(edge);
        var.setH(var.getH() + HWithout);
        var.setArray(Other.INSTANCE.cloneMatrix(M1));
        sa.setActivatehwo(true);
        var.setMin(var.getH());
        //da.add(sa);
        //System.out.println("");display(var.getArray());System.out.println("");
        return sa;
    }

    protected double[][] getHWithout(int[] edge, double sum, double[][] array) {
        double[][] M1;
        //getPathForZero(array, edge);
        //getPathAlternative(array, edge);
        //array[edge[1]][edge[0]] = Double.POSITIVE_INFINITY;
        changeJI(array, edge[0], edge[1]);
        M1 = setElementsM0toM(array, edge[1], edge[1]);
        for (int i = 0; i < M1.length; i++) {
            if (M1[i][i] != Double.POSITIVE_INFINITY)
                //M1ch[i][i] = Double.POSITIVE_INFINITY;
                System.err.println("\n Error wiht array["+i+"]["+i+"] != INFINITY");
        }
        Normalize.INSTANCE.normalize(M1);
        return M1;
    }

    protected double[][] changeJI(double[][] M0, int di, int dj) {
        double temp;
        for (int j = 0; j < M0.length; j++) {
            temp = M0[dj][j];
            M0[dj][j] = M0[di][j];
            M0[di][j] = temp;
        }

        return M0;
    }

    protected double[][] setElementsM0toM(double[][] M0, int di, int dj) {
        double[][] M1 = new double[M0.length - 1][M0.length - 1];
        int ki = 0;
        int kj;
        for (int i = 0; i < M0.length; i++) {
            if (i == di) {
                ki++;
                continue;
            }
            kj = 0;
            for (int j = 0; j < M0.length; j++) {
                if (j == dj) {
                    kj++;
                } else {
                    M1[i - ki][j - kj] = M0[i][j];
                }
            }
        }
        return M1;
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

    public Struct choseLeftOnlyLast(Path path, Var var) {
        Struct sa = new Struct();
        double HWith;
        double HWithout;
        var.setArray(null);
        var.setM1(null);
        path.getP()[var.getOriginalSize() - 2][0] = path.getMi()[0];
        path.getP()[var.getOriginalSize() - 2][1] = path.getMj()[1];
        path.getP()[var.getOriginalSize() - 1][0] = path.getMi()[1];
        path.getP()[var.getOriginalSize() - 1][1] = path.getMj()[0];
        double Sum = 0;
        for (int k = 0; k < var.getOriginalSize(); k++) {
            Sum += var.getA()[path.getP()[k][0]][path.getP()[k][1]];
        }
        HWithout = Sum - var.getH();
        if (HWithout < 0) System.out.println("Error with Sum - H = " + HWithout);
        var.setH(var.getH() + HWithout);
        var.setMin(var.getH());
        HWith = Double.POSITIVE_INFINITY;
        sa.newStruct(null, HWith, HWithout, var.getH(), Other.INSTANCE.cloneMatrix(path.getP()), path.getPathCount());
        sa.setActivatehw(true);
        sa.setActivatehwo(true);
        return sa;
    }

    public Struct chooseBoth(DA da, Path path, Var var) {
        Struct sa = new Struct();
        ArrayList mins = new ArrayList();;
        Map map = new HashMap<Object, Double>();
        // Если длина матрицы > 2
        if (var.getArrayLength() > 2) {
            // Очистка матриц
            mins.clear();
            map.clear();
            // Находим max суммы всех нулевых эл-тов по каждой СиС
            map = defineMapEdge(var.getArray());
            // Находим эл-т соотв-щий max сумме из карты map
            ArrayList edges = difineEdges(map);
            // Формирует объект Struct и помечает в нем большее из HWith и HWithout
            //try {
                sa = chooseHone((int[])edges.get(0), (double) map.get((int[])edges.get(0)), da, path, var);
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
            if (HWithout < 0) System.out.println("Error with Sum - H = " + HWithout);
            var.setH(var.getH() + HWithout);
            var.setMin(var.getH());
            HWith = Double.POSITIVE_INFINITY;
            sa.newStruct(null, HWith, HWithout, var.getH(), Other.INSTANCE.cloneMatrix(path.getP()), path.getPathCount());
            sa.setActivatehw(true);
        }
        if (sa.getM1() == null) {
            sa.setAdditional(Other.INSTANCE.cloneMatrix(path.getP()), Other.INSTANCE.cloneMatrix(var.getM1()), path.getMi().clone(), path.getMj().clone(), path.getPathCount());
        }
        return sa;
    }

    // Формирует объект Struct и помечает в нем большее из HWith и HWithout
    protected Struct chooseHone(int[] edge, double HWith, DA da, Path path, Var var) {
        double HWithout;
        Struct sa = new Struct();

        arrayC = Other.INSTANCE.cloneMatrix(var.getArray());
        var.getArray()[edge[1]][edge[0]] = Double.POSITIVE_INFINITY;
        var.setM1(getHWithout(edge, HWith, var.getArray()));
        HWithout = getSumOfDelta();

        arrayC[edge[0]][edge[1]] = Double.POSITIVE_INFINITY;
        Normalize.INSTANCE.normalize(arrayC);
        sa.setArray(Other.INSTANCE.cloneMatrix(arrayC));// если поднять повыше на 2 строчки, то рез-тат меняется на много ответов.
        sa.newStruct(edge.clone(), HWith, HWithout, var.getH(), Other.INSTANCE.cloneMatrix(path.getP()), path.getPathCount());
        sa.addMiMj(path.getMi().clone(), path.getMj().clone());


        //todo System.out.println((H + HWithout) + ",  " + (H + HWith) + "     (" + (mi[edge[0]] + 1) + ", " + (mj[edge[1]] + 1) + ")");
        //System.out.println("");display(array);System.out.println("");

        if (HWithout <= HWith) {
            path.getPath(edge);
            var.setH(var.getH() + HWithout);
            var.setArray(Other.INSTANCE.cloneMatrix(var.getM1()));
            sa.setActivatehwo(true);
            var.setMin(var.getH());
        } else {
            //array[edge[0]][edge[1]] = Double.POSITIVE_INFINITY;
            //array[edge[0]][edge[0]] = Double.POSITIVE_INFINITY;
            //array[edge[1]][edge[1]] = Double.POSITIVE_INFINITY;
            //countHwith++;
            sa.setActivatehw(true);
            var.setH(var.getH() + HWith);
            var.setMin(var.getH());
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
            sa.setAdditional(Other.INSTANCE.cloneMatrix(pC), Other.INSTANCE.cloneMatrix(var.getM1()), miC.clone(), mjC.clone(), path.getPathCount() + 1);
        }
        //da.add(sa);
        //System.out.println("");display(array);System.out.println("");
        return sa;
    }

}
