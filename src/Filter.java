import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Filter {
	private String regexp;
	private boolean accepting;
	private Pattern p;
	
	public void setAccepting(boolean accepting) {
		this.accepting = accepting;
	}

	public String getRegexp() {
		return regexp;
	}

	public void setRegexp(String regexp) {
		this.regexp = regexp;
		p = Pattern.compile(this.regexp);
	}
	public boolean filter_message(String message) {
		Matcher m = p.matcher(message);
		if(m.matches())return accepting;
		return !accepting;
	}
}
