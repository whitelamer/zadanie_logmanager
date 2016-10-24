

public class StartMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello World!!!");
		LogManager.loadFromXml("<LogManager>" +
						"<filelogger type=\"DEBUG\"><filter regexp=\".*file\" accepting=\"true\"/><Handler type=\"file\" filename=\"1.log\"/></filelogger>" +
						"<baselogger type=\"INFO\"></baselogger>" +
				    "</LogManager>");
		//filename=\"1.log\"
	    LogManager.writeLog(LogManager.Type.DEBUG,"filelogger","Start write to file");
	}

}
