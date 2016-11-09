package netcracker.school.whitelamer.logmanager.databases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class PostgresQueryExecutor implements DataBase {
    private Connection connect;
    private Statement stmt;
    @Override
    public boolean connect(String baseName, String user, String password) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("[LogManager] writeMessage:"+e.getLocalizedMessage());
            return false;
        }
        try (Connection tmp= DriverManager.getConnection(baseName,user, password)){
            tmp.setAutoCommit(true);
            stmt = tmp.createStatement();
            connect = tmp;
        } catch (SQLException e) {
            System.out.println("[LogManager] writeMessage:"+e.getLocalizedMessage());
            return false;
        }


        return true;
    }

    @Override
    public boolean insert(String query) {
        if(stmt!=null){
            try {
                stmt.executeUpdate(query);
            } catch (SQLException e) {
                System.out.println("[LogManager] writeMessage:"+e.getLocalizedMessage());
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean close() {
        try {
            if(stmt!=null){
                stmt.close();
                stmt=null;
            }
        } catch (SQLException e) {
            System.out.println("[LogManager] writeMessage:"+e.getLocalizedMessage());
            return false;
        }finally {
            try {
                if(connect!=null) {
                    connect.close();
                }
            } catch (SQLException e) {
                System.out.println("[LogManager] writeMessage:" + e.getLocalizedMessage());
                return false;
            }
        }
        return true;
    }
}
