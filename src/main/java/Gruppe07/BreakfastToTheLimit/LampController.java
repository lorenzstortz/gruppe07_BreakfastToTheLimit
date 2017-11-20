package Gruppe07.BreakfastToTheLimit;

import java.util.ArrayList;

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
				if (min <= 0) {
					strobe = true;
				}
			}
			if (strobe && !strobing) {
				for (Lamp l : lamplist) {
					l.strobeOn();
				}
				strobing = true;
			} else if (!strobe) {
				for (int index = lamplist.size() - 1; index >= 0; index--) {
					lamplist.get(index).strobeOff();
					lamplist.get(index).setColor(LampColor.getColor(i[index]));
				}
				strobing = false;
			}
			
		}
		
		
		
}