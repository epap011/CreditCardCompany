package view;

import view.ccc.CccView;
import view.registration.CompanyRegistrationView;
import view.registration.PrivateCitizenRegistrationView;
import view.registration.SupplierRegistrationView;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;

public class GUI extends JFrame{

    private JPanel        cardPanel;
    private HomeView homePanel;
    private UserTypeView userTypePanel;
    private CompanyRegistrationView companyRegistrationPanel;
    private PrivateCitizenRegistrationView privateCitizenRegistrationPanel;
    private SupplierRegistrationView supplierRegistrationPanel;
    private CccView cccView;
    private OverviewView overviewPanel;
    private CardLayout    cardLayout;

    public GUI() {
        UIManager.put("ComboBox.selectionBackground", new ColorUIResource(new Color(220,220,240)));

        this.setTitle("CCC App");
        this.setSize(825, 550);
        this.setMinimumSize(new Dimension(825, 550));
        this.setResizable(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        cardPanel  = new JPanel();

        cardPanel.setLayout(cardLayout);
        this.setLayout(cardLayout);

        initComponents();
        this.setVisible(true);
    }

    public void initComponents() {
         homePanel                       = new HomeView(this);
         userTypePanel                   = new UserTypeView(this);
         companyRegistrationPanel        = new CompanyRegistrationView(this);
         privateCitizenRegistrationPanel = new PrivateCitizenRegistrationView(this);
         supplierRegistrationPanel       = new SupplierRegistrationView(this);
         overviewPanel                   = new OverviewView(this);
         cccView                         = new CccView(this);



         cardPanel.add(homePanel, "home");
         cardPanel.add(userTypePanel,"user_type");
         cardPanel.add(companyRegistrationPanel, "companyReg");
         cardPanel.add(privateCitizenRegistrationPanel, "privateCitizenReg");
         cardPanel.add(supplierRegistrationPanel, "supplierReg");
         cardPanel.add(overviewPanel, "overview");
         cardPanel.add(cccView, "ccc");

         this.add(cardPanel);
    }

    public CardLayout getCardLayout() { return cardLayout; }

    public JPanel getCardPanel() { return cardPanel; }

    public CompanyRegistrationView getCompanyRegistrationPanel() {
        return companyRegistrationPanel;
    }

    public PrivateCitizenRegistrationView getPrivateCitizenRegistrationPanel() { return privateCitizenRegistrationPanel; }

    public SupplierRegistrationView getSupplierRegistrationPanel() {
        return supplierRegistrationPanel;
    }

    public CccView getCccView() { return cccView; }
}