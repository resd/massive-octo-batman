package algorithm.bab.parallel_with_back;

import algorithm.bab.Bab;
import algorithm.bab.classic.BaBClassicAlgorithm;
import algorithm.bab.parallel_with_back.branch.*;
import algorithm.bab.util.*;
import algorithm.util.MethodAction;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Admin
 * @since 26.10.2015
 */
//@SuppressWarnings("all")
//@SuppressWarnings({"unused", "Duplicates"})
public class BaBParallelWithBack extends BaBClassicAlgorithm implements MethodAction {

    /**

     Что есть:
     * Получение struct для каждого choseBranch
     * Сохранение struct в da
     * Проход ChoseBranch по всем левым ветвям и получение ориентировачной нижней границы
     * Размышления на тему, когда сделать проверку на меньшее значение, записанное в da, по checkMin()
     * Вставить код из Clasic's solve сюда
     * Когда проверять на нижнюю границу
     * Убрать из GeneralStruct ненужные поля
     * Убрать из Var

     Что нужно сделать:
     * Найти причину по которой результат данного метода не совпадет с результатом полного перебора
     * Придумать способ прерывания долгих матриц
     * Убрать из GeneralStruct неиспользуемые параметры из конструкторов

     Что можно сделать:
     * ctrl + Period [google]
     * Возможно ли сделать рекурсивный алгоритм расчета дерева вариантов struct
     При этом разделить в Var значение array etc с min etc
     Посмотреть нужно ли делать что-то еще с Var, Path, Da

     */

    private Path pathForLeft;
    private ArrayList<int[]> edgesTemp;
    private final List<String> methodsOrderParallel;
    private ArrayList<ChoseBranchParallel> choseBranchParallels;


    public BaBParallelWithBack(double[][] array, List<String> methodsOrderParallel) {
        super(array);
        this.methodsOrderParallel = methodsOrderParallel;
    }

    @Override
    protected void initialize() {
        path = new Path(arrayClass);
        pathForLeft = new Path(arrayClass);
        da = new DA();
        choseBranchParallels = new ArrayList<>();
        for (String method : methodsOrderParallel) {
            switch (method) {
                case "МВиГ классический (с учетом потерянных ветвей)":
                    choseBranchParallels.add(new ChoseBranchParallel());
                    break;
                case "МВиГ классический (с суммой)":
                    choseBranchParallels.add(new ChoseBranchParallelSum());
                    break;
                case "МВиГ классический (по каждому элементу с суммой)":
                    //cbForEachElement = new ChoseBranchParallelForEachElement();
                    choseBranchParallels.add(new ChoseBranchParallelForEachElementSum());
                    break;
                case "МВиГ классический (по каждому элементу со смежными)":
                    choseBranchParallels.add(new ChoseBranchParallelForEachElementWithRelated());
                    break;
                case "МВиГ классический (по каждому элементу со смежными с суммой)":
                    choseBranchParallels.add(new ChoseBranchParallelForEachElementWithRelatedSum());
                    break;
                default:
                    // nothing
            }
        }
        if (choseBranchParallels.isEmpty()) throw new NumberFormatException("Не выбрано ни одного метода.");
        minP = new int[originalSize][2];
        edgesTemp = new ArrayList<>();
    }

    private Struct doWithChooseBranch(ChoseBranchParallel choseBranchParallel, Path pathCopy, Var varCopy) {

        Struct sa;
        sa = choseBranchParallel.chooseBoth(pathCopy, varCopy, this, arrayClass);

        if (sa != null) {
            sa = da.checkSa(sa, var.getMinLowerBound());
        }

        return sa;
    }

