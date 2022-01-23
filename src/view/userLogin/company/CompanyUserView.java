package view.userLogin.company;

import view.GUI;

import javax.swing.*;
import javax.swing.plaf.metal.MetalToggleButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CompanyUserView extends JPanel {

    private JButton makeTransactionsButton;
    private JButton returnButton;
    private JButton paydebtButton;
    private JButton infoButton;
    private JButton homeButton;
    private CardLayout cardLayout;
    private JPanel cardPanel;

    public CompanyUserView(GUI gui) {
        this.setBackground(Color.WHITE);
        this.setLayout(new BorderLayout());

        this.cardLayout = gui.getCardLayout();
        this.cardPanel  = gui.getCardPanel();
        initComponents();
    }

    private void initComponents() {
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        buttonsPanel.setBackground(new Color(252,252,255));
        this.add(buttonsPanel, BorderLayout.NORTH);

        makeTransactionsButton = new JButton("Make Transactions");
        makeTransactionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "companyTransactions");
            }
        });

        personalizeButton(makeTransactionsButton);
        buttonsPanel.add(makeTransactionsButton);

        returnButton = new JButton("Return");
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "return");
            }
        });
        personalizeButton(returnButton);
        buttonsPanel.add(returnButton);

        paydebtButton = new JButton("Pay debt");
        personalizeButton(paydebtButton);
        buttonsPanel.add(paydebtButton);
        paydebtButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "payDebt");
            }
        });

        infoButton = new JButton("Info");
        infoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "companyInfo");
            }
        });
        personalizeButton(infoButton);
        buttonsPanel.add(infoButton);

        homeButton = new JButton("Home");
        personalizeButton(homeButton);
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "home");
            }
        });
        this.add(homeButton, BorderLayout.SOUTH);
    }

    private void personalizeButton(JButton button) {
        button.setPreferredSize(new Dimension(150,30));
        button.setBackground(new Color(248,248,255));
        button.setForeground(new Color(40,5,150));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createMatteBorder(1,1,1,1, new Color(35,35,160)));
        button.setUI(new MetalToggleButtonUI() {
            @Override
            protected Color getSelectColor() {
                return new Color(230, 230,250);
            }
        });
    }

    public void addMakeTransactionButtonActionListener(ActionListener actionListener) {
        makeTransactionsButton.addActionListener(actionListener);
    }

    public void addReturnButtonActionListener(ActionListener actionListener) {
        returnButton.addActionListener(actionListener);
    }

    public void addPayDebtActionListener(ActionListener actionListener) {
        paydebtButton.addActionListener(actionListener);
    }

    public void addInfoActionListener(ActionListener actionListener) {
        infoButton.addActionListener(actionListener);
    }
}
