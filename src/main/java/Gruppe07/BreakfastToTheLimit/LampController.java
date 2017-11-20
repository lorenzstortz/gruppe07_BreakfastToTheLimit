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
					strobe();
				}
				strobing = true;
			} else if (!strobe) {
				for (Lamp l : lamplist) {
					l.setColor(LampColor.ON);
				}
				strobing = false;
				for (int index = lamplist.size() - 1; index >= 0; index--) {
					lamplist.get(index).setColor(LampColor.getColor(i[index]));
				}
			}
			
		}
		
		
		//added
		public void strobe() {
			
			for (Lamp l : lamplist) {
				l.setColor(LampColor.RED);
			}
			
			Thread strobeThread = new Thread() {
				public void run() {
					System.out.println("Strobe started");
					while (strobing) {
						try {
							Thread.sleep(500);
							for (Lamp l : lamplist) {
								l.setColor(LampColor.OFF);
							}
							
							Thread.sleep(500); 
							for (Lamp l : lamplist) {
								l.setColor(LampColor.ON);
							}
							
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}		

				}
			};
			strobeThread.start();
		}
}