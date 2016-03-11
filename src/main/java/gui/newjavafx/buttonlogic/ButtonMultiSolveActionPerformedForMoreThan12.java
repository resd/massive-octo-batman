package gui.newjavafx.buttonlogic;

import algorithm.bab.classic.BaBClassicAlgorithm;
import algorithm.bab.classic.BaBClassicAlgorithmWithFirstLeftBounds;
import algorithm.bab.classic.branch.*;
import algorithm.bab.parallel.BaBParallel;
import algorithm.bab.parallel_with_back.BaBParallelWithBack;
import algorithm.bastrikov.Work1Main;
import algorithm.far.FarAlgorithm;
import algorithm.near.NearAlgorithmEveryDot;
import algorithm.util.MethodAction;
import gui.newjavafx.ControllerMain;
import gui.newjavafx.FileController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Admin
 * @since 29.02.2016
 * lastModify on 29.02.16
 */
public class ButtonMultiSolveActionPerformedForMoreThan12{

    // TODO Delete unused fields
    private final ButtonLogic buttonLogic;
    double[][] a;
    private StringBuilder blder;
    BaBClassicAlgorithmWithFirstLeftBounds baBClassicAlgorithmWithFirstLeftBounds2;
    protected BaBParallel parallel;
    ChoseBranchClassic choseBranchBefore;
    private ChoseBranchClassic choseBranchAfter;
    protected Work1Main w;
    protected Work1Main w2;
    FileController fileController;
    int iteratorForMethods;
    private int countMethod;
    private long[] time;
    double[] s;
    private int[] mins;
    private int[] countRightMethod;
    private boolean[] countFlag;
    int kfa;

    public ButtonMultiSolveActionPerformedForMoreThan12(ButtonLogic buttonLogic) {
        this.buttonLogic = buttonLogic;
    }


    /*
     * Циклично вычисляет путь вмеми заданными методами заданное количество раз
     * Для матриц размером большим 12
     * */
    public String btnMultiSolveActionPerformedForMoreThan12(int multiSolveCountInt,
                                                            ArrayList<String> methodsOrder,
                                                            List<String> methodsOrderParallel,
                                                            ControllerMain controllerMain) {
        initialize(methodsOrder, controllerMain);

        for (int i = 0; i < multiSolveCountInt; i++) {

            a = buttonLogic.getMatrix(controllerMain, i);

            for (String method : methodsOrder) {

                doSwitch(method, methodsOrderParallel, i, multiSolveCountInt);

            }

            iteratorForMethods = 0;
            double min = Arrays.stream(s).min().getAsDouble();

            for (int j = 0; j < methodsOrder.size(); j++) {
//                String method = methodsOrder.get(j); // todo Не знаю, зачем нужны была эта строчка
                if (s[j] == min) {
                    mins[j] += 1;
                    for (int k = j; k < countMethod; k++) {
                        countFlag[k] = true;
                    }
                } /*else {
                    if (j == 1) {
                        int temp = 0; // todo Не знаю, зачем нужны была эта строчка
                    }
                }*/
            }

            for (int j = 0; j < countMethod; j++) {
                if (countFlag[j]) {
                    countRightMethod[j]++;
                }
                countFlag[j] = false;
            }

            a = null;
        }

        iteratorForMethods = 0;

        doLastForLoop(methodsOrder);

            /*
            blder.append("\n\nОбщее количество совпадений с ПП:\n");
            Arrays.sort(countRightMethod);
            blder.append(countRightMethod[countRightMethod.length - 1]);*/

        return blder.toString();
    }

    private void initialize(ArrayList<String> methodsOrder,
                            ControllerMain controllerMain) {
        countMethod = methodsOrder.size();
        choseBranchBefore = new ChoseBranchClassicForEachElement();
        choseBranchAfter = new ChoseBranchClassicForEachElementSum();

        fileController = controllerMain.getFileController();

        blder = new StringBuilder();
        time = new long[countMethod];
        s = new double[countMethod];
        mins = new int[countMethod];
        countRightMethod = new int[countMethod];
        countFlag = new boolean[countMethod];
        kfa = 0;
        for (int i = 0; i < countMethod; i++) {
            countRightMethod[i] = 0;
            countFlag[i] = false;
            mins[i] = 0;
            time[i] = 0;
        }
//        boolean flag = false;
        iteratorForMethods = 0;
    }

