package netcracker.school.whitelamer.logmanager;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import netcracker.school.whitelamer.logmanager.exceptions.LogerNotFound;
import netcracker.school.whitelamer.logmanager.utils.LogType;
import netcracker.school.whitelamer.logmanager.utils.Logger;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

public class LogManager {
    /**
     * instance
     */

    private static volatile LogManager instance;

    public static LogManager getInstance() {
        LogManager localInstance = instance;
        if (localInstance == null) {
            synchronized (LogManager.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new LogManager();
                }
            }
        }
        return localInstance;
    }

    private Map<String, Logger> hm = new HashMap<String, Logger>();
    private void LogManager(){}
    private void writeLog(String tag, String string) {
        writeLog(LogType.FATAL, tag, string);
    }

    public void writeLog(LogType type, String tag, String string) {
        Logger l = hm.get(tag);
        if (l == null) {
            throw new LogerNotFound();
        } else {
            l.log(type, string);
        }
    }

    public static void loadFromXml(String xml) throws XMLStreamException {
        getInstance();
        try (Reader reader = new StringReader(xml)){
            XmlMapper xmlMapper = new XmlMapper();
            instance.hm = xmlMapper.readValue(reader, new TypeReference<Map<String, Logger>>() {
            });
        } catch (IOException e) {
            System.out.println("[LogManager] IOException:"+e.getLocalizedMessage());
        }
    }
}
