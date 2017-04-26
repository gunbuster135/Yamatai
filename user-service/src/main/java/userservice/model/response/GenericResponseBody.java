package userservice.model.response;


import com.fasterxml.jackson.annotation.JsonProperty;

public class GenericResponseBody {
    @JsonProperty(value = "meta")
    private MetaResponse meta;

    @JsonProperty(value = "data")
    private DataResponse dataResponse;

    public GenericResponseBody(){
    }

    public DataResponse getDataResponse() {
        return dataResponse;
    }

    public void setDataResponse(DataResponse dataResponse) {
        this.dataResponse = dataResponse;
    }

    public MetaResponse getMeta() {
        return meta;
    }

    public void setMeta(MetaResponse meta) {
        this.meta = meta;
    }
}
