package com.stackroute.seedData;

import com.stackroute.domain.Track;
import com.stackroute.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
public class SpringCommandLineRunnerImpl implements CommandLineRunner {

    private TrackRepository trackRepository;

    @Autowired
    public SpringCommandLineRunnerImpl(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Autowired
    private Environment environment;

    @Value("${track.id}")
    int id;

//    @Value("${track.name}")
//    String name;
//
//    @Value("${track.comments}")
//    String comments;


    @Override
    public void run(String... args) throws Exception {

        Track t1 = new Track(id,environment.getProperty("track.name"),environment.getProperty("track.comments"));
//        Track t2 = new Track(22,"song22","songAlbum22");
//        Track t3 = new Track(33,"song33","songAlbum33");
//        Track t4 = new Track(44,"song44","songAlbum44");

        trackRepository.save(t1);
//        trackRepository.save(t2);
//        trackRepository.save(t3);
//        trackRepository.save(t4);

        /*@ConfigurationProperties it is recommended to add ‘spring-boot-configuration-processor’
        @Configuration annotation registers this POJO as a bean.
        The @ConfigurationProperties annotation allows us to bind the POJO to properties of an external properties file, e.g. application.properties.
         Notice that we are using the prefix “track” to filter the properties.

         Eg:
         @Configuration
         @ConfigurationProperties("track")
         public class Track{
         int id;
         String name;
         String comments;
         getters and setters
         }
         */

    }
}
