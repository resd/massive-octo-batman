package anotherTryMVIG;

import common.BruteforceAlgo;

import java.util.*;

/**
 * @author zabr1
 */
@SuppressWarnings({"unused", "all"})
public class ClassicAlgoAnother {
    /**
     * Сделать вариант без возвратов.
     * Сделать вариант с возвратами.
     */
    private double[][] array;
    private double[][] a;
    private double H = 0;
    private int originalsize;
    private String delimeter = "=======================================================================================";
    private int[][] p;
    private int[] mi;
    private int[] mj;
    private double[] minArrI;
    private double[] minArrJ;
    private long sysTime;
    private Struct minStruct;
    private double minStructNum = -1;


    public double[][] getArray() {
        return array;
    }

    //конструктор
    public ClassicAlgoAnother(double[][] array) {
        this.array = cloneMatrix(array);
        this.a = cloneMatrix(array);
        originalsize = array.length;
    }

    private void initialize() {
        double M = Double.POSITIVE_INFINITY;
        H = 0;
        mi = new int[originalsize];
        mj = new int[originalsize];
        p = new int[originalsize][2];
        for (int i = 0; i < originalsize; i++) {
            array[i][i] = M;
            mi[i] = i;
            mj[i] = i;
            p[i][0] = -2;
            p[i][1] = -2;
        }
    }

    public void setArray(double[][] array) {
        this.array = cloneMatrix(array);
        originalsize = array.length;
    }

    public void setA(double[][] a) {
        this.a = cloneMatrix(a);
        originalsize = array.length;
    }

    public int[][] getP() {
        return p;
    }

    public void setP(int[][] p) {
        this.p = p;
    }

    public double getH() {
        return H;
    }

    public void setH(double H)  {
        this.H = H;
    }

    public int[] getMj() {
        return mj;
    }

    public void setMj(int[] mj) {
        this.mj = mj;
    }

    public int[] getMi() {
        return mi;
    }

    public void setMi(int[] mi) {
        this.mi = mi;
    }

