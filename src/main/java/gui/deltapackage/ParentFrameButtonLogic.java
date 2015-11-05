package gui.deltapackage;

import algorithm.bab.classic.branch.ChoseBranchClassic;
import algorithm.bab.classic.branch.ChoseBranchClassicForEachElement;
import algorithm.bab.classic.branch.ChoseBranchClassicForEachElementSum;
import algorithm.bab.classic.BaBClassicAlgorithmWithFirstLeftBounds;
import algorithm.bruteforce.BruteforceAlgo;
import algorithm.bastrikov.Work1Main;
import algorithm.bab.classic_old.ClassicAlgo;
import algorithm.bab.classic_old.ClassicAlgoWithBacktrack;
import algorithm.far.FarAlgo;
import algorithm.near.NearAlgoEveryDot;

import java.util.Arrays;

/**
 * Created by Admin on 21.07.15.
 */
@SuppressWarnings("all")
public class ParentFrameButtonLogic {

    String jButton2ActionPerformed(int n, String method, ParentFrame parentFrame) {//GEN-FIRST:event_jButton2ActionPerformed

        int countMethod = 8;
        int[] methodsOrder = new int[countMethod];
        methodsOrder[0] = 0;
        methodsOrder[1] = -1;
        methodsOrder[2] = -1;
        methodsOrder[3] = -1;
        methodsOrder[4] = -1;
        methodsOrder[5] = -1;
        methodsOrder[6] = -1;
        methodsOrder[7] = 1;
        /*
        methodsOrder
        0 - Classic module
        1 - Classic
        2 - Classic without backtrack
        3 - New without backtrack
        4 - New
        5 - Near
        6 - Far
        */
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
        // change this
        ChoseBranchClassic choseBranchBefore = new ChoseBranchClassicForEachElement();
        ChoseBranchClassic choseBranchAfter = new ChoseBranchClassicForEachElementSum();
        Work1Main w = new Work1Main(1);
        Work1Main w2 = new Work1Main(2);
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

        for (int i = 0; i < n; i++) {
            parentFrame.fillValues();
            a = parentFrame.getValuesFromTable();
            bf = new BruteforceAlgo();
            //bf.setM0(a);
            bf.main(a);
            pp = bf.getSum(a);
            switch (method) {
                case "Все":
                    if (methodsOrder[2] != -1) {
                        try {
                            clwb = new ClassicAlgoWithBacktrack(a);
                            //clwb.setA(a);
                            //clwb.setArray(a);
                            clwb.main();
                            s[methodsOrder[2]] = clwb.getSum(a);
                            time[methodsOrder[2]] += clwb.getTime();
                            if (pp == clwb.getSum(a)) {
                                m[methodsOrder[2]]++;
                                countFlagg = true;
                                for (int j = methodsOrder[2]; j < countMethod; j++) {
                                    countFlag[j] = true;
                                }
                            /*for (int j = 0; j < countMethod; j++) {
                                countFlag[i] = true;
                            }*/
                            }
                        } catch (Exception e) {
                            kcl++;
                            //e.printStackTrace();
                        }
                    }

                    if (methodsOrder[1] != -1) {
                        sm = new ClassicAlgo(a);
                        //sm.setArray(a);
                        sm.main();
                        s[methodsOrder[1]] = sm.getSum(a);
                        time[methodsOrder[1]] += sm.getTime();
                        if (pp == sm.getSum(a)) {
                            m[methodsOrder[1]]++;
                            countFlagg = true;
                            for (int j = methodsOrder[1]; j < countMethod; j++) {
                                countFlag[j] = true;
                            }
                        }
                    }

                    if (methodsOrder[3] != -1) {
                        w = new Work1Main(1);
                    w.setM0(a);
                    w.main();
                    s[methodsOrder[3]] = w.getSum(a);
                    time[methodsOrder[3]] += w.getTime();
                    if (pp == w.getSum(a)) {
                        m[methodsOrder[3]]++;
                        countFlagg = true;
                        for (int j = methodsOrder[3]; j < countMethod; j++) {
                            countFlag[j] = true;
                        }
                    }
                    }

                    if (methodsOrder[4] != -1) {
                        w2 = new Work1Main(2);
                        w2.setM0(a);
                        w2.main();
                        s[methodsOrder[4]] = w2.getSum(a);
                        time[methodsOrder[4]] += w2.getTime();
                        if (pp == w2.getSum(a)) {
                            m[methodsOrder[4]]++;
                            countFlagg = true;
                            for (int j = methodsOrder[4]; j < countMethod; j++) {
                                countFlag[j] = true;
                            }
                        }
                    }

                    if (methodsOrder[5] != -1) {
                        na = new NearAlgoEveryDot(a);
                        //na.setM0(a);
                        na.go();
                        s[methodsOrder[5]] = na.getSum(a);
                        time[methodsOrder[5]] += na.getTime();
                        if (pp == na.getSum(a)) {
                            m[methodsOrder[5]]++;
                            countFlagg = true;
                            for (int j = methodsOrder[5]; j < countMethod; j++) {
                                countFlag[j] = true;
                            }
                        }
                    }

                    if (methodsOrder[6] != -1) {
                        try {
                            fa = new FarAlgo(a);
                            //fa.setM0(a);
                            fa.go();
                            s[methodsOrder[6]] = fa.getSum(a);
                            time[methodsOrder[6]] += fa.getTime();
                            if (pp == fa.getSum(a)) {
                                m[methodsOrder[6]]++;
                                countFlagg = true;
                                for (int j = methodsOrder[6]; j < countMethod; j++) {
                                    countFlag[j] = true;
                                }
                            }
                        } catch (Exception e) {
                            kfa++;
                            //flag = true;
                            //e.printStackTrace();
                        }
                    }

                    if (methodsOrder[0] != -1) {
                        try {
                            baBClassicAlgorithmWithFirstLeftBounds = new BaBClassicAlgorithmWithFirstLeftBounds(a, choseBranchBefore);
                            baBClassicAlgorithmWithFirstLeftBounds.main();
                            s[methodsOrder[0]] = baBClassicAlgorithmWithFirstLeftBounds.getSum(a);
                            time[methodsOrder[0]] += baBClassicAlgorithmWithFirstLeftBounds.getTime();
                            if (pp == baBClassicAlgorithmWithFirstLeftBounds.getSum(a)) {
                                m[methodsOrder[0]]++;
                                countFlagg = true;
                                for (int j = methodsOrder[0]; j < countMethod; j++) {
                                    countFlag[j] = true;
                                }
                            }
                        } catch (Exception e) {
                            parentFrame.saveInformationFromFormToTextFile();
                            e.printStackTrace();
                        }
                    }

                    if (methodsOrder[7] != -1) {
                        try {
                            baBClassicAlgorithmWithFirstLeftBounds = new BaBClassicAlgorithmWithFirstLeftBounds(a, choseBranchAfter);
                            baBClassicAlgorithmWithFirstLeftBounds.main();
                            s[methodsOrder[7]] = baBClassicAlgorithmWithFirstLeftBounds.getSum(a);
                            time[methodsOrder[7]] += baBClassicAlgorithmWithFirstLeftBounds.getTime();
                            if (pp == baBClassicAlgorithmWithFirstLeftBounds.getSum(a)) {
                                m[methodsOrder[7]]++;
                                countFlagg = true;
                                for (int j = methodsOrder[7]; j < countMethod; j++) {
                                    countFlag[j] = true;
                                }
                            }
                        } catch (Exception e) {
                            parentFrame.saveInformationFromFormToTextFile();
                            e.printStackTrace();
                        }
                    }

                    for (int j = 0; j < countMethod; j++) {
                        if (countFlag[j]) {
                            countRigthMethod[j]++;
                        }
                        countFlag[j] = false;
                    }
                    /*if (countFlagg) {
                        countRigth++;
                    }
                    countFlagg = false;*/
                    //C.p(pp);
                    //C.p(Arrays.toString(s));
                    /*if (s[2] == pp && s[0] != pp)
                        flag = true;
                    */
                    break;
                default:
                    return null;
            }
            //if (flag) break;
        }
        //String firstMessage = "\nОтдельное количество совпадений данного метода с ПП = \n";
        //String secondMessage = "\nАбсолютное количество совпадений с ПП включая все вышестоящие методы = \n";
        String firstMessage = "\nSum = ";
        String secondMessage = ", Absolute sum = ";
        String thirdMessage = ", Delta = ";
        String fourthMessage = ", Time = ";
        String[] methodName = new String[]{"МВиГ классический(модульный):",
                "\n\nМВиГ классический:", "\n\nМВиГ классический(без возвратов):",
                "\n\nМВиГ улучшенный(без разрывов):", "\n\nМВиГ улучшенный(с разрывами):",
                "\n\nБлижнего соседа:", "\n\nДальнего соседа:", "\n\nМВиГ классический (по каждому элементу)"};

        if (methodsOrder[0] != -1) {
            blder.append(methodName[0]);
            blder.append(firstMessage);
            blder.append(m[methodsOrder[0]]);
            blder.append(secondMessage);
            blder.append(countRigthMethod[methodsOrder[0]]);
            blder.append(thirdMessage);
            blder.append(methodsOrder[0] == 0 ? countRigthMethod[methodsOrder[0]] : countRigthMethod[methodsOrder[0]] - countRigthMethod[methodsOrder[0] - 1]);
            blder.append(fourthMessage);
            blder.append(time[methodsOrder[0]]);
        }

        if (methodsOrder[1] != -1) {
            blder.append(methodName[1]);
            //blder.append(kcl);
            blder.append(firstMessage);
            blder.append(m[methodsOrder[1]]);
            blder.append(secondMessage);
            blder.append(countRigthMethod[methodsOrder[1]]);
            blder.append(thirdMessage);
            blder.append(methodsOrder[1] == 0 ? countRigthMethod[methodsOrder[1]] : countRigthMethod[methodsOrder[1]] - countRigthMethod[methodsOrder[1] - 1]);
            blder.append(fourthMessage);
            blder.append(time[methodsOrder[1]]);
        }

        if (methodsOrder[2] != -1) {
            blder.append(methodName[2]);
            blder.append(firstMessage);
            blder.append(m[methodsOrder[2]]);
            blder.append(secondMessage);
            blder.append(countRigthMethod[methodsOrder[2]]);
            blder.append(thirdMessage);
            blder.append(methodsOrder[2] == 0 ? countRigthMethod[methodsOrder[2]] : countRigthMethod[methodsOrder[2]] - countRigthMethod[methodsOrder[2] - 1]);
            blder.append(fourthMessage);
            blder.append(time[methodsOrder[2]]);
        }

        if (methodsOrder[3] != -1) {
            blder.append(methodName[3]);
            blder.append(firstMessage);
            blder.append(m[methodsOrder[3]]);
            blder.append(secondMessage);
            blder.append(countRigthMethod[methodsOrder[3]]);
            blder.append(thirdMessage);
            blder.append(methodsOrder[3] == 0 ? countRigthMethod[methodsOrder[3]] : countRigthMethod[methodsOrder[3]] - countRigthMethod[methodsOrder[3] - 1]);
            blder.append(fourthMessage);
            blder.append(time[methodsOrder[3]]);
        }

        if (methodsOrder[4] != -1) {
            blder.append(methodName[4]);
            blder.append(firstMessage);
            blder.append(m[methodsOrder[4]]);
            blder.append(secondMessage);
            blder.append(countRigthMethod[methodsOrder[4]]);
            blder.append(thirdMessage);
            blder.append(methodsOrder[4] == 0 ? countRigthMethod[methodsOrder[4]] : countRigthMethod[methodsOrder[4]] - countRigthMethod[methodsOrder[4] - 1]);
            blder.append(fourthMessage);
            blder.append(time[methodsOrder[4]]);
        }

        if (methodsOrder[5] != -1) {
            blder.append(methodName[5]);
            blder.append(firstMessage);
            blder.append(m[methodsOrder[5]]);
            blder.append(secondMessage);
            blder.append(countRigthMethod[methodsOrder[5]]);
            blder.append(thirdMessage);
            blder.append(methodsOrder[5] == 0 ? countRigthMethod[methodsOrder[5]] : countRigthMethod[methodsOrder[5]] - countRigthMethod[methodsOrder[5] - 1]);
            blder.append(fourthMessage);
            blder.append(time[methodsOrder[5]]);
        }

        if (methodsOrder[6] != -1) {
            blder.append(methodName[6]);
            //blder.append(kfa);
            blder.append(firstMessage);
            blder.append(m[methodsOrder[6]]);
            blder.append(secondMessage);
            blder.append(countRigthMethod[methodsOrder[6]]);
            blder.append(thirdMessage);
            blder.append(methodsOrder[6] == 0 ? countRigthMethod[methodsOrder[6]] : countRigthMethod[methodsOrder[6]] - countRigthMethod[methodsOrder[6] - 1]);
            blder.append(fourthMessage);
            blder.append(time[methodsOrder[6]]);//*n/(n-kfa)
        }

        if (methodsOrder[7] != -1) {
            blder.append(methodName[7]);
            blder.append(firstMessage);
            blder.append(m[methodsOrder[7]]);
            blder.append(secondMessage);
            blder.append(countRigthMethod[methodsOrder[7]]);
            blder.append(thirdMessage);
            blder.append(methodsOrder[7] == 7 ? countRigthMethod[methodsOrder[7]] : countRigthMethod[methodsOrder[7]] - countRigthMethod[methodsOrder[7] - 1]);
            blder.append(fourthMessage);
            blder.append(time[methodsOrder[7]]);
        }

        blder.append("\n\nОбщее количество совпадений с ПП:\n");
        Arrays.sort(countRigthMethod);
        blder.append(countRigthMethod[0]);

        return blder.toString();
    }//GEN-LAST:event_jButton2ActionPerformed

