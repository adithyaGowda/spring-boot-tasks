package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exception.TrackAlreadyExistsException;
import com.stackroute.exception.TrackNotFoundException;
import com.stackroute.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrackServiceImpl implements TrackService {
    private TrackRepository trackRepository;

    @Autowired
    public TrackServiceImpl(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Override
    public Optional<Track> deleteTrackById(int id) throws TrackNotFoundException {
        Optional<Track> optional = trackRepository.findById(id);

        if (optional.isPresent()) {
            trackRepository.deleteById(id);
        }
        else
            throw new TrackNotFoundException("Track Not Found");

        return optional;
    }

    @Override
    public Track saveTrack(Track track) throws TrackAlreadyExistsException {
        if(trackRepository.findById(track.getId()).isPresent()){
            throw new TrackAlreadyExistsException("Track Already Exists");
        }
        Track saveTrack = trackRepository.save(track);
        return saveTrack;
    }


    @Override
    public Track updateTrack(int id, Track track) throws TrackNotFoundException {
        if (trackRepository.findById(id).isPresent()){
            Track update = trackRepository.findById(id).get();
            update.setName(track.getName());
            update.setComments(track.getComments());
            return trackRepository.save(track);
        }
        else
            throw new TrackNotFoundException("Track Not Found");

    }

    @Override
    public Track getById(int id) throws TrackNotFoundException {
        if (trackRepository.findById(id).isPresent()) {
            return trackRepository.findById(id).get();
        }
        else
            throw new TrackNotFoundException("Track Not Found");
    }



    @Override
    public List<Track> getAllTracks() throws Exception {
        if (trackRepository.findAll().isEmpty()){
            throw new Exception("No Tracks Available");
        }
        return trackRepository.findAll();
    }

    //getByName implemented
//    @Override
//    public Track getByName(String name) {
//        return trackRepository.getByName(name);
//    }

    @Override
    public List<Track> getByName(String name) throws TrackNotFoundException{
        List<Track> dbName = trackRepository.getByName(name);
        if (dbName == null){
            throw new TrackNotFoundException("Track Not Found");
        }
        return dbName;
    }
}
