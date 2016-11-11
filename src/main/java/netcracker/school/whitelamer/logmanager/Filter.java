package netcracker.school.whitelamer.logmanager;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Filter {
	private String regexp;
	private boolean accepting;
	private Pattern pattern;
	
	public void setAccepting(boolean accepting) {
		this.accepting = accepting;
	}

	public String getRegexp() {
		return regexp;
	}

	public void setRegexp(String regexp) {
		this.regexp = regexp;
		pattern = Pattern.compile(this.regexp);
	}
	public boolean filterMessage(String message) {
		Matcher m = pattern.matcher(message);
		if(m.matches())return accepting;
		return !accepting;
	}

	@Override
	public String toString() {
		return "Filter{" +
				"regexp='" + regexp + '\'' +
				", accepting=" + accepting +
				'}';
	}
}
