package testSever;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class TestHTTPUtil {
public static void main(String[] args) throws IOException {
     
    // test sending GET request
    String requestURL = "http://www.google.com";
    InputStream is = HTTPUtil.sendGetRequest(requestURL);
    String[] response = HTTPUtil.parseMultipleLinesRespone(is);
    for (String line : response) {
        System.out.println(line);
    }
     
    System.out.println("=====================================");
     
    // test sending POST request
    Map<String, String> params = new HashMap<String, String>();
    requestURL = "https://accounts.google.com/ServiceLoginAuth";
    params.put("Email", "");
    params.put("Passwd", "");    
    is = HTTPUtil.sendPostRequest(requestURL, params);
    response = HTTPUtil.parseMultipleLinesRespone(is);
    for (String line : response) {
        System.out.println(line);
    }        
}
}