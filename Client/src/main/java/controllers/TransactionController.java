package controllers;

import models.Id;
import models.Message;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionController {
    private String rootURL = "http://zipcode.rocks:8085/ids";
    private MessageController msgCtrl;
    private List<Id> ids;
    private IdController idCtrl = new IdController();
    private ServerController svr;
    private ObjectMapper mapper = new ObjectMapper();

    public TransactionController() throws MalformedURLException {
        ids = new ArrayList<>();

    }

    public TransactionController(MessageController m, IdController j) throws MalformedURLException {
        idCtrl = j;

        msgCtrl = m;
    }

    public List<Id> getIds() throws Exception {

        ids = mapper.readValue(svr.idGet(), new TypeReference<List<Id>>(){});

        return ids;
    }
    public Id postId(String id, String idToRegister, String githubName) throws Exception {
        Id tid = new Id(id, idToRegister, githubName);
        tid = idCtrl.postId(tid);
        return tid;
    }

//    public Message messagePost(String message, String idFrom, String idTo) throws Exception {
//        Message tid = new Message(message, idFrom, idTo);
//        tid = msgCtrl.postMessage(message, idFrom, idTo);
//        return tid;
//    }
}

//    public static void main(String[] args) throws IOException {
//        TransactionController  x = new TransactionController();
//        List<String> y = Collections.singletonList(x.postId("35", "Fitru", "tfitru"));
//        System.out.println(y);
//    }
//}
