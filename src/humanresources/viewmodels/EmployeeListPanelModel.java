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
public class EmployeeListPanelModel extends AbstractTableModel {

    private static final String columnNames[] = {"Employee Id",
        "Employee First Name",
        "Employee Last Name",
        "Category"};

    protected ArrayList<Employee> empList;

    public EmployeeListPanelModel(ArrayList<Employee> empList) {
        this.empList = empList;
        
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return empList.size();
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
        Employee e = this.empList.get(row);
        switch (col) {
            case 0:
                return e.getEid();
            case 1:
                return e.getFname();
            case 2:
                return e.getLname();
            case 3:
                return e.getPosition().getDisplayName();
            //case 4: return (e instanceof SalesPerson)?((SalesPerson)e).getCustomers().size():"N/A";
        }
        return "";
    }
    
    @Override
    public void setValueAt(Object val, int row, int col) {
        if (row < 0 || row >= getRowCount()) {
            return;
        }
        
        /*
Employee emp = this.empList.get(row);
        switch (col) {
            case 1:
                emp.setFname(val.toString());
                break;
            case 2:
                emp.setLname(val.toString());
                break;
        }
        */
        
        this.fireTableCellUpdated(row, col);
    }
    
    public void removeRow(int index) {
        this.fireTableRowsDeleted(index, index);
    }
    
    public void addRow(Employee emp) {
        this.fireTableRowsInserted(empList.size() - 1, empList.size() - 1);
    }

}
