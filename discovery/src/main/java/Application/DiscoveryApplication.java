package Application;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
public class DiscoveryApplication {
    public static void main(String[] args){
        SpringApplication.run(DiscoveryApplication.class, args);
    }
}