    private double[][] normalize(double[][] M) {//todo сразу считать minSum, правда если нужно.
        minArrI = null;
        minArrJ = null;
        minArrI = new double[M.length];
        minArrJ = new double[M.length];
        for (int i = 0; i < M.length; i++) {
            minArrI[i] = Double.MAX_VALUE;
            minArrJ[i] = Double.MAX_VALUE;
        }
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M.length; j++) {
                if (M[i][j] != Double.POSITIVE_INFINITY && M[i][j] < minArrI[i]) {
                    minArrI[i] = M[i][j];
                    if (M[i][j] == 0) {
                        break;
                    }
                }
            }
        }
        for (int i = 0; i < M.length; i++) {
            if (minArrI[i] == 0) {
                continue;
            }
            for (int j = 0; j < M.length; j++) {
                if (M[i][j] != Double.POSITIVE_INFINITY) {
                    M[i][j] -= minArrI[i];
                }
            }
        }
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M.length; j++) {
                if (M[j][i] != Double.POSITIVE_INFINITY && M[j][i] < minArrJ[i]) {
                    minArrJ[i] = M[j][i];
                    if (M[j][i] == 0) {
                        break;
                    }
                }
            }
        }
        for (int i = 0; i < M.length; i++) {
            if (minArrJ[i] == 0) {
                continue;
            }
            for (int j = 0; j < M.length; j++) {
                if (M[j][i] != Double.POSITIVE_INFINITY) {
                    M[j][i] -= minArrJ[i];
                }
            }
        }
        return M;
    }

    //получает минимальное значение в строке
    //необходимо для правильной сортировки
    private double getMinInRow(int row, int j) {
        double minInRow = Integer.MAX_VALUE;

        for (int i = 0; i < array[row].length; i++) {

            if (array[row][i] < minInRow
                    && minInRow != Double.POSITIVE_INFINITY && i != j) {
                minInRow = array[row][i];
            }

        }

        return minInRow;

    }

    private double getMinInCollumn(int col, int i) {
        double minInRow = Integer.MAX_VALUE;

        for (int j = 0; j < array[col].length; j++) {

            if (array[j][col] < minInRow
                    && minInRow != Double.POSITIVE_INFINITY && j != i) {
                minInRow = array[j][col];
            }

        }

        return minInRow;

    }


    //выводит значения массива списка в консоль
    private void display(ArrayList<Double> collection, String name) {

        System.out.println(name);

        for (Double element : collection) {
            System.out.print(element + " \t");
        }
        System.out.println("");

    }

    //выводит значения массива на экран
    private void display(double[][] array1) {
        for (int i = 0; i < array1.length; i++) {
            for (int j = 0; j < array1[i].length; j++) {
                if (array1[i][j] == Double.POSITIVE_INFINITY) {
                    System.out.print("M\t\t\t");
                } else
                    System.out.print(array1[i][j] + "\t\t\t");
            }
            System.out.println("");
        }
    }

    //ищет сумму констант приведения
    private double getSumOfDelta() {
        double sum = 0;

        for (int i = 0; i < minArrI.length; i++) {
            sum += minArrI[i];
            sum += minArrJ[i];
        }

        //System.out.println("H = " + sum);

        return sum;
    }

    // Находим max суммы всех нулевых эл-тов по каждой СиС
    private Map defineMapEdge() {

        Map map = new LinkedHashMap<Object, Object>();

        double t;
        for (int i = 0; i < array.length; i++) {

            for (int j = 0; j < array[i].length; j++) {

                if (array[i][j] == 0) {

                    int[] indexes = new int[2];
                    indexes[0] = i;
                    indexes[1] = j;
                    t = getMinInRow(i, j);// todo delete
                    t = getMinInCollumn(j, i);
                    map.put(indexes, getMinInRow(i, j) + getMinInCollumn(j, i));
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

    // Находим эл-т соотв-щий max сумме из карты map
    private ArrayList difineEdges(Map map) {
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

    private int[] difineEdge(Map map) {
        double comparator = -Double.MAX_VALUE;
        int[] edge = new int[2];

        for (Object entrySet : map.entrySet()) {
            Map.Entry entry = (Map.Entry) entrySet;
            double sum = (double) entry.getValue();
            int[] key1 = (int[]) entry.getKey();

            if (sum > comparator) {
                comparator = sum;
                edge = key1;
            }
        }
        return edge;
    }

    private double[][] getHWithout(int[] edge, double sum) {
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
        normalize(M1);
        return M1;
    }

    private void getPathAlternative(double[][] array, int[] d) {
        int x = d[0];
        int y = d[1];
        int len = array[0].length;
        int di = -1;
        boolean yes = true;
        for (int i = 0; i < len; i++) {
            if (array[i][y] == Double.POSITIVE_INFINITY && i != x) {
                for (int j = 0; j < len; j++) {
                    if (array[i][j] == Double.POSITIVE_INFINITY && j != y) {
                        yes = false;
                        break;
                    }
                }
                if (yes) {
                    di = i;
                    break;
                }
                yes = true;
            }
        }
        array[di][x] = Double.POSITIVE_INFINITY;
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

    private double[][] setElementsM0toM(double[][] M0, int di, int dj) {
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

    private int pathCount = 0;
    private void getPath(int[] d) {
        int x = d[0];
        int y = d[1];
        p[pathCount][0] = mi[x];
        p[pathCount][1] = mj[y];
        pathCount++;
        mi[x] = mi[y];

        mi = remove(mi, y);
        mj = remove(mj, y);
    }

    private void getPath(int[] d, int[] mi, int[] mj, int pathCount) {
        int x = d[0];
        int y = d[1];
        p[pathCount][0] = mi[x];
        p[pathCount][1] = mj[y];
        pathCount++;
        mi[x] = mi[y];

        mi = remove(mi, y);
        mj = remove(mj, y);
    }

    private int[] remove(int[] mi, int y) {
        int[] tmp = new int[mi.length - 1];
        System.arraycopy(mi, 0, tmp, 0, y);
        System.arraycopy(mi, y + 1, tmp, y, mi.length - y - 1);
        return tmp;
    }

    private int count;
    private boolean minStructBoolean;
    private double[][][] arrs;
    private double[][] arrayC;
    private ArrayList<Struct> da;
    double[][] M1;
    double min;
    ArrayList mins;
    int[][] minP;
    double minSum;
    private void solve() {
        // Нормализация
        normalize(array);
        // Задание начальное нижней оценки H
        setH(getH() + getSumOfDelta());
        // Объявление переменных
        double HWith;
        double HWithout;
        Map map = new HashMap<Object, Double>();
        int[] edge;
        da = new ArrayList();
        mins = new ArrayList();
        minP = new int[originalsize][2];
        int countBackWith = 0;
        int countHwith = 0;
        // Пока длина матрицы > 2
        while(array.length > 2) {
            Struct sa = new Struct();
            // Очистка матриц
            mins.clear();
            map.clear();
            // Находим max суммы всех нулевых эл-тов по каждой СиС
            map = defineMapEdge();
            // Находим эл-т соотв-щий max сумме из карты map
            ArrayList edges = difineEdges(map);
            // Формирует объект Struct только из эл-тов HWithout
            sa = chooseHoneLeftOnly((int[]) edges.get(0), (double) map.get((int[]) edges.get(0)));
            if (sa.getM1() == null) {
                sa.setAdditional(cloneMatrix(p), cloneMatrix(M1), mi.clone(), mj.clone(), pathCount);
            }
            da.add(sa);
            sa = null;
            //count++;
        }
        Struct sa = new Struct();
        array = null;
        M1 = null;
        p[originalsize - 2][0] = mi[0];
        p[originalsize - 2][1] = mj[1];
        p[originalsize - 1][0] = mi[1];
        p[originalsize - 1][1] = mj[0];
        double Sum = 0;
        for (int k = 0; k < originalsize; k++) {
            Sum += a[p[k][0]][p[k][1]];
        }
        HWithout = Sum - getH();
        if (HWithout < 0) System.out.println("Error with Sum - H = " + HWithout);
        setH(getH() + HWithout);
        min = getH();
        HWith = Double.POSITIVE_INFINITY;
        sa.newStruct(null, HWith, HWithout, getH(), cloneMatrix(getP()), pathCount);
        sa.setActivatehw(true);
        sa.setActivatehwo(true);
        //da.add(sa);
        minSum = min;
        minP = cloneMatrix(getP());
        checkDa();
        boolean bool = checkMin(sa);
        // Пока не переберутся все варианты
        while(bool) { // checkMin(sa)
            sa = new Struct();
            // Если длина матрицы > 2
            if (array.length > 2) {
                // Очистка матриц
                mins.clear();
                map.clear();
                // Находим max суммы всех нулевых эл-тов по каждой СиС
                map = defineMapEdge();
                // Находим эл-т соотв-щий max сумме из карты map
                ArrayList edges = difineEdges(map);
                // Формирует объект Struct и помечает в нем большее из HWith и HWithout
                sa = chooseHone((int[])edges.get(0), (double) map.get((int[])edges.get(0)));
            } else {
                array = null;
                M1 = null;
                p[originalsize - 2][0] = mi[0];
                p[originalsize - 2][1] = mj[1];
                p[originalsize - 1][0] = mi[1];
                p[originalsize - 1][1] = mj[0];
                Sum = 0;
                for (int k = 0; k < originalsize; k++) {
                    Sum += a[p[k][0]][p[k][1]];
                }
                HWithout = Sum - getH();
                if (HWithout < 0) System.out.println("Error with Sum - H = " + HWithout);
                setH(getH() + HWithout);
                min = getH();
                HWith = Double.POSITIVE_INFINITY;
                sa.newStruct(null, HWith, HWithout, getH(), cloneMatrix(getP()), pathCount);
                sa.setActivatehw(true);
            }
            if (sa.getM1() == null) {
                sa.setAdditional(cloneMatrix(p), cloneMatrix(M1), mi.clone(), mj.clone(), pathCount);
            }

            boolean exit = false;
            if (sa.getHWithSum() > minSum && sa.getHWithoutSum() > minSum) {
                checkDa();
                if (da.size() == 0) {
                    if (minSum < min) {
                        setP(cloneMatrix(minP));
                    }
                    break;
                }
                exit = checkMin(sa);
            } else {
                da.add(sa);
                checkMin(sa);
            }

            minStructBoolean = false;

            sa = null;
            if (array == null) {
                checkDa();
                exit = checkMin(sa);
                if (da.size() == 0 || !exit) {
                    if (minSum < min) {
                        setP(cloneMatrix(minP));
                    }
                    break;
                }
                if (minSum < min) {
                    minSum = min;
                    minP = cloneMatrix(getP());
                }
            }
            count++;
        }
        /*System.out.println("countBackWith = " + countBackWith);todo
        System.out.println("countHwith = " + countHwith);
        System.out.println("count = " + count);*/
    }

    private Struct chooseH(int[] edge, double HWith) {
        double HWithout;
        double[][] arrayCC = cloneMatrix(arrayC);
        double[][] arrayY = cloneMatrix(array);
        double[][] M1C = cloneMatrix(M1);
        Struct sa = new Struct();
        double HC = getH();
        int pathCountC = pathCount;
        int[] miC = mi.clone();
        int[] mjC = mj.clone();
        int[][] pC = cloneMatrix(p);
        arrayCC = cloneMatrix(arrayY);

        arrayY[edge[1]][edge[0]] = Double.POSITIVE_INFINITY;
        M1C = getHWithout(edge, HWith);
        HWithout = getSumOfDelta();

        arrayCC[edge[0]][edge[1]] = Double.POSITIVE_INFINITY;
        normalize(arrayCC);
        sa.setArray(cloneMatrix(arrayCC));// если поднять повыше на 2 строчки, то рез-тат меняется на много ответов.
        sa.newStruct(edge.clone(), HWith, HWithout, getH(), cloneMatrix(getP()), pathCountC);
        sa.addMiMj(miC.clone(), mjC.clone());


        //System.out.println((H + HWithout) + ",  " + (H + HWith) + "     (" + (miC[edge[0]] + 1) + ", " + (mjC[edge[1]] + 1) + ")");
        //System.out.println("");display(array);System.out.println("");

        if (HWithout <= HWith) {
            getPath(edge, miC, mjC, pathCountC);
            pathCountC++;
            setH(getH() + HWithout);
            arrayY = cloneMatrix(M1C);
            sa.setActivatehwo(true);
            mins.add(getH());
        } else {
            //array[edge[0]][edge[1]] = Double.POSITIVE_INFINITY;
            //array[edge[0]][edge[0]] = Double.POSITIVE_INFINITY;
            //array[edge[1]][edge[1]] = Double.POSITIVE_INFINITY;
            //countHwith++;
            sa.setActivatehw(true);
            setH(getH() + HWith);
            mins.add(getH());
            int x = edge[0];
            int y = edge[1];

            pC[pathCountC][0] = miC[x];
            pC[pathCountC][1] = mjC[y];
            miC[x] = miC[y];

            miC = remove(miC, y);
            mjC = remove(mjC, y);
            sa.setAdditional(cloneMatrix(pC), cloneMatrix(M1C), miC.clone(), mjC.clone(), pathCountC + 1);
        }
        H = HC;
        sa.setAdditional(cloneMatrix(pC), cloneMatrix(M1C), miC.clone(), mjC.clone(), pathCountC);
        da.add(sa);
        //System.out.println("");display(array);System.out.println("");
        return sa;
    }

    // Формирует объект Struct только из эл-тов HWithout
    private Struct chooseHoneLeftOnly(int[] edge, double HWith) {
        double HWithout;
        Struct sa = new Struct();

        arrayC = cloneMatrix(array);
        array[edge[1]][edge[0]] = Double.POSITIVE_INFINITY;
        M1 = getHWithout(edge, HWith);
        HWithout = getSumOfDelta();

        arrayC[edge[0]][edge[1]] = Double.POSITIVE_INFINITY;
        normalize(arrayC);
        sa.setArray(cloneMatrix(arrayC));// если поднять повыше на 2 строчки, то рез-тат меняется на много ответов.
        sa.newStruct(edge.clone(), HWith, HWithout, getH(), cloneMatrix(getP()), pathCount);
        sa.addMiMj(mi.clone(), mj.clone());

        //todo System.out.println((H + HWithout) + ",  " + (H + HWith) + "     (" + (mi[edge[0]] + 1) + ", " + (mj[edge[1]] + 1) + ")");
        //System.out.println("");display(array);System.out.println("");

        getPath(edge);
        setH(getH() + HWithout);
        array = cloneMatrix(M1);
        sa.setActivatehwo(true);
        min = getH();
        //da.add(sa);
        //System.out.println("");display(array);System.out.println("");
        return sa;
    }

    // Формирует объект Struct и помечает в нем большее из HWith и HWithout
    private Struct chooseHone(int[] edge, double HWith) {
        double HWithout;
        Struct sa = new Struct();

        arrayC = cloneMatrix(array);
        array[edge[1]][edge[0]] = Double.POSITIVE_INFINITY;
        M1 = getHWithout(edge, HWith);
        HWithout = getSumOfDelta();

        arrayC[edge[0]][edge[1]] = Double.POSITIVE_INFINITY;
        normalize(arrayC);
        sa.setArray(cloneMatrix(arrayC));// если поднять повыше на 2 строчки, то рез-тат меняется на много ответов.
        sa.newStruct(edge.clone(), HWith, HWithout, getH(), cloneMatrix(getP()), pathCount);
        sa.addMiMj(mi.clone(), mj.clone());


        //todo System.out.println((H + HWithout) + ",  " + (H + HWith) + "     (" + (mi[edge[0]] + 1) + ", " + (mj[edge[1]] + 1) + ")");
        //System.out.println("");display(array);System.out.println("");

        if (HWithout <= HWith) {
            getPath(edge);
            setH(getH() + HWithout);
            array = cloneMatrix(M1);
            sa.setActivatehwo(true);
            min = getH();
        } else {
            //array[edge[0]][edge[1]] = Double.POSITIVE_INFINITY;
            //array[edge[0]][edge[0]] = Double.POSITIVE_INFINITY;
            //array[edge[1]][edge[1]] = Double.POSITIVE_INFINITY;
            //countHwith++;
            sa.setActivatehw(true);
            setH(getH() + HWith);
            min = getH();
            int x = edge[0];
            int y = edge[1];
            int[][] pC = cloneMatrix(p);
            int[] miC = mi.clone();
            int[] mjC = mj.clone();
            pC[pathCount][0] = miC[x];
            pC[pathCount][1] = mjC[y];
            miC[x] = miC[y];

            miC = remove(miC, y);
            mjC = remove(mjC, y);
            sa.setAdditional(cloneMatrix(pC), cloneMatrix(M1), miC.clone(), mjC.clone(), pathCount + 1);
        }
        //da.add(sa);
        //System.out.println("");display(array);System.out.println("");
        return sa;
    }

    private void checkDa() {
        int index = 0;
        while (index != da.size()) {
            Struct temp = da.get(index);
            if (temp.isActivatehwo() && temp.getHWithSum() > minSum) { // todo  minSum or min ?
                da.remove(temp);
            } else if (temp.isActivatehw() && temp.getHWithoutSum() > minSum) {
                da.remove(temp);
            } else {
                index++;
            }
            temp = null;
        }
    }

    private boolean checkMin(Struct sa) {
        // Проверить нет ли решений меньше, чем уже полученное решение
        // Прыгнуть на то решение
        boolean stop = false;
        for (int i = 0; i < da.size(); i++) {
            Struct temp = da.get(i);
            if (!temp.isActivatehw() && temp.getHWithSum() < min) {
                if (!minStructBoolean) {
                    minStruct = sa;
                    minStructNum = min;
                } else {
                    minStructNum = min;
                }
                ((Struct) da.get(i)).setActivatehwo(true);
                ((Struct) da.get(i)).setActivatehw(true);
                setP(temp.getP().clone());
                setH(temp.getHWithSum());
                setMi(temp.getMiOld().clone());
                setMj(temp.getMjOld().clone());
                pathCount = temp.getPathCount();
                array = cloneMatrix(temp.getArray());
                //((Struct) dh[i].get(j)).setActivatehw(true);// i0 j0
                //array[edge[0]][edge[1]] = Double.POSITIVE_INFINITY;
                //array[edge[0]][edge[0]] = Double.POSITIVE_INFINITY;
                //array[edge[1]][edge[1]] = Double.POSITIVE_INFINITY;
                normalize(array);
                da.remove(temp);
                stop = true;
                break;
            } else if (!temp.isActivatehwo() && temp.getHWithoutSum() < min) {
                if (!minStructBoolean) {
                    minStruct = sa;
                    minStructNum = min;
                } else {
                    minStructNum = min;
                }
                //countBackWith++;
                ((Struct) da.get(i)).setActivatehwo(true);
                ((Struct) da.get(i)).setActivatehw(true);
                setP(temp.getpNew().clone());
                setH(temp.getHWithoutSum());
                setMi(temp.getMi().clone());
                setMj(temp.getMj().clone());
                pathCount = temp.getPathCountNew();
                array = cloneMatrix(temp.getM1());
                da.remove(temp);
                stop = true;
                break;
            }
            temp = null;
        }
        return stop;
    }
    public void main() {
        sysTime = System.currentTimeMillis();
        initialize();
        solve();
        //computeLastElement();

        //System.out.println("Sum = " + this.getSum(getArray()) + ", H = " + H);
        //System.out.println("Path = " + this.getPath());
    }

    public String getPath() {
        StringBuffer str = new StringBuffer("");
        for (int i = 0; i < originalsize; i++) {
            str.append("(").append(p[i][0] + 1).append("-").append(p[i][1] + 1).append(") ");
        }
        int[][] pc = new int[originalsize][2];
        int t = 0;
        int c = 0;
        str.append('\n');
        for (int i = 0; i < originalsize; i++) {
            for (int j = 0; j < originalsize; j++) {
                if (p[j][0] == t) {
                    pc[c][0] = p[j][0];
                    pc[c][1] = p[j][1];
                    c++;
                    t = p[j][1];
                    break;
                }
            }
        }
        for (int i = 0; i < originalsize; i++) {
            str.append("(").append(pc[i][0] + 1).append("-").append(pc[i][1] + 1).append(") ");
        }
        return str.toString();
    }

    public double getSum(double[][] a) {
        double Sum = 0;
        for (int k = 0; k < originalsize; k++) {
            Sum += a[p[k][0]][p[k][1]];
        }
        return Sum;
    }

    public long getTime() {
        return System.currentTimeMillis() - sysTime;
    }

    private void getPathForZero(double[][] array, int[] d) {
        int x = d[0];//0
        int y = d[1];//2
        int mx = mi[x];
        int my = mj[y];
        int di = -1;
        int dj = -1;
        for (int i = 0; i < mi.length; i++) {// перепилить поиск в быстрый поиск.todo
            if (mi[i] == my) {
                di = i;
                break;
            }
        }
        for (int i = 0; i < mj.length; i++) {// перепилить поиск в быстрый поиск.todo
            if (mj[i] == mx) {
                dj = i;
                break;
            }
        }
        if (di != -1 && dj != -1) {
            array[di][dj] = Double.POSITIVE_INFINITY;
        }
        //mi = remove(mi, x);
        //mj = remove(mj, y);
    }

    public void computeLastElement() {
        p[originalsize - 2][0] = mi[0];
        p[originalsize - 2][1] = mj[1];
        p[originalsize - 1][0] = mi[1];
        p[originalsize - 1][1] = mj[0];
    }

    public double[][] cloneMatrix(double[][] a) {
        if (a == null) return null;
        double[][] clone = a.clone();

        for (int i = 0; i < a.length; i++) {
            clone[i] = a[i].clone();
        }
        return clone;
    }

    public int[][] cloneMatrix(int[][] a) {
        int[][] clone = a.clone();

        for (int i = 0; i < a.length; i++) {
            clone[i] = a[i].clone();
        }
        return clone;
    }

    public static void main(String[] args) {
        double M = Double.POSITIVE_INFINITY;
        double[][] example =
                {
                        {
                                M, 90, 80, 40, 100
                        },
                        {
                                60, M, 40, 50, 70
                        },
                        {
                                50, 30, M, 60, 20
                        },
                        {
                                10, 70, 20, M, 50
                        },
                        {
                                20, 40, 50, 20, M
                        }
                };

        double[][] M0 = {
                {M, 0, 83, 9, 30, 6, 50},
                {0, M, 66, 37, 17, 12, 26},
                {29, 1, M, 19, 0, 12, 5},
                {32, 83, 66, M, 49, 0, 80},
                {3, 21, 56, 7, M, 0, 28},
                {0, 85, 8, 42, 89, M, 0},
                {18, 0, 0, 0, 58, 13, M}
        };

        double[][] M2 = {
                {M, 3, 93, 13, 33, 9, 57},
                {4, M, 77 , 42, 21, 16, 34},
                {45, 17, M, 36,16, 28,25},
                {39, 90, 80, M, 56, 7, 91},
                {28, 46, 88,33, M,25, 57},
                {3, 88,18, 46, 92, M, 7},
                {44,26,33,27, 84, 39, M}
        };
        double[][] M1 = {
                {
                        M,	12,	22,	28,	32,	40,	46
                },
                {
                        12,	M,	10	,40,	20,	28,	34
                },
                {
                        22	,10,	M	,50	,10	,18	,24
                },
                {
                        28,	27,	17,	M	,27,	35	,41
                },
                {
                        32,	20	,10	,60,	M,	8,	14
                },
                {
                        46	,34	,24	,74	,14	,M,	6
                },
                {
                        52	,40	,30	,80	,20,	6	,M
                }
        };
        double[][] M120 = {
                /*{M,},
                {,M,},
                {,M,},
                {,M,},
                {,M}*/
        };
        double[][] MExc = {
                {M,87,41,74,17},
                {52,M,14,87,56},
                {60,85,M,18,7},
                {86,53,33,M,27},
                {24,34,81,24,M}
        };
        ClassicAlgoAnother ca = new ClassicAlgoAnother(MExc);
        ca.print(MExc);

        //ca.display(ca.arrs[1]);
        //System.out.println(ca.delimeter);
        //ca.display(ca.arrs[4]);
        //System.err.println(array[0][3]+ array[3][2]+ array[2][4]+ array[4][1]+ array[1][0]);
    }

    private void print(double[][] example) {
        initialize();
        //solveLeft();
        solve();
        //computeLastElement();
        System.out.println("Sum = " + getSum(example) + ", H = " + H + ", count = " + count);
        System.out.println("Path = " + getPath());
        BruteforceAlgo bf = new BruteforceAlgo();
        StringBuilder blder = new StringBuilder();
        bf.setM0(example);
        bf.main(example);
        blder.append("\nPath: ");
        blder.append(bf.getPath());
        blder.append("\nSum = ");
        blder.append(bf.getSum(example));
        blder.append(",  Time: ");
        blder.append(bf.getTime());
        //parseAndHighlightPath(bf.getPath());
        //w.mainNewMethod();
        System.out.println(blder.toString());
    }

    private void solveLeft() {
        double HWith;
        double HWithout;
        normalize(array);
        setH(getSumOfDelta());
        Map map = new HashMap<Object, Double>();
        Map maparr = new HashMap<Integer, Map>();
        Iterator iterator;
        Map.Entry entry;
        int[] edge = new int[0];
        double sum = 0;
        double[][] M1;
        int count = array.length - 2;
        Map st = new LinkedHashMap<Integer, ArrayList<Struct>>(count);
        Struct s = null;
        Map m = new LinkedHashMap<Object, Object>();
        ArrayList arrayList = null;
        for (int i = 0; i < count; i++) {
            m.clear();
            map.clear();
            map = defineMapEdge();
            edge = difineEdge(map);
            double temp = (double) map.get(edge);
            m.put(edge, (double) map.get(edge));
            HWith = sum;
            M1 = getHWithout(edge, sum);
            HWithout = getSumOfDelta();
            getPath(edge);
            setH(HWithout);
            array = cloneMatrix(M1);
        }
        count = 0;
        for (Object entrySet : st.entrySet()) {
            entry = (Map.Entry) entrySet;
            arrayList = (ArrayList) entry.getValue();
            Struct ss = (Struct) arrayList.get(0);
            System.err.println("i = " + count + "  getHWithSum = " + ss.getHWithSum());
            System.err.println("i = " + count + "  getHWithoutSum = " + ss.getHWithoutSum());
            System.err.println("\n");
            count++;
        }

    }

}
