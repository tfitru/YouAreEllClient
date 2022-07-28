package models;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/*
 * POJO for an Id object
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Id {

    @JsonProperty("uid")
    private String uid = "";
    private String name = "";
    private String github = "";

    public Id(){

    }

    public Id (String userid, String name, String githubId) {
        this.name = name;
        this.github = githubId;
    }


    public String getUid() {
        return uid;
    }
    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    @Override
    public String toString() {
        return "\n{\n" + "   \"userid\"" + ":"  + " \"-,\"" + ",\n" + "   \"name\"" + ":"  + " \"" + this.name + ",\""  + ",\n"  + "   \"github\"" + ":"  +  " \"" + this.github + "\"" + "\n" +
                "}\n";
    }
}