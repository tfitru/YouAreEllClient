package controllers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import models.Id;
import models.Message;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.MapperConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.util.JSONPObject;
import org.codehaus.jackson.type.TypeReference;
//import spiffyUrlManipulator

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.ClientInfoStatus;
import java.util.*;

//    private ServerController svr = new ServerController();


//    public ServerController() throws IOException {
////        HttpGet request = new HttpGet("http://zipcode.rocks:8085/ids");
////
////        CloseableHttpResponse response = httpClient.execute(request);
////        HttpEntity entity = response.getEntity();
////
////        System.out.println((EntityUtils.toString(entity)));
//    }


//    public static shared() {
//        return svr;
//    }

public class ServerController {
    private String rootURL = "http://zipcode.rocks:8085";
    private HttpGet request = new HttpGet("http://zipcode.rocks:8085/ids");
    private HttpGet requestMessage = new HttpGet("http://zipcode.rocks:8085/messages");

    private Id myId;
    private Message message;
    private List<Id> ids;
    private List<Message> messages;
    private MessageController messageController;
    private ServerController svr;

    public ServerController(){
        ids = new ArrayList<>();
        messages = new ArrayList<>();

    }


    public String messagesGet() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        URL url = new URL("http://zipcode.rocks:8085/messages");
        messages = mapper.readValue(url, new TypeReference<List<Message>>() {
        });

        String studentJson = "";

        try {
            studentJson = mapper.writeValueAsString(messages);

        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return studentJson;

    }

    public String idGet() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        URL url = new URL("http://zipcode.rocks:8085/ids");
        ids = mapper.readValue(url, new TypeReference<List<Id>>() {
        });

        String studentJson = "";

        try {
             studentJson = mapper.writeValueAsString(ids);

        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return studentJson;

    }

        public static void main(String[] args) throws IOException {
        new ServerController().messagesForMyIdFromFriend();

        }
 // Message from my id from friend
    public String messagesForMyIdFromFriend() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        messageController = new MessageController();

        URL url = new URL("http://zipcode.rocks:8085/ids/tfitru/from/linjiayo");


        messages = mapper.readValue(url,  new TypeReference<List<Message>>(){} );

//        231e3aa511381e144e007c049297cd05e55254bb
//        messages = mapper.readValue(url, new TypeReference<List<Message>>() {
//        });

        // Use the sequence to connect the message to the id in the url

        String studentJson = "";

        try {
            studentJson = mapper.writeValueAsString(messages);

        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

//       for(int i = 0; i<messages.size(); i++){
//           if(messages.get(i).getFromId().equals())
//       }

//        messageController.getMessagesForId();

        return studentJson;

    }
    public String messageGetSequence() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        messageController = new MessageController();

        URL url = new URL("http://zipcode.rocks:8085/ids/tfitru/messages/sequence");




        message = mapper.readValue(url, new TypeReference<Message>(){});

//        231e3aa511381e144e007c049297cd05e55254bb
//        messages = mapper.readValue(url, new TypeReference<List<Message>>() {
//        });

        String studentJson = "";

        try {
            studentJson = mapper.writeValueAsString(message);

        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        messageController.getMessageForSequence(studentJson);

        return studentJson;

    }




    public String messagePost(Message message) throws IOException {
        URL url = new URL("http://zipcode.rocks:8085/ids/tfitru/messages");

        HttpURLConnection http = (HttpURLConnection)url.openConnection();
//        http.setRequestMethod("POST");
        http.setRequestProperty("Content-Type", "application/json");
//        http.setRequestProperty("Accept","application/json");

        http.setDoOutput(true);


        ObjectMapper mapper = new ObjectMapper();
        this.message= message;

        // url -> /ids/

        String jsonInString = mapper.writeValueAsString(this.message);

        System.out.println(jsonInString);




//        HttpURLConnection con = (HttpURLConnection)url.openConnection();

//        con.setRequestProperty("Content-Type", "application/json");
//        con.setRequestProperty("Accept", "application/json");
//        con.setDoOutput(true);
//        con.setRequestMethod("POST");
//        con.setUseCaches(false);


//        try(OutputStream os = http.getOutputStream()){
//            byte[] input = jsonInString.getBytes(StandardCharsets.UTF_8);
//            os.write(input, 0, input.length);
//        }

        OutputStream os = http.getOutputStream();
        byte[] input = jsonInString.getBytes(StandardCharsets.UTF_8);
        os.write(input, 0, input.length);




                try(BufferedReader br = new BufferedReader(
                new InputStreamReader(http.getInputStream(), StandardCharsets.UTF_8))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while((responseLine = br.readLine()) != null){
                response.append(responseLine.trim());
            }
            System.out.println(response);
        }

        http.getResponseMessage();
        http.disconnect();;

//        try(BufferedReader br = new BufferedReader(
//                new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8))) {
//            StringBuilder response = new StringBuilder();
//            String responseLine = null;
//            while((responseLine = br.readLine()) != null){
//                response.append(responseLine.trim());
//            }
//            System.out.println(response);
//        }
        // create json from Id
        // request
        // reply
        // return json

        return  jsonInString;

    }




    public String idPost(Id id) throws Exception {
        URL url = new URL("http://zipcode.rocks:8085/ids");

        ObjectMapper mapper = new ObjectMapper();
        myId= id;

        // url -> /ids/

        String jsonInString = mapper.writeValueAsString(myId);

        System.out.println(jsonInString);




        HttpURLConnection con = (HttpURLConnection)url.openConnection();


        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);

        try(OutputStream os = con.getOutputStream()){
            byte[] input = jsonInString.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }


        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while((responseLine = br.readLine()) != null){
                response.append(responseLine.trim());
            }
            System.out.println(response);
        }
        // create json from Id
        // request
        // reply
        // return json

        return  jsonInString;
    }

    public String idPut(Id id) {
        // url -> /ids/
        Scanner console = new Scanner(System.in);
        String inputId = console.next();
        String inputName = console.next();
        String inputGit = console.next();
        myId = id;
        myId.setUid(inputId);
        myId.setName(inputName);
        myId.setGithub(inputGit);

        return String.valueOf(myId);
    }



}


// Save this code if I need it
//        svr.idGet();
//        try {
//            ids.add(myId);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }

// ServerController.shared.doGet()