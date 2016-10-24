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
	public enum Type { INFO(0), DEBUG(1), WARN(2), ERROR(3), FATAL(4); 
			private int value; 
			private Type(int value) { 
				this.value=value; 
				}
			public int getValue() {
				return value;
			}
		};
	public static class LogerNotFound extends RuntimeException{
		private static final long serialVersionUID = 1L;
		
	}
	/**
	 * iner HashMap
	 */
	private Map<String, Logger> hm = new HashMap<String,Logger>();

	private static void writeLog(String tag, String string){
		writeLog(Type.FATAL, tag, string);
	}
	
	public static void writeLog(Type type, String tag, String string){
		getInstance();
		Logger l=instance.hm.get(tag);
		if(l==null){
			throw new LogManager.LogerNotFound();
		}else{
			l.log(type,string);
		}
	}
	
	public static void loadFromXml(String xml){
		getInstance();
		//JacksonXmlModule module = new JacksonXmlModule();
		// to default to using "unwrapped" Lists:
		//module.setDefaultUseWrapper(false);
		try {
			XMLInputFactory f = XMLInputFactory.newFactory();
			Reader reader = new StringReader(xml);
			XMLStreamReader sr = f.createXMLStreamReader(reader);
			XmlMapper xmlMapper = new XmlMapper();	
			//xmlMapper.enableDefaultTyping(); // default to using DefaultTyping.OBJECT_AND_NON_CONCRETE
			//xmlMapper.enableDefaultTyping();
			sr.next();
			instance.hm = xmlMapper.readValue(sr, new TypeReference<Map<String, Logger>>() { });
			//instance.addLogger("Tag", value);
			sr.close();
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<String> keys = new ArrayList<String>(instance.hm.keySet());
	    for (String key : keys) {
	    System.out.println(key + ": "+ (instance.hm.get(key)));
	    }
	}
}
