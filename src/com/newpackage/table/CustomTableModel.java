/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.newpackage.table;

import javax.swing.table.TableColumn;

/**
 *
 * @author yevgen
 */
public class CustomTableModel extends TableColumn{

    @Override
    public void setHeaderValue(Object headerValue) {
        super.setHeaderValue(1);
        
    }

        
    
}
