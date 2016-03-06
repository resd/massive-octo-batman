package gui.newjavafx;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import javafx.util.StringConverter;

import java.util.Random;

/**
 * Created by Admin on 28.08.15
 */
//@SuppressWarnings({"all"})
public class TableViewController {

    private TableView<DoubleData> table;
    private Label label1;
    private double[][] matrix;

    public TableViewController(TableView<DoubleData> table, Label label1) {
        this.table = table;
        this.label1 = label1;
        initialize();
    }

    public void initialize() {
        table.setRowFactory(tv -> new TableRow<DoubleData>() {
            @Override
            public void updateItem(DoubleData num, boolean empty) {
                super.updateItem(num, empty);
                label1.setText("Рядок = " + (table.getFocusModel().getFocusedCell().getRow() + 1) + ", Столбец = " + (table.getFocusModel().getFocusedCell().getColumn() + 1));
            }
        });
        //hBoxForCheckBoxes.setPadding(new Insets(10));
        //vBoxForSettings.getChildren().add(hBoxForCheckBoxes);
        //updateDisplay(10);

        //table.requestFocus();
        //table.focusModelProperty().get().focusBelowCell();

        //setTableViewData(3);
    }

    void setTableViewData(boolean random, int size, double constant) {

        table.getItems().clear();
        table.getColumns().clear();

        // Подготовка данных
        fillTableRandomValues(random, size, constant);

        setTableViewDataIn(size);
    }

    void setTableViewData(double[][] m) {

        table.getItems().clear();
        table.getColumns().clear();

        // Подготовка данных
        setTableValue(m);

        setTableViewDataIn(m.length);
    }

    private void setTableViewDataIn(int size) {
        for (int i = 0; i < size; i++) {
            table.getItems().add(new DoubleData((double) i));
        }

        table.setEditable(true);
        table.getSelectionModel().setCellSelectionEnabled(true);

        Callback<TableColumn<DoubleData, String>, TableCell<DoubleData, String>> cellFactorySecond =
                new Callback<TableColumn<DoubleData, String>, TableCell<DoubleData, String>>() {
                    @Override
                    public TextFieldTableCell<DoubleData, String> call(TableColumn<DoubleData, String> p) {
                        TextFieldTableCell<DoubleData, String> cell = new TextFieldTableCell<DoubleData, String>() {
                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                int rowIndex = getIndex();
                                int colIndex = this.getTableView().getVisibleLeafIndex(getTableColumn());
                                if (!empty) {
                                    if (rowIndex != colIndex) {
                                        if (item == null) {
                                            setItem(String.valueOf(matrix[rowIndex][colIndex]));
                                            setText(String.valueOf(matrix[rowIndex][colIndex]));
                                        } else if (!item.equals(String.valueOf(matrix[rowIndex][colIndex]))) {
                                            Double newItem = Double.parseDouble(item);
                                            setItem(item);
                                            matrix[rowIndex][colIndex] = newItem;
                                        }
                                    } else {
                                        setItem("-");
                                        setText("-");
                                    }
                                }
                            }
                        };
                        cell.setConverter(stringConverter);
                        return cell;
                    }
                };

        // Заполнение таблицы данными
        for (int i = 0; i < size; i++) {
            TableColumn<DoubleData, String> col = new TableColumn<>("" + (i + 1));

            col.setCellFactory(cellFactorySecond);

            col.setMinWidth(50);
            col.setSortable(false);
            table.getColumns().add(col);
        }
    }

    // Подготовка данных
    private void fillTableRandomValues(boolean random, int size, double constant) {
        matrix = new double[size][size];

        if (!random) {

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (i != j)
                        matrix[i][j] = constant;
                    else
                        matrix[i][j] = 0d;
                }

            }

        } else {

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (i != j) {
                        Random r = new Random();
                        matrix[i][j] = (double) r.nextInt(200);
                    } else
                        matrix[i][j] = 0d;
                }
            }

        } // end of else
    }

    private void setTableValue(double[][] matrix) throws NumberFormatException {
        this.matrix = matrix;
    }

    public static class DoubleData {
        private SimpleDoubleProperty value;

        public DoubleData(Double value) {
            this.value = new SimpleDoubleProperty(value);
        }


        public DoubleData(SimpleDoubleProperty value) {
            this.value = value;
        }

        public double getValue() {
            return value.get();
        }

        public SimpleDoubleProperty valueProperty() {
            return value;
        }

        public void setValue(double value) {
            this.value.set(value);
        }

        @Override
        public String toString() {
            return String.valueOf(getValue());
        }
    }

    private StringConverter stringConverter = new StringConverter() {
        @Override
        public String toString(Object t) {
                                /*if (t == null || t.toString().equals("-1.0"))
                                    return "-";
                                else*/
            if (t == null || t.toString().equals("-"))
                return "-";
            else
                return t.toString();
        }

        @Override
        public Object fromString(String string) {
                                /*if (string.equals("-"))
                                    return -1.0d;
                                else
                                    return Double.parseDouble(string);*/
            return string;
        }
    };

    public double[][] getMatrix() {
        return matrix;
    }

    public int getMatrixSize() {
        return matrix.length;
    }

}
