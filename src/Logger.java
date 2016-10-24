import java.util.List;

import com.fasterxml.jackson.annotation.JsonSetter;

//@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_ARRAY, property="handler")
//@JsonSubTypes({
//    @JsonSubTypes.Type(value = FileHandler.class, name = "FileHandler"),
//})
public class Logger {
	private LogManager.Type type = LogManager.Type.ALL;
	private Filter filter;
	//@JacksonXmlProperty( localName = "FileHandler")
	private Handler handler;
	
	private List<Logger> loggers;
	public LogManager.Type getType() {
		return type;
	}
	public void setType(LogManager.Type type) {
		//System.out.println("Logger type:"+type);
		this.type = type;
	}
	@Override
	public String toString(){
		return "I Logger my type:"+ type;
	}
	public void log(LogManager.Type type, String string) {
		// TODO Auto-generated method stub
		if(type==LogManager.Type.ALL||type==this.type){
			if(filter.filter_message(string))handler.writeMessage(string); //unrem after make filter and handler classes
		}
		if(loggers!=null&&!loggers.isEmpty())
		for (Logger log : loggers) {
			log.log(type,string);
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
	@JsonSetter("Handler")
	public void setHandler(Handler handler) {
		//System.out.println("Logger handler:"+handler);
		this.handler = handler;
	}
}
