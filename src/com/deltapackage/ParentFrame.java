package com.deltapackage;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import common.Work1Main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Arrays;
import java.util.Random;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;


/**
 *
 * @author Евгений
 */
public class ParentFrame extends JFrame {

    public void setMessage(String msg) {
        messagesTextPane.setText(messagesTextPane.getText() + msg);
    }

    /**
     * Creates new form DeltaFrame
     */
    public ParentFrame() {
        initComponents();
        setNormalGrid(valueTable);
        setTablesSize(10);
        fillValues();
    }

    private void setNormalGrid(JTable table) {
        table.setShowGrid(true);
        table.setGridColor(Color.black);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        valueTable = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        matrixColumn1 = new javax.swing.JTable();
        fillTableWithConstantsCheckBox = new javax.swing.JCheckBox();
        constantSpinner = new javax.swing.JSpinner();
        optionsPanel = new javax.swing.JPanel();
        ClearBtn = new javax.swing.JButton();
        btnSolve = new javax.swing.JButton();
        chkbxRandomValues = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        checkBoxConstants = new javax.swing.JCheckBox();
        jSpinner1 = new javax.swing.JSpinner();
        MatrixSizeSpinner = new javax.swing.JSpinner();
        FillBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        messagesTextPane = new javax.swing.JTextPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        exitMenu = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        parametersMenu = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setUndecorated(true);
        setPreferredSize(java.awt.Toolkit.getDefaultToolkit().getScreenSize());

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        valueTable.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        valueTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        valueTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        valueTable.setFillsViewportHeight(true);
        valueTable.setMaximumSize(new java.awt.Dimension(10000, 100000));
        valueTable.setRowHeight(20);
        valueTable.setRowSelectionAllowed(false);
        valueTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        valueTable.setTableHeader(null);
        valueTable.setUpdateSelectionOnSort(false);
        valueTable.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                valueTableMouseMoved(evt);
            }
        });
        jScrollPane2.setViewportView(valueTable);
        valueTable.getAccessibleContext().setAccessibleName("");
        valueTable.getAccessibleContext().setAccessibleDescription("");

        matrixColumn1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        matrixColumn1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Rows"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        matrixColumn1.setFillsViewportHeight(true);
        matrixColumn1.setRowHeight(20);
        matrixColumn1.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(matrixColumn1);

        fillTableWithConstantsCheckBox.setLabel("Заполнять константами");
        fillTableWithConstantsCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fillTableWithConstantsCheckBoxActionPerformed(evt);
            }
        });

        constantSpinner.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        ClearBtn.setText("Очистить");
        ClearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClearBtnActionPerformed(evt);
            }
        });

        btnSolve.setText("Вычислить");
        btnSolve.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSolveActionPerformed(evt);
            }
        });

        chkbxRandomValues.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        chkbxRandomValues.setText("Заполнять случайными значениями");
        chkbxRandomValues.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                chkbxRandomValuesMousePressed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel1.setText("Размерность матрицы");

        checkBoxConstants.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        checkBoxConstants.setText("Заполнять константами");
        checkBoxConstants.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkBoxConstantsMouseClicked(evt);
            }
        });

        jSpinner1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jSpinner1.setEnabled(false);

        MatrixSizeSpinner.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MatrixSizeSpinner.setModel(new javax.swing.SpinnerNumberModel(1, 1, 100, 1));
        MatrixSizeSpinner.setToolTipText("");

        FillBtn.setText("Заполнить");
        FillBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FillBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout optionsPanelLayout = new javax.swing.GroupLayout(optionsPanel);
        optionsPanel.setLayout(optionsPanelLayout);
        optionsPanelLayout.setHorizontalGroup(
            optionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(optionsPanelLayout.createSequentialGroup()
                .addGroup(optionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(chkbxRandomValues, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(optionsPanelLayout.createSequentialGroup()
                        .addComponent(checkBoxConstants)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(optionsPanelLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(MatrixSizeSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(ClearBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSolve, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(FillBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        optionsPanelLayout.setVerticalGroup(
            optionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, optionsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(optionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(MatrixSizeSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addComponent(chkbxRandomValues)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(optionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkBoxConstants)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(FillBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSolve)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ClearBtn))
        );

        messagesTextPane.setEditable(false);
        jScrollPane1.setViewportView(messagesTextPane);

        jMenu1.setText("Файл");
        jMenu1.add(jSeparator1);

        exitMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        exitMenu.setText("Выход");
        exitMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuActionPerformed(evt);
            }
        });
        jMenu1.add(exitMenu);

        jMenuItem1.setText("Сохранить");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Параметры");

        parametersMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_TAB, java.awt.event.InputEvent.CTRL_MASK));
        parametersMenu.setText("Настройки");
        parametersMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                parametersMenuActionPerformed(evt);
            }
        });
        jMenu2.add(parametersMenu);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 799, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(optionsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(optionsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(100, 100, 100))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(18, 18, 18)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        fillTableWithConstantsCheckBox.getAccessibleContext().setAccessibleName("FillConstants");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void FillBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FillBtnActionPerformed

        //устанавливаем размер матрицы
        JSpinner size = (JSpinner) MatrixSizeSpinner;
        int matrixSize = Integer.valueOf(size.getValue().toString());
        setTablesSize(matrixSize);

        //заполняем её
        fillValues();

    }

    private void fillValues() throws NumberFormatException {

        double[][] M1;

        if (chkbxRandomValues.isSelected()) {

            M1 = FillTableRandomValues(true);

        } else if (checkBoxConstants.isSelected()) {

            M1 = FillTableRandomValues(false);

        } else {

            // Входной массив
            double[][] M0 = {
                {0, 0, 83, 9, 30, 6, 50},
                {0, 0, 66, 37, 17, 12, 26},
                {29, 1, 0, 19, 0, 12, 5},
                {32, 83, 66, 0, 49, 0, 80},
                {3, 21, 56, 7, 0, 0, 28},
                {0, 85, 8, 42, 89, 0, 0},
                {18, 0, 0, 0, 58, 13, 0}
            };

            M1 = M0;

        }

        setTableValue(M1);

    }//GEN-LAST:event_FillBtnActionPerformed

    private void setTableValue(double[][] M0) throws NumberFormatException {

        setTablesSize(M0.length);
        MatrixSizeSpinner.setValue(M0.length);

        double sum = 0;
        double sumRow[] = new double[M0.length];
        DefaultTableModel model = (DefaultTableModel) valueTable.getModel();

        setTableSize(M0.length, M0.length, valueTable);

        for (int i = 0; i < M0.length; i++) {
            for (int j = 0; j < M0[0].length; j++) {
                //главная таблица
                model.setValueAt(M0[i][j], i, j);
                //для матрицы столбца
                sum = sum + M0[i][j];
                sumRow[j] = sumRow[j] + M0[i][j];
            }

            sum = 0;
        }
        model.fireTableDataChanged();
    }

    private void setTablesSize(int matrixSize) throws NumberFormatException {

        setTableSize(matrixSize, matrixSize, valueTable);

    }

    private void setTableSize(int width, int height, JTable table) throws NumberFormatException {

        table.setSize(width, height);
        
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setColumnCount(width);
        model.setRowCount(height);
        setWhiteColorForDiagonal(width, height, model);
        model.fireTableStructureChanged();
        resizeColumns();
        
        
    }
    
    private void setWhiteColorForDiagonal(int width, int height, DefaultTableModel model){
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (i == j){
                    valueTable.setValueAt("-",i,j);
                }
            }
        }
    }

    private void btnSolveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSolveActionPerformed

        double[][] a = getValuesFromTable();
        Work1Main w = new Work1Main(a);
        w.main();
        String msg = ("\nPath: " + w.getPath() + "\nSum: " + w.getSum(a)
                + " Time: " + w.getTime());

        //w.mainNewMethod();
        setMessage(msg);

        //Work1OldStableVersion obj = new Work1OldStableVersion(a, this);

    }//GEN-LAST:event_btnSolveActionPerformed

    private void fillTableWithConstantsCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fillTableWithConstantsCheckBoxActionPerformed
        chkbxRandomValues.setSelected(false);
    }//GEN-LAST:event_fillTableWithConstantsCheckBoxActionPerformed

    private void exitMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuActionPerformed
        dispose();
    }//GEN-LAST:event_exitMenuActionPerformed

    private void parametersMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_parametersMenuActionPerformed
        optionsPanel.setVisible(!optionsPanel.isVisible());
    }//GEN-LAST:event_parametersMenuActionPerformed

    private void valueTableMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_valueTableMouseMoved
        Point p = evt.getPoint();
        int i = valueTable.rowAtPoint(p) + 1;
        int j = valueTable.columnAtPoint(p) + 1;
        valueTable.setToolTipText(i + " : " + j);
    }//GEN-LAST:event_valueTableMouseMoved

    private void chkbxRandomValuesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkbxRandomValuesMousePressed
        checkBoxConstants.setSelected(false);
    }//GEN-LAST:event_chkbxRandomValuesMousePressed

    private void checkBoxConstantsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkBoxConstantsMouseClicked
        chkbxRandomValues.setSelected(false);
        jSpinner1.setEnabled(true);
    }//GEN-LAST:event_checkBoxConstantsMouseClicked

    private void ClearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClearBtnActionPerformed
        FillTableRandomValues(false);
    }//GEN-LAST:event_ClearBtnActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {
        /*  try {
         //exportToExcel();
         } catch (IOException ex) {
         Logger.getLogger(ParentFrame.class.getName()).log(Level.SEVERE, null, ex);
         }*/
    }
    
    private void resizeColumns(){
        
        int columnCount         = valueTable.getColumnCount();
        
        for ( int i = 0; i < columnCount; i++ ) {
            
            valueTable.getColumn(valueTable.getColumnName(i)).setPreferredWidth(80);
            
        }
        
    }
    
    private double[][] getValuesFromTable() {

        int tableSize = (int) MatrixSizeSpinner.getValue();
        double[][] matrix = new double[tableSize][tableSize];

        for (int i = 0; i < tableSize; i++) {
            for (int j = 0; j < tableSize; j++) {
                try {
                    Object o = valueTable.getValueAt(i, j);
                    if (o instanceof Double) {
                        matrix[i][j] = (double) valueTable.getValueAt(i, j);
                    } else {
                        matrix[i][j] = Double.parseDouble((String) valueTable.getValueAt(i, j));
                    }
                } catch (NullPointerException e) {
                    System.out.println(e);
                }

            }
        }

        printMatrix(matrix);

        return matrix;
    }

    private double[][] FillTableRandomValues(boolean random) {

        int tableSize = (int) MatrixSizeSpinner.getValue();

        double[][] matrix = new double[tableSize][tableSize];

        if (!random) {

            String value = jSpinner1.getValue().toString();
            for (double[] ds : matrix) {
                Arrays.fill(ds, Double.valueOf(value));
            }
            

        } else {

            for (int i = 0; i < tableSize; i++) {
                for (int j = 0; j < tableSize; j++) {

                    Random r = new Random();
                    matrix[i][j] = r.nextInt(99);

                }
            }

        } // end of else

        return matrix;

    }

    private void printMatrix(double[][] matrix) {

        int size = matrix.length;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(matrix[i][j]);
            }
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ClearBtn;
    private javax.swing.JButton FillBtn;
    private javax.swing.JSpinner MatrixSizeSpinner;
    private javax.swing.JButton btnSolve;
    private javax.swing.JCheckBox checkBoxConstants;
    private javax.swing.JCheckBox chkbxRandomValues;
    private javax.swing.JSpinner constantSpinner;
    private javax.swing.JMenuItem exitMenu;
    private javax.swing.JCheckBox fillTableWithConstantsCheckBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTable matrixColumn1;
    private javax.swing.JTextPane messagesTextPane;
    private javax.swing.JPanel optionsPanel;
    private javax.swing.JMenuItem parametersMenu;
    private javax.swing.JTable valueTable;
    // End of variables declaration//GEN-END:variables

    /*private void exportToExcel() throws FileNotFoundException, IOException {

     String filename = "C:/newExcel.xls";
     HSSFWorkbook book = new HSSFWorkbook();
     HSSFSheet sheet = book.createSheet();

     double[][] value = getValuesFromTable();

     for (int i = 0; i < value.length; i++) {

     HSSFRow rowHead = sheet.createRow((short) i);

     for (int j = 0; j < value.length; j++) {
     rowHead.createCell(j).setCellValue(value[i][j]);
     }

     HSSFRow row = sheet.createRow((short) value.length + 2);
     HSSFCell cell = row.createCell(0);
     HSSFCell cell = row.createCell(0);
     cell.setCellValue(messagesTextPane.getText());

     }

     FileOutputStream stream = new FileOutputStream(filename);
     book.write(stream);
     stream.close();
     }*/
}