    String btnSolveActionPerformed(int n, String method, double[][] a) {//GEN-FIRST:event_btnSolveActionPerformed
        //setTablesSize(7);
        //fillValues();

        StringBuilder blder = new StringBuilder();
        //blder.append("\n\n");
        BruteforceAlgo bf = new BruteforceAlgo();
        ClassicAlgo sm = new ClassicAlgo(a);
        ClassicAlgoWithBacktrack clwb = new ClassicAlgoWithBacktrack(a);
        NearAlgoEveryDot na = new NearAlgoEveryDot(a);
        BaBClassicAlgorithmWithFirstLeftBounds cam = new BaBClassicAlgorithmWithFirstLeftBounds(a);
        FarAlgo fa = new FarAlgo(a);
        Work1Main w = new Work1Main(1);
        Work1Main w2 = new Work1Main(2);
        double sum;

        switch (method) {
            case "Все":
                if (n < 13) {
                    bf.setM0(a);
                    bf.main(a);
                    blder.append("Полный перебор");
                    blder.append(":");
                    blder.append("\nPath: ");
                    blder.append(bf.getPath());
                    blder.append("\nSum = ");
                    blder.append(bf.getSum(a));
                    blder.append(",  Time: ");
                    blder.append(bf.getTime());
                }
                //parseAndHighlightPath(bf.getPath());
                //w.mainNewMethod();


                /*caa.main();
                blder.append("\n\nМВиГ классический (переделанный)");
                blder.append(":");
                blder.append("\nPath: ");
                blder.append(caa.getPath());
                blder.append("\nSum = ");
                blder.append(caa.getSum(a));
                blder.append(",  Time: ");
                blder.append(caa.getTime());*/

                cam.main();
                blder.append("\n\nМВиГ классический (модульный)");
                blder.append(":");
                blder.append("\nPath: ");
                blder.append(cam.getPath());
                blder.append("\nSum = ");
                blder.append(cam.getSum(a));
                blder.append(",  Time: ");
                blder.append(cam.getTime());

                clwb.main();
                blder.append("\n\nМВиГ классический");
                blder.append(":");
                blder.append("\nPath: ");
                blder.append(clwb.getPath());
                blder.append("\nSum = ");
                blder.append(clwb.getSum(a));
                blder.append(",  Time: ");
                blder.append(clwb.getTime());

                sm.main();
                blder.append("\n\nМВиГ классический(без возвратов)");
                blder.append(":");
                blder.append("\nPath: ");
                blder.append(sm.getPath());
                blder.append("\nSum = ");
                blder.append(sm.getSum(a));
                blder.append(",  Time: ");
                blder.append(sm.getTime());

                //if (clwb.getSum(a) > sm.getSum(a))  setTablesSize(555);

                w.setM0(a);
                w.main();
                blder.append("\n\nМВиГ улучшенный(без разрывов)");
                blder.append(":");
                blder.append("\nPath: ");
                blder.append(w.getPath());
                blder.append("\nSum = ");
                blder.append(w.getSum(a));
                blder.append(",  Time: ");
                blder.append(w.getTime());
                //parseAndHighlightPath(w.getPath());
                //w.mainNewMethod();

                w2.setM0(a);
                w2.main();
                blder.append("\n\nМВиГ улучшенный(с разрывами)");
                blder.append(":");
                blder.append("\nPath: ");
                blder.append(w2.getPath());
                blder.append("\nSum = ");
                blder.append(w2.getSum(a));
                blder.append(",  Time: ");
                blder.append(w2.getTime());

                na.go();
                sum = na.getSum(a);
                blder.append("\n\nБлижнего соседа");
                blder.append(":");
                blder.append("\nPath: ");
                blder.append(na.getPath());
                blder.append("\nSum = ");
                blder.append(sum);
                blder.append(",  Time: ");
                blder.append(na.getTime());

                try {
                    fa.go();
                    blder.append("\n\nДальнего соседа");
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
                break;
            case "Полный перебор":
                bf.setM0(a);
                bf.main(a);
                blder.append(method);
                blder.append(":");
                blder.append("\nPath: ");
                blder.append(bf.getPath());
                blder.append("\nSum = ");
                blder.append(bf.getSum(a));
                blder.append(",  Time: ");
                blder.append(bf.getTime());
                //parseAndHighlightPath(bf.getPath());
                //w.mainNewMethod();
                break;
            case "МВиГ классический":
                clwb.main();
                blder.append(method);
                blder.append(":");
                blder.append("\nPath: ");
                blder.append(clwb.getPath());
                blder.append("\nSum = ");
                blder.append(clwb.getSum(a));
                blder.append(",  Time: ");
                blder.append(clwb.getTime());
                break;
            /*case "МВиГ классический (переделанный)":
                caa.main();
                blder.append("\n\nМВиГ классический (переделанный)");
                blder.append(":");
                blder.append("\nPath: ");
                blder.append(caa.getPath());
                blder.append("\nSum = ");
                blder.append(caa.getSum(a));
                blder.append(",  Time: ");
                blder.append(caa.getTime());
                break;*/
            case "МВиГ классический(без возвратов)":
                sm.main();
                blder.append(method);
                blder.append(":");
                blder.append("\nPath: ");
                blder.append(sm.getPath());
                blder.append("\nSum = ");
                blder.append(sm.getSum(a));
                blder.append(",  Time: ");
                blder.append(sm.getTime());
                break;
            case "МВиГ улучшенный(без разрывов)":
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
                break;
            case "МВиГ улучшенный(с разрывами)":
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
                break;
            case "Ближнего соседа":
                na.go();
                sum = na.getSum(a);
                blder.append("\n\nБлижнего соседа");
                blder.append(":");
                blder.append("\nPath: ");
                blder.append(na.getPath());
                blder.append("\nSum = ");
                blder.append(sum);
                blder.append(",  Time: ");
                blder.append(na.getTime());
                break;
            case "Дальнего соседа":
                fa.go();
                blder.append("\n\nДальнего соседа");
                blder.append(":");
                blder.append("\nPath: ");
                blder.append(fa.getPath());
                blder.append("\nSum = ");
                blder.append(fa.getSum(a));
                blder.append(",  Time: ");
                blder.append(fa.getTime());
                break;
        }

        // Example of using getters that get 3d matrix contents M1 matrix
        //w.getM1ch();
        // or with parameter that point to which matrix you want to get.
        //w.getM1ch(5);
        return blder.toString();
    }//GEN-LAST:event_btnSolveActionPerformed
}

