package model.company;

import model.Generator;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CompanyQueryHandler {

    private static Statement stmt;

    public CompanyQueryHandler(Statement stmt) {
        CompanyQueryHandler.stmt = stmt;
    }

    public static void registerCompany(Company company) throws SQLException {
        int registerID;

        registerID = CompanyQueryHandler.count("Account");
        registerID++;

        insertAccount(registerID, company);
        insertClientAccount(registerID);
        insertUsrType(registerID, company);
        insertClient(registerID);
        insertCompany(registerID, company);
    }
    private static void insertAccount(int accountID, Company company) throws SQLException {
        String query;

        query = " INSERT INTO Account VALUES (" + accountID + "," + company.getCompanyAccount().getIban() + "," + company.getCompanyAccount().getExpDate() + "," + true + "," + 1 + ")" + ";" ;

        System.out.println("SQL Query: " + query);
        stmt.executeUpdate(query);
    }

    private static void insertClientAccount(int clientAccountID) throws SQLException {
        String query;

        query = " INSERT INTO Client_account VALUES (" + "5000" + "," + "5000" + "," + clientAccountID + ")" + ";" ;
        System.out.println("SQL Query: " + query);

        stmt.executeUpdate(query);
    }

    private static void insertUsrType(int userID, Company company) throws SQLException {
        String subDate = "temp", query;

        subDate = Generator.expDateGenerate();

        query = " INSERT INTO USR_type VALUES (" + userID + "," + subDate + "," + company.getEmail() + "," + company.getAddress().getCountry() + ","
                + company.getAddress().getCity() + "," + company.getAddress().getStreet() + "," + company.getAddress().getNumber() + ","
                + company.getAddress().getPostalCode() + "," + userID + ")" + ";" ;

        System.out.println("SQL Query: " + query);
        stmt.executeUpdate(query);
    }

    private static void insertClient(int userID) throws SQLException {
        String query;

        query = " INSERT INTO Client VALUES (" + userID + "," + userID +")" + ";" ;

        System.out.println("SQL Query: " + query);
        stmt.executeUpdate(query);
    }

    private static void insertCompany(int companyID, Company company) throws SQLException {
        String query;

        query = " INSERT INTO Company VALUES (" + company.getCompanyName() + "," + company.getCeoName() +
                "," + company.getNumOfEmployees() + "," + companyID + "," + companyID +")" + ";" ;

        System.out.println("SQL Query: " + query);
        stmt.executeUpdate(query);
    }

    private static int count(String table) throws SQLException {
        String query = "SELECT COUNT(*) FROM " + table + ";";
        ResultSet rs = stmt.executeQuery(query);
        rs.next();
        return rs.getInt(1);
    }

    public static void setStatement(Statement stmt) { CompanyQueryHandler.stmt = stmt; }
}
