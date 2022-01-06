package view;

import javax.swing.*;
import javax.swing.plaf.metal.MetalToggleButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UserTypeView extends JPanel {

    private JButton companyButton;
    private JButton privateCitizenButton;
    private JButton supplierButton;

    public UserTypeView(GUI gui) {
        this.setBackground(new Color(248,248,255));
        this.setLayout(new GridBagLayout());

        companyButton        = new JButton("Company");
        privateCitizenButton = new JButton("Private Citizen");
        supplierButton       = new JButton("Supplier");

        companyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.getCardLayout().show(gui.getCardPanel(), "companyReg");
            }
        });
        personalizeButton(companyButton);
        this.add(companyButton);
        this.add(Box.createHorizontalStrut(10));

        privateCitizenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.getCardLayout().show(gui.getCardPanel(), "privateCitizenReg");
            }
        });
        personalizeButton(privateCitizenButton);
        this.add(privateCitizenButton);
        this.add(Box.createHorizontalStrut(10));

        supplierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.getCardLayout().show(gui.getCardPanel(), "supplierReg");
            }
        });
        personalizeButton(supplierButton);
        this.add(supplierButton);
    }

    private void personalizeButton(JButton button) {
        button.setPreferredSize(new Dimension(180,40));
        button.setBackground(new Color(248,248,255));
        button.setForeground(new Color(40,5,150));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createMatteBorder(1,1,1,1, new Color(35,35,160)));
        button.setUI(new MetalToggleButtonUI() {
            @Override
            protected Color getSelectColor() {
                return new Color(200, 200,255);
            }
        });
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                button.setBackground(new Color(230, 230,250));
            }
            public void mouseExited(MouseEvent evt) {
                button.setBackground(new Color(248,248,255));
            }
        });
    }

}
