/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package humanresources.views;

import java.util.*;
import java.util.List;
import java.util.regex.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import humanresources.businessdomain.*;
import humanresources.viewmodels.*;
import humanresources.systemevent.*;

/**
 *
 * @author Vincent
 */
public class EmployeeListPanel extends JPanel implements ActionListener {

    // data & state
    private EmployeeFactory empFacty = new EmployeeFactory();
    private EmployeeRepository empyReposty = new EmployeeRepository();
    private ArrayList<Employee> empList;
    private String state = "ALL"; // ALL || SALES || OTHERS
    private int rowInd = -1;
    private List<String> errList = new ArrayList<String>();
    private Pattern pattern = Pattern.compile("^[a-zA-Z]+$");

    // component
    private JScrollPane scrollPane;
    private JTable employeeGrid;
    private JLabel titleLbl;
    private EmployeeListPanelModel employeeModel;

    // buttons to interact with the grid
    private JPanel btnBar = new JPanel();
    private JButton createBtn = new JButton("Create");
    private JButton editBtn = new JButton("Edit");
    private JButton deleteBtn = new JButton("Delete");
    private JButton viewCustmBtn = new JButton("View Customers");

    // employee property panel
    private JTextField empFNameTxtFld = new JTextField();
    private JTextField empLNameTxtFld = new JTextField();
    private JComboBox empPosTypeCombox;

    private Object[] employeeEditCtrls = null;

    // custom event
    private List<IViewCustomersListener> viewCustsListeners 
            = new ArrayList<IViewCustomersListener>();

    public EmployeeListPanel(String state, EmployeeRepository er) {

        String title = "";
        this.state = state;
        this.empyReposty = er;
        
        switch (this.state) {
            case "ALL":
                defaut:
                title = "All Employees";
                this.empList = this.empyReposty.all();
                break;
            case "SALES":
                title = "All Sales";
                this.empList = this.empyReposty.getByPosition(PositionType.SALESPERSON);
                break;
            case "OTHERS":
                title = "All Other Employees";
                this.empList = this.empyReposty.getByPosition(PositionType.OTHERS);
                break;
        }

        // populate position type combo box
        ArrayList<String> posTypeItems = new ArrayList<String>();
        for (PositionType pt : PositionType.values()) {
            posTypeItems.add(pt.getDisplayName());
        }
        this.empPosTypeCombox = new JComboBox(posTypeItems.toArray());
        
        this.employeeEditCtrls
                = new Object[]{
                    "First name:", empFNameTxtFld,
                    "Last name:", empLNameTxtFld,
                    "Position:", empPosTypeCombox
                };

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.employeeModel = new EmployeeListPanelModel(this.empList);
        this.titleLbl = new JLabel(title);
        this.titleLbl.setFont(new Font("Helvetica", Font.PLAIN, 24));
        this.titleLbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.add(this.titleLbl);

        this.employeeGrid = new JTable(employeeModel);
        this.employeeGrid.setName("employeeTable");
        this.employeeGrid.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.employeeGrid.setPreferredScrollableViewportSize(new Dimension(600, 200));

        this.scrollPane = new JScrollPane();
        this.scrollPane.setPreferredSize(new Dimension(800, 420));
        this.scrollPane.setMaximumSize(new Dimension(800, 420));
        this.scrollPane.getViewport().add(this.employeeGrid);
        this.scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.add(scrollPane);

        this.btnBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.btnBar.setAlignmentX(Component.LEFT_ALIGNMENT);

        this.deleteBtn.addActionListener(this);
        this.editBtn.addActionListener(this);
        this.createBtn.addActionListener(this);
        this.viewCustmBtn.setName("viewButtonInEmployeePanel");
        this.viewCustmBtn.addActionListener(this);

        this.btnBar.add(this.createBtn);
        this.btnBar.add(this.editBtn);
        this.btnBar.add(this.deleteBtn);
        this.btnBar.add(this.viewCustmBtn);

        this.add(this.btnBar);

        this.setBounds(0, 0, 800, 300);

    }

