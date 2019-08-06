package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exception.TrackAlreadyExistsException;
import com.stackroute.repository.TrackRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class TrackServiceTest {

    Track track;

    @Mock
    TrackRepository trackRepository;

    @InjectMocks
    TrackServiceImpl trackService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        track = new Track();
        track.setId(1);
        track.setName("soul");
        track.setComments("soft metallic");
    }

    @Test
    public void testSaveTrackSuccess() throws TrackAlreadyExistsException {

        when(trackRepository.save(any())).thenReturn(track);
        Track savedTrack = trackService.saveTrack(track);
        assertEquals(track,savedTrack);

        verify(trackRepository,times(1)).save(track);

    }

    @Test
    public void getById() {
    }

    @Test
    public void getAllTracks() {
    }

    @Test
    public void deleteTrackById() {
    }

    @Test
    public void updateTrack() {
    }

    @Test
    public void getByName() {
    }
}