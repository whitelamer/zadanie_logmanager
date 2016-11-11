package netcracker.school.whitelamer.logmanager;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import netcracker.school.whitelamer.logmanager.exceptions.LogerNotFoundException;

import javax.xml.stream.XMLStreamException;
import java.io.FileReader;
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

    private Map<String, Logger> cacheOfLoggers = new HashMap<>();

    private void LogManager(){}

    public void allLog(String tag, String string) {
        writeLog(LogType.ALL, tag, string);
    }

    public void writeLog(LogType type, String tag, String string) {
        Logger l = cacheOfLoggers.get(tag);
        if (l == null) {
            throw new LogerNotFoundException();
        } else {
            l.log(type, string);
        }
    }

    public void loadFromXml(String xml) {
        try (Reader reader = new StringReader(xml)){
            parseFromReader(reader);
        }catch (XMLStreamException e) {
            System.err.println("[LogManager] Error in xml:"+e.getLocalizedMessage());
        } catch (IOException e) {
            System.err.println("[LogManager] IOException, never can be:"+e.getLocalizedMessage());
        }
    }

    public void loadFromFile(String filePath) {
        try (Reader reader = new FileReader(filePath)){
            parseFromReader(reader);
        }catch (XMLStreamException e) {
            System.err.println("[LogManager] Error in xml:"+e.getLocalizedMessage());
        }catch (IOException e) {
            System.err.println("[LogManager] IOException error while read file {"+filePath+"}:"+e.getLocalizedMessage());
        }
    }

    private void parseFromReader(Reader reader) throws XMLStreamException,IOException {
        XmlMapper xmlMapper = new XmlMapper();
            cacheOfLoggers = xmlMapper.readValue(reader, new TypeReference<Map<String, Logger>>() {
        });
        //System.out.println("LogManager loaded:" + hashMap.toString());
    }
}
