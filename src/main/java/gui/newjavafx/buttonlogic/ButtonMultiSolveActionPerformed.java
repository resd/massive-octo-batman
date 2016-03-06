package gui.newjavafx.buttonlogic;

import algorithm.bab.classic.BaBClassicAlgorithm;
import algorithm.bab.classic.BaBClassicAlgorithmWithFirstLeftBounds;
import algorithm.bab.classic.branch.*;
import algorithm.bab.classic_old.ClassicAlgo;
import algorithm.bab.classic_old.ClassicAlgoWithBacktrack;
import algorithm.bab.parallel.BaBParallel;
import algorithm.bab.parallel_with_back.BaBParallelWithBack;
import algorithm.bastrikov.Work1Main;
import algorithm.bruteforce.BruteforceAlgo;
import algorithm.far.FarAlgo;
import algorithm.near.NearAlgoEveryDot;
import gui.newjavafx.ControllerMain;
import gui.newjavafx.FileController;

import java.util.Arrays;
import java.util.List;

/**
 * @author Admin
 * @since 29.02.2016
 * lastModify on 29.02.16
 */
//@SuppressWarnings({"all"})
public class ButtonMultiSolveActionPerformed {

    ButtonLogic buttonLogic;

    public ButtonMultiSolveActionPerformed(ButtonLogic buttonLogic) {
        this.buttonLogic = buttonLogic;
    }

