package Gruppe07.BreakfastToTheLimit;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;

/**
 * @author sieber, stortz
 *
 */
public class RoommateHandler implements Runnable {

  private static final String GOOGLE_API_KEY = "AIzaSyAd40gDCh2eFTHVr6ocvmyElOf7J93BYrQ";
  private static final String GOOGLE_BASE = "https://maps.googleapis.com/maps/api/distancematrix/json?units=metric";

  private static Gson GSON = new Gson();
  private Roommate roommate;

  public RoommateHandler(Roommate roommate) {
    this.roommate = roommate;
  }

  @Override
  public void run() {
    while (true) {
      try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
        long currentTimeInSeconds = java.time.Instant.now().getEpochSecond();
        HttpGet httpget = new HttpGet(GOOGLE_BASE + "&origins=" + roommate.getOrigin()
            + "&destinations=" + roommate.getDestination() + "&mode=" + roommate.getMode()
            + "&key=" + GOOGLE_API_KEY + "&departure_time="+ currentTimeInSeconds);

        if(App.isDebugEnabled())System.out.println("Executing request " + httpget.getRequestLine());

        ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

          @Override
          public String handleResponse(final HttpResponse response)
              throws ClientProtocolException, IOException {
            int status = response.getStatusLine().getStatusCode();
            if (status >= 200 && status < 300) {
              HttpEntity entity = response.getEntity();
              return entity != null ? EntityUtils.toString(entity) : null;
            } else {
              throw new ClientProtocolException("Unexpected response status: " + status);
            }
          }

        };
        String responseBody = httpclient.execute(httpget, responseHandler);
        if(App.isDebugEnabled())System.out.println("----------------------------------------");
        if(App.isDebugEnabled())System.out.println(responseBody);

        ResponseData test = GSON.fromJson(responseBody, ResponseData.class);
        int timeNeeded = test.rows.get(0).elements.get(0).duration_in_traffic.value;
        roommate.setTravelTimeInSeconds(timeNeeded);
        if(App.isDebugEnabled())System.out.println(roommate.getName() + " braucht momentan so lange:"
           + test.rows.get(0).elements.get(0).duration_in_traffic.text);
        Thread.sleep(5000);

      } catch (Exception e) {

      }

    }

  }

  // DATA SLAVES
  private class ResponseData {
    // List<String> destination_addresses;
    // List<String> origin_addresses;
    List<Row> rows;
  }

  private class Row {
    List<Element> elements;
  }

  private class Element {
    //General duration;
    //General distance;
    General duration_in_traffic;
    //String status;


  }

  private class General {
    String text;
    int value;
  }

}
