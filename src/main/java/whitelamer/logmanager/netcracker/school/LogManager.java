package whitelamer.logmanager.netcracker.school;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class LogManager {
    /**
     * instance
     */
    private static LogManager instance;

    private static synchronized LogManager getInstance() {
        if (instance == null) {
            instance = new LogManager();
        }
        return instance;
    }

    /**
     * iner enum and exeptions
     */
    public enum Type {
        TRACE(0), DEBUG(1), INFO(2), WARN(3), ERROR(4), FATAL(5), ALL(6), OFF(7);
        private int value;

        private Type(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    ;

    public static class LogerNotFound extends RuntimeException {
        private static final long serialVersionUID = 1L;

    }

    /**
     * iner HashMap
     */
    private Map<String, Logger> hm = new HashMap<String, Logger>();

    private static void writeLog(String tag, String string) {
        writeLog(Type.FATAL, tag, string);
    }

    public static void writeLog(Type type, String tag, String string) {
        getInstance();
        Logger l = instance.hm.get(tag);
        if (l == null) {
            throw new LogManager.LogerNotFound();
        } else {
            l.log(type, string);
        }
    }

    public static void loadFromXml(String xml) throws XMLStreamException {
        getInstance();

        try {
            XMLInputFactory f = XMLInputFactory.newFactory();
            Reader reader = new StringReader(xml);
            XMLStreamReader sr = f.createXMLStreamReader(reader);
            XmlMapper xmlMapper = new XmlMapper();
            sr.next();
            instance.hm = xmlMapper.readValue(sr, new TypeReference<Map<String, Logger>>() {
            });
            sr.close();

        } catch (XMLStreamException e) {
            throw e;
        } catch (IOException e) {
        }

        /*!for debug
		List<String> keys = new ArrayList<String>(instance.hm.keySet());
	    for (String key : keys) {
	    System.out.println(key + ": "+ (instance.hm.get(key)));
	    }*/
    }
}
