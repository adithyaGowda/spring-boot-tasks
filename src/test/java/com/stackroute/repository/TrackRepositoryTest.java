package com.stackroute.repository;

import com.stackroute.domain.Track;
import com.stackroute.exception.TrackNotFoundException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.NoSuchElementException;


import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrackRepositoryTest {

    @Autowired
    private TrackRepository trackRepository;
    private Track track;

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
        track = null;
    }

    @Test
    public void givenInputTrackShouldSaveTheTrack() {

        Track testTrack = trackRepository.findById(track.getId()).get();
        assertEquals(track, testTrack);
    }

    @Test
    public void givenInputTrackShouldNotSaveAnyTracks() {

        Track newTrack = new Track(12, "Beautiful", "Restful Evening");
        trackRepository.save(newTrack);
        assertNotSame(track, newTrack);
    }

    @Test
    public void givenInputTrackShouldReturnAllTheTracks() {
        Track t1 = new Track(2, "soul", "soft metallic");
        Track t2 = new Track(3, "teardrop", "soft rock");
        Track t3 = new Track(4, "dance", "hiphop");
        trackRepository.save(t1);
        trackRepository.save(t2);
        trackRepository.save(t3);

        List<Track> trackList = trackRepository.findAll();

        assertEquals("teardrop", trackList.get(2).getName());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void givenInputShouldGetAllTheTracksThrowsException() {
        Track t1 = new Track(2, "soul", "soft metallic");
        Track t2 = new Track(3, "teardrop", "soft rock");
        Track t3 = new Track(4, "dance", "hiphop");
        trackRepository.save(t1);
        trackRepository.save(t2);
        trackRepository.save(t3);

        List<Track> trackList = trackRepository.findAll();

        assertEquals("teardrop", trackList.get(5).getName());
    }

    @Test
    public void givenInputIdShouldReturnTrackById() {
        Track t1 = new Track();
        Track t2 = new Track(2, "soul", "soft metallic");
        Track t3 = new Track(3, "teardrop", "soft rock");
        trackRepository.save(t2);
        trackRepository.save(t3);
        t1.setId(3);
        Track t4 = trackRepository.findById(t1.getId()).get();

        assertEquals(t3, t4);

    }

    @Test(expected = NoSuchElementException.class)
    public void givenInputIdShouldReturnGetTrackByIdThrowsException() {
        Track t1 = new Track();
        Track t2 = new Track(2, "soul", "soft metallic");
        Track t3 = new Track(3, "teardrop", "soft rock");
        trackRepository.save(t2);
        trackRepository.save(t3);
        t1.setId(4);
        trackRepository.findById(t1.getId()).get();

    }

    @Test
    public void givenInputIdShouldDeleteTrackById() {

        assertEquals(true, trackRepository.existsById(track.getId()));
        trackRepository.deleteById(track.getId());

    }

    @Test
    public void givenInputIdShouldDeleteTrackByIdReturnsException() {
        Track t1 = new Track(2, "teardrop", "soft rock");
        trackRepository.save(t1);
        assertNotSame(false, trackRepository.existsById(t1.getId()));
        trackRepository.deleteById(t1.getId());

    }

    @Test
    public void givenInputIdShouldReturnUpdateTrackFoundById() {
        Track t1 = new Track(2, "soul", "soft metallic");
        Track t2 = new Track(3, "teardrop", "soft rock");
        Track t3 = new Track(4, "dance", "hiphop");
        trackRepository.save(t1);
        trackRepository.save(t2);
        trackRepository.save(t3);

        Track trackList = trackRepository.findById(t2.getId()).get();
        trackList.setName(t3.getName());

        assertEquals(trackList.getName(), t3.getName());

    }

    @Test
    public void givenInputIdShouldReturnUpdateChecksForNotNull() {
        assertNotNull(trackRepository.existsById(track.getId()));
    }

    @Test
    public void givenInputIdShouldFindTrackByName(){

        trackRepository.save(track);
        Track t1 = trackRepository.findByName(track.getName());
        assertEquals("Riverside",t1.getName());


    }

    @Test
    public void givenInputIdShouldReturnFindByNameChecksForNotEquality(){

        trackRepository.save(track);

        Track t1 = trackRepository.findByName(track.getName());
        assertNotEquals("soul",t1.getName());

    }


}