import controller.ControllerContainer;
import model.*;
import model.company.CompanyQueryHandler;
import model.privateCitizen.PrivateCitizenQueryHandler;
import model.supplier.SupplierQueryHandler;
import view.GUI;

public class Main {

    public static void main(String[] args) {

        GUI gui = new GUI();
        Generator model = new Generator();
        ControllerContainer controllerContainer = new ControllerContainer(gui, model);

        try {
            DatabaseConnection ccc_database_connection = new DatabaseConnection("root", "");
            CompanyQueryHandler.setStatement(ccc_database_connection.getStatement());
            PrivateCitizenQueryHandler.setStatement(ccc_database_connection.getStatement());
            SupplierQueryHandler.setStatement(ccc_database_connection.getStatement());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