    @Override
    protected void solve() {

        // Объявление переменных

        int countOfChoseBranches = choseBranchParallels.size();
        Var[] varCopy = new Var[countOfChoseBranches];
        Path[] pathCopy = new Path[countOfChoseBranches];
        Struct structForCheckToExitOfWhile = null;
        boolean existNext;
        ArrayList<Struct> tempStructArrayList = new ArrayList<>(countOfChoseBranches);

        // Получить опорную нижнюю границу - var.minLeftBound

        var.setMinLeftBound(choseLeftForParallel(choseBranchParallels.get(0)));
        var.setMinLowerBound(var.getMinLeftBound());

        // Цикл для нахождения минимального пути

        while (true) {



            var.setMinParallel(Double.MAX_VALUE);
            for (int i = 0; i < countOfChoseBranches; i++) {

                varCopy[i] = new Var(var);
                pathCopy[i] = new Path(path);

            } // end of for

            double minCbForVarCopy = Double.MAX_VALUE;
            double minCbForMinStruct = Double.MAX_VALUE;
            int minIndexOfMinParallelInVarCopy = 0;
            int minIndexForMinStruct = 0;
            int tempIndexForMinIndexForMinStruct = 0;

            // Определение всех edge по всем choseBranch

//            if (var.getArrayLength() < 3) {
//                int asdf = 3;
//            }

            for (int i = 0; i < countOfChoseBranches; i++) {

                Struct structFromMethod = doWithChooseBranch(choseBranchParallels.get(i), pathCopy[i], varCopy[i]);

                if (varCopy[i].getMinParallel() < minCbForVarCopy) {

                    minCbForVarCopy = varCopy[i].getMinParallel();
                    minIndexOfMinParallelInVarCopy = i;

                } // end of if

                if (structFromMethod != null) {

                    tempStructArrayList.add(structFromMethod);

                    if (varCopy[i].getMinParallel() < minCbForMinStruct) {

                        minCbForMinStruct = varCopy[i].getMinParallel();
                        minIndexForMinStruct = tempIndexForMinIndexForMinStruct;

                    } // end of if
                    tempIndexForMinIndexForMinStruct++;

                } // end of if
                //Double.isInfinite(var.array[0][1]) ||Double.isInfinite(var.array[0][2]) || Double.isInfinite(var.array[1][0]) || Double.isInfinite(var.array[1][2]) || Double.isInfinite(var.array[2][0]) || Double.isInfinite(var.array[2][1])
            } // end of for
            clearEdges();

            /*minCB = Double.MAX_VALUE; // todo del this line
            minIndexOfMinParallelInVarCopy = 0; // todo del this line
            for (int i = 0; i < countOfAddedStructInDa; i++) {

                if (varCopy[i].getMinParallel() < minCB) {

                    minCB = varCopy[i].getMinParallel();
                    minIndexOfMinParallelInVarCopy = i;

                } // end of if

            } // end of for*/

//            System.out.println(count);
            var = new Var(varCopy[minIndexOfMinParallelInVarCopy]);
            path = new Path(pathCopy[minIndexOfMinParallelInVarCopy]);

            if (!tempStructArrayList.isEmpty()) {

                //int minStructIndex = indexOfCurrentSizeOfDa + minIndexOfMinParallelInVarCopy;
                //Struct sa = da.get(minStructIndex); // Получение struct, соответствующего минимальному элементу
                structForCheckToExitOfWhile = tempStructArrayList.get(minIndexForMinStruct);

            } // end of if

            if ( Bab.isEndByNoMoreSmallerBounds( structForCheckToExitOfWhile, da, path, var ) ) {
                break;
            }

            if (structForCheckToExitOfWhile != null) {

                da.add(tempStructArrayList.get(minIndexForMinStruct));

            } // end of if

            if (!tempStructArrayList.isEmpty()) tempStructArrayList.remove(minIndexForMinStruct);

            // end of if
// end of for
            tempStructArrayList.stream().filter(structInLoop -> structInLoop != null).
                    forEach(structInLoop -> da.add(structInLoop));

            tempStructArrayList.clear();
            structForCheckToExitOfWhile = null;

            // Проверка на достижение нижней границы и на выход из цикла

            if (var.isArrayNull()) {

                //da.checkLowerBound(sa, var.getMinLowerBound());
                da.searchForLowestBound(path, var);

                System.out.println(var.getMinLeftBound());

                da.clearRestOfGeneralStructWithBiggerLowerBound(var);

                if (var.getMinLeftBound() > var.getMin()) {

                    var.setMinLeftBound(var.getMin());
                    minP = Other.cloneMatrix(path.getP());

                }  // end of if

                existNext = da.checkMin(path, var);

                if (!existNext) {
                    break;
                } else {
                    da.checkDa(var.getMinLeftBound());
                }

            } // end of if

            count++;
        } // end of while

        /*double minSum = Double.MAX_VALUE; //todo Need or no?
        double tempSum;
        for (int i = 0; i < countOfChoseBranches; i++) {
            tempSum = getSum(var.getA(), pathCopy[i]);
            if (tempSum < minSum) {
                minSum = tempSum;
                path = pathCopy[i];
            }
        }*/

            /*boolean existNext;// = false
            sa = da.checkSa(sa, var.getMinLowerBound());

            if (sa == null || !sa.getGeneralStruct().isLowerBound()) {
                existNext = da.checkMin(path, var);
                if (sa != null) {
                    if (!existNext) {
                        if (sa.hasStructHWout() && sa.hasStructHW()) { // ���� ���� � HW � HWo
                            if (sa.getStructHWout().getHWithoutSum() <= sa.getStructHW().getHWithSum) {
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
                if (var.getMinLeftBound() > var.getMin()) { // todo Перенести в choseBrance => ( > 2 {} ( else ) { here }
                    var.setMinLeftBound(var.getMin());
                    minP = Other.cloneMatrix(path.getP());
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

    private double choseLeftForParallel(ChoseBranchParallel choseBranchParallel) {
        Var varCopy = new Var(var);

        // Пока длина матрицы > 2

        while(varCopy.getArrayLength() > 2) {
            choseBranchParallel.choseLeftOnly(da, pathForLeft, varCopy); // Struct sa =
//            System.out.println(varCopy.getH() + "");
//            System.out.println(sa); //      comment
//            print(varCopy.getArray());
//            C.p("");
        }

        // Вычисление последней границы

        Struct struct = choseBranchParallel.choseLeftOnlyLast(pathForLeft, varCopy, arrayClass);
        minP = Other.cloneMatrix(pathForLeft.getP());
        da.add(struct);
        da.checkDa(varCopy.getMinLowerBound());
        return varCopy.getMinLowerBound();
//        System.out.println(sa); //      comment

        //      Проверка на наличие границы меньше, чем найденная нижняя граница
    }

    public void addEdge(int[] edge) {
        edgesTemp.add(edge);
    }

    private void clearEdges() {
        edgesTemp.clear();
    }

    public boolean isEdgesNotEmpty() {
        return !edgesTemp.isEmpty();
    }

    public boolean checkForEdges(int[] edge) {
        for (int[] e : edgesTemp) {
            if (e[0] == edge[0] && e[1] == edge[1]) {
                return true;
            }
        }
        return false;
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

/*if (!varCopy[0].isArrayNull()) {
                for (int i = 1; i < 6; i++) {
                    if (da.get(i).getStructHWout().getHWithoutSum() < da.get(0).getStructHWout().getHWithoutSum()) {
                        System.out.format("On %d iteration HWOut %f of %d smaller of %f \n", count, da.get(i).getStructHWout().getHWithoutSum(),
                                i, da.get(0).getStructHWout().getHWithoutSum());
                    }
                    if (da.get(i).getStructHW().getHWithSum() < da.get(0).getStructHW().getHWithSum()) {
                        System.out.format("On %d iteration HW %f of %d smaller of %f \n", count, da.get(i).getStructHW().getHWithSum(),
                                i, da.get(0).getStructHW().getHWithSum());
                    }
                }
            }*/