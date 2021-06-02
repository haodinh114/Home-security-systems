package model;

public class User {

    private String password;
    private String email;
    private String lastname;
    private String firstname;
    private String phoneNumber;
    public User(String username, String password, String email,
                String lastname, String firstname, String phoneNumber){
        this.username = username;
        this.password = password;
        this.email = email;
        this.lastname = lastname;
        this.firstname = firstname;
        this.phoneNumber = phoneNumber;
    }
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getLastname() {
        return lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    private String username;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String toString() {
        return "User{" +  "username=" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    public String getFields(){
        return "username, password, firstname, lastname, email, phonenumber";
    }

    public String getValues(){
        return String.format("'%s', '%s', '%s', '%s', '%s', '%s'", this.username, this.password, this.firstname, this.lastname,
                this.email, this.phoneNumber);
    }
}
