package simpleMethod;

import common.BruteforceAlgo;

import java.util.*;

/**
 * @author zabr1
 */
@SuppressWarnings({"unused", "all"})
public class ClassicAlgoWithBacktrack {
    /**
     * Сделать вариант без возвратов.
     * Сделать вариант с возвратами.
     */
    private static double[][] array;
    private static double H = 0;
    private static int originalsize;
    private static String delimeter = "========================================";
    private static int[][] p;
    private static int[] mi;
    private static int[] mj;
    private static double[] minArrI;
    private static double[] minArrJ;
    private static long sysTime;
    private static Struct minStruct;
    private static double minStructNum = -1;

    public static double[][] getArray() {
        return array;
    }

    //конструктор
    public ClassicAlgoWithBacktrack(double[][] array) {
        ClassicAlgoWithBacktrack.array = cloneMatrix(array);
        originalsize = array.length;
    }

    private static void initialize() {
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

    public static int[][] getP() {
        return p;
    }

    public static void setP(int[][] p) {
        ClassicAlgoWithBacktrack.p = p;
    }

    public static double getH() {
        return H;
    }

    public static void setH(double H)  {
        ClassicAlgoWithBacktrack.H = H;
    }

    public static int[] getMj() {
        return mj;
    }

    public static void setMj(int[] mj) {
        ClassicAlgoWithBacktrack.mj = mj;
    }

    public static int[] getMi() {
        return mi;
    }

    public static void setMi(int[] mi) {
        ClassicAlgoWithBacktrack.mi = mi;
    }

    private static double[][] normalize(double[][] M) {//todo сразу считать minSum, правда если нужно.
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
                if (M[i][j] != Double.POSITIVE_INFINITY && M[j][i] < minArrJ[i]) {
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
                if (M[i][j] != Double.POSITIVE_INFINITY) {
                    M[j][i] -= minArrJ[i];
                }
            }
        }
        return M;
    }

    public static void setArray(double[][] array) {
        ClassicAlgoWithBacktrack.array = array;
    }

    //получает минимальное значение в строке
    //необходимо для правильной сортировки
    private static double getMinInRow(int row, int j) {
        double minInRow = Integer.MAX_VALUE;

        for (int i = 0; i < array[row].length; i++) {

            if (array[row][i] < minInRow
                    && minInRow != Double.POSITIVE_INFINITY && i != j) {
                minInRow = array[row][i];
            }

        }

        return minInRow;

    }

