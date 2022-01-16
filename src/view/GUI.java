package view;

import view.cccLogin.CccView;
import view.registration.CompanyRegistrationView;
import view.registration.PrivateCitizenRegistrationView;
import view.registration.SupplierRegistrationView;
import view.userLogin.*;

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
    private UserLoginView userLoginView;
    private PrivateCitizenUserView privateCitizenUserView;
    private CompanyUserView companyUserView;
    private SupplierUserView supplierUserView;
    private OverviewView overviewPanel;
    private MakeTransactionView makeTransactionView;
    private CardLayout    cardLayout;
    private ReturnView returnView;

    public GUI() {
        UIManager.put("ComboBox.selectionBackground", new ColorUIResource(new Color(220,220,240)));

        this.setTitle("CCC App");
        this.setSize(825, 550);
        this.setMinimumSize(new Dimension(825, 550));
        this.setResizable(false);
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
         userLoginView                   = new UserLoginView(this);
         privateCitizenUserView          = new PrivateCitizenUserView(this);
         companyUserView                 = new CompanyUserView(this);
         supplierUserView                = new SupplierUserView(this);
         makeTransactionView             = new MakeTransactionView();
         returnView                      = new ReturnView();


         cardPanel.add(homePanel, "home");
         cardPanel.add(userTypePanel,"user_type");
         cardPanel.add(companyRegistrationPanel, "companyReg");
         cardPanel.add(privateCitizenRegistrationPanel, "privateCitizenReg");
         cardPanel.add(supplierRegistrationPanel, "supplierReg");
         cardPanel.add(overviewPanel, "overview");
         cardPanel.add(cccView, "ccc");
         cardPanel.add(userLoginView, "userLogin");
         cardPanel.add(privateCitizenUserView, "private_user_login");
         cardPanel.add(companyUserView, "company_user_login");
         cardPanel.add(supplierUserView, "supplier_user_login");
         cardPanel.add(makeTransactionView, "make_transaction");
         cardPanel.add(returnView, "return");

         this.add(cardPanel);
    }

    public CardLayout getCardLayout() { return cardLayout; }

    public JPanel getCardPanel() { return cardPanel; }

    public HomeView getHomePanel() {return homePanel; }

    public CompanyRegistrationView getCompanyRegistrationPanel() {
        return companyRegistrationPanel;
    }

    public PrivateCitizenRegistrationView getPrivateCitizenRegistrationPanel() { return privateCitizenRegistrationPanel; }

    public SupplierRegistrationView getSupplierRegistrationPanel() {
        return supplierRegistrationPanel;
    }

    public CccView getCccView() { return cccView; }

    public UserLoginView getUserLoginView() { return userLoginView; }

    public MakeTransactionView getMakeTransactionView() { return makeTransactionView; }

    public ReturnView getReturnView() {return returnView; }

    public PrivateCitizenUserView getPrivateCitizenUserView() {return privateCitizenUserView; }
}