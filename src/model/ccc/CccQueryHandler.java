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
        String table = "(Select * FROM USR_type u, Account a Where (u.USR_ID = a.Account_ID) and (a.Account_owedToCCC = 0)) aa;";
        int numberOfGoodUsers = CccQueryHandler.count(table);
        String[][] data = new String[numberOfGoodUsers][3];

        String query    = " Select * FROM USR_type u, Account a Where (u.USR_ID = a.Account_ID) and (a.Account_owedToCCC = 0); ";
        ResultSet rs    = stmt.executeQuery(query);

        int user = 0;
        try {
            while (rs.next()) {
                String id        = rs.getString("USR_id");
                String email     = rs.getString("USR_email");
                String iban      = rs.getString("Account_IBAN");

                data[user][0] = id;
                data[user][1] = email;
                data[user][2] = iban;
                user++;
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public static String[][] getBadUsers() throws SQLException {
        String table = "(Select * FROM USR_type u, Account a Where (u.USR_ID = a.Account_ID) and (a.Account_owedToCCC > 0)) aa;";
        int numberOfGoodUsers = CccQueryHandler.count(table);
        String[][] data = new String[numberOfGoodUsers][3];

        String query    = " Select * FROM USR_type u, Account a Where (u.USR_ID = a.Account_ID) and (a.Account_owedToCCC > 0); ";
        ResultSet rs    = stmt.executeQuery(query);

        int user = 0;
        try {
            while (rs.next()) {
                String id        = rs.getString("USR_id");
                String email     = rs.getString("USR_email");
                String iban      = rs.getString("Account_IBAN");

                data[user][0] = id;
                data[user][1] = email;
                data[user][2] = iban;
                user++;
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public static String[][] getMostActiveEmployee() throws SQLException {
        String table = "(SELECT F_Supp_AccountID,COUNT(F_Supp_AccountID) AS cnt " +
                "FROM Transaction " +
                "GROUP BY F_Supp_AccountID " +
                "HAVING cnt=(SELECT COUNT(F_Supp_AccountID) as count " +
                "FROM Transaction " +
                "GROUP BY F_Supp_AccountID " +
                "ORDER BY count desc limit 1)) aaa;";
        int numberOfGoodUsers = CccQueryHandler.count(table);
        String[][] data = new String[numberOfGoodUsers][3];
        
        String query = "SELECT F_Supp_AccountID,COUNT(F_Supp_AccountID) AS cnt " +
                "FROM Transaction " +
                "GROUP BY F_Supp_AccountID " +
                "HAVING cnt=(SELECT COUNT(F_Supp_AccountID) as count " +
                "FROM Transaction " +
                "GROUP BY F_Supp_AccountID " +
                "ORDER BY count desc limit 1);";

        ResultSet rs    = stmt.executeQuery(query);

        int user = 0;
        try {
            while (rs.next()) {
                String id        = rs.getString("F_Supp_AccountID");
                String cnt       = rs.getString("cnt");

                data[user][0] = id;
                data[user][1] = cnt;
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
