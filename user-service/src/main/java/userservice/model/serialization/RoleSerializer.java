package userservice.model.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.security.core.GrantedAuthority;

import java.io.IOException;

public class RoleSerializer extends JsonSerializer<GrantedAuthority> {

    @Override
    public void serialize(GrantedAuthority value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeString(value.getAuthority());
    }
}
