package gui.newjavafx;

import gui.deltapackage.ParentFrame;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyStringProperty;
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
import java.util.ResourceBundle;

/**
 * Created by Admin on 11.08.15.
 */
@SuppressWarnings("all")
public class ControllerMain implements Initializable {
    @FXML
    public VBox vBoxForSettings;
    public Button btnFill;
    public TableView<TableViewController.DoubleData> table;
    private static final String[] taskNames = {
            /*"Полный перебор",*/
            "МВиГ классический (начиная по левым ветвям)",
            "МВиГ классический (с учетом потерянных ветвей)",
//            "МВиГ классический",
            "МВиГ параллельный (с возвратом)",
            "МВиГ параллельный",
            /*"МВиГ классический (без возвратов)",*/  // За повторением результата модульного метода
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
    public GridPane gridPane;
    public TextField matrixSize;

    public TextArea outputText;
    public Label label1;
    public RadioButton RadioButtonRandom;
    public RadioButton RadioButtonConstant;
    public MenuItem btnSave;
    public MenuItem btnLoad;
    public TextField constantValue;
    public TextField multiSolveCount;
    public MenuItem btnOldProgramm;
    public MenuItem btnMultiLoad;
    public MenuItem btnMultiSave;
    public CheckBox cbMultiSolveFromFile;
    public Label multiSolveStr;
    public HBox hBoxForCheckBoxesForParallel;
    //public ListView<Task> listViewForCheckBoxes;
    //checkList

    private ObservableList<Task> tasks;
    private ObservableList<Task> tasksParallel;
    public ListView<Task> listViewForCheckBoxes;
    public ListView<Task> listViewForCheckBoxesParallel;

    public HBox hBoxForCheckBoxes;
    public VBox vBoxForButtonBeforeCheckBoxes;
    public Button btnCheckAll;
    public Button btnUncheckAll;
    public Button btnMoveUp;
    public Button btnMoveDown;
    private static TableViewController tableViewController;
    private FileController fileController;
    private ButtonLogic.ButtonLogicForMultiLoad newBtnLogic;
    private boolean fireBtnFillBeforeBtnSolve;

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
        listViewForCheckBoxesParallel = new ListView<>(tasksParallel);

        Callback<Task, ObservableValue<Boolean>> callback = new Callback<Task, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(Task param) {
                return param.selectedProperty();
            }
        };

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
        tasks.get(2).setSelected(true);
        tasksParallel.get(0).setSelected(true);
        tasksParallel.get(1).setSelected(true);
//        tasks.get(8).setSelected(true);
        //cbMultiSolveFromFile.fire();

