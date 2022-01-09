package controller;

import model.ccc.CccQueryHandler;
import view.CccView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class CccController {

    private CccView theView;

    public CccController(CccView theView) {
        this.theView = theView;

        this.theView.addGoodUsersButtonListener(new GoodUsersButton());
        this.theView.addBadUsersButtonListener(new BadUsersButton());
        this.theView.addMostActiveEmployeeButtonListener(new MostActiveEmployeeButton());
    }

    class GoodUsersButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                theView.getTablesCardLayout().show(theView.getTablesPanel(), "goodUsersTable");
                theView.cleanGoodUsersTableModel();
                theView.setGoodUsersData(CccQueryHandler.getGoodUsers());
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    class BadUsersButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                theView.getTablesCardLayout().show(theView.getTablesPanel(), "badUsersTable");
                theView.cleanBadUsersTableModel();
                theView.setBadUsersData(CccQueryHandler.getBadUsers());
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    class MostActiveEmployeeButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                theView.getTablesCardLayout().show(theView.getTablesPanel(), "mostActiveEmployeeTable");
                theView.cleanMostActiveEmployeeTableModel();
                theView.setMostActiveEmployeeData(CccQueryHandler.getMostActiveEmployee());
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

}
