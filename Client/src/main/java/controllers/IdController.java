package controllers;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import models.Id;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

public class IdController {
    private HashMap<String, Id> allIds;
    private ServerController svr = new ServerController();
    private ObjectMapper mapper = new ObjectMapper();
    private List<Id> ids;
    private Id myId;

    public List<Id> getIds() throws Exception {
        ids = mapper.readValue(svr.idGet(), new TypeReference<List<Id>>(){});

        return ids;
    }


    public IdController() throws MalformedURLException {
        this.ids = new ArrayList<>();

    }

    public Id postId(Id id) throws Exception {
        myId = id;

        svr.idPost(myId);

        // create json from id
        // call server, get json result Or error
        // result json to Id obj

        return myId;
    }

    public Id putId(Id id) {
        Scanner console = new Scanner(System.in);
        String inputId = console.next();
        String inputName = console.next();
        String inputGit = console.next();
        myId = id;
        myId.setUid(inputId);
        myId.setName(inputName);
        myId.setGithub(inputGit);
        return myId;
    }
 
}