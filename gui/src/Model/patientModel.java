package Model;

/**
 * Contains the user to be validated.
 *
 * Obviously this should have hashes instead of string passwords and such.
 *
 * This does nothing but hold user info to be checked against DB
 */
public class patientModel {

    private String username;
    private String password;


    public patientModel(String username, String password){
        setUsername(username);
        setPassword(password);
    }

    public void setUsername(String un){
        this.username = un;
    }

    public void setPassword(String pw){
        this.password = pw;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }
}
