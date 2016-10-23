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

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.sun.crypto.provider.HmacMD5;

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
	public enum Type { INFO, WARNING, DEBUG, ERROR, CRITICAL };
	public static class LogerNotFound extends RuntimeException{
		private static final long serialVersionUID = 1L;
		
	}
	/**
	 * iner HashMap
	 */
	private Map<String, Logger> hm = new HashMap<String,Logger>();

	private static void writeLog(Type type, String tag, String string){
		getInstance();
		Logger l=instance.hm.get(string);
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
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello World!!!");
		loadFromXml("<LogManager>" +
						"<fileloger type=\"DEBUG\"></fileloger>" +
						"<baseloger type=\"INFO\"></baseloger>" +
				    "</LogManager>");
		
		List<String> keys = new ArrayList<String>(instance.hm.keySet());
	    for (String key : keys) {
	    System.out.println(key + ": "+ (instance.hm.get(key)));
	    }
	    writeLog(LogManager.Type.DEBUG,"fileloger","Start write to file");
	}
}
