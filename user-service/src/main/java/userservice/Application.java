package userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


/**
 * Created by Omar on 2016-11-28.
 */

@SpringBootApplication
@EnableAutoConfiguration
public class Application {

    public static void main(String[] args){
        ApplicationContext ctx = SpringApplication.run(Application.class,args);
    }

}
