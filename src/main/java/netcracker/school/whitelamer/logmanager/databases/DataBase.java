package netcracker.school.whitelamer.logmanager.databases;

public interface DataBase {
    boolean connect(String baseName,String user, String password);
    boolean insert(String query);
    boolean close();
}
