package userservice.model.response;

public class MetaResponse {
    private String code;
    private String message;

    public MetaResponse(){

    }
    public MetaResponse(String code, String message){
        this.code    = code;
        this.message = message;
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MetaResponse withMessage(String message){
        this.message = message;
        return this;
    }

    public MetaResponse withCode(String code){
        this.message = code;
        return this;
    }
}
