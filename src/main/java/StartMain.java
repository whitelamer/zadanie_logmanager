

import netcracker.school.whitelamer.logmanager.LogManager;
import netcracker.school.whitelamer.logmanager.utils.LogType;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;

public class StartMain {
	final static LogManager logger=LogManager.getInstance();


	public static void main(String[] args) {

			logger.loadFromFile("./resources/LogManager.xml");//настраиваем менеджер

			logger.writeLog(LogType.WARN,"filelogger","Start write to file");//пишем сообщение в логера с именем filelogger
	}

}
