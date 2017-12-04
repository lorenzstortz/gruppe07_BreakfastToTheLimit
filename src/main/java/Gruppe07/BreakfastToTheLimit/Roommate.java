package Gruppe07.BreakfastToTheLimit;

import org.joda.time.DateTime;


/**
 * @author sieber, stortz
 *
 */
public class Roommate {

  private String name;

  private String destination;

  private String origin;

  private String mode;

  private int hourOfArrival;

  private int minutesOfArrival;

  private boolean left = false;


  private int travelTimeInSeconds = 100000; //dummy value

  public Roommate(String name, String destination, String origin, String mode, int hour,
      int minutes) {
    this.name = name;
    this.mode = mode;
    this.origin = origin;
    this.destination = destination;
    this.hourOfArrival = hour;
    this.minutesOfArrival = minutes;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDestination() {
    return destination;
  }

  public void setDestination(String destination) {
    this.destination = destination;
  }

  public String getOrigin() {
    return origin;
  }

  public void setOrigin(String origin) {
    this.origin = origin;
  }

  public String getMode() {
    return mode;
  }

  public void setMode(String mode) {
    this.mode = mode;
  }

  public int getTravelTimeInSeconds(){
    return travelTimeInSeconds;
  }

  public int getTravelTimeInMinutes() {
    return travelTimeInSeconds /60;
  }

  public int getHourOfArrival() {
    return hourOfArrival;
  }

  public void setHourOfArrival(int hourOfArrival) {
    this.hourOfArrival = hourOfArrival;
  }

  public int getMinutesOfArrival() {
    return minutesOfArrival;
  }

  public void setMinutesOfArrival(int minutesOfArrival) {
    this.minutesOfArrival = minutesOfArrival;
  }

  public void setTravelTimeInSeconds(int travelTimeInSeconds) {
    this.travelTimeInSeconds = travelTimeInSeconds;
  }

  public int getRemainingTimeInMinutes(){
      DateTime dt = new DateTime();
      int secondofTheDay = dt.getSecondOfDay();
      int secondOfArrival = (hourOfArrival*60+minutesOfArrival)*60;
      int remaining = ((secondOfArrival-secondofTheDay) -travelTimeInSeconds)/60;
      return remaining;
  }


    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }
}
