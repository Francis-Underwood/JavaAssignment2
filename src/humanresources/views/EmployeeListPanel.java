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

    private ArrayList<Employee> empList;
    private JScrollPane scrollPane;
    private JTable employeeGrid;
    private JLabel titleLbl;
    private EmployeeListPanelModel employeeModel;
    private JPanel btnBar = new JPanel();
    private JButton createBtn = new JButton("Create");
    private JButton editBtn = new JButton("Edit");
    private JButton deleteBtn = new JButton("Delete");
    private JButton viewCustmBtn = new JButton("View Customers");
    private String status = "all"; // all || sales || others
    
    private EmployeeFactory empFacty = new EmployeeFactory();
    private EmployeeRepository empyReposty;

    public EmployeeListPanel(String title, String status) {
        
        this.status = status;
        
        this.empyReposty = EmployeeRepository.getRepository();
        
        
        switch (this.status) {
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

        this.btnBar.add(this.createBtn);
        this.btnBar.add(this.editBtn);
        this.btnBar.add(this.deleteBtn);
        this.btnBar.add(this.viewCustmBtn);

        this.add(this.btnBar);

        this.setBounds(0, 0, 800, 300);

    }

    public void actionPerformed(ActionEvent atnEvt) {

    }

}
