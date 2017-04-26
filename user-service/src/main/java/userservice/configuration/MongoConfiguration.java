package userservice.configuration;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.annotations.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.filter.AnnotationTypeFilter;

@Configuration
public class MongoConfiguration {

    @Autowired
    private MongoClient mongoClient;

    @Bean
    public Datastore datastore() throws ClassNotFoundException {
        final Morphia morphia = new Morphia();
        // map entities, there is maybe a better way to find and map all entities
        ClassPathScanningCandidateComponentProvider entityScanner = new ClassPathScanningCandidateComponentProvider(true);
        entityScanner.addIncludeFilter(new AnnotationTypeFilter(Entity.class));
        for (BeanDefinition candidate : entityScanner.findCandidateComponents("your.basepackage")) { // from properties?
            morphia.map(Class.forName(candidate.getBeanClassName()));
        }
        final Datastore datastore = morphia.createDatastore(mongoClient, "userservice");
        datastore.ensureIndexes();
        return datastore;
    }


}
