package gui.newjavafx.buttonlogic;

import algorithm.bab.classic.BaBClassicAlgorithm;
import algorithm.bab.classic.BaBClassicAlgorithmWithFirstLeftBounds;
import algorithm.bab.classic.branch.*;
import algorithm.bab.parallel.BaBParallel;
import algorithm.bab.parallel_with_back.BaBParallelWithBack;
import algorithm.bastrikov.Work1Main;
import algorithm.bruteforce.BruteforceAlgo;
import algorithm.far.FarAlgo;
import algorithm.near.NearAlgoEveryDot;
import gui.newjavafx.FileController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Admin
 * @since 29.02.2016
 * lastModify on 29.02.16
 */
@SuppressWarnings("Duplicates")
public class ButtonSolveActionPerformed {

    /*
 * Вычисляет путь вмеми заданными методами
 * */
    public String btnSolveActionPerformed(double[][] a, List<String> methodsOrder,
                                          ArrayList<String> methodsOrderParallel) {
        StringBuilder blder = new StringBuilder();
        //blder.append("\n\n");
        BruteforceAlgo bf = new BruteforceAlgo();
//        ClassicAlgo sm = new ClassicAlgo(a);
        BaBClassicAlgorithmWithFirstLeftBounds baBClassicAlgorithmWithFirstLeftBounds = new BaBClassicAlgorithmWithFirstLeftBounds(a);
//        ClassicAlgoWithBacktrack clwb = new ClassicAlgoWithBacktrack(a);
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
                /*case "МВиГ классический":
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
                    break;*/
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
                /*case "МВиГ классический (без возвратов)":
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
                    break;*/
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
                    na.main();
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
                        fa.main();
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

}
