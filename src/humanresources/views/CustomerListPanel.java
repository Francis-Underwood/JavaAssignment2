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
import java.sql.SQLException;
import humanresources.businessdomain.*;
import humanresources.viewmodels.*;

/**
 *
 * @author Vincent
 */
public class CustomerListPanel extends JPanel implements ActionListener {
    
    // data & state
    private CustomerFactory custFacty = new CustomerFactory();
    private CustomerRepository custReposty = new CustomerRepository();
    private EmployeeRepository empyReposty = new EmployeeRepository();
    private ArrayList<Customer> custList = new ArrayList<Customer>();
    private ArrayList<Employee> empyList = new ArrayList<Employee>();
    private String state = "ALL"; // ALL || AGENT
    private int rowInd = -1;
    private Employee empy = null;
    private List<String> errList = new ArrayList<String>();
    private Pattern pattern = Pattern.compile("^[ \\p{Alpha}]*$");
    
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
    
    
    public CustomerListPanel(String state, int empyId, EmployeeRepository er, CustomerRepository cr) throws SQLException {  // TODO: have to refactor it for testing DI?
        String title = "";
        this.state = state;
        this.empyReposty = er;
        this.custReposty = cr;
        
        switch (this.state) {
            case "ALL":
                defaut:
                title = "All Customers";
                this.custList = this.custReposty.all();
                this.empyList = this.empyReposty.getByPosition(PositionType.SALESPERSON);
                // populate manager combo box
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
                
            case "AGENT":
                title = "'s Customers";
                if (empyId > 0) {
                    this.custList = this.custReposty.getByEmployeeId(empyId);
                    this.empy = this.empyReposty.get(empyId);
                    
                    if (null != this.empy) {
                        title = this.empy.getFname() + " " + this.empy.getLname() + title;
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
        
        // populate payment method combo box
        ArrayList<String> pMedOptItems = new ArrayList<String>();
        for (PaymentMethodOption pmo : PaymentMethodOption.values()) {
            pMedOptItems.add(pmo.getDisplayName());
        }
        this.payMethdCombox = new JComboBox(pMedOptItems.toArray());
        
        this.customerEditCtrls
                = new Object[]{
                    "Customer name:", custNameTxtFld,
                    "Payment method:", payMethdCombox,
                    "Agent:", managerCombox
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
        this.scrollPane.setPreferredSize(new Dimension(800, 420));
        this.scrollPane.setMaximumSize(new Dimension(800, 420));
        this.scrollPane.getViewport().add(this.customerGrid);
        this.scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.add(scrollPane);
		
        // buttons to interact with customer table
        this.btnBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.btnBar.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        this.createBtn.setName("createCustomer");
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
    
    public void setCustReposty(CustomerRepository cr) {
        this.custReposty = cr;
    }
        
    public void actionPerformed(ActionEvent atnEvt) {
        
        if ("Create" == atnEvt.getActionCommand()) {
            
            this.rowInd = -1;
            resetCustEditCtrls();
            
            if ("AGENT" == this.state) {
                this.managerCombox.setEnabled(false);
            }
            
            int option = JOptionPane.showConfirmDialog(
                null, 
                this.customerEditCtrls, 
                "Customer Info", 
                JOptionPane.OK_CANCEL_OPTION
            );
            
            if (option == JOptionPane.OK_OPTION) {
                if (this.validateCustomerForm()) {
                    Customer tempCust 
                        = this.custFacty.createCustomer(
                            0, 
                            ((VinComboItem)this.managerCombox.getSelectedItem()).getValue(), 
                            this.custNameTxtFld.getText(), 
                            ((VinComboItem)this.managerCombox.getSelectedItem()).getLabel(), 
                            PaymentMethodOption.fromString(this.payMethdCombox.getSelectedItem().toString())
                        );
                    
                    try {
                        int generatedId = this.custReposty.add(tempCust);
                        if (generatedId > 0) {
                            tempCust.setCid(generatedId);
                            this.custList.add(tempCust);
                            this.customerModel.addRow(tempCust);
                        }
                    }
                    catch (SQLException ex) {
                        this.showErrorMessage("Database error, try again later.");
                    }
                    finally {
                        resetCustEditCtrls();
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, String.join("\n", this.errList));
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
                this.managerCombox.setEnabled(false);
                int option = JOptionPane.showConfirmDialog(
                    null,
                    customerEditCtrls,
                    "Customer Info",
                    JOptionPane.OK_CANCEL_OPTION
                );            
                if (option == JOptionPane.OK_OPTION) {
                    if (this.validateCustomerForm()) {
                        c.setCname(this.custNameTxtFld.getText());
                        
                        try {
                            if (this.custReposty.update(c)) {
                                this.customerModel.setValueAt(this.custNameTxtFld.getText(), this.rowInd, 1);
                            }
                        }
                        catch (SQLException ex) {
                            this.showErrorMessage("Database error, try again later.");
                        }
                        finally {
                            this.rowInd = -1;
                            resetCustEditCtrls();
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(null, String.join("\n", this.errList));
                    }
                } 
                else {
                }
                this.rowInd = -1;
                resetCustEditCtrls();
            }
        }
        else if ("Delete" == atnEvt.getActionCommand()) {
            this.rowInd = this.customerGrid.getSelectedRow();
            if (this.rowInd > -1) {
                Customer cust = this.custList.get(this.rowInd);
                
                try {
                    if (this.custReposty.delete(cust.getCid())) {
                        this.custList.remove(this.rowInd);
                        this.customerModel.removeRow(this.rowInd);
                        //this.showMessageBox(cust.getCname() + " is deleted.");
                    }
                }
                catch (SQLException ex) {
                    this.showErrorMessage("Database error, try again later.");
                }
                finally {
                    this.rowInd = -1;
                }
            }
        }
    }
    
    private void resetCustEditCtrls() {
        this.custNameTxtFld.setText("");
        this.custNameTxtFld.setEditable(true);
        this.payMethdCombox.setEnabled(true);
        this.managerCombox.setEnabled(true);
    }

    private void showErrorMessage(String message) {
        JOptionPane pane = new JOptionPane(message);
        JFrame f = (JFrame)(this.getParent().getParent().getParent().getParent());
        JInternalFrame intframe = pane.createInternalFrame(f.getLayeredPane(), "Error");
        f.getLayeredPane().add(intframe);
        intframe.show();
    }
    
    private boolean validateCustomerForm() {
        boolean valid = true;
        this.errList.clear();
        
        // customer name
        if (this.custNameTxtFld.getText() != null ) {
            if (this.custNameTxtFld.getText().isEmpty()) {
                valid = false;
                this.errList.add("Please input customer name.");
            }
            else {
                Matcher matcher = this.pattern.matcher(this.custNameTxtFld.getText());
                if (matcher.matches()) {
                    // all good
                }
                else {
                    valid = false;
                    this.errList.add("Customer name contains invalid symbols.");
                }
            }
        }
        else {
            valid = false;
        }
        
        return valid;
    }
    
}
