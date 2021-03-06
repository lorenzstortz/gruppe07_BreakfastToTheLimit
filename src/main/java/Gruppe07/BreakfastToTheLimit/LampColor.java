package Gruppe07.BreakfastToTheLimit;


/**
 * @author sieber, stortz
 *
 */
public enum LampColor {
	
	WHITE("{\"on\":true, \"sat\":0, \"bri\":254, \"hue\":0}"),
	ORANGE("{\"on\":true, \"sat\":254, \"bri\":254, \"hue\":10000}"),
	RED("{\"on\":true, \"sat\":254, \"bri\":254, \"hue\":0}"),
	OFF("{\"on\":false}"),
	ON("{\"on\":true}");
	
	
	private String body;
	
	LampColor(String body) {
		this.body = body;
	}

	public String getBody(){
		return body;
	}
	
	public static LampColor getColor(int i) {
		if (i <= 1 && i > -3) {
			return RED; 
		} else if (i <= 2 && i > -3) {
			return ORANGE;
		} else {
			return WHITE;
		}
	} 
	
}
