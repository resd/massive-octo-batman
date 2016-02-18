package gui.newjavafx;

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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("all")
public class ButtonLogic {

    /*
     * Циклично вычисляет путь вмеми заданными методами заданное количество раз
     * Для матриц размером до 12
     * */
    String btnMultiSolveActionPerformed(int multiSolveCountInt,
                                        List<String> methodsOrder, List<String> methodsOrderParallel, ControllerMain controllerMain) {

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
            a = getMatrix(controllerMain, i);
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
                        na.go();
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
                            fa.go();
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

    /*
     * Циклично вычисляет путь вмеми заданными методами заданное количество раз
     * Для матриц размером большим 12
     * */
    public String btnMultiSolveActionPerformedForMoreThan12(int multiSolveCountInt, ArrayList<String> methodsOrder,
                                                            List<String> methodsOrderParallel, ControllerMain controllerMain) {
        int countMethod = methodsOrder.size();
        double[][] a;
        StringBuilder blder = new StringBuilder();
        ClassicAlgo sm;
        ClassicAlgoWithBacktrack clwb;
        NearAlgoEveryDot na;
        FarAlgo fa;
        BaBClassicAlgorithm baBClassicAlgorithm;
        BaBClassicAlgorithmWithFirstLeftBounds baBClassicAlgorithmWithFirstLeftBounds2;
        BaBParallel parallel;
        BaBParallelWithBack parallelWithBack;
        // change this
        ChoseBranchClassic choseBranchBefore = new ChoseBranchClassicForEachElement();
        ChoseBranchClassic choseBranchAfter = new ChoseBranchClassicForEachElementSum();
        Work1Main w = new Work1Main(1);
        Work1Main w2 = new Work1Main(2);

        FileController fileController = controllerMain.getFileController();

        long[] time = new long[countMethod];
        double[] s = new double[countMethod];
        int[] mins = new int[countMethod];
        int[] countRigthMethod = new int[countMethod];
        boolean[] countFlag = new boolean[countMethod];
        int kfa = 0;
        int kcl = 0;
        for (int i = 0; i < countMethod; i++) {
            countRigthMethod[i] = 0;
            countFlag[i] = false;
            mins[i] = 0;
            time[i] = 0;
        }
        boolean flag = false;
        int iteratorForMethods = 0;

        for (int i = 0; i < multiSolveCountInt; i++) {
            a = null;
            a = getMatrix(controllerMain, i);
            for (String method : methodsOrder) {
                switch (method) {
                    case "МВиГ классический":
                        try {
                            clwb = new ClassicAlgoWithBacktrack(a);
                            clwb.main();
                            s[iteratorForMethods] = clwb.getSum(a);
                            time[iteratorForMethods] += clwb.getTime();
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
                            baBClassicAlgorithmWithFirstLeftBounds2 = new BaBClassicAlgorithmWithFirstLeftBounds(a);
                            baBClassicAlgorithmWithFirstLeftBounds2.main();
                            s[iteratorForMethods] = baBClassicAlgorithmWithFirstLeftBounds2.getSum(a);
                            time[iteratorForMethods] += baBClassicAlgorithmWithFirstLeftBounds2.getTime();
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
                        iteratorForMethods++;
                        break;
                    case "МВиГ улучшенный (без разрывов)":
                        w = new Work1Main(1);
                        w.setM0(a);
                        w.main();
                        s[iteratorForMethods] = w.getSum(a);
                        time[iteratorForMethods] += w.getTime();
                        iteratorForMethods++;
                        break;
                    case "МВиГ улучшенный (с разрывами)":
                        w2 = new Work1Main(2);
                        w2.setM0(a);
                        w2.main();
                        s[iteratorForMethods] = w2.getSum(a);
                        time[iteratorForMethods] += w2.getTime();
                        iteratorForMethods++;
                        break;
                    case "Ближнего соседа":
                        na = new NearAlgoEveryDot(a);
                        //na.setM0(a);
                        na.go();
                        s[iteratorForMethods] = na.getSum(a);
                        time[iteratorForMethods] += na.getTime();
                        iteratorForMethods++;
                        break;
                    case "Дальнего соседа":
                        try {
                            fa = new FarAlgo(a);
                            //fa.setM0(a);
                            fa.go();
                            s[iteratorForMethods] = fa.getSum(a);
                            time[iteratorForMethods] += fa.getTime();

                            if (s[iteratorForMethods] < s[iteratorForMethods - 1]) {
                                System.out.println("Far algo = " + s[iteratorForMethods]);
                                System.out.println("Classic algo = " + s[iteratorForMethods - 1]);
                                fileController.setAddToSaveFile(" far algo Smaller then Classic");
                                fileController.autoSaveInformationFromFormToTextFile(a);
                            }

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
                            baBClassicAlgorithm = new BaBClassicAlgorithm(a, new ChoseBranchClassicSum());
                            baBClassicAlgorithm.main();
                            s[iteratorForMethods] = baBClassicAlgorithm.getSum(a);
                            time[iteratorForMethods] += baBClassicAlgorithm.getTime();
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
            iteratorForMethods = 0;
            double min = Arrays.stream(s).min().getAsDouble();
            for (int j = 0; j < methodsOrder.size(); j++) {
                String method = methodsOrder.get(j);
                if (s[j] == min) {
                    mins[j] += 1;
                    for (int k = j; k < countMethod; k++) {
                        countFlag[k] = true;
                    }
                } else {
                    if (j == 1) {
                        int temp = 0;
                    }
                }
            }
            for (int j = 0; j < countMethod; j++) {
                if (countFlag[j]) {
                    countRigthMethod[j]++;
                }
                countFlag[j] = false;
            }
        }

        iteratorForMethods = 0;

        for (String method : methodsOrder) {
            switch (method) {
                case "МВиГ классический":
                    blder.append(getMessageForMoreThan12(method, mins, iteratorForMethods, countRigthMethod, time));
                    //blder.append(kcl);
                    iteratorForMethods++;
                    break;
                case "МВиГ классический (с учетом потерянных ветвей)":
                    blder.append(getMessageForMoreThan12(method, mins, iteratorForMethods, countRigthMethod, time));
                    iteratorForMethods++;
                    break;
                case "МВиГ классический (начиная по левым ветвям)":
                    blder.append(getMessageForMoreThan12(method, mins, iteratorForMethods, countRigthMethod, time));
                    iteratorForMethods++;
                    break;
                case "МВиГ параллельный":
                    blder.append(getMessageForMoreThan12(method, mins, iteratorForMethods, countRigthMethod, time));
                    iteratorForMethods++;
                    break;
                case "МВиГ параллельный (с возвратом)":
                    blder.append(getMessageForMoreThan12(method, mins, iteratorForMethods, countRigthMethod, time));
                    iteratorForMethods++;
                    break;
                case "МВиГ классический (по каждому элементу)":
                    blder.append(getMessageForMoreThan12(method, mins, iteratorForMethods, countRigthMethod, time));
                    iteratorForMethods++;
                    break;
                case "МВиГ классический (без возвратов)":
                    blder.append(getMessageForMoreThan12(method, mins, iteratorForMethods, countRigthMethod, time));
                    iteratorForMethods++;
                    break;
                case "МВиГ улучшенный (без разрывов)":
                    blder.append(getMessageForMoreThan12(method, mins, iteratorForMethods, countRigthMethod, time));
                    iteratorForMethods++;
                    break;
                case "МВиГ улучшенный (с разрывами)":
                    blder.append(getMessageForMoreThan12(method, mins, iteratorForMethods, countRigthMethod, time));
                    iteratorForMethods++;
                    break;
                case "Ближнего соседа":
                    blder.append(getMessageForMoreThan12(method, mins, iteratorForMethods, countRigthMethod, time));
                    iteratorForMethods++;
                    break;
                case "Дальнего соседа":
                    blder.append(getMessageForMoreThan12(method, mins, iteratorForMethods, countRigthMethod, time));
                    iteratorForMethods++;
                    break;
                case "МВиГ классический (с суммой)":
                    blder.append(getMessageForMoreThan12(method, mins, iteratorForMethods, countRigthMethod, time));
                    iteratorForMethods++;
                    break;
                case "МВиГ классический (по каждому элементу с суммой)":
                    blder.append(getMessageForMoreThan12(method, mins, iteratorForMethods, countRigthMethod, time));
                    iteratorForMethods++;
                    break;
                case "МВиГ классический (по каждому элементу со смежными)":
                    blder.append(getMessageForMoreThan12(method, mins, iteratorForMethods, countRigthMethod, time));
                    iteratorForMethods++;
                    break;
                case "МВиГ классический (по каждому элементу со смежными с суммой)":
                    blder.append(getMessageForMoreThan12(method, mins, iteratorForMethods, countRigthMethod, time));
                    iteratorForMethods++;
                    break;
            }
        }
            /*
            blder.append("\n\nОбщее количество совпадений с ПП:\n");
            Arrays.sort(countRigthMethod);
            blder.append(countRigthMethod[countRigthMethod.length - 1]);*/

        return blder.toString();
    }

    /*
     * Вычисляет путь вмеми заданными методами
     * */
    String btnSolveActionPerformed(double[][] a, List<String> methodsOrder, ArrayList<String> methodsOrderParallel) {//GEN-FIRST:event_btnSolveActionPerformed
        StringBuilder blder = new StringBuilder();
        //blder.append("\n\n");
        BruteforceAlgo bf = new BruteforceAlgo();
        ClassicAlgo sm = new ClassicAlgo(a);
        BaBClassicAlgorithmWithFirstLeftBounds baBClassicAlgorithmWithFirstLeftBounds = new BaBClassicAlgorithmWithFirstLeftBounds(a);
        ClassicAlgoWithBacktrack clwb = new ClassicAlgoWithBacktrack(a);
        NearAlgoEveryDot na = new NearAlgoEveryDot(a);
        BaBClassicAlgorithm cam = new BaBClassicAlgorithm(a);
        BaBClassicAlgorithm camp = new BaBClassicAlgorithm(a, new ChoseBranchClassicForEachElement());
        BaBClassicAlgorithm classicSum = new BaBClassicAlgorithm(a, new ChoseBranchClassicSum());
        BaBClassicAlgorithm classicForEachElementSum = new BaBClassicAlgorithm(a, new ChoseBranchClassicForEachElementSum());
        BaBClassicAlgorithm classicForEachRelatedElement = new BaBClassicAlgorithm(a, new ChoseBranchClassicForEachElementWithRelated());
        BaBClassicAlgorithm classicForEachRelatedElementSum = new BaBClassicAlgorithm(a, new ChoseBranchClassicForEachElementWithRelatedSum());
        BaBParallel parallel = new BaBParallel(a);
        BaBParallelWithBack parallelWithBack = new BaBParallelWithBack(a, methodsOrderParallel);
        FarAlgo fa = new FarAlgo(a);
        Work1Main w = new Work1Main(1);
        Work1Main w2 = new Work1Main(2);
        double ppsum = -Double.MAX_VALUE;
        double sum;

        //blder.append("\n\n");
        for (String method : methodsOrder) {
            switch (method) {
                case "Полный перебор":
                    bf.setM0(a);
                    bf.main(a);
                    blder.append(method);
                    blder.append(":");
                    blder.append("\nPath: ");
                    blder.append(bf.getPath());
                    blder.append("\nSum = ");
                    ppsum = bf.getSum(a);
                    blder.append(bf.getSum(a));
                    blder.append(",  Time: ");
                    blder.append(bf.getTime());
                    //parseAndHighlightPath(bf.getPath());
                    //w.mainNewMethod();
                    blder.append("\n\n");
                    break;
                case "МВиГ классический":
                    try {
                        clwb.main();
                        blder.append(method);
                        blder.append(":");
                        blder.append("\nPath: ");
                        blder.append(clwb.getPath());
                        blder.append("\nSum = ");
                        blder.append(clwb.getSum(a));
                        blder.append(",  Time: ");
                        blder.append(clwb.getTime());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    blder.append("\n\n");
                    break;
                case "МВиГ классический (начиная по левым ветвям)":
                    try {
                        baBClassicAlgorithmWithFirstLeftBounds.main();
                        blder.append(method);
                        blder.append(":");
                        blder.append("\nPath: ");
                        blder.append(baBClassicAlgorithmWithFirstLeftBounds.getPath());
                        blder.append("\nSum = ");
                        blder.append(baBClassicAlgorithmWithFirstLeftBounds.getSum(a));
                        blder.append(",  Time: ");
                        blder.append(baBClassicAlgorithmWithFirstLeftBounds.getTime());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    blder.append("\n\n");
                    break;
                case "МВиГ классический (с учетом потерянных ветвей)":
                    try {
                        cam.main();
                        blder.append(method);
                        blder.append(":");
                        blder.append("\nPath: ");
                        blder.append(cam.getPath());
                        blder.append("\nSum = ");
                        blder.append(cam.getSum(a));
                        if (ppsum != -Double.MAX_VALUE && ppsum != cam.getSum(a)) {
                            System.out.println("Don't match brutforce!");
                            FileController fileController = new FileController();
                            fileController.setAddToSaveFile(" Dont match brutforce");
                            fileController.autoSaveInformationFromFormToTextFile(a);
                        }
                        blder.append(",  Time: ");
                        blder.append(cam.getTime());
                    } catch (Exception e) {
                        e.printStackTrace(); //todo uncomment
                    }
                    blder.append("\n\n");
                    break;
                case "МВиГ параллельный":
                    try {
                        parallel.main();
                        blder.append(method);
                        blder.append(":");
                        blder.append("\nPath: ");
                        blder.append(parallel.getPath());
                        blder.append("\nSum = ");
                        blder.append(parallel.getSum(a));
                        /*if (ppsum != -Double.MAX_VALUE && ppsum != parallel.getSum(a)) {
                            System.out.println("Don't match brutforce!");
                            FileController fileController = new FileController();
                            fileController.setAddToSaveFile(" Dont match brutforce");
                            fileController.autoSaveInformationFromFormToTextFile(a);
                        }*/
                        blder.append(",  Time: ");
                        blder.append(parallel.getTime());
                    } catch (Exception e) {
                        e.printStackTrace(); //todo uncomment
                    }
                    blder.append("\n\n");
                    break;
                case "МВиГ параллельный (с возвратом)":
                    try {
                        parallelWithBack.main();
                        blder.append(method);
                        blder.append(":");
                        blder.append("\nPath: ");
                        blder.append(parallelWithBack.getPath());
                        blder.append("\nSum = ");
                        blder.append(parallelWithBack.getSum(a));
                        /*if (ppsum != -Double.MAX_VALUE && ppsum != parallel.getSum(a)) {
                            System.out.println("Don't match brutforce!");
                            FileController fileController = new FileController();
                            fileController.setAddToSaveFile(" Dont match brutforce");
                            fileController.autoSaveInformationFromFormToTextFile(a);
                        }*/
                        blder.append(",  Time: ");
                        blder.append(parallelWithBack.getTime());
                    } catch (NumberFormatException e) {
                        blder.append(method);
                        blder.append(":");
                        blder.append("\nНе выбрано ни одного метода.");
                    } catch (Exception e) {
                        e.printStackTrace(); //todo uncomment
                    }
                    blder.append("\n\n");
                    break;
                case "МВиГ классический (по каждому элементу)":
                    camp.main();
                    blder.append(method);
                    blder.append(":");
                    blder.append("\nPath: ");
                    blder.append(camp.getPath());
                    blder.append("\nSum = ");
                    blder.append(camp.getSum(a));
                    blder.append(",  Time: ");
                    blder.append(camp.getTime());
                    blder.append("\n\n");
                    break;
                case "МВиГ классический (без возвратов)":
                    sm.main();
                    blder.append(method);
                    blder.append(":");
                    blder.append("\nPath: ");
                    blder.append(sm.getPath());
                    blder.append("\nSum = ");
                    blder.append(sm.getSum(a));
                    blder.append(",  Time: ");
                    blder.append(sm.getTime());
                    blder.append("\n\n");
                    break;
                case "МВиГ улучшенный (без разрывов)":
                    w.setM0(a);
                    w.main();
                    blder.append(method);
                    blder.append(":");
                    blder.append("\nPath: ");
                    blder.append(w.getPath());
                    blder.append("\nSum = ");
                    blder.append(w.getSum(a));
                    blder.append(",  Time: ");
                    blder.append(w.getTime());
                    //parseAndHighlightPath(w.getPath());
                    //w.mainNewMethod();
                    blder.append("\n\n");
                    break;
                case "МВиГ улучшенный (с разрывами)":
                    w2.setM0(a);
                    w2.main();
                    blder.append(method);
                    blder.append(":");
                    blder.append("\nPath: ");
                    blder.append(w2.getPath());
                    blder.append("\nSum = ");
                    blder.append(w2.getSum(a));
                    blder.append(",  Time: ");
                    blder.append(w2.getTime());
                    //parseAndHighlightPath(w.getPath());
                    //w.mainNewMethod();
                    blder.append("\n\n");
                    break;
                case "Ближнего соседа":
                    na.go();
                    sum = na.getSum(a);
                    blder.append(method);
                    blder.append(":");
                    blder.append("\nPath: ");
                    blder.append(na.getPath());
                    blder.append("\nSum = ");
                    blder.append(sum);
                    blder.append(",  Time: ");
                    blder.append(na.getTime());
                    blder.append("\n\n");
                    break;
                case "Дальнего соседа":
                    try {
                        fa.go();
                        blder.append(method);
                        blder.append(":");
                        blder.append("\nPath: ");
                        blder.append(fa.getPath());
                        blder.append("\nSum = ");
                        blder.append(fa.getSum(a));
                        blder.append(",  Time: ");
                        blder.append(fa.getTime());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    blder.append("\n\n");
                    break;
                case "МВиГ классический (с суммой)":
                    try {
                        classicSum.main();
                        blder.append(method);
                        blder.append(":");
                        blder.append("\nPath: ");
                        blder.append(classicSum.getPath());
                        blder.append("\nSum = ");
                        blder.append(classicSum.getSum(a));
                        blder.append(",  Time: ");
                        blder.append(classicSum.getTime());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    blder.append("\n\n");
                    break;
                case "МВиГ классический (по каждому элементу с суммой)":
                    try {
                        classicForEachElementSum.main();
                        blder.append(method);
                        blder.append(":");
                        blder.append("\nPath: ");
                        blder.append(classicForEachElementSum.getPath());
                        blder.append("\nSum = ");
                        blder.append(classicForEachElementSum.getSum(a));
                        blder.append(",  Time: ");
                        blder.append(classicForEachElementSum.getTime());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    blder.append("\n\n");
                    break;
                case "МВиГ классический (по каждому элементу со смежными)":
                    try {
                        classicForEachRelatedElement.main();
                        blder.append(method);
                        blder.append(":");
                        blder.append("\nPath: ");
                        blder.append(classicForEachRelatedElement.getPath());
                        blder.append("\nSum = ");
                        blder.append(classicForEachRelatedElement.getSum(a));
                        blder.append(",  Time: ");
                        blder.append(classicForEachRelatedElement.getTime());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    blder.append("\n\n");
                    break;
                case "МВиГ классический (по каждому элементу со смежными с суммой)":
                    try {
                        classicForEachRelatedElementSum.main();
                        blder.append(method);
                        blder.append(":");
                        blder.append("\nPath: ");
                        blder.append(classicForEachRelatedElementSum.getPath());
                        blder.append("\nSum = ");
                        blder.append(classicForEachRelatedElementSum.getSum(a));
                        blder.append(",  Time: ");
                        blder.append(classicForEachRelatedElementSum.getTime());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    blder.append("\n\n");
                    break;
            }
        }
        // Example of using getters that get 3d matrix contents M1 matrix
        //w.getM1ch();
        // or with parameter that point to which matrix you want to get.
        //w.getM1ch(5);
        return blder.toString();
    }

    // Addithion methods

    protected double[][] getMatrix(ControllerMain controllerMain, int i) {
        controllerMain.btnFill.fire();
        return controllerMain.getMatrix();
    }

    private StringBuilder getMessage(String method, int[] m, int iteratorForMethods, int[] countRigthMethod, long[] time) {
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

    private StringBuilder getMessageForMoreThan12(String method, int[] mins, int iteratorForMethods, int[] countRigthMethod, long[] time) {
        String firstMessage = "\nМинимальный результат = ";
        String firstEndMessage = " раз";
        String secondMessage = ", Absolute sum = ";
        String thirdMessage = ", Дельта = ";
        String fourthMessage = ", Время = ";
        String delimeter = "\n\n";
        StringBuilder blder = new StringBuilder();
        blder.append(delimeter);
        blder.append(method);
        blder.append(firstMessage);
        blder.append(mins[iteratorForMethods]);
        blder.append(firstEndMessage);
//        blder.append(secondMessage);
//        blder.append(countRigthMethod[iteratorForMethods]);
        blder.append(thirdMessage);
        blder.append(iteratorForMethods == 0 ? countRigthMethod[iteratorForMethods] : countRigthMethod[iteratorForMethods] - countRigthMethod[iteratorForMethods - 1]);
        blder.append(fourthMessage);
        blder.append(time[iteratorForMethods]);
        return blder;
    }

    public static class ButtonLogicForMultiLoad extends ButtonLogic {
        List<List<String>> list;

        @Override
        protected double[][] getMatrix(ControllerMain controllerMain, int i) {
            return controllerMain.getFileController().parseStringFromFile(list.get(i + 1));
        }

        public void setList(List<List<String>> list) {
            this.list = list;
        }

        public List<List<String>> getList() {
            return list;
        }
    }



}