    void doSwitch(String method,
                  List<String> methodsOrderParallel,
                  int index,
                  int multiSolveCountInt) {
        switch (method) {
            case "МВиГ классический (начиная по левым ветвям)":
                try {
                    doMethodAction(new BaBClassicAlgorithmWithFirstLeftBounds(a));
                } catch (Exception e) {
                    fileController.setAddToSaveFile(" bab Dont match brutforce");
                    fileController.autoSaveInformationFromFormToTextFile(a);
//                            kcl++;
//                            System.out.println("Classic smaller than Module!"); //   uncomment
//                            fileController.setAddToSaveFile(" Classic smaller than Module"); //   uncomment
//                            fileController.autoSaveInformationFromFormToTextFile(a); //   uncomment
//                            e.printStackTrace();
//                            break;
                }
                iteratorForMethods++;
                break;
            case "МВиГ классический (с учетом потерянных ветвей)":
                try {
                    doMethodAction(new BaBClassicAlgorithm(a));
                } catch (RuntimeException e) {
//                            fileController.autoSaveInformationFromFormToTextFile(a);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                iteratorForMethods++;
                break;
            case "МВиГ параллельный":
                try {
                    doMethodAction(new BaBParallel(a));
                } catch (RuntimeException e) {
//                            fileController.autoSaveInformationFromFormToTextFile(a);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                iteratorForMethods++;
                break;
            case "МВиГ параллельный (с возвратом)":
                try {
                    doMethodAction(new BaBParallelWithBack(a, methodsOrderParallel));
                } catch (RuntimeException e) {
//                            fileController.autoSaveInformationFromFormToTextFile(a);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                iteratorForMethods++;
                break;
            case "МВиГ классический (по каждому элементу)":
                try {
                    doMethodAction(new BaBClassicAlgorithm(a, choseBranchBefore));
                } catch (Exception e) {
                    // parentFrame.saveInformationFromFormToTextFile(); todo
                    e.printStackTrace();
                }
                iteratorForMethods++;
                break;
            case "МВиГ улучшенный (без разрывов)":
                doMethodAction(new Work1Main(a, 1));
                iteratorForMethods++;
                break;
            case "МВиГ улучшенный (с разрывами)":
                doMethodAction(new Work1Main(a, 2));
                iteratorForMethods++;
                break;
            case "Ближнего соседа":
                doMethodAction(new NearAlgorithmEveryDot(a));
                //na.setM0(a);
                iteratorForMethods++;
                break;
            case "Дальнего соседа":
                try {
                    doMethodAction(new FarAlgorithm(a));
                    //fa.setM0(a);

//                    if (s[iteratorForMethods] < s[iteratorForMethods - 1]) {
//                        System.out.println("Far algo = " + s[iteratorForMethods]);
//                        System.out.println("Classic algo = " + s[iteratorForMethods - 1]);
//                        fileController.setAddToSaveFile(" far algo Smaller then Classic");
//                        fileController.autoSaveInformationFromFormToTextFile(a);
//                    }

                    // todo del
                            /*baBClassicAlgorithm = new BaBClassicAlgorithm(a);
                            baBClassicAlgorithm.main();
                            double temp = baBClassicAlgorithm.getSum(a);

                            if (s[iteratorForMethods] < temp) {
                                System.out.println("Far algo = " + s[iteratorForMethods]);
                                System.out.println("Classic algo = " + temp);
                                fileController.setAddToSaveFile(" far algo Smaller then Classic");
                                fileController.autoSaveInformationFromFormToTextFile(a);
                            }*/
                    // del

                } catch (Exception e) {
                    s[iteratorForMethods] = Double.POSITIVE_INFINITY;
                    kfa++;
                    //flag = true;
                    //e.printStackTrace();
                }
                iteratorForMethods++;
                break;
            case "МВиГ классический (с суммой)":
                try {
                    doMethodAction(new BaBClassicAlgorithm(a, new ChoseBranchClassicSum()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                iteratorForMethods++;
                break;
            case "МВиГ классический (по каждому элементу с суммой)":
                try {
                    doMethodAction(new BaBClassicAlgorithm(a, new ChoseBranchClassicForEachElementSum()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                iteratorForMethods++;
                break;
            case "МВиГ классический (по каждому элементу со смежными)":
                try {
                    doMethodAction(new BaBClassicAlgorithm(a, new ChoseBranchClassicForEachElementWithRelated()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                iteratorForMethods++;
                break;
            case "МВиГ классический (по каждому элементу со смежными с суммой)":
                try {
                    doMethodAction(new BaBClassicAlgorithm(a, new ChoseBranchClassicForEachElementWithRelatedSum()));
                } catch (RuntimeException e) {
                    //e.printStackTrace();
                    //controllerMain.getFileController().autoSaveInformationFromFormToTextFile(a);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                iteratorForMethods++;
                break;
        }
    }

    void doMethodAction(MethodAction methodAction) {
        methodAction.main();
        s[iteratorForMethods] = methodAction.getSum(a);
        time[iteratorForMethods] += methodAction.getTime();
    }

    private void doLastForLoop(ArrayList<String> methodsOrder) {
        for (String method : methodsOrder) {
            switch (method) {
                case "МВиГ классический":
                    blder.append(getMessageForMoreThan12(
                            method, mins, iteratorForMethods, countRightMethod, time));
                    //blder.append(kcl);
                    iteratorForMethods++;
                    break;
                case "МВиГ классический (с учетом потерянных ветвей)":
                    blder.append(getMessageForMoreThan12(
                            method, mins, iteratorForMethods, countRightMethod, time));
                    iteratorForMethods++;
                    break;
                case "МВиГ классический (начиная по левым ветвям)":
                    blder.append(getMessageForMoreThan12(
                            method, mins, iteratorForMethods, countRightMethod, time));
                    iteratorForMethods++;
                    break;
                case "МВиГ параллельный":
                    blder.append(getMessageForMoreThan12(
                            method, mins, iteratorForMethods, countRightMethod, time));
                    iteratorForMethods++;
                    break;
                case "МВиГ параллельный (с возвратом)":
                    blder.append(getMessageForMoreThan12(
                            method, mins, iteratorForMethods, countRightMethod, time));
                    iteratorForMethods++;
                    break;
                case "МВиГ классический (по каждому элементу)":
                    blder.append(getMessageForMoreThan12(
                            method, mins, iteratorForMethods, countRightMethod, time));
                    iteratorForMethods++;
                    break;
                case "МВиГ классический (без возвратов)":
                    blder.append(getMessageForMoreThan12(
                            method, mins, iteratorForMethods, countRightMethod, time));
                    iteratorForMethods++;
                    break;
                case "МВиГ улучшенный (без разрывов)":
                    blder.append(getMessageForMoreThan12(
                            method, mins, iteratorForMethods, countRightMethod, time));
                    iteratorForMethods++;
                    break;
                case "МВиГ улучшенный (с разрывами)":
                    blder.append(getMessageForMoreThan12(
                            method, mins, iteratorForMethods, countRightMethod, time));
                    iteratorForMethods++;
                    break;
                case "Ближнего соседа":
                    blder.append(getMessageForMoreThan12(
                            method, mins, iteratorForMethods, countRightMethod, time));
                    iteratorForMethods++;
                    break;
                case "Дальнего соседа":
                    blder.append(getMessageForMoreThan12(
                            method, mins, iteratorForMethods, countRightMethod, time));
                    iteratorForMethods++;
                    break;
                case "МВиГ классический (с суммой)":
                    blder.append(getMessageForMoreThan12(
                            method, mins, iteratorForMethods, countRightMethod, time));
                    iteratorForMethods++;
                    break;
                case "МВиГ классический (по каждому элементу с суммой)":
                    blder.append(getMessageForMoreThan12(
                            method, mins, iteratorForMethods, countRightMethod, time));
                    iteratorForMethods++;
                    break;
                case "МВиГ классический (по каждому элементу со смежными)":
                    blder.append(getMessageForMoreThan12(
                            method, mins, iteratorForMethods, countRightMethod, time));
                    iteratorForMethods++;
                    break;
                case "МВиГ классический (по каждому элементу со смежными с суммой)":
                    blder.append(getMessageForMoreThan12(
                            method, mins, iteratorForMethods, countRightMethod, time));
                    iteratorForMethods++;
                    break;
            }
        }
    }

    StringBuilder getMessageForMoreThan12(String method,
                                          int[] mins,
                                          int iteratorForMethods,
                                          int[] countRightMethod,
                                          long[] time) {
        String firstMessage = "\nМинимальный результат = ";
        String firstEndMessage = " раз";
//        String secondMessage = ", Absolute sum = ";
        String thirdMessage = ", Дельта = ";
        String fourthMessage = ", Время = ";
        String delimiter = "\n\n";
        StringBuilder blder = new StringBuilder();
        blder.append(delimiter);
        blder.append(method);
        blder.append(firstMessage);
        blder.append(mins[iteratorForMethods]);
        blder.append(firstEndMessage);
//        blder.append(secondMessage);
//        blder.append(countRightMethod[iteratorForMethods]);
        blder.append(thirdMessage);
        blder.append(iteratorForMethods == 0 ? countRightMethod[iteratorForMethods] : countRightMethod[iteratorForMethods] - countRightMethod[iteratorForMethods - 1]);
        blder.append(fourthMessage);
        blder.append(time[iteratorForMethods]);
        return blder;
    }

    public ButtonLogic getButtonLogic() {
        return buttonLogic;
    }
}
