package com.stackroute.controller;

import com.stackroute.domain.Track;
import com.stackroute.exception.TrackAlreadyExistsException;
import com.stackroute.exception.TrackNotFoundException;
import com.stackroute.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1")
public class TrackController {

    private TrackService trackService;
    ResponseEntity responseEntity;;

    @Autowired
    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    @PostMapping("track")
    public ResponseEntity<?> saveTrack(@RequestBody Track track) throws TrackAlreadyExistsException {


            trackService.saveTrack(track);
            responseEntity = new ResponseEntity("Successfully created", HttpStatus.CREATED);

      return  responseEntity;

    }

    @GetMapping("track/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) throws TrackNotFoundException {


            responseEntity = new ResponseEntity<Track>(trackService.getById(id),HttpStatus.OK);

        return responseEntity;
    }

    @GetMapping("tracks")
    public ResponseEntity<?> getAllTracks() throws Exception{


            responseEntity = new ResponseEntity<List<Track>>(trackService.getAllTracks(),HttpStatus.IM_USED);


        return responseEntity;
    }

    @DeleteMapping("track/{id}")
    public ResponseEntity<?> deleteTrackById(@PathVariable int id) throws TrackNotFoundException{


            responseEntity = new ResponseEntity<Optional<Track>>(trackService.deleteTrackById(id),HttpStatus.OK);

        return responseEntity;
    }

    @PutMapping("track/{id}")
    public ResponseEntity<?> updateTrack(@PathVariable int id,@RequestBody Track track) throws TrackNotFoundException{



            responseEntity = new ResponseEntity(trackService.updateTrack(id,track),HttpStatus.OK);

        return responseEntity;
    }



    @GetMapping("tracks/{name}")
    public ResponseEntity<?> getByName(@PathVariable String name) throws TrackNotFoundException{

            responseEntity = new ResponseEntity<List<Track>>(trackService.getByName(name),HttpStatus.IM_USED);


        return responseEntity;
    }



}
