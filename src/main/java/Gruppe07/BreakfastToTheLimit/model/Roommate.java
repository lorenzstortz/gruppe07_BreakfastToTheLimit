package Gruppe07.BreakfastToTheLimit.model;

public class Roommate {

  private String name;

  private String destination;

  private String origin;

  private String mode;

  private int hour;

  private int minutes;

  private int remainingTime;

  public Roommate(String name, String destination, String origin, String mode, int hour,
      int minutes) {
    this.name = name;
    this.mode = mode;
    this.origin = origin;
    this.destination = destination;
    this.hour = hour;
    this.minutes = minutes;
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

  public int getHour() {
    return hour;
  }

  public void setHour(int hour) {
    this.hour = hour;
  }

  public int getMinutes() {
    return minutes;
  }

  public void setMinutes(int minutes) {
    this.minutes = minutes;
  }

}
