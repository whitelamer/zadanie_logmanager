

public class StartMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello World!!!");
		LogManager.loadFromXml("<LogManager>" +
						"<filelogger type=\"DEBUG\">" +
							"<filter regexp=\".*file\" accepting=\"true\"/>" +
							"<handler type=\"file\" filename=\"1.log\"/>" +
							"<logger type=\"WARN\">" +
								"<handler type=\"file\" filename=\"warn.log\"/>" +
							"</logger>"+
							"<logger type=\"ERROR\">" +
								"<handler type=\"file\" filename=\"error.log\"/>" +
							"</logger>"+
						"</filelogger>" +
						"<baselogger type=\"INFO\">" +
							"<handler type=\"base\" basename=\"jdbc:postgresql://localhost:5432/testdb\" user=\"user\" password=\"1234\" insert=\"INSERT INTO LOGS (MESSAGE) VALUES ('%s');\"/>" +
						"</baselogger>" +
				    "</LogManager>");
		//filename=\"1.log\"
	    LogManager.writeLog(LogManager.Type.WARN,"filelogger","Start write to file");
	}

}
