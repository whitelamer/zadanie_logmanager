package netcracker.school.whitelamer.logmanager.databasequerys;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class PostgresQueryExecutor implements DataBaseQueryExecutor {

    @Override
    public boolean executeQuery(String baseName, String user, String password, String query) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("[LogManager] writeMessage error class org.postgresql.Driver not found:"+e.getLocalizedMessage());
            return false;
        }
        try (Connection tmp= DriverManager.getConnection(baseName,user, password);Statement stmt = tmp.createStatement()){
            tmp.setAutoCommit(true);
            try {
                stmt.executeUpdate(query);
            } catch (SQLException e) {
                System.err.println("[LogManager] writeMessage error execute query {"+query+"}:"+e.getLocalizedMessage());
                return false;
            }
        } catch (SQLException e) {
            System.err.println("[LogManager] writeMessage error execute query:"+e.getLocalizedMessage());
            return false;
        }
        return false;
    }
}
