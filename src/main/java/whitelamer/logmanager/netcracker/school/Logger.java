package whitelamer.logmanager.netcracker.school;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonSetter;

//@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_ARRAY, property="handler")
//@JsonSubTypes({
//    @JsonSubTypes.Type(value = FileHandler.class, name = "FileHandler"),
//})
public class Logger {
    private LogManager.Type type = LogManager.Type.FATAL;
    private Filter filter;
    private Handler handler;

    private List<Logger> loggers = new ArrayList<Logger>();

    public LogManager.Type getType() {
        return type;
    }

    public void setType(LogManager.Type type) {
        //System.out.println("Logger type:"+type);
        this.type = type;
    }

    @Override
    public String toString() {
        return "Logger{" +
                "type=" + type +
                ", filter=" + filter +
                ", handler=" + handler +
                ", loggers=" + loggers +
                '}';
    }

    public void log(LogManager.Type type, String string) {
        // TODO Auto-generated method stub
        if (this.type != LogManager.Type.OFF && type.getValue() >= this.type.getValue() && handler != null) {
            if (filter != null) {
                if (filter.filter_message(string)) {
                    handler.writeMessage(string);
                }
            } else {
                handler.writeMessage(string);
            }
        }
        //if(!loggers.isEmpty())
        for (Logger log : loggers) {
            log.log(type, string);
        }
    }

    public Filter getFilter() {
        return filter;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }

    public Handler getHandler() {
        return handler;
    }

    //@JacksonXmlProperty( localName = "FileHandler")
    @JsonSetter("handler")
    public void setHandler(Handler handler) {
        //System.out.println("Logger handler:"+handler);
        this.handler = handler;
    }

    //public List<Logger> getLoggers() {
    //	return loggers;
    //}
    @JsonSetter("logger")
    public void setLogger(Logger logger) {
        this.loggers.add(logger);
        System.out.println("Logger loggers:" + this.loggers);
    }
}
