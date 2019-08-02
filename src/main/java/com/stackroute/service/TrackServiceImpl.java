package com.stackroute.service;

import com.stackroute.domain.Track;
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
    public Optional<Track> deleteTrackById(int id) {
        Optional<Track> optional = trackRepository.findById(id);

        if (optional.isPresent()) {
            trackRepository.deleteById(id);
        }
        return optional;
    }

    @Override
    public Track saveTrack(Track track) {
        Track saveTrack = trackRepository.save(track);
        return saveTrack;
    }


    @Override
    public Track updateTrack(int id, Track track) {
        Track update = trackRepository.findById(id).get();
        update.setName(track.getName());
        update.setComments(track.getComments());
        return trackRepository.save(track);
    }

    @Override
    public Track getById(int id) {
        return trackRepository.findById(id).get();
    }



    @Override
    public List<Track> getAllTracks() {
        return trackRepository.findAll();
    }
}