    /*
        * Циклично вычисляет путь всеми заданными методами заданное количество раз
        * Для матриц размером до 12
        * */
    public String btnMultiSolveActionPerformed(int multiSolveCountInt,
                                               List<String> methodsOrder,
                                               List<String> methodsOrderParallel,
                                               ControllerMain controllerMain) {

        int countMethod = methodsOrder.size();
        double[][] a;
        StringBuilder blder = new StringBuilder();
        //blder.append("\n\n");
        BruteforceAlgo bf;
        //ClassicAlgoAnother caa;
        ClassicAlgo sm;
        ClassicAlgoWithBacktrack clwb;
        NearAlgoEveryDot na;
        FarAlgo fa;
        BaBClassicAlgorithm baBClassicAlgorithm;
        BaBClassicAlgorithmWithFirstLeftBounds baBClassicAlgorithmWithFirstLeftBounds;
        BaBParallel parallel;
        BaBParallelWithBack parallelWithBack;
        // change this
        ChoseBranchClassic choseBranchBefore = new ChoseBranchClassicForEachElement();
        ChoseBranchClassic choseBranchAfter = new ChoseBranchClassicForEachElementSum();
        Work1Main w = new Work1Main(1);
        Work1Main w2 = new Work1Main(2);

        FileController fileController = controllerMain.getFileController();

        int[] m = new int[countMethod];
        double sum;
        long[] time = new long[countMethod];
        double pp;
        double[] s = new double[countMethod];
        int kfa = 0;
        int kcl = 0;
        int[] countRigthMethod = new int[countMethod];
        boolean[] countFlag = new boolean[countMethod];
        boolean countFlagg = false;
        for (int i = 0; i < countMethod; i++) {
            countRigthMethod[i] = 0;
            time[i] = 0;
            countFlag[i] = false;
        }
        boolean flag = false;
        int iteratorForMethods = 0;

        for (int i = 0; i < multiSolveCountInt; i++) {
            a = null;
            a = buttonLogic.getMatrix(controllerMain, i);
            bf = new BruteforceAlgo();
            bf.main(a);
            pp = bf.getSum(a);
            for (String method : methodsOrder) {
                switch (method) {
                    case "МВиГ классический":
                        try {
                            clwb = new ClassicAlgoWithBacktrack(a);
                            clwb.main();
                            s[iteratorForMethods] = clwb.getSum(a);
                            time[iteratorForMethods] += clwb.getTime();
                            if (pp == clwb.getSum(a)) {
                                m[iteratorForMethods]++;
                                countFlagg = true;
                                for (int j = iteratorForMethods; j < countMethod; j++) {
                                    countFlag[j] = true;
                                }
//                                if (s[iteratorForMethods] < s[iteratorForMethods-1]) //   uncomment
//                                    throw new Exception("Classic smaller than Module!"); //   uncomment
                            }
                        } catch (Exception e) {
                            kcl++;
//                            System.out.println("Classic smaller than Module!"); //   uncomment
//                            fileController.setAddToSaveFile(" Classic smaller than Module"); //   uncomment
//                            fileController.autoSaveInformationFromFormToTextFile(a); //   uncomment
                            e.printStackTrace();
//                            break;
                        }
                        iteratorForMethods++;
                        break;
                    case "МВиГ классический (начиная по левым ветвям)":
                        try {
                            baBClassicAlgorithmWithFirstLeftBounds = new BaBClassicAlgorithmWithFirstLeftBounds(a);
                            baBClassicAlgorithmWithFirstLeftBounds.main();
                            s[iteratorForMethods] = baBClassicAlgorithmWithFirstLeftBounds.getSum(a);
                            time[iteratorForMethods] += baBClassicAlgorithmWithFirstLeftBounds.getTime();
                            if (pp == baBClassicAlgorithmWithFirstLeftBounds.getSum(a)) {
                                m[iteratorForMethods]++;
                                countFlagg = true;
                                for (int j = iteratorForMethods; j < countMethod; j++) {
                                    countFlag[j] = true;
                                }
//                                if (s[iteratorForMethods] < s[iteratorForMethods-1]) //   uncomment
//                                    throw new Exception("Classic smaller than Module!"); //   uncomment
                            } else {
                                System.out.println("bab Don't match brutforce!");
                                fileController.setAddToSaveFile(" bab Dont match brutforce");
                                fileController.autoSaveInformationFromFormToTextFile(a);
                            }
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
                            baBClassicAlgorithm = new BaBClassicAlgorithm(a);
                            baBClassicAlgorithm.main();
                            s[iteratorForMethods] = baBClassicAlgorithm.getSum(a);
                            time[iteratorForMethods] += baBClassicAlgorithm.getTime();
                            if (pp == baBClassicAlgorithm.getSum(a)) {
                                m[iteratorForMethods]++;
                                countFlagg = true;
                                for (int j = iteratorForMethods; j < countMethod; j++) {
                                    countFlag[j] = true;
                                }
                            } else {
                                System.out.println("mod Don't match brutforce!");
                                fileController.setAddToSaveFile(" mod Dont match brutforce");
                                fileController.autoSaveInformationFromFormToTextFile(a);
                            }
                        } catch (RuntimeException e) {
//                            fileController.autoSaveInformationFromFormToTextFile(a);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        iteratorForMethods++;
                        break;
                    case "МВиГ параллельный":
                        try {
                            parallel = new BaBParallel(a);
                            parallel.main();
                            s[iteratorForMethods] = parallel.getSum(a);
                            time[iteratorForMethods] += parallel.getTime();
                            if (pp == parallel.getSum(a)) {
                                m[iteratorForMethods]++;
                                countFlagg = true;
                                for (int j = iteratorForMethods; j < countMethod; j++) {
                                    countFlag[j] = true;
                                }
                            }/* else {
                                System.out.println("mod Don't match brutforce!");
                                fileController.setAddToSaveFile(" mod Dont match brutforce");
                                fileController.autoSaveInformationFromFormToTextFile(a);
                            }*/
                        } catch (RuntimeException e) {
//                            fileController.autoSaveInformationFromFormToTextFile(a);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        iteratorForMethods++;
                        break;
                    case "МВиГ параллельный (с возвратом)":
                        try {
                            parallelWithBack = new BaBParallelWithBack(a, methodsOrderParallel);
                            parallelWithBack.main();
                            s[iteratorForMethods] = parallelWithBack.getSum(a);
                            time[iteratorForMethods] += parallelWithBack.getTime();
                            if (pp == parallelWithBack.getSum(a)) {
                                m[iteratorForMethods]++;
                                countFlagg = true;
                                for (int j = iteratorForMethods; j < countMethod; j++) {
                                    countFlag[j] = true;
                                }
                            } else {
                                System.out.println("parallel Don't match brutforce!");
                                fileController.setAddToSaveFile(" parallel Don't match brutforce");
                                fileController.autoSaveInformationFromFormToTextFile(a);
                            }
                        } catch (RuntimeException e) {
//                            fileController.autoSaveInformationFromFormToTextFile(a);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        iteratorForMethods++;
                        break;
                    case "МВиГ классический (по каждому элементу)":
                        try {
                            baBClassicAlgorithm = new BaBClassicAlgorithm(a, choseBranchBefore);
                            baBClassicAlgorithm.main();
                            s[iteratorForMethods] = baBClassicAlgorithm.getSum(a);
                            time[iteratorForMethods] += baBClassicAlgorithm.getTime();
                            if (pp == baBClassicAlgorithm.getSum(a)) {
                                m[iteratorForMethods]++;
                                countFlagg = true;
                                for (int j = iteratorForMethods; j < countMethod; j++) {
                                    countFlag[j] = true;
                                }
                            }
                        } catch (Exception e) {
                            // parentFrame.saveInformationFromFormToTextFile(); todo
                            e.printStackTrace();
                        }
                        iteratorForMethods++;
                        break;
                    case "МВиГ классический (без возвратов)":
                        sm = new ClassicAlgo(a);
                        //sm.setArray(a);
                        sm.main();
                        s[iteratorForMethods] = sm.getSum(a);
                        /*if (s[iteratorForMethods] < s[iteratorForMethods - 1]) {todo вернуть проверку на without > ...
                            fileController.autoSaveInformationFromFormToTextFile(a);
                        }*/
                        time[iteratorForMethods] += sm.getTime();
                        if (pp == sm.getSum(a)) {
                            m[iteratorForMethods]++;
                            countFlagg = true;
                            for (int j = iteratorForMethods; j < countMethod; j++) {
                                countFlag[j] = true;
                            }
                        }
                        iteratorForMethods++;
                        break;
                    case "МВиГ улучшенный (без разрывов)":
                        w = new Work1Main(1);
                        w.setM0(a);
                        w.main();
                        s[iteratorForMethods] = w.getSum(a);
                        time[iteratorForMethods] += w.getTime();
                        if (pp == w.getSum(a)) {
                            m[iteratorForMethods]++;
                            countFlagg = true;
                            for (int j = iteratorForMethods; j < countMethod; j++) {
                                countFlag[j] = true;
                            }
                        }
                        iteratorForMethods++;
                        break;
                    case "МВиГ улучшенный (с разрывами)":
                        w2 = new Work1Main(2);
                        w2.setM0(a);
                        w2.main();
                        s[iteratorForMethods] = w2.getSum(a);
                        time[iteratorForMethods] += w2.getTime();
                        if (pp == w2.getSum(a)) {
                            m[iteratorForMethods]++;
                            countFlagg = true;
                            for (int j = iteratorForMethods; j < countMethod; j++) {
                                countFlag[j] = true;
                            }
                        }
                        iteratorForMethods++;
                        break;
                    case "Ближнего соседа":
                        na = new NearAlgoEveryDot(a);
                        //na.setM0(a);
                        na.main();
                        s[iteratorForMethods] = na.getSum(a);
                        time[iteratorForMethods] += na.getTime();
                        if (pp == na.getSum(a)) {
                            m[iteratorForMethods]++;
                            countFlagg = true;
                            for (int j = iteratorForMethods; j < countMethod; j++) {
                                countFlag[j] = true;
                            }
                        }
                        iteratorForMethods++;
                        break;
                    case "Дальнего соседа":
                        try {
                            fa = new FarAlgo(a);
                            //fa.setM0(a);
                            fa.main();
                            s[iteratorForMethods] = fa.getSum(a);
                            time[iteratorForMethods] += fa.getTime();
                            if (pp == fa.getSum(a)) {
                                m[iteratorForMethods]++;
                                countFlagg = true;
                                for (int j = iteratorForMethods; j < countMethod; j++) {
                                    countFlag[j] = true;
                                }
                            }
                        } catch (Exception e) {
                            kfa++;
                            //flag = true;
                            //e.printStackTrace();
                        }
                        iteratorForMethods++;
                        break;
                    case "МВиГ классический (с суммой)":
                        try {
                            baBClassicAlgorithm = new BaBClassicAlgorithm(a, new ChoseBranchClassicSum());
                            baBClassicAlgorithm.main();
                            s[iteratorForMethods] = baBClassicAlgorithm.getSum(a);
                            time[iteratorForMethods] += baBClassicAlgorithm.getTime();
                            if (pp == baBClassicAlgorithm.getSum(a)) {
                                m[iteratorForMethods]++;
                                countFlagg = true;
                                for (int j = iteratorForMethods; j < countMethod; j++) {
                                    countFlag[j] = true;
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        iteratorForMethods++;
                        break;
                    case "МВиГ классический (по каждому элементу с суммой)":
                        try {
                            baBClassicAlgorithm = new BaBClassicAlgorithm(a, new ChoseBranchClassicForEachElementSum());
                            baBClassicAlgorithm.main();
                            s[iteratorForMethods] = baBClassicAlgorithm.getSum(a);
                            time[iteratorForMethods] += baBClassicAlgorithm.getTime();
                            if (pp == baBClassicAlgorithm.getSum(a)) {
                                m[iteratorForMethods]++;
                                countFlagg = true;
                                for (int j = iteratorForMethods; j < countMethod; j++) {
                                    countFlag[j] = true;
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        iteratorForMethods++;
                        break;
                    case "МВиГ классический (по каждому элементу со смежными)":
                        try {
                            baBClassicAlgorithm = new BaBClassicAlgorithm(a, new ChoseBranchClassicForEachElementWithRelated());
                            baBClassicAlgorithm.main();
                            s[iteratorForMethods] = baBClassicAlgorithm.getSum(a);
                            time[iteratorForMethods] += baBClassicAlgorithm.getTime();
                            if (pp == baBClassicAlgorithm.getSum(a)) {
                                m[iteratorForMethods]++;
                                countFlagg = true;
                                for (int j = iteratorForMethods; j < countMethod; j++) {
                                    countFlag[j] = true;
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        iteratorForMethods++;
                        break;
                    case "МВиГ классический (по каждому элементу со смежными с суммой)":
                        try {
                            baBClassicAlgorithm = new BaBClassicAlgorithm(a, new ChoseBranchClassicForEachElementWithRelatedSum());
                            baBClassicAlgorithm.main();
                            s[iteratorForMethods] = baBClassicAlgorithm.getSum(a);
                            time[iteratorForMethods] += baBClassicAlgorithm.getTime();
                            if (pp == baBClassicAlgorithm.getSum(a)) {
                                m[iteratorForMethods]++;
                                countFlagg = true;
                                for (int j = iteratorForMethods; j < countMethod; j++) {
                                    countFlag[j] = true;
                                }
                            }
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
            for (int j = 0; j < countMethod; j++) {
                if (countFlag[j]) {
                    countRigthMethod[j]++;
                }
                countFlag[j] = false;
            }
            iteratorForMethods = 0;
            //if (flag) break;
        }
        //String firstMessage = "\nОтдельное количество совпадений данного метода с ПП = \n";
        //String secondMessage = "\nАбсолютное количество совпадений с ПП включая все вышестоящие методы = \n";

        iteratorForMethods = 0;

        for (String method : methodsOrder) {
            switch (method) {
                case "МВиГ классический":
                    blder.append(getMessage(method, m, iteratorForMethods, countRigthMethod, time));
                    //blder.append(kcl);
                    iteratorForMethods++;
                    break;
                case "МВиГ классический (с учетом потерянных ветвей)":
                    blder.append(getMessage(method, m, iteratorForMethods, countRigthMethod, time));
                    iteratorForMethods++;
                    break;
                case "МВиГ классический (начиная по левым ветвям)":
                    blder.append(getMessage(method, m, iteratorForMethods, countRigthMethod, time));
                    iteratorForMethods++;
                    break;
                case "МВиГ параллельный":
                    blder.append(getMessage(method, m, iteratorForMethods, countRigthMethod, time));
                    iteratorForMethods++;
                    break;
                case "МВиГ параллельный (с возвратом)":
                    blder.append(getMessage(method, m, iteratorForMethods, countRigthMethod, time));
                    iteratorForMethods++;
                    break;
                case "МВиГ классический (по каждому элементу)":
                    blder.append(getMessage(method, m, iteratorForMethods, countRigthMethod, time));
                    iteratorForMethods++;
                    break;
                case "МВиГ классический (без возвратов)":
                    blder.append(getMessage(method, m, iteratorForMethods, countRigthMethod, time));
                    iteratorForMethods++;
                    break;
                case "МВиГ улучшенный (без разрывов)":
                    blder.append(getMessage(method, m, iteratorForMethods, countRigthMethod, time));
                    iteratorForMethods++;
                    break;
                case "МВиГ улучшенный (с разрывами)":
                    blder.append(getMessage(method, m, iteratorForMethods, countRigthMethod, time));
                    iteratorForMethods++;
                    break;
                case "Ближнего соседа":
                    blder.append(getMessage(method, m, iteratorForMethods, countRigthMethod, time));
                    iteratorForMethods++;
                    break;
                case "Дальнего соседа":
                    blder.append(getMessage(method, m, iteratorForMethods, countRigthMethod, time));
                    iteratorForMethods++;
                    break;
                case "МВиГ классический (с суммой)":
                    blder.append(getMessage(method, m, iteratorForMethods, countRigthMethod, time));
                    iteratorForMethods++;
                    break;
                case "МВиГ классический (по каждому элементу с суммой)":
                    blder.append(getMessage(method, m, iteratorForMethods, countRigthMethod, time));
                    iteratorForMethods++;
                    break;
                case "МВиГ классический (по каждому элементу со смежными)":
                    blder.append(getMessage(method, m, iteratorForMethods, countRigthMethod, time));
                    iteratorForMethods++;
                    break;
                case "МВиГ классический (по каждому элементу со смежными с суммой)":
                    blder.append(getMessage(method, m, iteratorForMethods, countRigthMethod, time));
                    iteratorForMethods++;
                    break;
            }
        }
        blder.append("\n\nОбщее количество совпадений с ПП:\n");
        Arrays.sort(countRigthMethod);
        blder.append(countRigthMethod[countRigthMethod.length - 1]);

        return blder.toString();
    }


    protected StringBuilder getMessage(String method, int[] m, int iteratorForMethods, int[] countRigthMethod, long[] time) {
        String firstMessage = "\nSum = ";
        String secondMessage = ", Absolute sum = ";
        String thirdMessage = ", Delta = ";
        String fourthMessage = ", Время = ";
        String delimeter = "\n\n";
        StringBuilder blder = new StringBuilder();
        blder.append(delimeter);
        blder.append(method);
        blder.append(firstMessage);
        blder.append(m[iteratorForMethods]);
        blder.append(secondMessage);
        blder.append(countRigthMethod[iteratorForMethods]);
        blder.append(thirdMessage);
        blder.append(iteratorForMethods == 0 ? countRigthMethod[iteratorForMethods] : countRigthMethod[iteratorForMethods] - countRigthMethod[iteratorForMethods - 1]);
        blder.append(fourthMessage);
        blder.append(time[iteratorForMethods]);
        return blder;
    }
    
    public ButtonLogic getButtonLogic() {
        return buttonLogic;
    }
}
