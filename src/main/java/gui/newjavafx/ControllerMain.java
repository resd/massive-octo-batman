package gui.newjavafx;

import gui.deltapackage.ParentFrame;
import gui.newjavafx.buttonlogic.*;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * @author Admin
 * @since 11.08.15
 * lastModify on 29.02.16
 */
public class ControllerMain implements Initializable {
    @FXML
    public VBox vBoxForSettings;
    public Button btnFill;
    public TableView<TableViewController.DoubleData> table;
    private static final String[] taskNames = {
            /*"Полный перебор",*/
            "МВиГ классический (начиная по левым ветвям)",
            "МВиГ классический (с учетом потерянных ветвей)",
            "МВиГ параллельный (с возвратом)",
            "МВиГ параллельный",
            "МВиГ улучшенный (без разрывов)",
            "МВиГ улучшенный (с разрывами)",
            "Ближнего соседа",
            "Дальнего соседа",
            "МВиГ классический (с суммой)",
            /*"МВиГ классический (по каждому элементу)",*/ // За повторением результата модульного метода
            "МВиГ классический (по каждому элементу с суммой)",
            "МВиГ классический (по каждому элементу со смежными)",
            "МВиГ классический (по каждому элементу со смежными с суммой)"
    };

    private static final String[] methodsForParallelNames = {
            "МВиГ классический (с учетом потерянных ветвей)",
//            "МВиГ классический",
            /*"МВиГ классический (без возвратов)",*/  // За повторением результата модульного метода
//            "МВиГ улучшенный (без разрывов)",
//            "МВиГ улучшенный (с разрывами)",
//            "Ближнего соседа",
//            "Дальнего соседа",
            "МВиГ классический (с суммой)",
            /*"МВиГ классический (по каждому элементу)",*/ // За повторением результата модульного метода
            "МВиГ классический (по каждому элементу с суммой)",
            "МВиГ классический (по каждому элементу со смежными)",
            "МВиГ классический (по каждому элементу со смежными с суммой)"
    };
    public Button btnSolve;
    public Button btnMultiSolve;
    private GridPane gridPane;
    public TextField matrixSize;

    public TextArea outputText;
    public Label label1;
    public RadioButton RadioButtonRandom;
    public RadioButton RadioButtonConstant;
    public MenuItem btnSave;
    public MenuItem btnLoad;
    public TextField constantValue;
    public TextField multiSolveCount;
    public MenuItem btnOldProgram;
    public MenuItem btnMultiLoad;
    public MenuItem btnMultiSave;
    public CheckBox cbMultiSolveFromFile;
    public Label multiSolveStr;
    public HBox hBoxForCheckBoxesForParallel;
    public CheckBox cbMultiSolvePercent;
    //public ListView<Task> listViewForCheckBoxes;
    //checkList

    private ObservableList<Task> tasks;
    private ObservableList<Task> tasksParallel;
    private ListView<Task> listViewForCheckBoxes;

    public HBox hBoxForCheckBoxes;
    public Button btnCheckAll;
    public Button btnUncheckAll;
    public Button btnMoveUp;
    public Button btnMoveDown;
    private static TableViewController tableViewController;
    private FileController fileController;
    @SuppressWarnings("unused")
    private boolean fireBtnFillBeforeBtnSolve; // Need to debug
    private ButtonMultiSolveActionPerformedForMoreThan12 newBtnLogicMore12;
    private ButtonMultiSolveActionPerformed newBtnLogicLess12;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tasks = FXCollections.observableArrayList();
        for (String name : taskNames) {
            tasks.add(new Task(name));
        }
        tasksParallel = FXCollections.observableArrayList();
        for (String name : methodsForParallelNames) {
            tasksParallel.add(new Task(name));
        }

        listViewForCheckBoxes = new ListView<>(tasks);
        ListView<Task> listViewForCheckBoxesParallel = new ListView<>(tasksParallel);

        Callback<Task, ObservableValue<Boolean>> callback = Task::selectedProperty;

