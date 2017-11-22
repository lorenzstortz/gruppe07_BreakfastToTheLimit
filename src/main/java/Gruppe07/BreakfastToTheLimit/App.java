package Gruppe07.BreakfastToTheLimit;

/**
 * @author sieber, stortz
 *
 */
public class App {
	private static final String GOOGLE_API_KEY = "AIzaSyAd40gDCh2eFTHVr6ocvmyElOf7J93BYrQ";
	private static final String username = "2217334838210e7f244460f83b42026f";
	  
  public static void main(String[] args) {
	  
	  LampController lampcont = new LampController(3, username);
	  
	  int[] minutes = new int[3];
	  minutes[0] = 20;
	  minutes[1] = 20;
	  minutes[2] = 20;
 	  
	  try {
		  lampcont.checkLampColor(minutes);
		  Thread.sleep(1000);
		  minutes[0] = 2;
		  lampcont.checkLampColor(minutes);
		  Thread.sleep(1000);
		  minutes[0] = 1;
		  lampcont.checkLampColor(minutes);
		  Thread.sleep(1000);
		  minutes[0] = 0;
		  lampcont.checkLampColor(minutes);
		  Thread.sleep(10000);
		  minutes[0] = 20;
		  lampcont.checkLampColor(minutes);
		  
	  } catch (InterruptedException e) {
		  e.printStackTrace();
	  }
  }
}