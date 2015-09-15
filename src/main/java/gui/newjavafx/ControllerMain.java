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
            "Полный перебор",
            "МВиГ классический (модульный)",
            "МВиГ классический",
            "МВиГ классический (без возвратов)",
            "МВиГ улучшенный (без разрывов)",
            "МВиГ улучшенный (с разрывами)",
            "Ближнего соседа",
            "Дальнего соседа",
            "МВиГ классический (с суммой)",
            "МВиГ классический (по каждому элементу)",
            /*"МВиГ классический (по каждому элементу с суммой)",*/
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
    public MenuItem btnExit;
    public MenuItem btnOldProgramm;
    //public ListView<Task> listViewForCheckBoxes;
    //checkList

    private ObservableList<Task> tasks;
    public ListView<Task> listViewForCheckBoxes;

    public HBox hBoxForCheckBoxes;
    public VBox vBoxForButtonBeforeCheckBoxes;
    public Button btnCheckAll;
    public Button btnUncheckAll;
    public Button btnMoveUp;
    public Button btnMoveDown;
    private static TableViewController tableViewController;
    private FileController fileController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tasks = FXCollections.observableArrayList();
        for (String name : taskNames) {
            tasks.add(new Task(name));
        }

        listViewForCheckBoxes = new ListView<>(tasks);

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
        hBoxForCheckBoxes.getChildren().add(listViewForCheckBoxes);
        label1.setText("");
        outputText.setWrapText(true);
        tableViewController = new TableViewController(table, label1);
        fileController = new FileController();
        //btnSolve.fire();
        btnCheckAll.fire();
        matrixSize.setText("10");
        btnFill.fire();
        //btnSolve.fire();
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
            if (matrixSizeInt > 0) {// todo Запилить поле для константы и вставить значение сюда ->
                tableViewController.setTableViewData(RadioButtonRandom.isSelected(), matrixSizeInt, constantValueInt);
            }
            return;
        }

        // Кнопка для выполнения вычислений
        if (actionEvent.getSource() == btnSolve) {
            // Получение массива строк методов в заданном пользователем порядке
            ArrayList<String> methodsOrder = new ArrayList<String>();
            for (Task task : tasks) {
                if ( !( task.getName().equals("Полный перебор") && tableViewController.getDataObservableList().size() > 13) ) {
                    if (task.isSelected()) {
                        methodsOrder.add(task.getName());
                    }
                }
            }
            if (methodsOrder.size() == 0) {
                // Тернарный оператор не работает
                outputText.setText(( outputText.getText() != "" ? "\n\n" : "" ) + "Не выбранно ни одного метода."); // outputText.getText() +
                return;
            }
            // Считывание данных из TableView в массив matrix
            double[][] matrix = getMatrix();

            // Вывод результата выполнения вычислений
            outputText.setText(new ButtonLogic().btnSolveActionPerformed(matrix, methodsOrder));
            return;
        }

        // Кнопка для выполнения серии вычислений
        if (actionEvent.getSource() == btnMultiSolve) {
            String str = multiSolveCount.textProperty().getValue();
            int multiSolveCountInt = Integer.parseInt(str);

            // Получение массива строк методов в заданном пользователем порядке
            ArrayList<String> methodsOrder = new ArrayList<String>();
            for (Task task : tasks) {
                if ( !( task.getName().equals("Полный перебор") && tableViewController.getDataObservableList().size() > 13) ) {
                    if (task.isSelected()) {
                        methodsOrder.add(task.getName());
                    }
                }
            }
            if (methodsOrder.size() == 0) {
                // Тернарный оператор не работает
                outputText.setText(( outputText.getText() != "" ? "\n\n" : "" ) + "Не выбранно ни одного метода."); // outputText.getText() +
                return;
            }

            // Вывод результата выполнения вычислений
            outputText.setText(new ButtonLogic().jButton2ActionPerformed(multiSolveCountInt, methodsOrder, this));
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
            if (list.isEmpty()) return;
            double[][] newValues = fileController.parseStringFromFile(list);
            matrixSize.setText(newValues.length + "");
            //передать newValues в таблицу.......
            tableViewController.setTableViewData(newValues);
            return;
        }

        // Кнопка меню для выхода из программы
        if (actionEvent.getSource() == btnExit) {
            MainScreen.getStage().close();
            return;
        }

        // Кнопка меню для запуска старой программы
        if (actionEvent.getSource() == btnOldProgramm) {
            //MainScreen.getStage().hide();
            //MainScreen.getStage().close();
            new ParentFrame(getMatrix()).setVisible(true);
            return;
        }
    }

    public double[][] getMatrix() {
        ObservableList<TableViewController.DataColumn<TableViewController.DoubleData>> data = tableViewController.getDataObservableList();
        int size = data.size();
        double[][] matrix = new double[size][size];
        for (int i = 0; i < size; i++) {
            TableViewController.DataColumn<TableViewController.DoubleData> colData = data.get(i);
            for (int j = 0; j < size; j++) {
                if (i != j) {
                    matrix[i][j] = (colData.getData(j).getValue());
                } else {
                    matrix[i][j] = Double.POSITIVE_INFINITY;;
                }

            }
        }
        return matrix;
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

}
