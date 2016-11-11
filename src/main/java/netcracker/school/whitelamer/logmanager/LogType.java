package netcracker.school.whitelamer.logmanager;

public enum LogType {
    TRACE(0), DEBUG(1), INFO(2), WARN(3), ERROR(4), FATAL(5), ALL(6), OFF(7);
    private int value;

    LogType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}