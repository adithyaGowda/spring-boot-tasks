package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exception.TrackAlreadyExistsException;
import com.stackroute.exception.TrackNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
/*@Qualifier("dummyImplementation")*/
//Inorder to run this implementation class either we can use @Primary annotation or we can specify in @Qualifier

@Profile("dev")
public class TrackDummyServiceImpl implements TrackService {

    @Override
    public Track saveTrack(Track track) throws TrackAlreadyExistsException {
        System.out.println("Running dummy TrackServiceImpl");
        return null;
    }

    @Override
    public Track getById(int id) throws TrackNotFoundException {
        System.out.println("Running dummy TrackServiceImpl");
        return null;
    }

    @Override
    public List<Track> getAllTracks() throws Exception {
        System.out.println("Running dummy TrackServiceImpl");
        return null;
    }

    @Override
    public Optional<Track> deleteTrackById(int id) throws TrackNotFoundException {
        System.out.println("Running dummy TrackServiceImpl");
        return Optional.empty();
    }

    @Override
    public Track updateTrack(int id, Track track) throws TrackNotFoundException {
        System.out.println("Running dummy TrackServiceImpl");
        return null;
    }

    @Override
    public List<Track> getByName(String name) throws TrackNotFoundException {
        System.out.println("Running dummy TrackServiceImpl");
        return null;
    }
}
