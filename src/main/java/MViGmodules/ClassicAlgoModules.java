package MViGmodules;

import common.BruteforceAlgo;

/**
 * @author zabr1
 */
@SuppressWarnings({"unused", "all"})
public class ClassicAlgoModules {
    /**
     * Сделать вариант без возвратов.
     * Сделать вариант с возвратами.
     */
    protected int originalSize;
    protected String delimeter = "=======================================================================================";
    protected long sysTime;
    protected Path path;
    protected Var var;
    protected DA da;

    protected int count;
    protected double[][][] arrs;
    protected double[][] arrayC;
    protected ChoseBranchClassic cb;
    protected int[][] minP;

    //конструктор
    public ClassicAlgoModules(double[][] array) {
        var = new Var(Other.INSTANCE.cloneMatrix(array), Other.INSTANCE.cloneMatrix(array));
        originalSize = array.length;
    }

    public ClassicAlgoModules(double[][] array, ChoseBranchClassic cb) {
        var = new Var(Other.INSTANCE.cloneMatrix(array), Other.INSTANCE.cloneMatrix(array));
        originalSize = array.length;
        this.cb = cb;
    }

    private void initialize() {
        path = new Path(var);
        da = new DA();
        if (cb == null) {
            cb = new ChoseBranchClassic();
        }
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
        return sum;
    }

    protected void solve() {
        // Нормализация
        Normalize.INSTANCE.normalize(var.getArray());
        // Задание начальное нижней оценки H
        var.setH(getSumOfDelta());
        // Объявление переменных
        double HWith;
        double HWithout;
        minP = new int[originalSize][2];
        //int countBackWith = 0;
        //int countHwith = 0;
        // Пока длина матрицы > 2
//        C.out(var.getArray());
//        C.p("");
        while(var.getArrayLength() > 2) {
            Struct sa = cb.choseLeftOnly(da, path, var);
//            System.out.println(var.getH() + "");
//            System.out.println(sa); //      comment
//            print(var.getArray());
//            C.p("");
        }
        Struct struct = cb.choseLeftOnlyLast(path, var);
        //da.add(sa);
        minP = Other.INSTANCE.cloneMatrix(path.getP());
        var.setMinLeftBound(var.getMinLowerBound());
        da.add(struct);
        da.checkDa(var.getMinLowerBound());
//        System.out.println(sa); //      comment
        boolean bool = checkMin();
        // Пока не переберутся все варианты
        //System.out.println("H = " + var.getH());
        while(bool) { // checkMin(sa)
            Struct sa = cb.chooseBoth(da, path, var);
//            System.out.println(sa);

            boolean existNext;// = false
            sa = da.checkSa(sa, var.getMinLowerBound());
//            System.out.println(sa); //      comment
//            if (sa != null) {
//                if (sa.hasStructHW() && sa.getStructHW().getHWithSum() > var.getMinLowerBound() ||
//                        sa.hasStructHWout() && sa.getStructHWout().getHWithoutSum() > var.getMinLowerBound()) {
//                }
//                if (da.isEmpty()) {
//                    if (var.getMinLowerBound() < var.getMin()) {
//                        path.setP(Other.INSTANCE.cloneMatrix(minP));
//                    }
//                    break;
//                } else {
//                    existNext = checkMin();

//                }
//            } else {
//                existNext = checkMin();
//            }

            existNext = checkMin();
            if (sa != null) {
                if (!existNext && !sa.getGeneralStruct().isLowerBound()) {
                    if (sa.hasStructHWout() && sa.hasStructHW()) { // Если есть и HW и HWo
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
                    searchForLowestBound();
                    break;
                }
            }

            if (sa != null)
                da.add(sa);

            //sa = null;
            if (var.isArrayNull()) {
                //da.checkLowerBound(sa, var.getMinLowerBound());
                searchForLowestBound();
                if (var.getMinLeftBound() > var.getMin()) { // todo Перенести в choseBrance => ( > 2 ) {} ( else ) { here }
                    var.setMinLeftBound(var.getMin());
                    minP = Other.INSTANCE.cloneMatrix(path.getP());
                }
                existNext = checkMin();
                if (!existNext) {
                    break;
                } else {
                    da.checkDa(var.getMinLeftBound());
                }
            }
            count++;
            sa = null;
        }
        /*System.out.println("countBackWith = " + countBackWith);todo
        System.out.println("countHwith = " + countHwith);
        System.out.println("count = " + count);*/
    }

    protected boolean checkMin() {
        // Проверить нет ли решений меньше, чем уже полученное решение
        // Прыгнуть на то решение
        Struct temp = null;
        boolean stopHW = false;
        boolean stopHWO = false;
        int i;
        for (i = 0; i < da.getSize(); i++) {
            temp = da.get(i);
            if (temp.hasStructHW() && temp.getStructHW().getHWithSum() < var.getMin()) {
                StructHW structHW = temp.getStructHW();
                ((Struct) da.get(i)).getStructHW().setActivatehw(true);
                path.setP(structHW.getP().clone());
                var.setH(structHW.getHWithSum());
                path.setMi(structHW.getMiOld().clone());
                path.setMj(structHW.getMjOld().clone());
                path.setPathCount(structHW.getPathCount());
                var.setArray(Other.INSTANCE.cloneMatrix(structHW.getArray()));
                Normalize.INSTANCE.normalize(var.getArray());
                stopHW = true;
                break;
            } else if (temp.hasStructHWout() && temp.getStructHWout().getHWithoutSum() < var.getMin()) {
                //countBackWith++;
                StructHWout structHWout = temp.getStructHWout();
                ((Struct) da.get(i)).getStructHWout().setActivatehwo(true);
                path.setP(structHWout.getpNew().clone());
                var.setH(structHWout.getHWithoutSum());
                path.setMi(structHWout.getMi().clone());
                path.setMj(structHWout.getMj().clone());
                path.setPathCount(structHWout.getPathCountNew());
                var.setArray(Other.INSTANCE.cloneMatrix(structHWout.getM1()));
                stopHWO = true;
                break;
            }
        }
        if (stopHWO || stopHW) {
            if (temp.hasStructHWout() && temp.hasStructHW()) {
                if (stopHW) {
                    da.get(i).setStructHW(null);
                } else {
                    da.get(i).setStructHWout(null);
                }
            } else {
                da.remove(temp);
            }
        }
        return stopHWO || stopHW;
    }

    public void searchForLowestBound() {
        for (int i = 0; i < da.getSize(); i++) {
            if (da.get(i).hasGeneralStruct()) {
                GeneralStruct temp = da.get(i).getGeneralStruct();
                if (temp.isLowerBound() && temp.getH() <= var.getMinLeftBound()) {
                    var.setMinLeftBound(temp.getH());
                    path.setP(temp.getMinP());
                }
            }

        }
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
        int[][] p = path.getP();
        StringBuffer str = new StringBuffer("");
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

    public double getSum(double[][] a) {
        double Sum = 0;
        int[][] p = path.getP();
        for (int k = 0; k < originalSize; k++) {
            Sum += a[p[k][0]][p[k][1]];
        }
        return Sum;
    }

    public long getTime() {
        return System.currentTimeMillis() - sysTime;
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
        ChoseBranchClassic cb = new ChoseBranchClassicForEachElement();
        ClassicAlgoModules ca = new ClassicAlgoModules(example, cb);
        ca.print(example);

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
        System.out.println("Sum = " + getSum(example) + ", H = " + var.getH() + ", count = " + count);
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

}
