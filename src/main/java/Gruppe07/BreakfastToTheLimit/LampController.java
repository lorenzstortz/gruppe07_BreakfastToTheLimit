package Gruppe07.BreakfastToTheLimit;

import java.util.ArrayList;


/**
 * @author sieber, stortz
 *
 */
public class LampController {

		private ArrayList<Lamp> lamplist;
		boolean strobing = false;
		
		public LampController(int count, String username) {		
			lamplist = new ArrayList<Lamp> ();
			for (int i = 1; i <= count; i++) {
				lamplist.add(new Lamp(username, i));
			}
		}
		
		
		public void checkLampColor (int[] i) {
			boolean strobe = false;
			for (int min : i) {
				if (min <= 0 && min > -3) {
					strobe = true;
				}
			}
			if (strobe && !strobing) {
				for (Lamp l : lamplist) {
					l.strobeStart();
				}
				strobing = true;
			} else if (!strobe) {
				for (Lamp l : lamplist) {
					l.setColor(LampColor.ON);
					l.strobeStop();
				}
				strobing = false;
				for (int index = lamplist.size() - 1; index >= 0; index--) {
					lamplist.get(index).setColor(LampColor.getColor(i[index]));
				}
			}	
		}
	
}