    public void actionPerformed(ActionEvent atnEvt) {
        if ("Delete" == atnEvt.getActionCommand()) {
            this.rowInd = this.employeeGrid.getSelectedRow();
            if (this.rowInd > -1) {
                Employee emp = this.empList.get(this.rowInd);
                if (this.empyReposty.delete(emp.getEid())) {
                    this.empList.remove(this.rowInd);
                    this.employeeModel.removeRow(this.rowInd);
                }
                this.rowInd = -1;
            }
        } else if ("Edit" == atnEvt.getActionCommand()) {
            this.rowInd = this.employeeGrid.getSelectedRow();
            if (this.rowInd > -1) {
                Employee emp = this.empList.get(this.rowInd);
                this.empFNameTxtFld.setText(emp.getFname());
                this.empLNameTxtFld.setText(emp.getLname());
                this.empPosTypeCombox.setSelectedItem(emp.getPosition().getDisplayName());
                this.empPosTypeCombox.setEnabled(false);
                int option = JOptionPane.showConfirmDialog(null, this.employeeEditCtrls,
                        "Employee Info", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    emp.setFname(this.empFNameTxtFld.getText());
                    emp.setLname(this.empLNameTxtFld.getText());
                    if (this.empyReposty.update(emp)) {
                        this.employeeModel.setValueAt(empFNameTxtFld.getText(), this.rowInd, 1);
                    }
                } else {
                }
                this.rowInd = -1;
                resetEmpyEditCtrls();
            }
        } else if ("Create" == atnEvt.getActionCommand()) {
            this.rowInd = -1;
            resetEmpyEditCtrls();
            
            switch (this.state) {
            case "ALL":
                defaut:
                
                break;
            case "SALES":
                this.empPosTypeCombox.setSelectedItem(PositionType.SALESPERSON.getDisplayName());
                this.empPosTypeCombox.setEnabled(false);
                break;
            case "OTHERS":
                this.empPosTypeCombox.setSelectedItem(PositionType.OTHERS.getDisplayName());
                this.empPosTypeCombox.setEnabled(false);
                break;
            }
            
            int option = JOptionPane.showConfirmDialog(
                null, 
                this.employeeEditCtrls,
                "Employee Info", 
                JOptionPane.OK_CANCEL_OPTION
            );
            
            if (option == JOptionPane.OK_OPTION) {
                
                if (this.validateEmployeeForm()) {
                    Employee tempEmp = empFacty.createEmployee(
                        PositionType.fromString(
                            this.empPosTypeCombox.getSelectedItem().toString()
                        ),
                        0,
                        this.empFNameTxtFld.getText(),
                        this.empLNameTxtFld.getText()
                    );
                    int generatedId = this.empyReposty.add(tempEmp);
                    if (generatedId > 0) {
                        tempEmp.setEid(generatedId);
                        this.empList.add(tempEmp);
                        this.employeeModel.addRow(tempEmp);
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, String.join("\n", this.errList));
                }
                
            } 
            else {
            }
            resetEmpyEditCtrls();
        } else if ("View Customers" == atnEvt.getActionCommand()) {
            this.rowInd = this.employeeGrid.getSelectedRow();
            if (this.rowInd > -1) {
                Employee emp = this.empList.get(this.rowInd);
                if (emp.getPosition() == PositionType.SALESPERSON) {
                    ViewCustomersEvent vce = new ViewCustomersEvent(this, emp.getEid());
                    this.goViewCustomers(vce);
                }
            }
        }
    }

    public void addDeleteEmployeeListener(IViewCustomersListener vcLtner) {
        this.viewCustsListeners.add(vcLtner);
    }

    public void removeDeleteEmployeeListener(IViewCustomersListener vcLtner) {
        this.viewCustsListeners.remove(vcLtner);
    }

    private void resetEmpyEditCtrls() {
        this.empFNameTxtFld.setText("");
        this.empFNameTxtFld.setEditable(true);
        this.empLNameTxtFld.setText("");
        this.empLNameTxtFld.setEditable(true);
        this.empPosTypeCombox.setEnabled(true);
    }
    
    private boolean validateEmployeeForm() {
        boolean valid = true;
        this.errList.clear();
        
        // first name
        if (this.empFNameTxtFld.getText() != null ) {
            
            System.out.println("input value: " + this.empFNameTxtFld.getText() );
            
            if (this.empFNameTxtFld.getText().isEmpty()) {
                valid = false;
                this.errList.add("Please input first name.");
            }
            else {
                Matcher matcher = this.pattern.matcher(this.empFNameTxtFld.getText());
               
                if (matcher.matches()) {
                    valid = false;
                    this.errList.add("First name contains invalid symbols.");
                    if (matcher.find( )) {
         System.out.println("Found value: " + matcher.group(0) );
      } 
                }
                else {
                    // all good
                }
            }
        }
        else {
            valid = false;
        }
        
        /*
        // last name
        if (this.empLNameTxtFld.getText() != null ) {
            if (this.empLNameTxtFld.getText().isEmpty()) {
                valid = false;
                this.errList.add("Please input last name.");
            }
            else {
                Matcher matcher = this.pattern.matcher(this.empLNameTxtFld.getText());
                if (matcher.matches()) {
                    valid = false;
                    this.errList.add("Last name contains invalid symbols.");
                }
                else {
                    // all good
                }
            }
        }
        else {
            valid = false;
        }
        
        */
        
        return valid;
    }

    private void goViewCustomers(ViewCustomersEvent vcEvt) {
        //http://stackoverflow.com/questions/5644568/getting-concurrentexception-when-traversing-a-list
        /*
        for (IViewCustomersListener cvLns : this.viewCustsListeners) {
            cvLns.ViewCustomers(vcEvt);
        }
        */
        for (int i=0; i<this.viewCustsListeners.size(); i++) {
            ((IViewCustomersListener)this.viewCustsListeners.get(i)).ViewCustomers(vcEvt);
        }
    }

}
