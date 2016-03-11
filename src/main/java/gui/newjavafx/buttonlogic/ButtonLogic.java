package gui.newjavafx.buttonlogic;

import gui.newjavafx.ControllerMain;

import java.util.List;

/**
 * @author Admin
 * @since 29.02.16
 * lastModify on 29.02.16
 */

public class ButtonLogic {

    /**TODO
     * Переделать статический класс в отдельный класс.
       * Если это будет невозможно, то подумать, можно ли что-то сделать с архитектурой.
     * */

    // Addition methods

    double[][] getMatrix(ControllerMain controllerMain, int i) {
        controllerMain.btnFill.fire();
        return controllerMain.getMatrix();
    }

    public void setList(List<List<String>> list) {
        // Do nothing
        // Need for inherit class
        // Bad arhitecture, I know // TODO Check word arhitecture
    }

    public List<List<String>> getList() {
        // Return null
        // Need for inherit class
        // Bad arhitecture, I know // TODO Check word arhitecture
        return null;
    }
}