    private static double getMinInCollumn(int col, int i) {
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
    private static void display(ArrayList<Double> collection, String name) {

        System.out.println(name);

        for (Double element : collection) {
            System.out.print(element + " \t");
        }
        System.out.println("");

    }

    //выводит значения массива на экран
    private static void display(double[][] array1, String msg) {

        for (int i = 0; i < array1.length; i++) {

            for (int j = 0; j < array1[i].length; j++) {

                System.out.print(array1[i][j] + "\t");

            }

            System.out.println("");

        }


    }

    //ищет сумму констант приведения
    private static double getSumOfDelta() {
        double sum = 0;

        for (int i = 0; i < minArrI.length; i++) {
            sum += minArrI[i];
            sum += minArrJ[i];
        }

        //System.out.println("H = " + sum);

        return sum;
    }

    //находим нулевые элементы, определяем ребро ветвления
    private static Map defineMapEdge() {

        Map map = new LinkedHashMap<Object, Object>();

        double t;
        for (int i = 0; i < array.length; i++) {

            for (int j = 0; j < array[i].length; j++) {

                if (array[i][j] == 0 && array[i][j] != Double.POSITIVE_INFINITY) {

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

    public static <K, V extends Comparable<? super V>> Map<K, V>
    sortByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new LinkedList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
            @Override
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

    private static int[] difineEdge(Map map) {
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

    private static int[] difineEdgeWithout(Map map) {
        double comparator = 0;
        int[] edge = new int[2];

        /*for (Object entrySet : map.entrySet()) {
            Map.Entry entry = (Map.Entry) entrySet;
            double sum = (double) entry.getValue();
            int[] key1 = (int[]) entry.getKey();

            if (sum > comparator) {
                comparator = sum;
                edge = key1;
            }
        }*/
        List<Map.Entry<Object,Object>> entryList =
                new ArrayList<Map.Entry<Object, Object>>(map.entrySet());
        Map.Entry<Object, Object> lastEntry =
                entryList.get(entryList.size()-1);

        //array[edge[0]][edge[1]] = Double.POSITIVE_INFINITY;
        return (int[]) lastEntry.getKey();
    }

    // Считаем H включая max элемент их map и заменяя его Infinity
    private static double getHWith(Map map) {

        /*System.out.println("KEY : " + edge[0] + ":" + edge[1]);
        System.out.println(comparator);
        //Path example
        //(6-7) (7-3) (4-6) (3-5) (1-4) (5-2) (2-1)
        path.append("(").append(edge[0])
                .append("-")
                .append(edge[1])
                .append(") ");

        H = getSumOfDelta() + comparator;
        System.out.println(delimeter);
        GetValueOfH();
        System.out.println("path = " + path);*/
        Iterator iterator = map.entrySet().iterator();
        Map.Entry entry = (Map.Entry) iterator.next();
        return (double) entry.getValue();
    }

    private static double[][] getHWithout(int[] edge, double sum) {
        double[][] M1;
        //getPathForZero(array, edge);
        //getPathAlternative(array, edge);
        array[edge[1]][edge[0]] = Double.POSITIVE_INFINITY;
        changeJI(array, edge[0], edge[1]);
        M1 = setElementsM0toM(array, edge[1], edge[1]);
        for (int i = 0; i < M1.length; i++) {
            if (M1[i][i] != Double.POSITIVE_INFINITY)
                System.err.println("\n ASDFSAFSADFSAFSAD FASDF ASDF ASD FSADF ASDfas\nADFSADFSADF\nADFASDFSAFSA\n");
        }
        normalize(M1);
        return M1;
    }

    private static void getPathAlternative(double[][] array, int[] d) {
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

    private static double[][] changeJI(double[][] M0, int di, int dj) {
        double temp;
        for (int j = 0; j < M0.length; j++) {
            temp = M0[dj][j];
            M0[dj][j] = M0[di][j];
            M0[di][j] = temp;
        }

        return M0;
    }

    private static double[][] setElementsM0toM(double[][] M0, int di, int dj) {
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

    private static void getPath(int[] d, int i) {
        int x = d[0];
        int y = d[1];
        p[i][0] = mi[x];
        p[i][1] = mj[y];
        mi[x] = mi[y];

        mi = remove(mi, y);
        mj = remove(mj, y);
    }

    private static int[] remove(int[] mi, int y) {
        int[] tmp = new int[mi.length - 1];
        System.arraycopy(mi, 0, tmp, 0, y);
        System.arraycopy(mi, y + 1, tmp, y, mi.length - y - 1);
        return tmp;
    }

    private static void solve() {
        double HWith;
        double HWithout;
        normalize(array);
        setH(getH() + getSumOfDelta());
        //System.out.println(delimeter);
        Map map = new HashMap<Object, Double>();
        Map maparr = new HashMap<Integer, Map>();
        Iterator iterator;
        Map.Entry entry;
        int[] edge = new int[0];
        double[][] M1;
        /*map.put(1, 2);
        map.put(3, 4);
        map.put(5, 6);
        m.put(3, 4);
        iterator = m.entrySet().iterator();
        entry = (Map.Entry) iterator.next();
        map.remove(entry.getKey());*/
        int count = array.length - 2;
        Map st = new LinkedHashMap<Integer, ArrayList<Struct>>(count);
        ArrayList[] dh = new ArrayList[originalsize];
        /*st.put(1, 2);
        st.put(1, 3);
        st.put(4, 2);
        st.put(4, 2);
        st.put(4, 5);*/
        Struct s = null;
        ArrayList arrayList = null;
        for (int i = 0; i < count; i++) {
            map.clear();
            map = defineMapEdge();
            edge = difineEdge(map);
            HWith = (double) map.get(edge);

            /*if (maparr.get(i) == null) { // Если матрица не существует
                map.clear();
                map = defineMapEdge();      // то создать её
                List<Map.Entry<Object, Object>> entryList =
                        new ArrayList<Map.Entry<Object, Object>>(map.entrySet());
                Map.Entry<Object, Object> lastEntry =
                        entryList.get(entryList.size() - 1);
                edge = (int[]) lastEntry.getKey();
                sum = (double) lastEntry.getValue();
                maparr.put(i, map);
            } else { // допилить
                map = (Map) maparr.get(count);
                //map.remove(entry.getKey()); // lastEntry.getKey()
            }*/
            M1 = getHWithout(edge, HWith);
            HWithout = getSumOfDelta();
            //C.p(delimeter);
            //C.p("Step = " + (i + 1));
            //C.p("HWith = " + HWith);
            //C.p("HWithout = " + HWithout);
            /*if (st.get(i) == null) {
                s = new Struct();
                s.setAll(i,
                        map, edge, HWith,
                        HWithout, array, getH() + HWith,
                        getH() + HWithout, p, true);
                arrayList = new ArrayList<Struct>();
                arrayList.add(s);
                st.put(i, arrayList);
                //st.put(i, new ArrayList<Struct>().add(s));
            } else {
                //arrayList = (ArrayList) st.get(i);
                // arrayList.add(s);//todo  realize and test
                // st.put(i, arrayList);
            }*/
            Struct sa = new Struct(i, edge.clone(), HWith, HWithout, cloneMatrix(array), getH(), cloneMatrix(getP()));
            //sa.setAll(i, edge.clone(), HWith, HWithout, cloneMatrix(array), getH(), p.clone(), mi.clone(), mj.clone());
            if (dh[i] == null) {
                dh[i] = new ArrayList<Struct>();
            }
            /*if (i != 0) {
                if (false) {
                } else {*/
            double min;
            if (HWithout <= HWith) {
                getPath(edge, i);
                setH(getH() + HWithout);
                array = cloneMatrix(M1);
                sa.setActivatehwo(true);//todo трехразрядная логика или как проверить, что мы уже прошли эту вершину?
                min = getH();
            } else {
                array[edge[0]][edge[1]] = Double.POSITIVE_INFINITY;
                array[edge[0]][edge[0]] = Double.POSITIVE_INFINITY;
                array[edge[1]][edge[1]] = Double.POSITIVE_INFINITY;
                normalize(array);
                i--;
                sa.setActivatehw(true);
                setH(getH() + HWith);
                min = getH();
            }
               /* }
            } else {
                if (HWithout < HWith) {
                    getPath(edge, i);
                    setH(getH() + HWithout);
                    array = cloneMatrix(M1);
                } else {
                    array[edge[0]][edge[1]] = Double.POSITIVE_INFINITY;
                    array[edge[0]][edge[0]] = Double.POSITIVE_INFINITY;
                    array[edge[1]][edge[1]] = Double.POSITIVE_INFINITY;
                }
            }*/
            sa.setAdditional(cloneMatrix(getP()), cloneMatrix(M1), mi.clone(), mj.clone());// todo Safe delete pNew.
            dh[i].add(sa);

            if (minStruct != null && min > minStructNum) {
                setP(minStruct.getP());
                setH(minStruct.getHWithSum());
                setMi(minStruct.getMi());
                setMj(minStruct.getMj());
                i = minStruct.getId();
                array = cloneMatrix(minStruct.getArray());
                min = minStruct.getHWithoutSum() > minStruct.getHWithSum() ? minStruct.getHWithSum() : minStruct.getHWithoutSum();
            }

            // Проверить нет ли решений меньше, чем уже полученное решение
            // Прыгнуть на то решение
            boolean stop = false;
            for (int k = 0; k < dh.length; k++) {
                Struct temp;
                if (stop || dh[k] == null) break;// todo dh[k] == null  nado ili net?
                for (int j = 0; j < dh[k].size(); j++) {
                    temp = (Struct) dh[k].get(j);
                    if (!temp.isActivatehw() && temp.getHWithSum() < min) {
                        if (minStruct == null) {
                            minStruct = sa;
                            minStructNum = temp.getHWithSum();
                        } else {
                        }
                        //((Struct) dh[i].get(dh[i].size() - 1)).setActivatehwo(true);
                        //((Struct) dh[i].get(dh[i].size() - 1)).setActivatehw(true);
                        setP(temp.getP());
                        setH(temp.getHWithSum());
                        setMi(temp.getMi());
                        setMj(temp.getMj());
                        i = temp.getId() - 1;
                        array = cloneMatrix(temp.getArray());
                        //((Struct) dh[i].get(j)).setActivatehw(true);// i0 j0
                        array[edge[0]][edge[1]] = Double.POSITIVE_INFINITY;// todo stop here
                        array[edge[0]][edge[0]] = Double.POSITIVE_INFINITY;
                        array[edge[1]][edge[1]] = Double.POSITIVE_INFINITY;
                        normalize(array);
                        stop = true;
                        break;
                    } else if (!temp.isActivatehwo() && temp.getHWithoutSum() < min) {
                        if (minStruct == null) {
                            minStruct = sa;
                            minStructNum = temp.getHWithSum();
                        } else {
                        }
                        //((Struct) dh[i].get(dh[i].size())).setActivatehwo(true);
                        //((Struct) dh[i].get(dh[i].size())).setActivatehw(true);
                        setP(temp.getpNew());
                        setH(temp.getHWithoutSum());
                        setMi(temp.getMi());
                        setMj(temp.getMj());
                        i = temp.getId();
                        array = cloneMatrix(temp.getArray());
                        stop = true;
                        break;
                    }
                }
            }

            sa = null;
            /*for (Object entrySet : map.entrySet()) {
                entry = (Map.Entry) entrySet;
                double sum = (double) entry.getValue();
                int id = (int) entry.getKey();

                if (HWithout > sum) {

                } else {
                    getPath(edge, i);
                    array = cloneMatrix(M1);
                    break;

                        *//*iterator = map.entrySet().iterator();
                        entry = (Map.Entry) iterator.next();
                        map.remove(entry.getKey());*//*
                }
            }*/
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

    public void main() {
        sysTime = System.currentTimeMillis();
        initialize();
        solve();
        computeLastElement();
        //System.out.println("Sum = " + this.getSum(getArray()) + ", H = " + H);
        //System.out.println("Path = " + this.getPath());
    }

    public String getPath() {
        StringBuffer str = new StringBuffer("");
        for (int i = 0; i < originalsize; i++) {
            str.append("(").append(p[i][0] + 1).append("-").append(p[i][1] + 1).append(") ");
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

    private static void getPathForZero(double[][] array, int[] d) {
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

    public static void computeLastElement() {
            p[originalsize - 2][0] = mi[0];
            p[originalsize - 2][1] = mj[1];
            p[originalsize - 1][0] = mi[1];
            p[originalsize - 1][1] = mj[0];
    }

    public static double[][] cloneMatrix(double[][] a) {
        double[][] clone = a.clone();

        for (int i = 0; i < a.length; i++) {
            clone[i] = a[i].clone();
        }
        return clone;
    }

    public static int[][] cloneMatrix(int[][] a) {
        int[][] clone = a.clone();

        for (int i = 0; i < a.length; i++) {
            clone[i] = a[i].clone();
        }
        return clone;
    }

    public static void main(String[] args) {
        sysTime = System.currentTimeMillis();
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

        /*
        

         */
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

        print(M0);
        //System.err.println(array[0][3]+ array[3][2]+ array[2][4]+ array[4][1]+ array[1][0]);
    }

    private static void print(double[][] example) {
        ClassicAlgoWithBacktrack ca = new ClassicAlgoWithBacktrack(example);
        initialize();
        solve();
        computeLastElement();
        System.out.println("Sum = " + ca.getSum(example) + ", H = " + H);
        System.out.println("Path = " + ca.getPath());
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
    static double[][] a = new double[][]{{1,2},{3,4}};
    static double[][] b;
    static double[][] c;

}
