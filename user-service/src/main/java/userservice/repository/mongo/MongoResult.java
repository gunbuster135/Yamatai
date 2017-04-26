package userservice.repository.mongo;

/**
 * Created by Omar on 2016-12-13.
 */
public class MongoResult<T>{
    private String message;
    private boolean success;
    private T result;

    public MongoResult(String message, boolean success, T result) {
        this.message = message;
        this.success = success;
        this.result = result;
    }
    public boolean isSuccess() {
        return success;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }


    public String getMessage() {

        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
