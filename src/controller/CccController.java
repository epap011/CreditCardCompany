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
    }

    class GoodUsersButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                theView.cleanTableModel();
                theView.setGoodUsersData(CccQueryHandler.getGoodUsers());
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