                    /*currentMethod++;
                    try {
                        caa = new ClassicAlgoAnother(a);
                        //clwb.setA(a);
                        //clwb.setArray(a);
                        caa.main();
                        s[currentMethod] = caa.getSum(a);
                        time[currentMethod] += caa.getTime();
                        if (pp == caa.getSum(a)) {
                            m7++;
                            countFlagg = true;
                            for (int j = currentMethod; j < countMethod; j++) {
                                countFlag[j] = true;
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("THIS IS HERE!!!!!!!!!!!!!!!!!!!!!");
                        System.out.println("THIS IS HERE!!!!!!!!!!!!!!!!!!!!!");
                        System.out.println("THIS IS HERE!!!!!!!!!!!!!!!!!!!!!");
                        System.out.println("THIS IS HERE!!!!!!!!!!!!!!!!!!!!!");
                        System.out.println("THIS IS HERE!!!!!!!!!!!!!!!!!!!!!");
                        System.out.println("THIS IS HERE!!!!!!!!!!!!!!!!!!!!!");
                        System.out.println("THIS IS HERE!!!!!!!!!!!!!!!!!!!!!");
                        System.out.println("THIS IS HERE!!!!!!!!!!!!!!!!!!!!!");
                        System.out.println("THIS IS HERE!!!!!!!!!!!!!!!!!!!!!");
                        System.out.println("THIS IS HERE!!!!!!!!!!!!!!!!!!!!!");
                        System.out.println("THIS IS HERE!!!!!!!!!!!!!!!!!!!!!");
                        System.out.println("THIS IS HERE!!!!!!!!!!!!!!!!!!!!!");
                        break;
                    }*/

/*blder.append(methodName[0]);
        blder.append(firstMessage);
        blder.append(m[methodsOrder[7]]);
        blder.append(secondMessage);
        blder.append(countRigthMethod[methodsOrder[7]]);
        blder.append(thirdMessage);
        blder.append(methodsOrder[7] == 0 ? countRigthMethod[methodsOrder[7]] : countRigthMethod[methodsOrder[7]] - countRigthMethod[methodsOrder[7] - 1]);
        blder.append(fourthMessage);
        blder.append(time[methodsOrder[7]]);*/