/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package humanresources;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import humanresources.businessdomain.*;
import humanresources.views.*;

/**
 *
 * @author Vincent
 */
public class StartUp {

    static EmployeeFactory empF = new EmployeeFactory();
    static EmployeeRepository rep;

    static CustomerFactory cusF = new CustomerFactory();
    static CustomerRepository crep;

    private final static String feelNLook = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
    private static String panelStatus = "empy";  // empy || cust
    private static String empyStatus = "all"; // all || sales || others
    private static String custStatus = "all"; // all || agent

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

    private static EmployeeListPanel empPanl;
    //private static EmployeeListPanel empPanl;

    public static void main(String[] args) {

        crep = CustomerRepository.getRepository();
        rep = EmployeeRepository.getRepository();

        /**
         * ***********************************************
         * create listeners
	 ************************************************
         */
        
        // menu listeners
        mItemAllEmpyeLstn = new ActionListener() {
            public void actionPerformed(ActionEvent aevt) {
                if ("empy" == panelStatus) {
                    if ("all" == empyStatus) {
                        // nothing
                        return;
                    }
                    else if ("sales" == empyStatus) {
                        goToEmployeeListPanel("All Employees", "all");
                    }
                    else {
                        goToEmployeeListPanel("All Employees", "all");
                    }
                }
                else {
                    goToEmployeeListPanel("All Employees", "all");
                }
                
                System.out.println("User's choice: ");
            }
        };
        
        mItemAllSalesLstn = new ActionListener() {
            public void actionPerformed(ActionEvent aevt) {
                System.out.println("User's choice: ");
            }
        };
        
        mItemAllOtherEmpyLstn = new ActionListener() {
            public void actionPerformed(ActionEvent aevt) {
                System.out.println("User's choice: ");
            }
        };
        
        mItemAllCustmLstn = new ActionListener() {
            public void actionPerformed(ActionEvent aevt) {
                System.out.println("User's choice: ");
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
        mItemAllEmpye.addActionListener(mItemAllEmpyeLstn);
        menuEmployee.add(mItemAllEmpye);
        
        mItemAllSales.addActionListener(mItemAllSalesLstn);
        menuEmployee.add(mItemAllSales);
        
        mItemAllOtherEmpy.addActionListener(mItemAllOtherEmpyLstn);
        menuEmployee.add(mItemAllOtherEmpy);
        
        menuBar.add(menuEmployee);

        mItemAllCustm.addActionListener(mItemAllCustmLstn);
        menuCustomer.add(mItemAllCustm);
        
        menuBar.add(menuCustomer);

        frame.setJMenuBar(menuBar);

        empPanl = new EmployeeListPanel("All Employees", "all");
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
    
    private static void goToEmployeeListPanel(String title, String empyStatus) {
        con.remove(empPanl);
        //con.
        
        empPanl = null;
        
        //custPanl = new CustomerPanel(empy);
        //custPanl.addSaveEmployeeListener(saveEmpyLstn);
        //con.add(custPanl, BorderLayout.WEST);
        //goBackBtn.setEnabled(true);
        
        empPanl = new EmployeeListPanel(title, empyStatus);
        con.add(empPanl, BorderLayout.WEST);
        
        
        frame.revalidate();
        frame.repaint();
    }
    
    
    
    
    

}
