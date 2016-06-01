/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package humanresources;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import humanresources.businessdomain.*;
import humanresources.views.*;
import humanresources.systemevent.*;

/**
 *
 * @author Vincent
 */
public class StartUp {

    private static EmployeeRepository empyRepo = new EmployeeRepository();
    private static CustomerRepository custRepo = new CustomerRepository();

    private final static String feelNLook = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
    private static String panelState = "EMPY";  // EMPY || CUST
    private static String empyState = "ALL"; // ALL || SALES || OTHERS
    private static String custStatus = "ALL"; // ALL || AGENT

    private static JFrame frame = new JFrame();
    private static Container con;
    private static JMenuBar menuBar = new JMenuBar();
    private static JMenu menuEmployee = new JMenu("Employees");
    private static JMenuItem mItemAllEmpye = new JMenuItem("All Employees");
    private static JMenuItem mItemAllSales = new JMenuItem("All Sales");
    private static JMenuItem mItemAllOtherEmpy = new JMenuItem("All Other Employees");
    private static JMenu menuCustomer = new JMenu("Customers");
    private static JMenuItem mItemAllCustm = new JMenuItem("All Customers");

    // listeners
    private static ActionListener mItemAllEmpyeLstn;
    private static ActionListener mItemAllSalesLstn;
    private static ActionListener mItemAllOtherEmpyLstn;
    private static ActionListener mItemAllCustmLstn;
    private static IViewCustomersListener viewCustsLstn;

    private static EmployeeListPanel empPanl;
    private static CustomerListPanel custPanl;

    public static void main(String[] args) {

        /**
         * ***********************************************
         * create listeners
	 ************************************************
         */
        
        // menu listeners
        mItemAllEmpyeLstn = new ActionListener() {
            public void actionPerformed(ActionEvent aevt) {
                if (!(("EMPY" == panelState) && ("ALL" == empyState))) {
                    goToEmployeeListPanel("ALL");
                    panelState = "EMPY";
                    empyState = "ALL";
                }
            }
        };
        
        mItemAllSalesLstn = new ActionListener() {
            public void actionPerformed(ActionEvent aevt) {
                if (!(("EMPY" == panelState) && ("SALES" == empyState))) {
                    goToEmployeeListPanel("SALES");
                    panelState = "EMPY";
                    empyState = "SALES";
                }
            }
        };
        
        mItemAllOtherEmpyLstn = new ActionListener() {
            public void actionPerformed(ActionEvent aevt) {
                if (!(("EMPY" == panelState) && ("OTHERS" == empyState))) {
                    goToEmployeeListPanel("OTHERS");
                    panelState = "EMPY";
                    empyState = "OTHERS";
                }
            }
        };
        
        mItemAllCustmLstn = new ActionListener() {
            public void actionPerformed(ActionEvent aevt) {
                if (!(("CUST" == panelState) && ("ALL" == custStatus))) {
                    goToCustomerListPanel("ALL", 0);
                    panelState = "CUST";
                    custStatus = "ALL";
                }
            }
        };
        
        viewCustsLstn = new IViewCustomersListener() {
            public void ViewCustomers(ViewCustomersEvent evt) {
                goToCustomerListPanel("AGENT", evt.getEmployeeId());
                panelState = "CUST";
                custStatus = "AGENT";
            }
        };

        /**
         * ***********************************************
         * set up the visual components
         * ***********************************************
         */
        // root container
        frame.setTitle("Human Resource Management System");
        frame.setBounds(120, 60, 900, 600);
        con = frame.getContentPane();

        ///////////////////////////////////
        mItemAllEmpye.setName("allEmployees");
        mItemAllEmpye.addActionListener(mItemAllEmpyeLstn);
        menuEmployee.add(mItemAllEmpye);
        
        mItemAllSales.setName("allSalespersons");
        mItemAllSales.addActionListener(mItemAllSalesLstn);
        menuEmployee.add(mItemAllSales);
        
        mItemAllOtherEmpy.setName("allOtherstaffs");
        mItemAllOtherEmpy.addActionListener(mItemAllOtherEmpyLstn);
        menuEmployee.add(mItemAllOtherEmpy);
        
        menuBar.add(menuEmployee);

        mItemAllCustm.setName("allCustomers");
        mItemAllCustm.addActionListener(mItemAllCustmLstn);
        menuCustomer.add(mItemAllCustm);
        
        menuBar.add(menuCustomer);

        frame.setJMenuBar(menuBar);

        empPanl = new EmployeeListPanel("ALL", empyRepo);
        empPanl.setName("employeeGrid");
        empPanl.addDeleteEmployeeListener(viewCustsLstn);
        con.add(empPanl, BorderLayout.WEST);
        
        // set style
        try {
            UIManager.setLookAndFeel(feelNLook);
            SwingUtilities.updateComponentTreeUI(frame);
        } catch (UnsupportedLookAndFeelException ex1) {
            System.err.println("Unsupported LookAndFeel: " + feelNLook);
        } catch (ClassNotFoundException ex2) {
            System.err.println("LookAndFeel class not found: " + feelNLook);
        } catch (InstantiationException ex3) {
            System.err.println("Could not load LookAndFeel: " + feelNLook);
        } catch (IllegalAccessException ex4) {
            System.err.println("Cannot use LookAndFeel: " + feelNLook);
        }

        UIManager.put("OptionPane.okButtonText", "OK");
        UIManager.put("OptionPane.cancelButtonText", "Cancel");

        frame.setVisible(true);
    }
    
