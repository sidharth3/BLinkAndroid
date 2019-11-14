package javanesecoffee.com.blink.entities;

public class User {

    private String username;
    private String email;
    private String company;

    public User()
    {
        this.username = "";
        this.email = "";
        this.company = "";
    }

    public User(String username, String email, String company)
    {
        this.username = username;
        this.email = email;
        this.company = company;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
