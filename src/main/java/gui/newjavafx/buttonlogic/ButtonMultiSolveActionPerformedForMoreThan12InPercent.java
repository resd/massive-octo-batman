package gui.newjavafx.buttonlogic;

import algorithm.bab.classic.BaBClassicAlgorithm;
import algorithm.bab.classic.BaBClassicAlgorithmWithFirstLeftBounds;
import algorithm.bab.classic.branch.ChoseBranchClassicForEachElementSum;
import algorithm.bab.classic.branch.ChoseBranchClassicForEachElementWithRelated;
import algorithm.bab.classic.branch.ChoseBranchClassicForEachElementWithRelatedSum;
import algorithm.bab.classic.branch.ChoseBranchClassicSum;
import algorithm.bab.parallel.BaBParallel;
import algorithm.bab.parallel_with_back.BaBParallelWithBack;
import algorithm.bastrikov.Work1Main;
import algorithm.far.FarAlgorithm;
import algorithm.near.NearAlgorithmEveryDot;
import algorithm.util.MethodAction;
import gui.newjavafx.ControllerMain;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Admin
 * @since 29.02.2016
 */

public class ButtonMultiSolveActionPerformedForMoreThan12InPercent extends ButtonMultiSolveActionPerformedForMoreThan12 {

    private double[][] proc;
    private double classicSum;
    private int[] methodsBetterThanClassic;
    private int index;
    private int multiSolveCountInt;

    public ButtonMultiSolveActionPerformedForMoreThan12InPercent(ButtonLogic buttonLogic) {
        super(buttonLogic);
    }

    @Override
    public String btnMultiSolveActionPerformedForMoreThan12(int multiSolveCountInt,
                                                            ArrayList<String> methodsOrder,
                                                            List<String> methodsOrderParallel,
                                                            ControllerMain controllerMain) {
        methodsOrder.remove("МВиГ классический (с учетом потерянных ветвей)");
        proc = new double[methodsOrder.size()][multiSolveCountInt];
        methodsBetterThanClassic = new int[methodsOrder.size()];
        this.multiSolveCountInt = multiSolveCountInt;
        return super.btnMultiSolveActionPerformedForMoreThan12(multiSolveCountInt,
                methodsOrder, methodsOrderParallel, controllerMain);
    }

    @Override
    protected void doSwitch(String method,
                            List<String> methodsOrderParallel,
                            int index,
                            int multiSolveCountInt) {
        this.index = index;
        baBClassicAlgorithmWithFirstLeftBounds2 =
                new BaBClassicAlgorithmWithFirstLeftBounds(a);
        baBClassicAlgorithmWithFirstLeftBounds2.main();
        classicSum = baBClassicAlgorithmWithFirstLeftBounds2.getSum(a);
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

    @Override
    protected void doMethodAction(MethodAction methodAction) {
        super.doMethodAction(methodAction);
        setProc();
    }

    private void setProc() {
        double result = (s[iteratorForMethods] / classicSum) * 100;
        proc[iteratorForMethods][index] = result;
        if (result < 100) {
            methodsBetterThanClassic[iteratorForMethods]++;
            fileController.setAddToSaveFile(" Classic smaller"); //   uncomment
            fileController.autoSaveInformationFromFormToTextFile(a); //   uncomment
        }
    }
    
    @Override
    protected StringBuilder getMessageForMoreThan12(String method,
                                                    int[] mins,
                                                    int iteratorForMethods,
                                                    int[] countRightMethod,
                                                    long[] time) {
//        String firstMessage = "\nМинимальный результат = ";
        String firstEndMessage = " раз";
        String secondMessage = "\nПроцент = ";
//        String thirdMessage = ", Дельта = ";
//        String fourthMessage = ", Время = ";
        String fifthMessage = "\nЛучше класссического = ";
        String delimiter = "\n\n";
        StringBuilder blder = new StringBuilder();
        blder.append(delimiter);
        blder.append(method);
//        blder.append(firstMessage);
//        blder.append(mins[iteratorForMethods]);
//        blder.append(firstEndMessage);
////        blder.append(secondMessage);
////        blder.append(countRightMethod[iteratorForMethods]);
        blder.append(secondMessage);
        blder.append(String.format("%.1f", getSum(iteratorForMethods)));
        if (methodsBetterThanClassic[iteratorForMethods] != 0) {
            blder.append(fifthMessage);
            blder.append(methodsBetterThanClassic[iteratorForMethods]);
            blder.append(firstEndMessage);
        }
//        blder.append(thirdMessage);
//        blder.append(iteratorForMethods == 0 ? countRightMethod[iteratorForMethods] :
// countRightMethod[iteratorForMethods] - countRightMethod[iteratorForMethods - 1]);
//        blder.append(fourthMessage);
//        blder.append(time[iteratorForMethods]);
        return blder;
    }

    private double getSum(int iteratorForMethods) {
        double sum = 0;
        for (int i = 0; i < multiSolveCountInt; i++) {
            sum += proc[iteratorForMethods][i];
        }
        return sum / multiSolveCountInt - 100;
    }

}
