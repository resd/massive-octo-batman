package gui.newjavafx;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import javafx.util.StringConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by Admin on 28.08.15.
 */
@SuppressWarnings("all")
public class TableViewController {

    private TableView<DoubleData> table;
    private Label label1;
    private ObservableList<DataColumn<DoubleData>> dataObservableList;

    public TableViewController(TableView<DoubleData> table, Label label1) {
        this.table = table;
        this.label1 = label1;
        initialize();
    }

    public void initialize() {
        table.setRowFactory(tv -> new TableRow<DoubleData>() {
            private Tooltip tooltip = new Tooltip();

            @Override
            public void updateItem(DoubleData num, boolean empty) {
                super.updateItem(num, empty);
                label1.setText("Рядок = " + ( table.getFocusModel().getFocusedCell().getRow() + 1 ) + ", Столбец = " + ( table.getFocusModel().getFocusedCell().getColumn() + 1 ));
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
            table.getItems().add(new DoubleData(Double.valueOf(i)));
        }

        table.setEditable(true);
        table.getSelectionModel().setCellSelectionEnabled(true);
        /*Callback<TableColumn<Double, String>, TableCell<Double, String>> cellFactory
                = new Callback<TableColumn<Double, String>, TableCell<Double, String>>() {

            @Override
            public TableCell call(TableColumn p) {
                return new TextFieldTableCell(new StringConverter() {
                    @Override
                    public String toString(Object t) {
                        if (t == null || t.toString().equals("-1.0"))
                            return "-";
                        else
                            return t.toString();
                    }

                    @Override
                    public Object fromString(String string) {
                        if (string.equals("-"))
                            return -1.0d;
                        else
                            return Double.parseDouble(string);
                        //return string;
                    }
                });
            }
        };*/

        Callback<TableColumn<DoubleData, String>, TableCell<DoubleData, String>> cellFactorySecond =
                new Callback<TableColumn<DoubleData, String>, TableCell<DoubleData, String>>() {
                    public TextFieldTableCell<DoubleData, String> call(TableColumn<DoubleData, String> p) {
                        TextFieldTableCell<DoubleData, String> cell = new TextFieldTableCell<DoubleData, String>() {
                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                //setText((item == null || empty) ? null : item.toString());
                                //setGraphic(null);
                                int rowIndex = getIndex();
                                int colIndex = this.getTableView().getVisibleLeafIndex(getTableColumn());
                                if (!empty) {
                                    //setText("Row = " + rowIndex + ", Col = " + colIndex);
                                    if (rowIndex != colIndex) {
                                        if (item == null) {
                                            setItem(String.valueOf(dataObservableList.get(colIndex).getData(rowIndex)));
                                            setText(String.valueOf(dataObservableList.get(colIndex).getData(rowIndex)));
                                        } else if (!item.equals(String.valueOf(dataObservableList.get(colIndex).getData(rowIndex)))) {
                                            Double newItem = Double.parseDouble(item);
                                            setItem(item);
                                            dataObservableList.get(colIndex).setData(rowIndex, new DoubleData(newItem));
                                        }
                                    } else {
                                        setItem("-");
                                        setText("-");
                                    }
                                }
                                //setIte
                            }
                        };
                        cell.setConverter(stringConverter);
                        return cell;
                    }
                };

        // Заполнение таблицы данными
        for (int i = 0; i < size; i++) {
            TableColumn<DoubleData, String> col = new TableColumn<>("" + ( i + 1 ));
            final int colIndex = i;

            col.setOnEditStart(new EventHandler<TableColumn.CellEditEvent<DoubleData, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<DoubleData, String> event) {
                    //rowIndexOutter = event.getTablePosition().getRow();
                }
            });

            col.setCellFactory(cellFactorySecond);
            //col.setCellFactory(TextFieldTableCell.<DoubleData>forTableColumn());
            /*col.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<DoubleData, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<DoubleData, String> t) {
                    ((DoubleData) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setValue(Double.parseDouble(t.getNewValue()));
                }
            });*/

            //col.setCellValueFactory(cellFactory);

            /*Label firstNameLabel = new Label("First Name" + i);
            firstNameLabel.setTooltip(new Tooltip("This column shows the first name" + i));
            col.setGraphic(firstNameLabel);*/

            col.setMinWidth(50);
            col.setSortable(false);
            //double a = table.getFixedCellSize();
            //System.out.println(a);
            table.getColumns().add(col);
        }
        TableColumn<DoubleData, String> uslessTestVarieble = new TableColumn<>("Str" + 3);
        uslessTestVarieble.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DoubleData, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DoubleData, String> cellData) {
                int rowIndex = (int) cellData.getValue().getValue();
                //System.out.println(colIndex);
                //System.out.println("rowIndex = " + rowIndex);
                //System.out.println("rowIndexOutter = " + rowIndexOutter);
                //System.out.println("");
                DoubleData doubleData = dataObservableList.get(rowIndex).getData(3);
                //DataColumn<DoubleData> dataColumn = data.get(colIndex);
                //return new ReadOnlyStringWrapper(String.valueOf(dataColumn.getData((int) rowIndex.getValue())));
                return new SimpleStringProperty(String.valueOf(doubleData.getValue()));
            }
        });
    }

    // Подготовка данных
    private void fillTableRandomValues(boolean random, int size, double constant) {
        List<DataColumn<DoubleData>> data = new ArrayList<>(size);
        dataObservableList = FXCollections.<DataColumn<DoubleData>>observableList(data);

        if (!random) {

            for (int i = 0; i < size; i++) {
                DoubleData[] colData = new DoubleData[size];
                for (int j = 0; j < size; j++) {
                    if (i != j)
                        colData[j] = new DoubleData(constant);
                    else
                        colData[j] = new DoubleData(0D);
                }
                DataColumn<DoubleData> dataColumn = new DataColumn<>(colData);
                dataObservableList.add(dataColumn);
            }

        } else {

            for (int i = 0; i < size; i++) {
                DoubleData[] colData = new DoubleData[size];
                for (int j = 0; j < size; j++) {
                    if (i != j) {
                        Random r = new Random();
                        colData[j] = new DoubleData((double) r.nextInt(99));
                    } else
                        colData[j] = new DoubleData(0D);
                }
                DataColumn<DoubleData> dataColumn = new DataColumn<>(colData);
                dataObservableList.add(dataColumn);
            }

        } // end of else
    }

    private void setTableValue(double[][] M0) throws NumberFormatException {
        List<TableViewController.DataColumn<TableViewController.DoubleData>> dataColumnArrayList = new ArrayList<>();
        ObservableList<TableViewController.DataColumn<TableViewController.DoubleData>> data
                = FXCollections.<TableViewController.DataColumn<TableViewController.DoubleData>>observableList(dataColumnArrayList);

        //setTablesSize(M0.length);
        //MatrixSizeSpinner.setValue(M0.length);
        int size = M0.length;

        double sumRow[] = new double[M0.length];
        //DefaultTableModel model = (DefaultTableModel) valueTable.getModel();
        //setTableSize(M0.length, M0.length, valueTable);
        for (int i = 0; i < size; i++) {
            TableViewController.DoubleData[] colData = new TableViewController.DoubleData[size];
            for (int j = 0; j < size; j++) {
                if (i != j)
                    colData[j] = new TableViewController.DoubleData(M0[i][j]);
                else
                    colData[j] = new TableViewController.DoubleData(0D);
            }
            TableViewController.DataColumn<TableViewController.DoubleData> dataColumn = new TableViewController.DataColumn<>(colData);
            data.add(dataColumn);
        }
        dataObservableList = data;
        //model.fireTableDataChanged();
    }

    public static class DataColumn<T> {
        private T[] data;

        public DataColumn(T[] data) {
            this.data = Arrays.copyOf(data, data.length);
        }

        public T getData(int index) {
            return data[index];
        }

        public void setData(int index, T data) {
            this.data[index] = data;
        }

        public int getSize() {
            return data.length;
        }
    }

    public static class DoubleData {
        private SimpleDoubleProperty value;

        public DoubleData(Double value) {
            this.value = new SimpleDoubleProperty(value);
        }

        public DoubleData(String value) {
            this.value = new SimpleDoubleProperty(Double.parseDouble(value));
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

    public ObservableList<DataColumn<DoubleData>> getDataObservableList() {
        return dataObservableList;
    }

    public void setDataObservableList(ObservableList<DataColumn<DoubleData>> dataObservableList) {
        this.dataObservableList = dataObservableList;
    }
}
