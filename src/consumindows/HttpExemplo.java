package consumindows;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class HttpExemplo {

    private final String USER_AGENT = "Mozilla/5.0";

    public static void main(String[] args) throws Exception {
        HttpExemplo http = new HttpExemplo();
        String chamadaWS;
        chamadaWS = "https://api.huggy.io/v2/chats/4242002/messages";
        String json = http.sendGet(chamadaWS);
        //System.out.println(json);
        
        Path caminho = Paths.get("C:/Users/francisco.pegado/Desktop/Huggy/clientes.txt");
        String texto = json;
        byte[] textoEmByte = texto.getBytes();
        
        try {
            Files.write(caminho, textoEmByte);
        } catch(Exception erro) {
            
        }
    }
    private String sendGet(String url) throws Exception {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Content-Type", "application/json ; charset=UTF-8");
        con.setRequestProperty("X-Authorization", "Bearer 46bacc58d295b63cbde006d485115bef");
        
        int responseCode = con.getResponseCode();
        System.out.println("Sending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);
                
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
           response.append(inputLine);
        }
        in.close();
        return response.toString();
    }
    /*private void sendPost(String url, String urlParameters, String method) throws Exception {

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        //add reuqest header
        con.setRequestMethod(method);
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

		//String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";
        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + urlParameters);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());

    }*/
 }
