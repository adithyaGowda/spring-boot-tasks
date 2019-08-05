package com.stackroute.repository;

import com.stackroute.domain.Track;
import com.stackroute.exception.TrackAlreadyExistsException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrackRepositoryTest {

    @Autowired
    TrackRepository trackRepository;
    Track track;

    @Before
    public void setUp() throws Exception {
        track = new Track();
        track.setId(1);
        track.setName("Riverside");
        track.setComments("Time travelling");
        trackRepository.save(track);
    }

    @After
    public void tearDown() throws Exception {
        trackRepository.deleteAll();
    }

    @Test
    public void givenInputShouldSaveTheTrack(){

        Track testTrack = trackRepository.findById(track.getId()).get();
        assertEquals(track,testTrack);
    }

    @Test
    public void givenInputShouldNotSaveAnyTracks(){

       Track newTrack = new Track(1,"Beautiful","Restful Evening");
       trackRepository.save(newTrack);
    }

    @Test
    public void givenInputObjectShouldDeleteById(){

        List<Track> testTrack = trackRepository.findAll();

//        assertEquals(testTrack,trackRepository.);
    }
}