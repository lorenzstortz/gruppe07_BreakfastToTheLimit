package Gruppe07.BreakfastToTheLimit;

import Gruppe07.BreakfastToTheLimit.model.Roommate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author sieber, stortz
 *
 */
public class App {
	private static final String GOOGLE_API_KEY = "AIzaSyAd40gDCh2eFTHVr6ocvmyElOf7J93BYrQ";

	private static String username = "2217334838210e7f244460f83b42026f";
	private static String usernameDebug = "newdeveloper";

	private static boolean running = true;
	private static List<Roommate> roommates = new ArrayList<>();
	public static boolean debug;
	  
  public static void main(String[] args) {

	  askForTestMode();
  	  if(!acceptUserInput()){
		  Roommate roommate1 = new Roommate("Paula Puenktlich", "Poing", "Hochschule+München", "bycicling", 18, 40  );
		  Roommate roommate2 = new Roommate("Lothar Late", "Ostbahnhof", "Hochschule+München", "car", 19,30 );
		  Roommate roommate3 = new Roommate("Ich Selber", "Hochschule+München", "Hochschule+München", "car", 22,0);
		  roommates.add(roommate1);
		  roommates.add(roommate2);
		  roommates.add(roommate3);
	  }

	  for(Roommate rm : roommates)(new Thread(new RoommateHandler(rm))).start();

	  LampController lampcont = new LampController(3, username);

	  Runnable run = new Runnable() {
		  public void run() {
			  try {
				  int[] minutes = new int[3];
				  while(running){
					  Thread.sleep(1000);
					  for(int i = 0 ; i < 3; i++){
					  	if(!roommates.get(i).isLeft()){
							minutes[i] = roommates.get(i).getRemainingTimeInMinutes();
						}else {
					  		minutes[i] = Integer.MAX_VALUE;
						}

					  }


					  System.out.println(Arrays.toString(minutes));
					  lampcont.checkLampColor(minutes);
				  }

			  } catch (InterruptedException e) {
				  e.printStackTrace();
			  }
		  }
	  };
	  new Thread(run).start();

	  //control loop
	  Scanner sc = new Scanner(System.in);
		while(running){
			String tmp = sc.next();
//			if(tmp.contains("leave")){
//				String name = tmp.substring(6);
//			}
			if(tmp.contains("l")){
				int id = Integer.parseInt(tmp.substring(1));
				roommates.get(id-1).setLeft(true);
			}

			if(tmp.contains("r")){
				int id = Integer.parseInt(tmp.substring(1));
				roommates.get(id-1).setLeft(false);
			}
		}


  }

	private static void askForTestMode() {
		System.out.println("Do you want to use the Hue Emulator for testing? [y/n]:");
		Scanner sc = new Scanner(System.in);
		while(true){
			String s = sc.next();
			if(s.equals("y")||s.equals("Y")){
				debug = true;
				username = usernameDebug;
				break;
			}
			else if(s.equals("n")||s.equals("N")){
				debug = false;
				username = usernameDebug;
				break;
			}
		}
	}

	private static boolean acceptUserInput() {
  		System.out.println("Do you want to enter your own data? [y/n]:");
  		Scanner sc = new Scanner(System.in);
  		while(true){
  			String s = sc.next();
  			if(s.equals("y")||s.equals("Y")){
  				break;
			}
			else if(s.equals("n")||s.equals("N")){
  				return false;
			}
		}
		for(int i = 1; i < 4; i++){
  			System.out.println("Enter data for roommate number: " + i);
			System.out.println("Name:");
  			String name = sc.next();
			System.out.println("Destination:");
			String destination = sc.next();
			System.out.print("Origin:");
			String origin = sc.next();
			System.out.print("Mode:");
			String mode = sc.next();
			System.out.print("Hour of Arrival:");
			String hour = sc.next();
			System.out.print("Minute of Arrival:");
			String minute = sc.next();
			System.out.println();
			try{
				roommates.add(new Roommate(name,destination,origin,mode,Integer.parseInt(hour),Integer.parseInt(minute)));
			}catch(NumberFormatException e){
				System.out.println("Invalid time, try again!");
				i--;
			}

		}


		return true;
	}



	public static boolean isDebugEnabled() {
		return debug;
	}
}