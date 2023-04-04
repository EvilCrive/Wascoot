package GroupCreation.resource;

import org.json.JSONObject;

public class Group {
    private final Long id;
    private final String email;
    private final String password;


    // this is used to return a admin after login
    public Group(Long id, String email, String password){
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public String getid() {
        return id;
    }

    public String getEmail() {
        return email;
    }



}
