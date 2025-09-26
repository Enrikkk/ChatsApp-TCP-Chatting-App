package communication;

import java.time.*;
import java.time.format.*;

public class timeFormatter {
	
	// Atributes
	private DateTimeFormatter formatter;
	
	// Constructor
	public timeFormatter(String pattern) {
		if(pattern.equals("hourDate"))
			formatter = DateTimeFormatter.ofPattern("HH:mm:ss - MM/dd/yyyy");
		else if(pattern.equals("hour"))
			formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
	}
	
	// Main Function.
	public String format(LocalDateTime time) {
		return time.format(formatter);
	}
	
}
