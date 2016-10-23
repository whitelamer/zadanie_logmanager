import java.util.List;


public class Logger {
	private LogManager.Type type;
	private Object filter;
	private Object handler;
	private List<Logger> loggers;
	public LogManager.Type getType() {
		return type;
	}
	public void setType(LogManager.Type type) {
		this.type = type;
	}
	@Override
	public String toString(){
		return "I Logger my type:"+ type;
	}
	public void log(LogManager.Type type, String string) {
		// TODO Auto-generated method stub
		if(type==this.type){
			System.out.println(string);
			//if(filter.filter_message(string))handler.writeMessage(string); //unrem after make filter and handler classes
		}else{
			for (Logger log : loggers) {
				log.log(type,string);
			}
		}
	}
}
