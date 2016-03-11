package gui.deltapackage;

import algorithm.bab.classic.branch.ChoseBranchClassic;
import algorithm.bab.classic.branch.ChoseBranchClassicForEachElement;
import algorithm.bab.classic.branch.ChoseBranchClassicForEachElementSum;
import algorithm.bab.classic.BaBClassicAlgorithm;
import algorithm.bruteforce.BruteForceAlgorithm;
import algorithm.bastrikov.Work1Main;
import algorithm.far.FarAlgorithm;
import algorithm.near.NearAlgorithmEveryDot;

import java.util.Arrays;

/**
 * @author Admin
 * @since 21.07.15
 */
//@SuppressWarnings({"all"})
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
        BruteForceAlgorithm bf;
        //ClassicAlgoAnother caa;
        NearAlgorithmEveryDot na;
        FarAlgorithm fa;
        BaBClassicAlgorithm baBClassicAlgorithm;
        // change this
        ChoseBranchClassic choseBranchBefore = new ChoseBranchClassicForEachElement();
        ChoseBranchClassic choseBranchAfter = new ChoseBranchClassicForEachElementSum();
        Work1Main w;
        Work1Main w2;
        int[] m = new int[countMethod];
        double sum;
        long[] time = new long[countMethod];
        double pp;
        int count = 100;
        double[] s = new double[countMethod];
        int kfa = 0;
        int kcl = 0;
        int[] countRightMethod = new int[countMethod];
        boolean[] countFlag = new boolean[countMethod];
        boolean countFlagg = false;
        int countRight = 0;
        //int currentMethod = 0;
        int tempValue = 0;
        for (int i = 0; i < countMethod; i++) {
            countRightMethod[i] = 0;
            time[i] = 0;
            countFlag[i] = false;
        }
        boolean flag = false;

        for (int i = 0; i < n; i++) {
            parentFrame.fillValues();
            a = parentFrame.getValuesFromTable();
            bf = new BruteForceAlgorithm();
            //bf.setM0(a);
            bf.main(a);
            pp = bf.getSum();
            switch (method) {
                case "Все":


                    if (methodsOrder[3] != -1) {
                        w = new Work1Main(a, 1);
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
                        w2 = new Work1Main(a, 2);
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
                        na = new NearAlgorithmEveryDot(a);
                        //na.setM0(a);
                        na.main();
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
                            fa = new FarAlgorithm(a);
                            //fa.setM0(a);
                            fa.main();
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
                            baBClassicAlgorithm = new BaBClassicAlgorithm(a, choseBranchBefore);
                            baBClassicAlgorithm.main();
                            s[methodsOrder[0]] = baBClassicAlgorithm.getSum(a);
                            time[methodsOrder[0]] += baBClassicAlgorithm.getTime();
                            if (pp == baBClassicAlgorithm.getSum(a)) {
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
                            baBClassicAlgorithm = new BaBClassicAlgorithm(a, choseBranchAfter);
                            baBClassicAlgorithm.main();
                            s[methodsOrder[7]] = baBClassicAlgorithm.getSum(a);
                            time[methodsOrder[7]] += baBClassicAlgorithm.getTime();
                            if (pp == baBClassicAlgorithm.getSum(a)) {
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
                            countRightMethod[j]++;
                        }
                        countFlag[j] = false;
                    }
                    /*if (countFlagg) {
                        countRight++;
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
            blder.append(countRightMethod[methodsOrder[0]]);
            blder.append(thirdMessage);
            blder.append(methodsOrder[0] == 0 ? countRightMethod[methodsOrder[0]] : countRightMethod[methodsOrder[0]] - countRightMethod[methodsOrder[0] - 1]);
            blder.append(fourthMessage);
            blder.append(time[methodsOrder[0]]);
        }

        if (methodsOrder[1] != -1) {
            blder.append(methodName[1]);
            //blder.append(kcl);
            blder.append(firstMessage);
            blder.append(m[methodsOrder[1]]);
            blder.append(secondMessage);
            blder.append(countRightMethod[methodsOrder[1]]);
            blder.append(thirdMessage);
            blder.append(methodsOrder[1] == 0 ? countRightMethod[methodsOrder[1]] : countRightMethod[methodsOrder[1]] - countRightMethod[methodsOrder[1] - 1]);
            blder.append(fourthMessage);
            blder.append(time[methodsOrder[1]]);
        }

        if (methodsOrder[2] != -1) {
            blder.append(methodName[2]);
            blder.append(firstMessage);
            blder.append(m[methodsOrder[2]]);
            blder.append(secondMessage);
            blder.append(countRightMethod[methodsOrder[2]]);
            blder.append(thirdMessage);
            blder.append(methodsOrder[2] == 0 ? countRightMethod[methodsOrder[2]] : countRightMethod[methodsOrder[2]] - countRightMethod[methodsOrder[2] - 1]);
            blder.append(fourthMessage);
            blder.append(time[methodsOrder[2]]);
        }

        if (methodsOrder[3] != -1) {
            blder.append(methodName[3]);
            blder.append(firstMessage);
            blder.append(m[methodsOrder[3]]);
            blder.append(secondMessage);
            blder.append(countRightMethod[methodsOrder[3]]);
            blder.append(thirdMessage);
            blder.append(methodsOrder[3] == 0 ? countRightMethod[methodsOrder[3]] : countRightMethod[methodsOrder[3]] - countRightMethod[methodsOrder[3] - 1]);
            blder.append(fourthMessage);
            blder.append(time[methodsOrder[3]]);
        }

        if (methodsOrder[4] != -1) {
            blder.append(methodName[4]);
            blder.append(firstMessage);
            blder.append(m[methodsOrder[4]]);
            blder.append(secondMessage);
            blder.append(countRightMethod[methodsOrder[4]]);
            blder.append(thirdMessage);
            blder.append(methodsOrder[4] == 0 ? countRightMethod[methodsOrder[4]] : countRightMethod[methodsOrder[4]] - countRightMethod[methodsOrder[4] - 1]);
            blder.append(fourthMessage);
            blder.append(time[methodsOrder[4]]);
        }

        if (methodsOrder[5] != -1) {
            blder.append(methodName[5]);
            blder.append(firstMessage);
            blder.append(m[methodsOrder[5]]);
            blder.append(secondMessage);
            blder.append(countRightMethod[methodsOrder[5]]);
            blder.append(thirdMessage);
            blder.append(methodsOrder[5] == 0 ? countRightMethod[methodsOrder[5]] : countRightMethod[methodsOrder[5]] - countRightMethod[methodsOrder[5] - 1]);
            blder.append(fourthMessage);
            blder.append(time[methodsOrder[5]]);
        }

        if (methodsOrder[6] != -1) {
            blder.append(methodName[6]);
            //blder.append(kfa);
            blder.append(firstMessage);
            blder.append(m[methodsOrder[6]]);
            blder.append(secondMessage);
            blder.append(countRightMethod[methodsOrder[6]]);
            blder.append(thirdMessage);
            blder.append(methodsOrder[6] == 0 ? countRightMethod[methodsOrder[6]] : countRightMethod[methodsOrder[6]] - countRightMethod[methodsOrder[6] - 1]);
            blder.append(fourthMessage);
            blder.append(time[methodsOrder[6]]);//*n/(n-kfa)
        }

        if (methodsOrder[7] != -1) {
            blder.append(methodName[7]);
            blder.append(firstMessage);
            blder.append(m[methodsOrder[7]]);
            blder.append(secondMessage);
            blder.append(countRightMethod[methodsOrder[7]]);
            blder.append(thirdMessage);
            blder.append(methodsOrder[7] == 7 ? countRightMethod[methodsOrder[7]] : countRightMethod[methodsOrder[7]] - countRightMethod[methodsOrder[7] - 1]);
            blder.append(fourthMessage);
            blder.append(time[methodsOrder[7]]);
        }

        blder.append("\n\nОбщее количество совпадений с ПП:\n");
        Arrays.sort(countRightMethod);
        blder.append(countRightMethod[0]);

        return blder.toString();
    }//GEN-LAST:event_jButton2ActionPerformed

    String btnSolveActionPerformed(int n, String method, double[][] a) {//GEN-FIRST:event_btnSolveActionPerformed
        //setTablesSize(7);
        //fillValues();

        StringBuilder blder = new StringBuilder();
        //blder.append("\n\n");
        BruteForceAlgorithm bf = new BruteForceAlgorithm();
        NearAlgorithmEveryDot na = new NearAlgorithmEveryDot(a);
        BaBClassicAlgorithm cam = new BaBClassicAlgorithm(a);
        FarAlgorithm fa = new FarAlgorithm(a);
        Work1Main w = new Work1Main(a, 1);
        Work1Main w2 = new Work1Main(a, 2);
        double sum;

        switch (method) {
            case "Все":
                if (n < 13) {
                    bf.main(a);
                    blder.append("Полный перебор");
                    blder.append(":");
                    blder.append("\nPath: ");
                    blder.append(bf.getPath());
                    blder.append("\nSum = ");
                    blder.append(bf.getSum());
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

                //if (clwb.getSum(a) > sm.getSum(a))  setTablesSize(555);

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

                w2.main();
                blder.append("\n\nМВиГ улучшенный(с разрывами)");
                blder.append(":");
                blder.append("\nPath: ");
                blder.append(w2.getPath());
                blder.append("\nSum = ");
                blder.append(w2.getSum(a));
                blder.append(",  Time: ");
                blder.append(w2.getTime());

                na.main();
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
                    fa.main();
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
                bf.main(a);
                blder.append(method);
                blder.append(":");
                blder.append("\nPath: ");
                blder.append(bf.getPath());
                blder.append("\nSum = ");
                blder.append(bf.getSum());
                blder.append(",  Time: ");
                blder.append(bf.getTime());
                //parseAndHighlightPath(bf.getPath());
                //w.mainNewMethod();
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
            case "МВиГ улучшенный(без разрывов)":
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
                na.main();
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
                fa.main();
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
        blder.append(countRightMethod[methodsOrder[7]]);
        blder.append(thirdMessage);
        blder.append(methodsOrder[7] == 0 ? countRightMethod[methodsOrder[7]] : countRightMethod[methodsOrder[7]] - countRightMethod[methodsOrder[7] - 1]);
        blder.append(fourthMessage);
        blder.append(time[methodsOrder[7]]);*/