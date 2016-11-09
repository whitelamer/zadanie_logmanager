

import netcracker.school.whitelamer.logmanager.LogManager;
import netcracker.school.whitelamer.logmanager.utils.LogType;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;

public class StartMain {
	public static LogManager logger=LogManager.getInstance();


	public static void main(String[] args) {
		try {
			logger.loadFromFile("./resources/LogManager.xml");//настраиваем менеджер

			logger.writeLog(LogType.WARN,"filelogger","Start write to file");//пишем сообщение в логера с именем filelogger

		} catch (XMLStreamException e) {
			System.out.println("[StartLogManager] Error in xml:"+e.getLocalizedMessage());
		} catch (IOException e) {
			System.out.println("[StartLogManager] Error file read:"+e.getLocalizedMessage());
		}
	}

}
