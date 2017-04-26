package userservice.persistence;
import org.mongodb.morphia.annotations.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    @OneToMany(mappedBy ="user")
    private List<Role> roles = new ArrayList<>();
    private String salt;
    private String email;

    public User(){

    }

    public User(String username, String password, List<Role> roles, String salt,  String email){
        this.username = username;
        this.password = password;
        this.roles    = roles;
        this.salt     = salt;
        this.email    = email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public Long getId() {
        return id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void addRole(Role role){
        this.roles.add(role);
    }

    public String getSalt() {
        return salt;
    }

    public String getEmail(){ return email;}

    @Override
    public String toString() {
        return ("username: " + username) +
                " password: " + password +
                " roles: " + roles +
                " salt: " + salt +
                " email: " + email;
    }

}
