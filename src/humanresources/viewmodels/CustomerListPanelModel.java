/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package humanresources.viewmodels;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import humanresources.businessdomain.*;

/**
 *
 * @author Vincent
 */
public class CustomerListPanelModel extends AbstractTableModel {
    
    private static final String columnNames[]
            = {
                "Customer Id",
                "Customer Name",
                "Payment Method",
                "Agent Name"
            };

    private ArrayList<Customer> custList;

    public CustomerListPanelModel(ArrayList<Customer> custList) {
        this.custList = custList;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return custList.size();
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Object getValueAt(int row, int col) {
        if (row < 0 || row >= getRowCount()) {
            return "";
        }
        Customer c = this.custList.get(row);
        switch (col) {
            case 0:
                return c.getCid();
            case 1:
                return c.getCname();
            case 2:
                return c.getPaymentMethod().getDisplayName();
            case 3:
                return c.getAgentName();
        }
        return "";
    }
    
    @Override
    public void setValueAt(Object val, int row, int col) {
        if (row < 0 || row >= getRowCount()) {
            return;
        }
/*        
        Customer c = this.custList.get(row);
        switch (col) {
            case 1:
                c.setCname(val.toString());
        }
*/
        this.fireTableCellUpdated(row, col);
    }
        
        
    public void removeRow(int index) {
        //custList.remove(index);
        this.fireTableRowsDeleted(index, index);
    }

    public void addRow(Customer cust) {
        //custList.add(cust);
        this.fireTableRowsInserted(getRowCount()-1, getRowCount()-1);
    }
    
    
}
