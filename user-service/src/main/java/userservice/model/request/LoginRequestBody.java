package userservice.model.request;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;


public class LoginRequestBody {
    @NotNull
    @Length(max=25)
    private String username;
    @NotNull
    @Length(max=256)
    private String password;

    public LoginRequestBody(){

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
