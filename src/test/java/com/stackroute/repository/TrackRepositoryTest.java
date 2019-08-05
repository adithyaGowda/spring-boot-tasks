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
    public void testToSaveTheTrack(){

        Track testTrack = trackRepository.findById(track.getId()).get();
        assertEquals(track,testTrack);
    }

    @Test
    public void testToNotSaveAnyTracks(){

       Track newTrack = new Track(12,"Beautiful","Restful Evening");
       trackRepository.save(newTrack);
       assertNotSame(track,newTrack);
    }

    @Test
    public void testToGetAllTheTracks(){
        Track t1 = new Track(2,"soul","soft metallic");
        Track t2 = new Track(3,"teardrop","soft rock");
        Track t3 = new Track(4,"dance","hiphop");
        trackRepository.save(t1);
        trackRepository.save(t2);
        trackRepository.save(t3);

        List<Track> trackList = trackRepository.findAll();

        assertEquals("teardrop",trackList.get(2).getName());
    }

    @Test
    public void testToGetTrackById(){
        Track t1 = new Track();
        t1.setId(1);
        Track t2 = trackRepository.findAllById;

    }

    @Test
    public void testToDeleteTrackById(){

        List<Track> testTrack = trackRepository.findAll();

       assertEquals(testTrack,trackRepository);
    }
}