        StringConverter<Task> stringConverter = new StringConverter<Task>() {
            @Override
            public String toString(Task object) {
                return object.getName();
            }

            @Override
            public Task fromString(String string) {
                return null;
            }
        };
        listViewForCheckBoxes.setCellFactory(CheckBoxListCell.forListView(callback, stringConverter));
        listViewForCheckBoxesParallel.setCellFactory(CheckBoxListCell.forListView(callback, stringConverter));
        hBoxForCheckBoxes.getChildren().add(listViewForCheckBoxes);
        hBoxForCheckBoxesForParallel.getChildren().add(listViewForCheckBoxesParallel);
        label1.setText("");
        outputText.setWrapText(true);
        tableViewController = new TableViewController(table, label1);
        fileController = new FileController();
//        btnCheckAll.fire();
//        tasks.get(0).setSelected(true);
//        tasks.get(2).setSelected(true);
//        tasks.get(2).setSelected(true);
        tasksParallel.get(0).setSelected(true);
        tasksParallel.get(1).setSelected(true);
//        tasks.get(4).setSelected(true);
//        tasks.get(5).setSelected(true);
        //tasksParallel.get(3).setSelected(true);
        //tasksParallel.get(4).setSelected(true);
//        tasks.get(8).setSelected(true);
        //cbMultiSolveFromFile.fire();

