package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class UserLoginQueryHandler {

    private static Statement stmt;

    public UserLoginQueryHandler(Statement stmt) throws SQLException {
        UserLoginQueryHandler.stmt = stmt;
    }

    public static String[] getAccountIbans() throws SQLException {
        String query = "Select Account_IBAN from Account;";
        String[] data = new String[UserLoginQueryHandler.count("(Select Account_IBAN from Account) a")];

        ResultSet rs = stmt.executeQuery(query);
        int user = 0;
        try {
            while (rs.next()) {
                data[user++]  = rs.getString("Account_IBAN");
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public static String getUserType(String iban) throws SQLException {

        String query0 = "select Account_ID from Account where Account_IBAN = " + "\"" + iban + "\"";
        int id = -1;
        ResultSet rs = stmt.executeQuery(query0);
        if(rs.next()) {
            id = rs.getInt(1);
        }

        String query1;
        String query2;
        String query3;

        query1 = "select s.F_USR_ID from Supplier s where s.F_USR_ID = " + id + ";";
        query2 = "select p.F_USR_ID from Private_citizen p where p.F_USR_ID = " + id + ";";
        query3 = "select c.F_USR_ID from Company c where c.F_USR_ID = " + id + ";";

        rs = stmt.executeQuery(query1);
        if (rs.next()) {
            if (Objects.equals(rs.getString(1), String.valueOf(id))) {
                return "supplier";
            }
        }

        rs = stmt.executeQuery(query2);
        if(rs.next()) {
            if(Objects.equals(rs.getString(1), String.valueOf(id))) {
                return "private_citizen";
            }
        }

        rs = stmt.executeQuery(query3);
        if(rs.next()) {
            if(Objects.equals(rs.getString(1), String.valueOf(id))) {
                return "company";
            }
        }

        return "skata";
    }

    public static String[] getSupplierIds() throws SQLException {
        String query = "Select F_USR_id from Supplier;";
        String[] data = new String[UserLoginQueryHandler.count("(Select F_USR_id from Supplier) a")];

        ResultSet rs = stmt.executeQuery(query);
        int user = 0;
        try {
            while (rs.next()) {
                data[user++]  = rs.getString("F_USR_id");
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public static String[] getTransactionIdsBasedOn(int clientId) throws SQLException {
        String query, query1, query2;
        query  = "Select Transaction_transactionID from Transaction where (F_Client_AccountID = " + clientId + " and Transaction_type = false" + ");";
        query1 = "Select Transaction_transactionID from Transaction where (F_Client_AccountID = " + clientId + " and Transaction_type = false)";
        query2 = "Select Transaction_transactionID from Transaction ";
        String[] data = new String[UserLoginQueryHandler.count("(" + query1 + ")a")];

        ResultSet rs = stmt.executeQuery(query);
        int user = 0;
        try {
            while (rs.next()) {
                data[user++]  = rs.getString("Transaction_transactionID");
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public static int convertIban2Id(String clientIban) throws SQLException {
        String query;
        int clientId = -1;

        query = "select Account_id from Account where Account_IBAN = " + "\"" + clientIban + "\"" + ";";
        ResultSet rs = stmt.executeQuery(query);

        if(rs.next()) clientId = rs.getInt(1);
        return  clientId;
    }

    public static Boolean makeTransaction(String clientIban, int supplierId, float price) throws SQLException {
        String query, query1, query2;

        query = "select Account_id from Account where Account_IBAN = " + "\"" + clientIban +  "\";";
        ResultSet rs = stmt.executeQuery(query);

        int clientId = -1;
        if(rs.next()) clientId = rs.getInt(1);

        query1 = "select p.F_USR_ID from Private_citizen p where p.F_USR_ID = " + clientId + ";";
        query2 = "select c.F_USR_ID from Company c where c.F_USR_ID = " + clientId + ";";

        String suppFirstName = "", suppLastName = "";
        query = "select Supp_First_Name, Supp_Last_Name from Supplier where F_USR_ID = " + supplierId + ";";
        rs = stmt.executeQuery(query);
        if(rs.next()) {
            suppFirstName = rs.getString(1);
            suppLastName  = rs.getString(2);
        }

        System.out.println(query1);

        rs = stmt.executeQuery(query1);
        if(rs.next()) {
            if(Objects.equals(rs.getInt(1), clientId)) {
                System.out.println(clientId + " is PrivateCitizen");
                int transcationID = UserLoginQueryHandler.count("Transaction");
                transcationID++;
                String pcFirstName = "", pcLastName = "";
                query = "select PC_First_Name, PC_Last_Name from Private_citizen where F_USR_ID = " + clientId + ";";
                rs = stmt.executeQuery(query);
                if(rs.next()) {
                    pcFirstName = rs.getString(1);
                    pcLastName  = rs.getString(2);
                    System.out.println(pcFirstName + " " + pcLastName);
                }

                query = "INSERT INTO TRANSACTION VALUES(null," + transcationID + ", " + "\"" + pcFirstName + " " + pcLastName + "\"" + "," + "\"" + suppFirstName + " " + suppLastName + "\"" + ", "
                        + "'" + Generator.getCurrentDate() + "'" + ", "
                        + (price < 0) + "," + price + ", " + supplierId + ", " + clientId + ");";

                System.out.println(query);

                stmt.executeUpdate(query);

                query = "UPDATE Client_account SET Client_remaining = Client_remaining - " + price + ";";
                stmt.executeUpdate(query);

                query = "UPDATE Supplier_account SET Supp_netIncome = Supp_netIncome + " + price + ";";
                stmt.executeUpdate(query);
            }
        }


        rs = stmt.executeQuery(query2);
        if(rs.next()) {
            if(Objects.equals(rs.getString(1), clientId)) {
                System.out.println(clientId + " is Company");



            }
        }

        return true;
    }

    public static Boolean refundTransaction(String clientIban, int transactionID) throws SQLException {
        String query, query1, query2;

        query = "select Account_id from Account where Account_IBAN = " + "\"" + clientIban +  "\";";
        ResultSet rs = stmt.executeQuery(query);

        int clientId = -1;
        if(rs.next()) clientId = rs.getInt(1);

        query1 = "select p.F_USR_ID from Private_citizen p where p.F_USR_ID = " + clientId + ";";
        query2 = "select c.F_USR_ID from Company c where c.F_USR_ID = " + clientId + ";";

        int supplierId = -1;

        query = "select F_Supp_AccountID from Transaction where Transaction_transactionID = " + transactionID + ";";
        rs = stmt.executeQuery(query);

        if(rs.next()) supplierId = rs.getInt(1);

        String suppFirstName = "", suppLastName = "";
        query = "select Supp_First_Name, Supp_Last_Name from Supplier where F_USR_ID = " + supplierId + ";";
        rs = stmt.executeQuery(query);
        if(rs.next()) {
            suppFirstName = rs.getString(1);
            suppLastName  = rs.getString(2);
        }

        System.out.println(query1);

        float cost = -1;
        query = "select Transaction_cost from Transaction where Transaction_transactionID = " + transactionID + ";";
        rs = stmt.executeQuery(query);

        if(rs.next()) cost = rs.getFloat(1);
        cost *= -1;

        rs = stmt.executeQuery(query1);
        if(rs.next()) {
            if(Objects.equals(rs.getInt(1), clientId)) {
                System.out.println(clientId + " is PrivateCitizen");
                int transcationID = UserLoginQueryHandler.count("Transaction");
                transcationID++;
                String pcFirstName = "", pcLastName = "";
                query = "select PC_First_Name, PC_Last_Name from Private_citizen where F_USR_ID = " + clientId + ";";
                rs = stmt.executeQuery(query);
                if(rs.next()) {
                    pcFirstName = rs.getString(1);
                    pcLastName  = rs.getString(2);
                    System.out.println(pcFirstName + " " + pcLastName);
                }

                query = "INSERT INTO TRANSACTION VALUES(null," + transcationID + ", " + "\"" + pcFirstName + " " + pcLastName + "\"" + "," + "\"" + suppFirstName + " " + suppLastName + "\"" + ", "
                        + "'" + Generator.getCurrentDate() + "'" + ", "
                        + (cost < 0) + "," + cost + ", " + supplierId + ", " + clientId + ");";

                System.out.println(query);

                stmt.executeUpdate(query);

                query = "UPDATE Client_account SET Client_remaining = Client_remaining + " + cost + ";";
                stmt.executeUpdate(query);

                query = "UPDATE Supplier_account SET Supp_netIncome = Supp_netIncome - " + cost + ";";
                stmt.executeUpdate(query);
            }
        }


        rs = stmt.executeQuery(query2);
        if(rs.next()) {
            if(Objects.equals(rs.getString(1), clientId)) {
                System.out.println(clientId + " is Company");



            }
        }

        return true;
        //UPDATE SUPPLIER'S NETINCOME
        //UPDATE CLIENT'S balance
        //IF CLIENTS NEW BALANCE IS 0 THEN RETURN FALSE
    }

    private static int count(String table) throws SQLException {
        String query = "SELECT COUNT(*) FROM " + table + ";";
        ResultSet rs = stmt.executeQuery(query);
        rs.next();
        return rs.getInt(1);
    }

    public static void setStatement(Statement stmt) { UserLoginQueryHandler.stmt = stmt; }
}
