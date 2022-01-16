package controller;

import view.GUI;
import model.Generator;

public class ControllerContainer {

    private CompanyRegistrationController companyRegistrationController;
    private PrivateCitizenRegistrationController privateCitizenRegistrationController;
    private SupplierRegistrationController supplierRegistrationController;
    private CccController cccController;
    private UserLoginController userLoginController;

    public ControllerContainer(GUI gui, Generator generator) {
        companyRegistrationController        = new CompanyRegistrationController(gui.getCompanyRegistrationPanel(), generator);
        privateCitizenRegistrationController = new PrivateCitizenRegistrationController(gui.getPrivateCitizenRegistrationPanel(), generator);
        supplierRegistrationController       = new SupplierRegistrationController(gui.getSupplierRegistrationPanel(), generator);
        cccController                        = new CccController(gui.getCccView());
        userLoginController                  = new UserLoginController(gui.getUserLoginView(), gui);

    }
}
