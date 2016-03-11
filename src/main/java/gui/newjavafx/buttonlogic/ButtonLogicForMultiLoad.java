package gui.newjavafx.buttonlogic;

import gui.newjavafx.ControllerMain;

import java.util.List;

/**
 * @author Admin
 * @since 29.02.2016
 * lastModify on 29.02.16
 */
public class ButtonLogicForMultiLoad extends ButtonLogic {
    private List<List<String>> list;

    @Override
    protected double[][] getMatrix(ControllerMain controllerMain, int i) {
        return controllerMain.getFileController().parseStringFromFile(list.get(i + 1));
    }

    @Override
    public void setList(List<List<String>> list) {
        this.list = list;
    }

    @Override
    public List<List<String>> getList() {
        return list;
    }
}
