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
public class CustomerListPanel extends JPanel implements ActionListener {
    
    // data & state
    private CustomerFactory custFacty = new CustomerFactory();
    private CustomerRepository custReposty;
    private EmployeeRepository empyReposty;
    private ArrayList<Customer> custList;
    private ArrayList<Employee> empyList;
    private String state = "all"; // all || agent
    private int rowInd = -1;
    private Employee empy = null;
    
    // component
    private JScrollPane scrollPane;
    private JTable customerGrid;
    private JLabel titleLbl;
    private CustomerListPanelModel customerModel;
    
    // buttons to interact with the grid
    private JPanel btnBar = new JPanel();
    private JButton createBtn = new JButton("Create");
    private JButton editBtn = new JButton("Edit");
    private JButton deleteBtn = new JButton("Delete");
    
    // customer property panel
    private JTextField custNameTxtFld = new JTextField();
    private JComboBox payMethdCombox;
    private JComboBox managerCombox = new JComboBox();
    private Object[] customerEditCtrls = null;
    
    
    public CustomerListPanel(String title, String state, int empyId) {
        this.state = state;
        this.custReposty = CustomerRepository.getRepository();
        this.empyReposty = EmployeeRepository.getRepository();
        
        switch (this.state) {
            case "all":
                defaut:
                this.custList = this.custReposty.all();
                this.empyList = this.empyReposty.getByPosition(PositionType.SALESPERSON);
                
                ArrayList<VinComboItem> combItems = new ArrayList<VinComboItem>();
                
                for (Employee empy : this.empyList) {
                    VinComboItem tempItem 
                            = new VinComboItem(
                                empy.getEid(), 
                                empy.getFname() + " " + empy.getLname()
                            );
                    combItems.add(tempItem);
                }
                
                this.managerCombox = new JComboBox(combItems.toArray());
                
                break;
                
                
            case "agent":
                if (empyId > 0) {
                    this.custList = this.custReposty.getByEmployeeId(empyId);
                    
                    this.empy = this.empyReposty.get(empyId);
                    
                    if (null != this.empy) {
                        //System.out.println("Rebecca: " + empy.getFname());
                        
                        this.managerCombox = new JComboBox(
                                new VinComboItem[] {
                                    new VinComboItem(
                                        this.empy.getEid(),
                                        this.empy.getFname() + " " + this.empy.getLname()
                                    )
                                }
                        );
                    }
                    
                    
                }
                else {
                    // throw
                }
                break;
        }
        
        ArrayList<String> pMedOptItems = new ArrayList<String>();
        for (PaymentMethodOption pmo : PaymentMethodOption.values()) {
            pMedOptItems.add(pmo.getDisplayName());
        }
        
        this.payMethdCombox = new JComboBox(pMedOptItems.toArray());
        
        this.customerEditCtrls
                = new Object[]{
                    "Customer name:", custNameTxtFld,
                    "Payment method:", payMethdCombox,
                    "Manager:", managerCombox
                };
        
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        this.customerModel = new CustomerListPanelModel(this.custList);
        this.titleLbl = new JLabel(title);
        this.titleLbl.setFont(new Font("Helvetica", Font.PLAIN, 24));
        this.titleLbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.add(this.titleLbl);
        
        // customer list
        this.customerGrid = new JTable(this.customerModel);
        this.customerGrid.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.customerGrid.setPreferredScrollableViewportSize(new Dimension(600, 100));
        
        this.scrollPane = new JScrollPane();
        this.scrollPane.setPreferredSize(new Dimension(800, 220));
        this.scrollPane.setMaximumSize(new Dimension(800, 220));
        this.scrollPane.getViewport().add(this.customerGrid);
        this.scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.add(scrollPane);
		
        // buttons to interact with customer table
        this.btnBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.btnBar.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        this.createBtn.addActionListener(this);
        this.editBtn.addActionListener(this);
        this.deleteBtn.addActionListener(this);
        
        this.btnBar.add(this.createBtn);
        this.btnBar.add(this.editBtn);
        this.btnBar.add(this.deleteBtn);
        
        this.add(this.btnBar);
        // customer list ends
        
        setBounds(0, 0, 800, 300);
        revalidate();
        repaint();
                
    }
    
    
    
    public void actionPerformed(ActionEvent atnEvt) {
        
        
        if ("Create" == atnEvt.getActionCommand()) {
            this.rowInd = -1;
            resetCustEditCtrls();
            
            if ("agent" == this.state) {
                this.managerCombox.setEnabled(false);
            }
            
            int option = JOptionPane.showConfirmDialog(null, this.customerEditCtrls, 
                    "Customer Info", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                Customer tempCust 
                    = this.custFacty.createCustomer(
                        0, 
                        ((VinComboItem)this.managerCombox.getSelectedItem()).getValue(), 
                        this.custNameTxtFld.getText(), 
                        ((VinComboItem)this.managerCombox.getSelectedItem()).getLabel(), 
                        PaymentMethodOption.fromString(this.payMethdCombox.getSelectedItem().toString())
                    );
                int generatedId = this.custReposty.add(tempCust);
                if (generatedId > 0) {
                    tempCust.setCid(generatedId);
                    this.custList.add(tempCust);
                    this.customerModel.addRow(tempCust);
                }
            } 
            else {}
            resetCustEditCtrls();
        }
        else if ("Edit" == atnEvt.getActionCommand()) {
            this.rowInd = this.customerGrid.getSelectedRow();
            if (this.rowInd > -1) {
                Customer c = this.custList.get(this.rowInd); // should be synchronized with table model's list
                this.custNameTxtFld.setText(c.getCname());
                this.payMethdCombox.setSelectedItem(c.getPaymentMethod().getDisplayName());
                this.payMethdCombox.setEnabled(false);
                
                ComboBoxModel model = this.managerCombox.getModel();
                int size = model.getSize();
                for(int i=0; i<size; i++) {
                    VinComboItem element = (VinComboItem)model.getElementAt(i);
                    if (element.getValue() == c.getEid()) {
                        this.managerCombox.setSelectedItem(element);
                    }
                }
                
                
                //System.out.println("type: " + element.getClass().getSimpleName());
                
                //System.out.println("size: " + size);
                
                //this.managerCombox.setSelectedItem(c.getAgentName());
                this.managerCombox.setEnabled(false);

                
                int option = JOptionPane.showConfirmDialog(null, customerEditCtrls, "Customer Info", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    customerModel.setValueAt(this.custNameTxtFld.getText(), this.rowInd, 1);
                } else {
                }
                this.rowInd = -1;
                resetCustEditCtrls();
            }
        }
        
        
        
        
        
    }
    
    
    
    private void resetCustEditCtrls() {
        this.custNameTxtFld.setText("");
        this.custNameTxtFld.setEditable(true);
        this.payMethdCombox.setEnabled(true);
        this.managerCombox.setEnabled(true);
    }

    
    
}