        btnCheckAll.fire();
        if ( !loadData(fileController.loadDataFromFile( true ) ) ) {
            matrixSize.setText("10");
            btnFill.fire();
        }
        //btnMultiLoad.fire();
//        matrixSize.setText("5");
//        btnFill.fire();
//        btnSolve.fire();
        //fireBtnFillBeforeBtnSolve = true;
    }

    public void handleButtonAction(ActionEvent actionEvent) {
        // Кнопка для выделения всех методов
        if (actionEvent.getSource() == btnCheckAll) {
            tasks.forEach(task -> task.setSelected(true));
            return;
        }

        // Кнопка для снятия выделения со всех методов
        if (actionEvent.getSource() == btnUncheckAll) {
            tasks.forEach(task -> task.setSelected(false));
            return;
        }

        // Кнопка для перемещения выбранного метода вверх по списку
        if (actionEvent.getSource() == btnMoveUp) {
            Task takeTask = listViewForCheckBoxes.getSelectionModel().getSelectedItem();
            int takeIdx = tasks.indexOf(takeTask);
            if (takeIdx < 1) {
                return;
            }
            int sendIdx = takeIdx - 1;
            Task sendTask = tasks.get(sendIdx);

            tasks.set(sendIdx, takeTask);
            tasks.set(takeIdx, sendTask);

            listViewForCheckBoxes.setItems(tasks);
            listViewForCheckBoxes.getSelectionModel().select(sendIdx);
            return;
        }

        // Кнопка для перемещения выбранного метода вниз по списку
        if (actionEvent.getSource() == btnMoveDown) {
            Task takeTask = listViewForCheckBoxes.getSelectionModel().getSelectedItem();
            int takeIdx = tasks.indexOf(takeTask);
            if (takeIdx > tasks.size() - 2 || takeIdx == -1) {
                return;
            }
            int sendIdx = takeIdx + 1;
            Task sendTask = tasks.get(sendIdx);

            tasks.set(sendIdx, takeTask);
            tasks.set(takeIdx, sendTask);

            listViewForCheckBoxes.setItems(tasks);
            listViewForCheckBoxes.getSelectionModel().select(sendIdx);
            return;
        }

        // Кнопка для заполнения матрицы заданной размерностью и заданным способом
        if (actionEvent.getSource() == btnFill) {
            String str = matrixSize.textProperty().getValue();
            String str2 = constantValue.textProperty().getValue();
            int matrixSizeInt = Integer.parseInt(str);
            int constantValueInt = Integer.parseInt(str2);
            if (matrixSizeInt > 0) {
                tableViewController.setTableViewData(
                        RadioButtonRandom.isSelected(), matrixSizeInt, constantValueInt);
            }
            return;
        }

        // Кнопка для выполнения вычислений
        if (actionEvent.getSource() == btnSolve) {
            if (fireBtnFillBeforeBtnSolve) btnFill.fire();
            //
            //      Load data from file todo Load file ?
            /*List<String> list = fileController.loadOneFile(new File("C:/data/4/4
            2015-09-26 T 18-54-49-167000000.txt"));
            double[][] newValues = fileController.parseStringFromFile(list);
            tableViewController.setTableViewData(newValues);*/
            //
            //

            // Получение массива строк методов в заданном пользователем порядке
            ArrayList<String> methodsOrder = new ArrayList<>();
            ArrayList<String> methodsOrderParallel = new ArrayList<>();
            if (tableViewController.getMatrixSize() < 13) {
                methodsOrder.add("Полный перебор");
            }
            methodsOrder.addAll(tasks.stream().filter(Task::isSelected).map(Task::getName).collect(Collectors.toList()));

            methodsOrderParallel.addAll(tasksParallel.stream().filter(Task::isSelected).map(Task::getName).collect(Collectors.toList()));

            if (methodsOrder.size() == 0) {
                // Тернарный оператор не работает
                outputText.setText((!Objects.equals(outputText.getText(), "") ? "\n\n" : "") +
                        "Не выбранно ни одного метода."); // outputText.getText() +
                return;
            }
            // Считывание данных из TableView в массив matrix
            double[][] matrix = getMatrix();

            // Save last solved matrix
            fileController.saveInformationFromFormToTextFile(getMatrix(), true);

//            boolean correctOutput = false; // TODO Safe delete
            // Вывод результата выполнения вычислений
            try {
                outputText.setText(new ButtonSolveActionPerformed().btnSolveActionPerformed(
                        matrix, methodsOrder, methodsOrderParallel));
//                correctOutput = true;
            } catch (Exception e) {
                outputText.setText(e.getLocalizedMessage());
                e.printStackTrace();
            } catch (Error e) {
                outputText.setText("Недостаточно памяти виртуальной машины Java для выполнения вычисления.");
                e.printStackTrace();
            }
            return;
        }

        // Кнопка для выполнения серии вычислений
        if (actionEvent.getSource() == btnMultiSolve) {
            String str = multiSolveCount.textProperty().getValue();
            int multiSolveCountInt = Integer.parseInt(str);
            // Получение массива строк методов в заданном пользователем порядке
            ArrayList<String> methodsOrder = getMethodOrder();
            ArrayList<String> methodsOrderParallel = new ArrayList<>();

            if (methodsOrder.size() == 0) {
                // Тернарный оператор не работает
                outputText.setText((!Objects.equals(outputText.getText(), "") ? "\n\n" : "") + "Не выбранно ни одного метода."); // outputText.getText() +
                return;
            }

            methodsOrderParallel.addAll(tasksParallel.stream().filter(Task::isSelected).map(Task::getName).collect(Collectors.toList()));

            String strTemp = matrixSize.textProperty().getValue();
            int matrixSizeInt = Integer.parseInt(strTemp);
            boolean isConstant = RadioButtonConstant.isSelected();
//            if (matrixSizeInt > 12) {
//                outputText.setText((outputText.getText() != "" ? "\n\n" : "") + "Размерность матриц задана больше 12. Полный перебор не будет выполнен за адекватное время.");
//                return;
//            }
            if (isConstant) {
                outputText.setText((!Objects.equals(outputText.getText(), "") ? "\n\n" : "") +
                        "Методом заполнения выбрана константа, что делает серию решений бессмысленной.");
                return;
            }

            // Вывод результата выполнения вычислений
            if (matrixSizeInt < 13) {
                if (cbMultiSolveFromFile.isSelected()) {
                    multiSolveCountInt = Integer.parseInt(newBtnLogicLess12.getButtonLogic().
                            getList().get(0).get(0));
                    outputText.setText(newBtnLogicLess12.btnMultiSolveActionPerformed(
                            multiSolveCountInt, methodsOrder, methodsOrderParallel, this));
                } else {
                    outputText.setText(new ButtonMultiSolveActionPerformed(
                            new ButtonLogic()).btnMultiSolveActionPerformed(
                            multiSolveCountInt, methodsOrder, methodsOrderParallel, this));
                }
            } else {
                if (cbMultiSolveFromFile.isSelected()) {
                    multiSolveCountInt = Integer.parseInt(newBtnLogicMore12.getButtonLogic().
                            getList().get(0).get(0));
                    outputText.setText(newBtnLogicMore12.btnMultiSolveActionPerformedForMoreThan12(
                            multiSolveCountInt, methodsOrder, methodsOrderParallel, this));
                } else {
                    if (cbMultiSolvePercent.isSelected()) {
                        outputText.setText(new ButtonMultiSolveActionPerformedForMoreThan12InPercent(
                                new ButtonLogic()).btnMultiSolveActionPerformedForMoreThan12(
                                multiSolveCountInt, methodsOrder, methodsOrderParallel, this));
                    } else {
                        outputText.setText(new ButtonMultiSolveActionPerformedForMoreThan12(
                                new ButtonLogic()).btnMultiSolveActionPerformedForMoreThan12(
                                multiSolveCountInt, methodsOrder, methodsOrderParallel, this));
                    }
                }
            }
            return;
        }

        // Кнопка меню для записи текущей матрицы в файл
        if (actionEvent.getSource() == btnSave) {
            fileController.saveInformationFromFormToTextFile(getMatrix());
            return;
        }

        // Кнопка меню для загрузки новой матрицы в программу
        if (actionEvent.getSource() == btnLoad) {
            List<String> list = fileController.loadDataFromFile();
            loadData(list);
            return;
        }

        // Кнопка меню для запуска старой программы
        if (actionEvent.getSource() == btnOldProgram) {
            //MainScreen.getStage().hide();
            //MainScreen.getStage().close();
            new ParentFrame(getMatrix()).setVisible(true);
            return;
        }

        // Кнопка меню для записи текущей матрицы в файл
        if (actionEvent.getSource() == btnMultiSave) {
            fileController.saveMultiInformationFromFormToTextFile(this);
            return;
        }

        // Кнопка меню для загрузки новой матрицы в программу
        if (actionEvent.getSource() == btnMultiLoad) {
            List<List<String>> list = fileController.loadMultiDataFromFile();
            if (list == null || list.isEmpty()) return;

            newBtnLogicLess12 = new ButtonMultiSolveActionPerformed(new ButtonLogicForMultiLoad());
            newBtnLogicMore12 = new ButtonMultiSolveActionPerformedForMoreThan12(new ButtonLogicForMultiLoad());
            newBtnLogicLess12.getButtonLogic().setList(list);
            newBtnLogicMore12.getButtonLogic().setList(list);
            multiSolveStr.setText("list = " + list.size() + ", countIteration = " + list.get(0).get(0));
            //outputText.setText(newBtnLogic.btnMultiSolveActionPerformed(multiSolveCountInt, methodsOrder, this));
            //noinspection UnnecessaryReturnStatement
            return; // Need to not forget if add another button
        }
    }

    public double[][] getMatrix() {
        return tableViewController.getMatrix();
    }

    private boolean loadData(List<String> list) {
        if (list == null || list.isEmpty()) return false;
        double[][] newValues = fileController.parseStringFromFile(list);
        matrixSize.setText(newValues.length + "");
        //передать newValues в таблицу.......
        tableViewController.setTableViewData(newValues);
        return true;
    }

    public ArrayList<String> getMethodOrder() {
        // Получение массива строк методов в заданном пользователем порядке
        return tasks.stream().filter(task -> !(task.getName().equals("Полный перебор") && tableViewController.getMatrixSize() > 13)).filter(Task::isSelected).map(Task::getName).collect(Collectors.toCollection(ArrayList::new));
    }

    @SuppressWarnings("unused") // TODO Safe delete
    public void updateDisplay(int matricSize) {
        gridPane.getChildren().clear();
        gridPane.setHgap(0);
        gridPane.setVgap(0);

        //setTableSize(M0.length, M0.length, valueTable);

        for (int i = 0; i < matricSize; i++) {
            for (int j = 0; j < matricSize; j++) {
                TextField textField = new TextField();
                textField.setMaxWidth(30);
                if (i == j) {
                    textField.setText("-");
                } else {
                    textField.setText("" + j);
                }
                //Binding the fraction of the grid size to the width
                //and heightProperty of the child
                gridPane.add(textField, i, j);
                //gridPane.add(textField, i, j);
                //noinspection UnusedAssignment
                textField = null; // TODO Check for memory leak
            }
        }
    }

    public FileController getFileController() {
        return fileController;
    }

    public static class Task {
        private final ReadOnlyStringWrapper name = new ReadOnlyStringWrapper();
        private final BooleanProperty selected = new SimpleBooleanProperty(false);

        public Task(String name) {
            this.name.set(name);
        }

        public String getName() {
            return name.get();
        }

        /*public ReadOnlyStringProperty nameProperty() { // TODO Safe delete?
            return name.getReadOnlyProperty();
        }*/

        public BooleanProperty selectedProperty() {
            return selected;
        }

        public boolean isSelected() {
            return selected.get();
        }

        public void setSelected(boolean selected) {
            this.selected.set(selected);
        }
    }

    public static TableViewController getTableViewController() {
        return tableViewController;
    }
}
