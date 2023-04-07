package LoginAdmin.resource;

import org.json.JSONObject;

public class Administrator {
    private final String id;
    private final String email;
    private final String password;


    // this is used to return a admin after login
    public Administrator(String id, String email, String password){
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public String getId() { return id;}

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public JSONObject toJSON(){
        JSONObject uJson = new JSONObject();
        uJson.put("id", id);
        uJson.put("email", email);
        uJson.put("password", password);


        return uJson;
    }

}
