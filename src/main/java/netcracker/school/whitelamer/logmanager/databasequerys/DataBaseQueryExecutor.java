package netcracker.school.whitelamer.logmanager.databasequerys;

public interface DataBaseQueryExecutor {
    boolean executeQuery(String baseName,String user, String password,String query);
}