    private static void goToEmployeeListPanel(String empyStatus) {
        
        if (null != empPanl) {
            empPanl.setName("");
            empPanl.removeDeleteEmployeeListener(viewCustsLstn);
            con.remove(empPanl);
            empPanl = null;
        }
        if (null != custPanl) {
            custPanl.setName("");
            con.remove(custPanl);
            custPanl = null;
        }
        //custPanl = new CustomerPanel(empy);
        //custPanl.addSaveEmployeeListener(saveEmpyLstn);
        //con.add(custPanl, BorderLayout.WEST);
        //goBackBtn.setEnabled(true);
        
        empPanl = new EmployeeListPanel(empyStatus, empyRepo);
        empPanl.setName("employeeGrid");
        empPanl.addDeleteEmployeeListener(viewCustsLstn);
        con.add(empPanl, BorderLayout.WEST);
        
        frame.revalidate();
        frame.repaint();
        
        //System.out.println("Bobbi star");
    }
    
    
    private static void goToCustomerListPanel(String custState, int empyId) {
        
        if (null != empPanl) {
            empPanl.removeDeleteEmployeeListener(viewCustsLstn);
            empPanl.setName("");
            con.remove(empPanl);
            empPanl = null;
        }
        if (null != custPanl) {
            custPanl.setName("");
            con.remove(custPanl);
            custPanl = null;
        }
        
        custPanl = new CustomerListPanel(custState, empyId, empyRepo, custRepo);
        custPanl.setName("customerGrid");
        con.add(custPanl, BorderLayout.WEST);
        
        frame.revalidate();
        frame.repaint();
    }
    
    public static void showMessageBox(String message) {
        JOptionPane pane = new JOptionPane(message);
        JInternalFrame intframe = pane.createInternalFrame(frame.getLayeredPane(), "Notice");
        intframe.setName("messageBoxPane");
        frame.getLayeredPane().add(intframe);
        intframe.show();
    }
    
    // for unit testing
    public static JFrame getRootContainer() {
        return frame;
    }
    
    // for unit testing
    public static void setEmpyRepo(EmployeeRepository er) {
        empyRepo = er;
    }
    
    // for unit testing
    public static void setCustRepo(CustomerRepository cr) {
        custRepo = cr;
    }
    
    // for unit testing
    public static void cleanUp() {
        if (null != empPanl) {
            empPanl.removeDeleteEmployeeListener(viewCustsLstn);
            empPanl.setName("Katsuni");
            con.remove(empPanl);
            empPanl = null;
        }
        if (null != custPanl) {
            custPanl.setName("Katsuni");
            con.remove(custPanl);
            custPanl = null;
        }
    }

}
