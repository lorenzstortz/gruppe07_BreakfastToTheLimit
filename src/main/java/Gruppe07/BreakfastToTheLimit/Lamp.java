package Gruppe07.BreakfastToTheLimit;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class Lamp {
	private URL urlLamp;
	private boolean strobeOn = false;
	
	
	public Lamp (String username, int number) {
		try {
			urlLamp = new URL("http://localhost:80/api/" + username + "/lights/" + number + "/state");	
			HttpURLConnection connTarget = (HttpURLConnection) urlLamp.openConnection();
			connTarget.setRequestMethod("PUT");
			connTarget.setDoOutput(true);
			BufferedWriter os = new BufferedWriter(new OutputStreamWriter (connTarget.getOutputStream()));
			os.write(LampColor.WHITE.getBody() + "\r\n");
			os.write("\r\n");
			os.flush();
			System.out.println("Request send");
			BufferedReader is = new BufferedReader(new InputStreamReader (connTarget.getInputStream()));
			
			String input;
			while ((input = is.readLine()) != null) {
				System.out.println(input);
			}
				
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	
	public void setColor(LampColor lampcolor) {
		try {
			HttpURLConnection connTarget = (HttpURLConnection) urlLamp.openConnection();
			connTarget.setRequestMethod("PUT");
			connTarget.setDoOutput(true);
			BufferedWriter os = new BufferedWriter(new OutputStreamWriter (connTarget.getOutputStream()));
			os.write(lampcolor.getBody() + "\r\n");
			os.write("\r\n");
			os.flush();
			System.out.println("Request send");

			BufferedReader is = new BufferedReader(new InputStreamReader (connTarget.getInputStream()));
			String input;
			while ((input = is.readLine()) != null) {
				System.out.println(input);
			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}

}
