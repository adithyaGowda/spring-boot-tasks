package com.stackroute.repository;

import com.stackroute.domain.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackRepository extends JpaRepository<Track, Integer> {

    //getByName implemented
//    @Query("select t from Track t where t.name = ?1")
//    public Track getByName(String name);

    @Query("select t from Track t where t.name = ?1")
    public List<Track> getByName(String name);

}
