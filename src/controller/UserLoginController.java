package controller;

import model.UserLoginQueryHandler;
import view.GUI;
import view.userLogin.UserLoginView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class UserLoginController {

    private UserLoginView theView;
    private GUI gui;

    public UserLoginController(UserLoginView theView, GUI gui) {
        this.theView = theView;
        this.gui     = gui;

        this.gui.getPrivateCitizenUserView().addMakeTransactionButtonActionListener(new MakeTransactionButtonActionListener());
        this.gui.getPrivateCitizenUserView().addReturnButtonActionListener(new MakeReturnButtonActionListener());
        this.gui.getPrivateCitizenUserView().addPayDebtActionListener(new PayDebtActionListener());
        this.gui.getSupplierUserView().addPayDebtActionListener(new PayDebtActionListener());
        this.gui.getPrivateCitizenUserView().addInfoActionListener(new InfoButtonActionListener());

        this.theView.addLoginButtonActionListener(new LoginButtonActionListener());
        this.gui.getMakeTransactionView().addBuyButtonActionListener(new BuyButtonActionListener());
        this.gui.getReturnView().addReturnButtonActionListener(new ReturnButtonActionListener());
        this.gui.getPayDebtView().addPayButtonActionListener(new PayDebtClientButton());
        this.gui.getInfoViewer().addCheckButtonActionListener(new CheckButtonActionListener());

        gui.getHomePanel().addLoginAsUserButtonActionListener(new LoginAsUserButtonActionListener());
    }

    class LoginAsUserButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                theView.setAccountIdsData(UserLoginQueryHandler.getAccountIbans());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    class LoginButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Login Button Pressed!");
            try {
                theView.setPanelOf(UserLoginQueryHandler.getUserType((String)theView.getComboBoxValue()));
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    class MakeTransactionButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("MakeTransaction Button Pressed");
            try {
                gui.getMakeTransactionView().setAccountIdsData(UserLoginQueryHandler.getSupplierIds());
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        }
    }

    class MakeReturnButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Return Button Pressed");
            try {
                int clientId = -1;
                clientId = UserLoginQueryHandler.convertIban2Id((String)theView.getComboBoxValue());
                gui.getReturnView().setTransactionIds(UserLoginQueryHandler.getTransactionIdsBasedOn(clientId));
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        }
    }

    class BuyButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Buy Button Pressed");
            System.out.println("ComboBox value: " + gui.getMakeTransactionView().getComboBoxValue() + " Price: " + gui.getMakeTransactionView().getPrice());
            try {
                if(!UserLoginQueryHandler.makeTransaction((String)theView.getComboBoxValue(),
                        Integer.parseInt((String)gui.getMakeTransactionView().getComboBoxValue()),
                        Float.parseFloat(gui.getMakeTransactionView().getPrice()))) {
                    gui.getMakeTransactionView().notEnoughMoney();
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    class ReturnButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Return Button Pressed");
            System.out.println("ComboBox value: " + gui.getReturnView().getComboBoxValue());
            try {
                UserLoginQueryHandler.refundTransaction((String)theView.getComboBoxValue(),Integer.parseInt((String)gui.getReturnView().getComboBoxValue()));
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    class PayDebtActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("PayDebt Button Pressed!");

            String owed2CCC;
            try {
                owed2CCC = UserLoginQueryHandler.getAccountRemainingOf((String)gui.getUserLoginView().getComboBoxValue());
                System.out.println(owed2CCC);
                gui.getPayDebtView().setRemainingLabel(owed2CCC);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    class PayDebtClientButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            String debtAmount = gui.getPayDebtView().getDesiredDebtAmount();

            try {
                if(!UserLoginQueryHandler.updateRemaining((String) gui.getUserLoginView().getComboBoxValue(), debtAmount)) {
                    System.out.println("Invalid PayDebt");
                    gui.getPayDebtView().invalidPayDebtAmountMessage();
                }

                gui.getPayDebtView().setRemainingLabel(UserLoginQueryHandler.getOwedToCCC((String)gui.getUserLoginView().getComboBoxValue()));
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    class InfoButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            gui.getInfoViewer().cleanTransactionHistoryTable();
        }
    }

    class CheckButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Check button pressed!");
            String userIban = (String)theView.getComboBoxValue();
            String dateFrom = gui.getInfoViewer().getDateFrom();
            String dateTo   = gui.getInfoViewer().getDateTo();
            try {
                gui.getInfoViewer().cleanTransactionHistoryTable();
                gui.getInfoViewer().setTransactionHistory(UserLoginQueryHandler.getTransactionHistory(userIban, dateFrom, dateTo));
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
