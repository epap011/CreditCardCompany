package view.userLogin;

import javax.swing.*;
import javax.swing.plaf.metal.MetalToggleButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReturnView extends JPanel {

    private JLabel transactionLabel = new JLabel("Transaction");
    private JLabel transactionsMadeLabel = new JLabel("Select Transaction");
    private JComboBox availableSuppliersComboBox = new JComboBox();
    private JTextField paymentAmounTextField = new JTextField();
    private JButton returnButton = new JButton("RETURN");

    public ReturnView() {
        this.setLayout(null);
        this.setBackground(Color.WHITE);

        transactionLabel.setBounds(330,20,200,20);
        transactionLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
        this.add(transactionLabel);

        transactionsMadeLabel.setBounds(240,150,200,20);
        transactionsMadeLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
        this.add(transactionsMadeLabel);

        availableSuppliersComboBox.setBounds(380, 150, 100, 30);
        this.add(availableSuppliersComboBox);

        personalizeButton(returnButton);
        returnButton.setBounds(480, 150, 50, 30);
        this.add(returnButton);
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

    public void setTransactionIds(String[] data) {
        availableSuppliersComboBox.removeAllItems();
        for(int i = 0; i < data.length; i++) {
            availableSuppliersComboBox.addItem(data[i]);
        }
    }

    public Object getComboBoxValue() {
        return availableSuppliersComboBox.getSelectedItem();
    }

    public void addReturnButtonActionListener(ActionListener actionListener) {
        returnButton.addActionListener(actionListener);
    }

    public String getPrice() { return paymentAmounTextField.getText(); }
}

