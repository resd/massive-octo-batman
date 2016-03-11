package algorithm.bab.classic;

import algorithm.bab.Bab;
import algorithm.bab.classic.branch.ChoseBranchClassic;
import algorithm.bab.classic.branch.ChoseBranchClassicForEachElement;
import algorithm.bab.util.*;
import algorithm.bruteforce.BruteForceAlgorithm;
import algorithm.util.MethodAction;

/**
 * @author zabr1
 */
public class BaBClassicAlgorithm implements MethodAction {
    /**
     * Сделать вариант без возвратов.
     * Сделать вариант с возвратами.
     */
    protected final int originalSize;
    private long sysTime;
    protected Path path;
    protected Var var;
    protected final ArrayClass arrayClass;
    protected DA da;

    protected int count;
    private ChoseBranchClassic cb;
    protected int[][] minP;
    boolean needChooseLeftFirst;

    // Конструктор

    public BaBClassicAlgorithm(double[][] array) {
        var = new Var(Other.cloneMatrix(array));
        arrayClass = new ArrayClass(array);
        originalSize = array.length;
    }

    public BaBClassicAlgorithm(double[][] array, ChoseBranchClassic cb) {
        var = new Var(Other.cloneMatrix(array));
        arrayClass = new ArrayClass(array);
        originalSize = array.length;
        this.cb = cb;
    }

    protected void initialize() {
        // Объявление переменных

        path = new Path(arrayClass);
        da = new DA();
        if (cb == null) {
            cb = new ChoseBranchClassic();
        }
        minP = new int[originalSize][2];
    }

    private void prepare() {

        // Нормализация

        Normalize.INSTANCE.normalize(var.getArray());

        // Задание начальное нижней оценки H

        var.setH(Normalize.INSTANCE.getSumOfDelta());
    }

    protected void solve() {

        // Пока не переберутся все варианты

        while(true) { // checkMin(sa)
            Struct sa = cb.chooseBoth(path, var, arrayClass);
//            System.out.println(sa);

            sa = da.checkSa(sa, var.getMinLowerBound());
//            System.out.println(sa); //      comment
//            if (sa != null) {
//                if (sa.hasStructHW() && sa.getStructHW().getHWithSum() > var.getMinLowerBound() ||
//                        sa.hasStructHWout() && sa.getStructHWout().getHWithoutSum() > var.getMinLowerBound()) {
//                }
//                if (da.isEmpty()) {
//                    if (var.getMinLowerBound() < var.getMin()) {
//                        path.setP(Other.cloneMatrix(minP));
//                    }
//                    break;
//                } else {
//                    existNext = checkMin(path, var);
//
//                }
//            } else {
//                existNext = checkMin(path, var);
//            }
            if (Bab.isEndByNoMoreSmallerBounds(sa, da, path, var)) {
                break;
            }

            if (sa != null)
                da.add(sa);

            if (isEndByFindLowestBound()) break;
            count++;
            //noinspection UnusedAssignment
            sa = null; // probably need to avoid memory leak
        }
        /*System.out.println("countBackWith = " + countBackWith);todo
        System.out.println("countHwith = " + countHwith);
        System.out.println("count = " + count);*/
    }

    private boolean isEndByFindLowestBound() {
        if (var.isArrayNull()) {
            //da.checkLowerBound(sa, var.getMinLowerBound());
            da.searchForLowestBound(path, var);
            if (var.getMinLeftBound() > var.getMin()) { // todo Перенести в choseBranch => ( > 2 ) {} ( else ) { here }
                var.setMinLeftBound(var.getMin());
                minP = Other.cloneMatrix(path.getP());
            }
            boolean existNext = da.checkMin(path, var);
            if (!existNext) {
                return true;
            } else {
                da.checkDa(var.getMinLeftBound());
            }
        }
        return false;
    }

    @Override
    public void main() {
        sysTime = System.currentTimeMillis();
        initialize();
        prepare();
        if (needChooseLeftFirst) {
            if (choseLeft()) {
                solve();
            }
        } else {
            solve();
        }
        //computeLastElement();

        //System.out.println("Sum = " + this.getSum(getArray()) + ", H = " + H);
        //System.out.println("Path = " + this.getPath());
    }

    private boolean choseLeft() {
        // Пока длина матрицы > 2

        while(var.getArrayLength() > 2) {
            cb.choseLeftOnly(da, path, var); // Struct sa =
//            System.out.println(var.getH() + "");
//            System.out.println(sa); //      comment
//            print(var.getArray());
//            C.p("");
        }

        // Вычисление последней границы

        Struct struct = cb.choseLeftOnlyLast(path, var, arrayClass);
        minP = Other.cloneMatrix(path.getP());
        var.setMinLeftBound(var.getMinLowerBound());
        da.add(struct);
        da.checkDa(var.getMinLowerBound());
//        System.out.println(sa); //      comment

        //      Проверка на наличие границы меньше, чем найденная нижняя граница

        return da.checkMin(path, var);
    }

    public String getPath() {
        int[][] p = path.getP();
        StringBuilder str = new StringBuilder("");
        for (int i = 0; i < originalSize; i++) {
            str.append("(").append(p[i][0] + 1).append("-").append(p[i][1] + 1).append(") ");
        }
        int[][] pc = new int[originalSize][2];
        int t = 0;
        int c = 0;
        str.append('\n');
        for (int i = 0; i < originalSize; i++) {
            for (int j = 0; j < originalSize; j++) {
                if (p[j][0] == t) {
                    pc[c][0] = p[j][0];
                    pc[c][1] = p[j][1];
                    c++;
                    t = p[j][1];
                    break;
                }
            }
        }
        for (int i = 0; i < originalSize; i++) {
            str.append("(").append(pc[i][0] + 1).append("-").append(pc[i][1] + 1).append(") ");
        }
        return str.toString();
    }

    @Override
    public double getSum(double[][] a) {
        double sum = 0;
        int[][] p = path.getP();
        for (int k = 0; k < originalSize; k++) {
            sum += a[p[k][0]][p[k][1]];
        }
        return sum;
    }

    @Override
    public long getTime() {
        return System.currentTimeMillis() - sysTime;
    }

    @SuppressWarnings({"MismatchedReadAndWriteOfArray", "UnusedAssignment", "unused"}) // Matrix need to test
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
        ChoseBranchClassic cb = new ChoseBranchClassicForEachElement();
        BaBClassicAlgorithm ca = new BaBClassicAlgorithm(example, cb);
        ca.print(example);

        //ca.display(ca.arrs[1]);
        //System.out.println(ca.delimiter);
        //ca.display(ca.arrs[4]);
        //System.err.println(array[0][3]+ array[3][2]+ array[2][4]+ array[4][1]+ array[1][0]);
    }

    private void print(double[][] example) {
        initialize();
        //solveLeft();
        solve();
        //computeLastElement();
        System.out.println("Sum = " + getSum(example) + ", H = " + var.getH() + ", count = " + count);
        System.out.println("Path = " + getPath());
        BruteForceAlgorithm bf = new BruteForceAlgorithm();
        StringBuilder blder = new StringBuilder();
        bf.main(example);
        blder.append("\nPath: ");
        blder.append(bf.getPath());
        blder.append("\nSum = ");
        blder.append(bf.getSum());
        blder.append(",  Time: ");
        blder.append(bf.getTime());
        //parseAndHighlightPath(bf.getPath());
        //w.mainNewMethod();
        System.out.println(blder.toString());
    }

}