        if ( !loadData(fileController.loadDataFromFile( true ) ) ) {
            matrixSize.setText("10");
            btnFill.fire();
        }
        //btnMultiLoad.fire();
//        matrixSize.setText("5");
//        btnFill.fire();
        //btnSolve.fire();
        //fireBtnFillBeforeBtnSolve = true;
    }

    public void handleButtonAction(ActionEvent actionEvent) {
        // Кнопка для выделения всех методов
        if (actionEvent.getSource() == btnCheckAll) {
            tasks.forEach(task -> {
                task.setSelected(true);
            });
            return;
        }

        // Кнопка для снятия выделения со всех методов
        if (actionEvent.getSource() == btnUncheckAll) {
            tasks.forEach(task -> {
                task.setSelected(false);
            });
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
                tableViewController.setTableViewData(RadioButtonRandom.isSelected(), matrixSizeInt, constantValueInt);
            }
            return;
        }

        // Кнопка для выполнения вычислений
        if (actionEvent.getSource() == btnSolve) {
            if (fireBtnFillBeforeBtnSolve) btnFill.fire();
            //
            //      Load data from file todo Load file ?
            /*List<String> list = fileController.loadOneFile(new File("C:/data/4/4  2015-09-26 T 18-54-49-167000000.txt"));
            double[][] newValues = fileController.parseStringFromFile(list);
            tableViewController.setTableViewData(newValues);*/
            //
            //

            // Получение массива строк методов в заданном пользователем порядке
            ArrayList<String> methodsOrder = new ArrayList<String>();
            ArrayList<String> methodsOrderParallel = new ArrayList<String>();
            if (tableViewController.getMatrixSize() < 13) {
                methodsOrder.add("Полный перебор");
            }
            for (Task task : tasks) {
                if (task.isSelected()) {
                    methodsOrder.add(task.getName());
                }
            }

            for (Task task : tasksParallel) {
                if (task.isSelected()) {
                    methodsOrderParallel.add(task.getName());
                }
            }

            if (methodsOrder.size() == 0) {
                // Тернарный оператор не работает
                outputText.setText((outputText.getText() != "" ? "\n\n" : "") + "Не выбранно ни одного метода."); // outputText.getText() +
                return;
            }
            // Считывание данных из TableView в массив matrix
            double[][] matrix = getMatrix();

            // Save last solved matrix
            fileController.saveInformationFromFormToTextFile(getMatrix(), true);

            boolean correctOutput = false;
            // Вывод результата выполнения вычислений
            try {
                outputText.setText(new ButtonLogic().btnSolveActionPerformed(matrix, methodsOrder, methodsOrderParallel));
                correctOutput = true;
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
                outputText.setText((outputText.getText() != "" ? "\n\n" : "") + "Не выбранно ни одного метода."); // outputText.getText() +
                return;
            }

            for (Task task : tasksParallel) {
                if (task.isSelected()) {
                    methodsOrderParallel.add(task.getName());
                }
            }

            String strTemp = matrixSize.textProperty().getValue();
            int matrixSizeInt = Integer.parseInt(strTemp);
            boolean isConstant = RadioButtonConstant.isSelected();
//            if (matrixSizeInt > 12) {
//                outputText.setText((outputText.getText() != "" ? "\n\n" : "") + "Размерность матриц задана больше 12. Полный перебор не будет выполнен за адекватное время.");
//                return;
//            }
            if (isConstant) {
                outputText.setText((outputText.getText() != "" ? "\n\n" : "") + "Методом заполнения выбрана константа, что делает серию решений бессмысленной.");
                return;
            }

            // Вывод результата выполнения вычислений
            if (matrixSizeInt < 13) {
                if (cbMultiSolveFromFile.isSelected()) {
                    multiSolveCountInt = Integer.parseInt(newBtnLogic.getList().get(0).get(0));
                    outputText.setText(newBtnLogic.btnMultiSolveActionPerformed(multiSolveCountInt,
                            methodsOrder, methodsOrderParallel, this));
                } else {
                    outputText.setText(new ButtonLogic().btnMultiSolveActionPerformed(multiSolveCountInt,
                            methodsOrder, methodsOrderParallel, this));
                }
            } else {
                if (cbMultiSolveFromFile.isSelected()) {
                    multiSolveCountInt = Integer.parseInt(newBtnLogic.getList().get(0).get(0));
                    outputText.setText(newBtnLogic.btnMultiSolveActionPerformedForMoreThan12(multiSolveCountInt,
                            methodsOrder, methodsOrderParallel, this));
                } else {
                    outputText.setText(new ButtonLogic().btnMultiSolveActionPerformedForMoreThan12(multiSolveCountInt,
                            methodsOrder, methodsOrderParallel, this));
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
        if (actionEvent.getSource() == btnOldProgramm) {
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
            List<List<String>> list = fileController.loadMultiDataFromFile(this);
            if (list == null || list.isEmpty()) return;
            newBtnLogic = new ButtonLogic.ButtonLogicForMultiLoad();
            newBtnLogic.setList(list);
            multiSolveStr.setText("list = " + list.size() + ", countIteration = " + list.get(0).get(0));
            //outputText.setText(newBtnLogic.btnMultiSolveActionPerformed(multiSolveCountInt, methodsOrder, this));
            return;
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

    ArrayList<String> getMethodOrder() {
        // Получение массива строк методов в заданном пользователем порядке
        ArrayList<String> methodsOrder = new ArrayList<String>();
        for (Task task : tasks) {
            if (!(task.getName().equals("Полный перебор") && tableViewController.getMatrixSize() > 13)) {
                if (task.isSelected()) {
                    methodsOrder.add(task.getName());
                }
            }
        }
        return methodsOrder;
    }

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
                textField = null;
            }
        }
    }

    public FileController getFileController() {
        return fileController;
    }

    public static class Task {
        private ReadOnlyStringWrapper name = new ReadOnlyStringWrapper();
        private BooleanProperty selected = new SimpleBooleanProperty(false);

        public Task(String name) {
            this.name.set(name);
        }

        public String getName() {
            return name.get();
        }

        public ReadOnlyStringProperty nameProperty() {
            return name.getReadOnlyProperty();
        }

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

    public ObservableList<Task> getTasks() {
        return tasks;
    }

    public static TableViewController getTableViewController() {
        return tableViewController;
    }
}
