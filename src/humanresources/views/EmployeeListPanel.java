/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package humanresources.views;

import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import humanresources.businessdomain.*;
import humanresources.viewmodels.*;

/**
 *
 * @author Vincent
 */
public class EmployeeListPanel extends JPanel implements ActionListener {
    
    // data & state
    private EmployeeFactory empFacty = new EmployeeFactory();
    private EmployeeRepository empyReposty;
    private ArrayList<Employee> empList;
    private String state = "all"; // all || sales || others
    private  int rowInd = -1;
    
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
    private JComboBox<String> empPosTypeCombox
            = new JComboBox<String>(
                    new String[]{
                        PositionType.SALESPERSON.getDisplayName(),
                        PositionType.OTHERS.getDisplayName()
                    }
            );
    private Object[] employeeEditCtrls = {
                    "First name:", empFNameTxtFld,
                    "Last name:", empLNameTxtFld,
                    "Position:", empPosTypeCombox
                };

    public EmployeeListPanel(String title, String state) {
        
        this.state = state;
        
        this.empyReposty = EmployeeRepository.getRepository();

        switch (this.state) {
            case "all":
            defaut:
                this.empList = this.empyReposty.all();
                break;
            case "sales":
                this.empList = this.empyReposty.getByPosition(PositionType.SALESPERSON);
                break;
            case "others":  
                this.empList = this.empyReposty.getByPosition(PositionType.OTHERS);
                break;
        }
      
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.employeeModel = new EmployeeListPanelModel(this.empList);
        this.titleLbl = new JLabel(title);

        this.titleLbl.setFont(new Font("Helvetica", Font.PLAIN, 24));
        this.titleLbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.add(this.titleLbl);

        this.employeeGrid = new JTable(employeeModel);
        // single row selection
        this.employeeGrid.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.employeeGrid.setPreferredScrollableViewportSize(new Dimension(600, 200));
        
        this.scrollPane = new JScrollPane();
        this.scrollPane.setPreferredSize(new Dimension(800, 300));
        this.scrollPane.setMaximumSize(new Dimension(800, 300));
        this.scrollPane.getViewport().add(this.employeeGrid);
        this.scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.add(scrollPane);

        this.btnBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.btnBar.setAlignmentX(Component.LEFT_ALIGNMENT);

        this.deleteBtn.addActionListener(this);
        this.editBtn.addActionListener(this);
        this.createBtn.addActionListener(this);
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
                //System.out.println("User's choice: " + emp.getEid());
                if (this.empyReposty.delete(emp.getEid())) {
                    this.empList.remove(this.rowInd);
                    this.employeeModel.removeRow(this.rowInd);
                }
                //  inform main
                //DeleteEmployeeEvent dee = new DeleteEmployeeEvent(this, empyKey);
                //goDeleteEmployee(dee);
                this.rowInd = -1;
            }
        }
        else if ("Edit" == atnEvt.getActionCommand()) {
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
                } 
                else { }
                this.rowInd = -1;
                resetEmpyEditCtrls();
            }
        }
        else if ("Create" == atnEvt.getActionCommand()) {
            this.rowInd = -1;
            resetEmpyEditCtrls();
            int option = JOptionPane.showConfirmDialog(null, this.employeeEditCtrls, 
                    "Employee Info", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                Employee tempEmp = empFacty.createEmployee(PositionType.fromString(this.empPosTypeCombox.getSelectedItem().toString()), 
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
            else {}
            resetEmpyEditCtrls();
        }
        
    }

    private void resetEmpyEditCtrls() {
        this.empFNameTxtFld.setText("");
        this.empFNameTxtFld.setEditable(true);
        this.empFNameTxtFld.setText("");
        this.empFNameTxtFld.setEditable(true);
        this.empPosTypeCombox.setEnabled(true);
    }
        

}
