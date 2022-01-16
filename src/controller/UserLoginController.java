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
        this.theView.addLoginButtonActionListener(new LoginButtonActionListener());
        this.gui.getMakeTransactionView().addBuyButtonActionListener(new BuyButtonActionListener());
        this.gui.getReturnView().addReturnButtonActionListener(new ReturnButtonActionListener());
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
                UserLoginQueryHandler.makeTransaction((String)theView.getComboBoxValue(), Integer.parseInt((String)gui.getMakeTransactionView().getComboBoxValue()), Float.parseFloat(gui.getMakeTransactionView().getPrice()));
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
}
