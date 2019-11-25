package ir.dabacenter.projectmanagement.security;


import java.io.Serializable;

public class UserPassSecurityModel implements Serializable {

    private String username;
    private String password;
    private String lang;

    public UserPassSecurityModel() {
        super();
    }

    public UserPassSecurityModel(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }

    public String getLang() {return lang; }

    public void setLang(String lang) { this.lang = lang; }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}