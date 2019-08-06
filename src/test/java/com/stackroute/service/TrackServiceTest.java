package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exception.TrackAlreadyExistsException;
import com.stackroute.exception.TrackNotFoundException;
import com.stackroute.repository.TrackRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class TrackServiceTest {

    Track track;

    @Mock
    private TrackRepository trackRepository;

    @InjectMocks
    private TrackServiceImpl trackService;
    private List<Track> list = null;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        track = new Track();
        track.setId(1);
        track.setName("soul");
        track.setComments("soft metallic");
        list = new ArrayList<>();
        list.add(track);
    }

    @Test
    public void testSaveTrackSuccess() throws TrackAlreadyExistsException {

        when(trackRepository.save(any())).thenReturn(track);
        Track savedTrack = trackService.saveTrack(track);
        assertEquals(track,savedTrack);

        verify(trackRepository,times(1)).save(track);

    }

    @Test
    public void testSaveTrackChecksForNotNull() throws TrackAlreadyExistsException {

        when(trackRepository.save(any())).thenReturn(track);
        Track savedTrack1 = trackService.saveTrack(track);

        assertNotNull(savedTrack1);

        verify(trackRepository,times(1)).save(track);

    }

    @Test
    public void testGetByIdSuccess() throws TrackNotFoundException {

        trackRepository.save(track);

        when(trackRepository.existsById(track.getId())).thenReturn(true);
        when(trackRepository.findById(track.getId())).thenReturn(java.util.Optional.of(track));
        Track getTrack = trackService.getById(track.getId());
        assertEquals(track,getTrack);

    }

    @Test(expected = TrackNotFoundException.class)
    public void testGetByIdFailure() throws TrackNotFoundException {

        trackRepository.save(track);

        when(trackRepository.existsById(track.getId())).thenReturn(true);
        when(trackRepository.findById(track.getId())).thenReturn(java.util.Optional.of(track));
        Track getTrack = trackService.getById(24);
        assertEquals(track,getTrack);

    }

    @Test
    public void testGetAllTracksSuccess() throws Exception {

        trackRepository.save(track);

        when(trackRepository.findAll()).thenReturn(list);
        List<Track> savedList = trackService.getAllTracks();
        assertEquals(list,savedList);
    }

    @Test
    public void testDeleteTrackByIdSuccess() throws TrackNotFoundException {
        trackRepository.save(track);

        when(trackRepository.existsById(track.getId())).thenReturn(true);
        when(trackRepository.findById(track.getId())).thenReturn(java.util.Optional.of(track));

        Optional<Track> track1 = trackService.deleteTrackById(track.getId());

        assertEquals(true,trackRepository.existsById(track.getId()));

    }

    @Test(expected = TrackNotFoundException.class)
    public void testDeleteTrackByIdException() throws TrackNotFoundException {
        trackRepository.save(track);

        when(trackRepository.existsById(track.getId())).thenReturn(true);
        when(trackRepository.findById(24)).thenReturn(java.util.Optional.of(track));

        Optional<Track> track1 = trackService.deleteTrackById(track.getId());

        assertEquals(true,trackRepository.existsById(24));

    }

    @Test
    public void testUpdateTrackSuccess() throws TrackNotFoundException {

        trackRepository.save(track);
        Track t1 = new Track();
        t1.setName("Better");
        t1.setComments("soft acoustic");

        when(trackRepository.findById(track.getId())).thenReturn(Optional.of(track));
        Track trackUpdated =  trackService.updateTrack(1,t1);
        when(trackRepository.save(trackUpdated)).thenReturn(trackUpdated);
        assertNotEquals(trackUpdated,track);

    }

    @Test(expected = TrackNotFoundException.class)
    public void testupdateTrackException() throws TrackNotFoundException {

        trackRepository.save(track);
        Track t1 = new Track();
        t1.setName("Better");
        t1.setComments("soft acoustic");

        when(trackRepository.findById(track.getId())).thenReturn(Optional.of(track));

        Track trackUpdated =  trackService.updateTrack(24,t1);

    }

    @Test
    public void testGetByNameSuccess() throws TrackNotFoundException {

        trackRepository.save(track);

        when(trackRepository.findByName(track.getName())).thenReturn(track);

        Track searchedName = trackService.getByName("soul");

        assertSame(searchedName,track);
    }

    @Test(expected = NullPointerException.class)
    public void testGetByNameException() throws TrackNotFoundException {

        trackRepository.save(track);

        when(trackRepository.findByName(track.getName())).thenReturn(track);

        Track searchedName = trackService.getByName("jagsdugasduasi");
        searchedName.getId();
    }
}