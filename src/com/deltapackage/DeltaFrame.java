/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deltapackage;

import java.awt.Color;
import java.awt.Point;
import java.util.Random;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Евгений
 */
public class DeltaFrame extends javax.swing.JFrame {

    /**
     * Creates new form DeltaFrame
     */
    public DeltaFrame() {

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
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        exitMenu = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        parametersMenu = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);

        valueTable.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        valueTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        valueTable.setFillsViewportHeight(true);
        valueTable.setPreferredSize(new java.awt.Dimension(7500, 8000));
        valueTable.setRowHeight(20);
        valueTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
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
        chkbxRandomValues.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkbxRandomValuesActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel1.setText("Размерность матрицы");

        checkBoxConstants.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        checkBoxConstants.setText("Заполнять константами");
        checkBoxConstants.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxConstantsActionPerformed(evt);
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
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 553, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(optionsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(optionsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(10, 10, 10))))
        );

        fillTableWithConstantsCheckBox.getAccessibleContext().setAccessibleName("FillConstants");

        pack();
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
        if (chkbxRandomValues.isSelected()) {
            
            double[][] M1 = null;
            M1  = FillTableRandomValues(false);
            setTableValue(M1);
            
        } else if ( checkBoxConstants.isSelected() ) {
            
            double[][] M1 = null;
            M1    =   FillTableRandomValues(true);
            setTableValue(M1);
            
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
            setTableValue(M0);
            
        }


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

    private void ClearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClearBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ClearBtnActionPerformed

    private void setTablesSize(int matrixSize) throws NumberFormatException {
        
        setTableSize(matrixSize, matrixSize, valueTable);
        
    }

    private void setTableSize(int width, int height, JTable table) throws NumberFormatException {

        table.setSize(width, height);
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setColumnCount(width);
        model.setRowCount(height);
        model.fireTableStructureChanged();
    }

    private void btnSolveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSolveActionPerformed

        double[][] a = getValuesFromTable();        
        Work1 obj   =   new Work1();
        obj.setM0(a);
        
            
    }//GEN-LAST:event_btnSolveActionPerformed

    private void chkbxRandomValuesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkbxRandomValuesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkbxRandomValuesActionPerformed

    private void fillTableWithConstantsCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fillTableWithConstantsCheckBoxActionPerformed
        
    }//GEN-LAST:event_fillTableWithConstantsCheckBoxActionPerformed

    private void checkBoxConstantsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxConstantsActionPerformed
        jSpinner1.setEnabled(checkBoxConstants.isEnabled());
    }//GEN-LAST:event_checkBoxConstantsActionPerformed

    private void exitMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuActionPerformed
        dispose();
    }//GEN-LAST:event_exitMenuActionPerformed

    private void parametersMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_parametersMenuActionPerformed
        optionsPanel.setVisible(!optionsPanel.isVisible());
    }//GEN-LAST:event_parametersMenuActionPerformed

    private void valueTableMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_valueTableMouseMoved
        Point p   = evt.getPoint();
        int   i   = valueTable.rowAtPoint(p)+1;
        int   j   = valueTable.columnAtPoint(p)+1;
        valueTable.setToolTipText(i + " : " + j);
    }//GEN-LAST:event_valueTableMouseMoved

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DeltaFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DeltaFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DeltaFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DeltaFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DeltaFrame().setVisible(true);
            }
        });
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTable matrixColumn1;
    private javax.swing.JPanel optionsPanel;
    private javax.swing.JMenuItem parametersMenu;
    private javax.swing.JTable valueTable;
    // End of variables declaration//GEN-END:variables

    private double[][] getValuesFromTable() {

        int tableSize = (int) MatrixSizeSpinner.getValue();
        double[][] matrix = new double[tableSize][tableSize];

        for (int i = 0; i < tableSize; i++) {
            for (int j = 0; j < tableSize; j++) {
                try {

                    matrix[i][j] = (double) valueTable.getValueAt(i, j);

                } catch (NullPointerException e) {
                    System.out.println(e);
                }

            }
        }
        printMatrix(matrix);
        return matrix;
    }

    private double[][] FillTableRandomValues(boolean constant) {

        int tableSize = (int) MatrixSizeSpinner.getValue();

        double[][] matrix = new double[tableSize][tableSize];

        if (constant) {

            for (int i = 0; i < tableSize; i++) {
                for (int j = 0; j < tableSize; j++) {
                    try {
                        Random r = new Random();
                        matrix[i][j] = r.nextDouble();
                    } catch (NullPointerException e) {
                        System.out.println(e);
                    }

                }
            }
        } else {
            String value = jSpinner1.getValue().toString();
            for (int i = 0; i < tableSize; i++) {
                for (int j = 0; j < tableSize; j++) {

                    matrix[i][j] = Double.valueOf(value);

                }
            }
        }     
            
        return matrix;
        
    }

    private void printMatrix(double[][] matrix) {
        int size = matrix.length;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.err.print(matrix[i][j]);
            }
            System.out.println("");
        }
    }
    
    public void moveTableScrollBar(){
        
    }
}
