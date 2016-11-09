package netcracker.school.whitelamer.logmanager;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import netcracker.school.whitelamer.logmanager.exceptions.LogerNotFound;
import netcracker.school.whitelamer.logmanager.utils.LogType;
import netcracker.school.whitelamer.logmanager.utils.Logger;

import javax.xml.stream.XMLStreamException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Iterator;
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

    private Map<String, Logger> hashMap = new HashMap<>();

    private void LogManager(){}

    public void writeLog(String tag, String string) {
        writeLog(LogType.ALL, tag, string);
    }

    public void writeLog(LogType type, String tag, String string) {
        Logger l = hashMap.get(tag);
        if (l == null) {
            throw new LogerNotFound();
        } else {
            l.log(type, string);
        }
    }

    public void loadFromXml(String xml) throws XMLStreamException {
        try (Reader reader = new StringReader(xml)){
            parseFromReader(reader);
        } catch (IOException e) {
            System.out.println("[LogManager] IOException:"+e.getLocalizedMessage());
        }
    }

    public void loadFromFile(String file) throws XMLStreamException,IOException {
        try (Reader reader = new FileReader(file)){
            parseFromReader(reader);
        }
    }

    private void parseFromReader(Reader reader) throws XMLStreamException,IOException {
        XmlMapper xmlMapper = new XmlMapper();
        hashMap = xmlMapper.readValue(reader, new TypeReference<Map<String, Logger>>() {
        });
        //System.out.println("LogManager loaded:" + hashMap.toString());
    }
}
