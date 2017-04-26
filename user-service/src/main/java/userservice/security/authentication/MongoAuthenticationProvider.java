package userservice.security.authentication;
import userservice.persistence.User;
import userservice.repository.mongo.MongoUserRepository;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class MongoAuthenticationProvider implements AuthenticationProvider {

    private final MongoUserRepository userRepository;
    private final StrongPasswordEncryptor strongPasswordEncryptor;

    @Autowired
    public MongoAuthenticationProvider(MongoUserRepository userRepository, StrongPasswordEncryptor strongPasswordEncryptor) {

        this.userRepository = userRepository;
        this.strongPasswordEncryptor = strongPasswordEncryptor;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try {
            User user = userRepository.findUserByUserName(authentication.getName()).getResult();
            if (user != null) {
                String  saltedInputPassword = authentication.getCredentials().toString()+""+user.getSalt();
                boolean validPassword       = strongPasswordEncryptor.checkPassword(saltedInputPassword,user.getPassword());
                if(validPassword){
                    return new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), user.getRoles());
                }
                else {
                    throw new BadCredentialsException("Failed to log in");
                }
            } else {
                throw new BadCredentialsException("Failed to log in");
            }
        } catch (Exception repositoryProblem) {
            throw new InternalAuthenticationServiceException(repositoryProblem.getMessage(), repositoryProblem);
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
