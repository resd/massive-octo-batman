package simpleMethod;

import java.util.*;

/**
 * @author zabr1
 */
@SuppressWarnings({"unused", "all"})
public class ClassicAlgo {
    /**
     * Сделать вариант без возвратов.
     * Сделать вариант с возвратами.
     */
    private static double[][] array;
    //private static StringBuilder path = new StringBuilder();
    private static double H = 0;
    private static int originalsize;
    public static String delimeter = "========================================";
    static int[][] p;
    static int[] mi;
    static int[] mj;
    static int[] beforeP;
    static double[] minArrI;
    static double[] minArrJ;
    private static long sysTime;

    //конструктор
    public ClassicAlgo(double[][] array) {
        ClassicAlgo.array = cloneMatrix(array);

    }

    private static void initialize() {
        /*if (path.length() != 0)
            path.delete(0, path.length() - 1);*/
        double M = Double.POSITIVE_INFINITY;
        H = 0;
        originalsize = array.length;
        mi = new int[originalsize];
        mj = new int[originalsize];
        beforeP = new int[2];
        p = new int[originalsize][2];
        for (int i = 0; i < originalsize; i++) {
            array[i][i] = M;
            mi[i] = i;
            mj[i] = i;
            p[i][0] = -2;
            p[i][1] = -2;
        }
    }

    public static double getH() {
        return H;
    }

    public static void setH(double H) {
        ClassicAlgo.H += H;
    }

    private static double[][] normalize(double[][] M) {
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
        ClassicAlgo.array = array;
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

        /*Iterator<Map.Entry<Object, Double>> i = map.entrySet().iterator();
        while(i.hasNext()){
            int[] d = (int[]) i.next().getKey();
            System.err.println(Arrays.toString(d)+", "+map.get(d));
        }
        System.err.println("\n");*/
        return map;

    }

    private static int[] difineEdge(Map map) {
        double comparator = 0;
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
        //array[edge[0]][edge[1]] = Double.POSITIVE_INFINITY;
        return edge;
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

    private static double[][] getHWithout(Map map) {
        Iterator iterator = map.entrySet().iterator();
        Map.Entry entry = (Map.Entry) iterator.next();
        double sum = (double) entry.getValue();
        int[] key1 = (int[]) entry.getKey();
        double[][] M1;
        getPathForZero(array, key1);
        M1 = setElementsM0toM(array, key1[0], key1[1]);
        normalize(M1);
        return M1;
    }

    private static void getPathAlternative(double[][] array, int[] d) {
        int x = d[0];
        int y = d[1];
        int len = array[0].length;
        int di = -1;
        for (int i = 0; i < len; i++) {
            if (array[i][y] == Double.POSITIVE_INFINITY) {
                di = i;
                break;
            }
        }
        array[di][x] = Double.POSITIVE_INFINITY;
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
        mi = remove(mi, x);
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

        /*display(array, "initial state");

        //находим минимальные значения в строках
        getMinInRow(array);

        //вычитаем минимальные значения из строки
        deductMinFromRow(array, false);

        display(array, "массив");

        //находим минимальные значения в колонках
        getMinInCollumn(array);

        //вычитаем минимальные значения из столбца
        deductMinFromRow(array, true);
        display(array, "массив");

        //получаем длину маршрута
        setH(getSumOfDelta());*/
        //вот здесь заканчивается правильное решение и начинается белиберда

        normalize(array);
        setH(getSumOfDelta());

        //System.out.println(delimeter);
        Map map = new HashMap<Object, Double>();
        Map m = new HashMap<Object, Double>();
        Iterator iterator;
        Map.Entry entry;
        int[] edge;
        double[][] M1;
        //entry.getValue();
        int count = array.length - 2;
        for (int i = 0; i < count; i++) {
            while (true) {
                m.clear();
                map.clear();
                map = defineMapEdge();
                edge = difineEdge(map);
                m.put(edge, (double) map.get(edge));
                HWith = getHWith(m);
                M1 = getHWithout(m);
                HWithout = getSumOfDelta();
                if (HWithout < HWith) {
                    getPath(edge, i);
                    array = cloneMatrix(M1);
                    break;
                } else {
                    iterator = map.entrySet().iterator();
                    entry = (Map.Entry) iterator.next();
                    map.remove(entry.getKey());
                }
            }

            setH(HWithout);
            /*path.append("(").append(edge[0] + 1)
                    .append("-")
                    .append(edge[1] + 1)
                    .append(") ");*/
            /*System.out.println("KEY : " + edge[0] + ":" + edge[1]);
            System.out.println(comparator);
            //setH(getSumOfDelta());*/
        }
    }

    public static void main() {
        sysTime = System.currentTimeMillis();
        initialize();
        solve();
        computeLastElement();
        //System.out.println("Sum = " + s.getSum(example) + ", H = " + H);
        //System.out.println("Path = " + s.getPath());
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
        int x = d[0];
        int y = d[1];
        int mx = mi[x];//3
        int my = mj[y];//5
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
            p[originalsize - 2][1] = mj[0];
            p[originalsize - 1][0] = mi[1];
            p[originalsize - 1][1] = mj[1];
    }

    private static double[][] cloneMatrix(double[][] a) {
        double[][] clone = a.clone();

        for (int i = 0; i < a.length; i++) {
            clone[i] = a[i].clone();
        }
        return clone;
    }

}



/*
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

        //System.err.println(array[0][3]+ array[3][2]+ array[2][4]+ array[4][1]+ array[1][0]);
        initialize();
        solve();
        computeLastElement();
        System.out.println("Sum = " + s.getSum(example) + ", H = " + H);
        System.out.println("Path = " + s.getPath());
    }
 */