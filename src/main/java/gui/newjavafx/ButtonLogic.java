package gui.newjavafx;

import algorithm.bab.classic.BaBClassicAlgorithm;
import algorithm.bab.classic.BaBClassicAlgorithmWithFirstLeftBounds;
import algorithm.bab.classic.branch.*;
import algorithm.bruteforce.BruteforceAlgo;
import algorithm.bastrikov.Work1Main;
import algorithm.bab.classic_old.ClassicAlgo;
import algorithm.bab.classic_old.ClassicAlgoWithBacktrack;
import algorithm.far.FarAlgo;
import algorithm.near.NearAlgoEveryDot;

import java.util.Arrays;
import java.util.List;

@SuppressWarnings("all")
public class ButtonLogic {
//
    String btnMultiSolveActionPerformed(int n, List<String> methodsOrder, ControllerMain controllerMain) {//GEN-FIRST:event_jButton2ActionPerformed

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
        BaBClassicAlgorithmWithFirstLeftBounds baBClassicAlgorithmWithFirstLeftBounds;
        BaBClassicAlgorithm baBClassicAlgorithm;
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
        int count = 100;
        double[] s = new double[countMethod];
        int kfa = 0;
        int kcl = 0;
        int[] countRigthMethod = new int[countMethod];
        boolean[] countFlag = new boolean[countMethod];
        boolean countFlagg = false;
        int countRigth = 0;
        //int currentMethod = 0;
        int tempValue = 0;
        for (int i = 0; i < countMethod; i++) {
            countRigthMethod[i] = 0;
            time[i] = 0;
            countFlag[i] = false;
        }
        boolean flag = false;
        int iteratorForMethods = 0;

        for (int i = 0; i < n; i++) {
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
                    case "МВиГ классический (по каждому элементу)":
                        try {
                            baBClassicAlgorithmWithFirstLeftBounds = new BaBClassicAlgorithmWithFirstLeftBounds(a, choseBranchBefore);
                            baBClassicAlgorithmWithFirstLeftBounds.main();
                            s[iteratorForMethods] = baBClassicAlgorithmWithFirstLeftBounds.getSum(a);
                            time[iteratorForMethods] += baBClassicAlgorithmWithFirstLeftBounds.getTime();
                            if (pp == baBClassicAlgorithmWithFirstLeftBounds.getSum(a)) {
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
                            baBClassicAlgorithmWithFirstLeftBounds = new BaBClassicAlgorithmWithFirstLeftBounds(a, new ChoseBranchClassicSum());
                            baBClassicAlgorithmWithFirstLeftBounds.main();
                            s[iteratorForMethods] = baBClassicAlgorithmWithFirstLeftBounds.getSum(a);
                            time[iteratorForMethods] += baBClassicAlgorithmWithFirstLeftBounds.getTime();
                            if (pp == baBClassicAlgorithmWithFirstLeftBounds.getSum(a)) {
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
                            baBClassicAlgorithmWithFirstLeftBounds = new BaBClassicAlgorithmWithFirstLeftBounds(a, new ChoseBranchClassicForEachElementSum());
                            baBClassicAlgorithmWithFirstLeftBounds.main();
                            s[iteratorForMethods] = baBClassicAlgorithmWithFirstLeftBounds.getSum(a);
                            time[iteratorForMethods] += baBClassicAlgorithmWithFirstLeftBounds.getTime();
                            if (pp == baBClassicAlgorithmWithFirstLeftBounds.getSum(a)) {
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
                            baBClassicAlgorithmWithFirstLeftBounds = new BaBClassicAlgorithmWithFirstLeftBounds(a, new ChoseBranchClassicForEachElementWithRelated());
                            baBClassicAlgorithmWithFirstLeftBounds.main();
                            s[iteratorForMethods] = baBClassicAlgorithmWithFirstLeftBounds.getSum(a);
                            time[iteratorForMethods] += baBClassicAlgorithmWithFirstLeftBounds.getTime();
                            if (pp == baBClassicAlgorithmWithFirstLeftBounds.getSum(a)) {
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
                            baBClassicAlgorithmWithFirstLeftBounds = new BaBClassicAlgorithmWithFirstLeftBounds(a, new ChoseBranchClassicForEachElementWithRelatedSum());
                            baBClassicAlgorithmWithFirstLeftBounds.main();
                            s[iteratorForMethods] = baBClassicAlgorithmWithFirstLeftBounds.getSum(a);
                            time[iteratorForMethods] += baBClassicAlgorithmWithFirstLeftBounds.getTime();
                            if (pp == baBClassicAlgorithmWithFirstLeftBounds.getSum(a)) {
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
    }//GEN-LAST:event_jButton2ActionPerformed

    protected double[][] getMatrix(ControllerMain controllerMain, int i) {
        controllerMain.btnFill.fire();
        return controllerMain.getMatrix();
    }

    private StringBuilder getMessage(String method, int[] m, int iteratorForMethods, int[] countRigthMethod, long[] time) {
        String firstMessage = "\nSum = ";
        String secondMessage = ", Absolute sum = ";
        String thirdMessage = ", Delta = ";
        String fourthMessage = ", Time = ";
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

    String btnSolveActionPerformed(double[][] a, List<String> methodsOrder) {//GEN-FIRST:event_btnSolveActionPerformed
        StringBuilder blder = new StringBuilder();
        //blder.append("\n\n");
        BruteforceAlgo bf = new BruteforceAlgo();
        ClassicAlgo sm = new ClassicAlgo(a);
        BaBClassicAlgorithm baBClassicAlgorithm = new BaBClassicAlgorithm(a);
        ClassicAlgoWithBacktrack clwb = new ClassicAlgoWithBacktrack(a);
        NearAlgoEveryDot na = new NearAlgoEveryDot(a);
        BaBClassicAlgorithmWithFirstLeftBounds cam = new BaBClassicAlgorithmWithFirstLeftBounds(a);
        BaBClassicAlgorithmWithFirstLeftBounds camp = new BaBClassicAlgorithmWithFirstLeftBounds(a, new ChoseBranchClassicForEachElement());
        BaBClassicAlgorithmWithFirstLeftBounds classicSum = new BaBClassicAlgorithmWithFirstLeftBounds(a, new ChoseBranchClassicSum());
        BaBClassicAlgorithmWithFirstLeftBounds classicForEachElementSum = new BaBClassicAlgorithmWithFirstLeftBounds(a, new ChoseBranchClassicForEachElementSum());
        BaBClassicAlgorithmWithFirstLeftBounds classicForEachRelatedElement = new BaBClassicAlgorithmWithFirstLeftBounds(a, new ChoseBranchClassicForEachElementWithRelated());
        BaBClassicAlgorithmWithFirstLeftBounds classicForEachRelatedElementSum = new BaBClassicAlgorithmWithFirstLeftBounds(a, new ChoseBranchClassicForEachElementWithRelatedSum());
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
                case "МВиГ классический (с учетом потерянных ветвей)":
                    try {
                        baBClassicAlgorithm.main();
                        blder.append(method);
                        blder.append(":");
                        blder.append("\nPath: ");
                        blder.append(baBClassicAlgorithm.getPath());
                        blder.append("\nSum = ");
                        blder.append(baBClassicAlgorithm.getSum(a));
                        blder.append(",  Time: ");
                        blder.append(baBClassicAlgorithm.getTime());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    blder.append("\n\n");
                    break;
                case "МВиГ классический (начиная по левым ветвям)":
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
    }//GEN-LAST:event_btnSolveActionPerformed

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

