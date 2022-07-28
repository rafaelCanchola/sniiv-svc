package mx.gob.sedatu.dgdusv.sniiv.utilities;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.*;

@Component
public class HttpMethods {

    @Autowired
    private Environment env;

    @Autowired
    private ApiAlfresco apiAlfresco;
    private static final String LINE = "\r\n";
    private PrintWriter writer;
    public String getHttpMethod(){
        return null;
    }

    public String postFormHttpMethod(String endPoint,String name,MultipartFile file) throws JSONException, IOException {
        String auth = authHeader();
        // Set header
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Basic "+auth);
        HttpPostMultipart multipart = new HttpPostMultipart(endPoint, "utf-8", headers);
        // Add form field
        multipart.addFormField("name", name);
        multipart.addFormField("nodeType", "cm:content");
        // Add file
        multipart.addFilePart("filedata", file,name);
        return multipart.finish();
    }


    public String postHttp(String endPoint, String jsonInputString, String auth) throws IOException, JSONException {
        URL url = new URL(endPoint);
        HttpURLConnection con;
        con = (HttpURLConnection) url.openConnection();
        if(auth != null){
            con.setRequestProperty("Authorization","Basic "+auth);
        }
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestMethod("POST");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);
        try(OutputStream os = con.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
            os.close();
            con.connect();
        }

        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            return response.toString();
        }
    }

    public String authHeader() throws JSONException, IOException {
        JSONObject ticket = new JSONObject();
        JSONObject jsonObj;
        ticket.put("userId",env.getProperty("alfresco.username"));
        ticket.put("password",env.getProperty("alfresco.password"));
        jsonObj = new JSONObject(postHttp(apiAlfresco.createTicket(),ticket.toString(),null));
        String entry = ((JSONObject) jsonObj.get("entry")).getString("id");
        return Base64.getEncoder().encodeToString(entry.getBytes());
    }
}
