package userservice.model.request;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RegisterRequestBody {

    @NotNull
    @Size(min=6, max = 25)
    private String username;

    @NotNull
    @Size(min=6, max = 256)
    private String password;

    @NotNull
    @Size(min=3, max = 254)
    @Email
    private String email;

    public RegisterRequestBody(){

    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getUsername() {

        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
