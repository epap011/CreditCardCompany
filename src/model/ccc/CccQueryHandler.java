package model.ccc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CccQueryHandler {

    private static Statement stmt;

    public CccQueryHandler(Statement stmt) {
        CccQueryHandler.stmt = stmt;
    }

    public static void setStatement(Statement stmt) {
        CccQueryHandler.stmt = stmt;
    }

    public static String[][] getGoodUsers() throws SQLException {
        String table = "Supplier";
        int numberOfGoodUsers = CccQueryHandler.count(table);
        String[][] data = new String[numberOfGoodUsers][4];

        String query    = " Select * FROM Supplier; ";
        ResultSet rs = stmt.executeQuery(query);
        int user = 0;
        try {
            while (rs.next()) {
                String id        = rs.getString("F_USR_id");
                String firstName = rs.getString("Supp_First_Name");
                String lastName  = rs.getString("Supp_Last_Name");
                String accountId = rs.getString("F_Supp_AccountID");

                data[user][0] = id;
                data[user][1] = firstName;
                data[user][2] = lastName;
                data[user][3] = accountId;
                user++;
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    private static int count(String table) throws SQLException {
        String query = "SELECT COUNT(*) FROM " + table + ";";
        ResultSet rs = stmt.executeQuery(query);
        rs.next();
        return rs.getInt(1);
    }
}
