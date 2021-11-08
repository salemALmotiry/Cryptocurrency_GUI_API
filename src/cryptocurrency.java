
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpHeaders;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.apache.hc.core5.net.URIBuilder;
import org.json.JSONObject;

public class cryptocurrency {

  private static String apiKey = "aea09fc2-2c25-4a04-8b4c-7af346374750";
  static String uri = "https://pro-api.coinmarketcap.com/v1/tools/price-conversion";
 
  
  public  String[] Cryptocurrency_Rebort(String amount , String cryptocurrency , String currency)throws ParseException{
    List<NameValuePair> paratmers = new ArrayList<NameValuePair>();
    paratmers.add(new BasicNameValuePair("amount",amount));
     paratmers.add(new BasicNameValuePair("convert",currency));
    paratmers.add(new BasicNameValuePair("symbol",cryptocurrency));
  
    String name , lastupdate,cLastUpdate ;
    double amount1,price;
    try {
     String rebort = makeAPICall(uri, paratmers);
      
  
      JSONObject obj = new JSONObject(rebort);
      JSONObject data = obj.getJSONObject("data");
     
      
      
      JSONObject quote = data.getJSONObject("quote");

      JSONObject Currency = quote.getJSONObject(currency);

      name = data.getString("name");
      lastupdate = data.getString("last_updated");
      amount1 =  data.getDouble("amount");
      price = Currency.getDouble("price");
      cLastUpdate = Currency.getString("last_updated");
      
      String  result []={name,lastupdate,String.valueOf( amount1) ,String.valueOf( price),cLastUpdate};
      return result; 
      
      
    } catch (IOException e) {
      System.out.println("Error: cannont access content - " + e.toString());
    } catch (URISyntaxException e) {
      System.out.println("Error: Invalid URL " + e.toString());
    }

    
    return null; 

  }
  
  public static String makeAPICall(String uri, List<NameValuePair> parameters)
      throws URISyntaxException, IOException, ParseException {
    String response_content = "";

    URIBuilder query = new URIBuilder(uri);
    query.addParameters(parameters);
    // System.out.println(query);
    CloseableHttpClient client = HttpClients.createDefault();
    HttpGet request = new HttpGet(query.build());

    request.setHeader(HttpHeaders.ACCEPT, "application/json");
    request.addHeader("X-CMC_PRO_API_KEY", apiKey);

    CloseableHttpResponse response = client.execute(request);

    try {
      // System.out.println(response.getStatusLine());
    
      HttpEntity entity = response.getEntity();
      response_content = EntityUtils.toString(response.getEntity());
      EntityUtils.consume(entity);
    } finally {
      response.close();
    }

    return response_content;
  }




}
