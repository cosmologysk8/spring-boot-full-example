package com.everis.d4i.tutorial.services;

import com.everis.d4i.tutorial.entities.Charapter;
import com.everis.d4i.tutorial.entities.TvShow;
import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.CharapterRest;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface CharapterService {

    List<CharapterRest> getAllCharapters() throws NetflixException;

    CharapterRest createCharapter(@RequestBody Charapter charapter) throws NetflixException;

    CharapterRest getCharapterById(Long id) throws NetflixException;

    CharapterRest updateCharapter(CharapterRest charapterRest) throws NetflixException;

    CharapterRest deleteCharapter(Long id) throws NetflixException;

    List<TvShow> getTvShowsByCharapter(Long id) throws NetflixException;

}
