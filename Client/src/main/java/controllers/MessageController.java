package controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import models.Id;
import models.Message;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

public class MessageController {

    private HashSet<Message> messagesSeen;
    private ServerController svr = new ServerController();
    private ObjectMapper mapper = new ObjectMapper();

    private String messages20;
    private String messagesForId;
    private String messageFromFriend;

    private Message messageSeq;

    private List<Message> messages;
    private List<Message> messagesId;
    private List<Message> messagesFromFriend;
    private Id myId;
    private Id told;

    // why a HashSet??

    public MessageController() throws MalformedURLException {
        messages = new ArrayList<>();
        messages20 = "";
        messagesId = new ArrayList<>();
        messagesFromFriend = new ArrayList<>();
    }


    public List<Message> getMessages() throws Exception {
        messages = mapper.readValue(svr.messagesGet(), new TypeReference<List<Message>>(){});
        messages20 = mapper.writeValueAsString(messages);

        return messages;
    }
    public List<Message> getMessagesForId(Id Id) {
        for(int i = 0; i<messages.size(); i++){
            if(messages.get(i).getFromId().equals(Id.getGithub())){
                messagesId.add(messages.get(i));
            }
        }

        return messagesId;
    }
    public Message getMessageForSequence(String seq) {
        try {
            messageSeq = mapper.readValue(seq, Message.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return messageSeq;
    }
    public List<Message> getMessagesFromFriend(Id myId, Id friendId) throws Exception {
        messages = new MessageController().getMessages();
        Message message = new Message("Professor Rankings: Fitru, Fitru, Fitru, ... Linda || Ryan\n" +
                "----", myId.getGithub(), friendId.getGithub());
        for(int i = 0; i<messages.size(); i++){

        }

        for(int i = 0; i<messages.size(); i++){
            if(messages.get(i).getMessage().equals(message.getMessage())){
                messagesFromFriend.add(messages.get(i));
            }

            if(messages.get(i).getMessage().equals(message.getMessage())){
                messagesFromFriend.add(messages.get(i));
            }
        }

        return messagesFromFriend;
    }

    public Message postMessage(Id myId, Id toId, Message msg) throws Exception {
        String messageFrom = "";
        String messageTo = "";
        String message = "";

//        messages = new MessageController().getMessages();

        for(int i = 0; i<messagesFromFriend.size(); i++){
            if(messages.get(i).getFromId().equals(myId.getGithub())){
               msg.setFromId(myId.getGithub());
               messageFrom = msg.getFromId();
            }
            if(messages.get(i).getToId().equals(toId.getGithub())){
                msg.setToId(toId.getGithub());
                messageTo = msg.getToId();
            }
        }

        if(messagesFromFriend.size()==0) {
            msg.setFromId(myId.getGithub());
            messageFrom = msg.getFromId();
            msg.setToId(toId.getGithub());
            messageTo = msg.getToId();

        }


        message = msg.getMessage();

        Message fro = new Message(messageFrom, messageTo, message);

        fro.toString();
        svr.messagePost(fro);

//        svr.messagePost(msg);

        return fro;
    }
 
}