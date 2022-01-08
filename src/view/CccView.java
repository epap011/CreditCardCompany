package view;

import javax.swing.*;
import javax.swing.plaf.metal.MetalToggleButtonUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class CccView extends JPanel {

    private CardLayout cardLayout;
    private JPanel cardPanel;
    private JTable goodUsersTable;
    private DefaultTableModel tableModel;
    private JButton goodUsersButton;
    private JButton badUsersButton;
    private JButton mostActiveEmployee;

    public CccView(GUI gui) {

        this.setLayout(new BorderLayout());
        this.setBackground(Color.white);
        this.cardLayout = gui.getCardLayout();
        this.cardPanel  = gui.getCardPanel();

        initComponents();
    }

    private void initComponents() {
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        buttonsPanel.setBackground(new Color(255,252,252));
        this.add(buttonsPanel, BorderLayout.NORTH);

        initGoodUsersTable();

        goodUsersButton = new JButton("Good Users");
        personalizeButton(goodUsersButton);
        buttonsPanel.add(goodUsersButton);

        badUsersButton = new JButton("Bad Users");
        personalizeButton(badUsersButton);
        badUsersButton.addActionListener(e -> {
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, "still working on that feature");
        });
        buttonsPanel.add(badUsersButton);

        mostActiveEmployee = new JButton("Most Active Employee");
        personalizeButton(mostActiveEmployee);
        mostActiveEmployee.addActionListener(e -> {
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, "still working on that feature");
        });
        buttonsPanel.add(mostActiveEmployee);

        JButton homeButton = new JButton("Home");
        personalizeButton(homeButton);
        homeButton.addActionListener(e -> cardLayout.show(cardPanel, "home"));
        this.add(homeButton, BorderLayout.SOUTH);
    }

    private void personalizeButton(JButton button) {
        button.setPreferredSize(new Dimension(200,30));
        button.setBackground(new Color(255,248,248));
        button.setForeground(new Color(150,5,40));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createMatteBorder(1,1,1,1, new Color(160,35,35)));
        button.setUI(new MetalToggleButtonUI() {
            @Override
            protected Color getSelectColor() {
                return new Color(250, 230,230);
            }
        });
    }

    private void initGoodUsersTable() {
        String columns[] = {"USER ID", "First Name", "Last Name", "AccountID"};
        String data[][]  = null;

        tableModel = new DefaultTableModel(data, columns);
        goodUsersTable = new JTable(tableModel);

        goodUsersTable.setCellSelectionEnabled(false);
        goodUsersTable.setShowVerticalLines(false);
        this.add(goodUsersTable, BorderLayout.CENTER);
    }

    public void cleanTableModel() {
        tableModel.setRowCount(0);
    }

    public void setGoodUsersData(String[][] data) {
        for (String[] datum : data) {
            tableModel.addRow(datum);
        }
    }

    public void addGoodUsersButtonListener(ActionListener listenerForDoneButton) {
        goodUsersButton.addActionListener(listenerForDoneButton);
    }
}