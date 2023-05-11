package com.everis.d4i.tutorial.controllers;

import com.everis.d4i.tutorial.entities.Charapter;
import com.everis.d4i.tutorial.entities.TvShow;
import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.CharapterRest;
import com.everis.d4i.tutorial.responses.NetflixResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface CharapterController {

    NetflixResponse<List<CharapterRest>> getAllCharapters()
            throws Exception;

    NetflixResponse<CharapterRest> createCharapter(@RequestBody Charapter charapter)
            throws NetflixException;

    NetflixResponse<CharapterRest> getCharapterById(@PathVariable Long id)
            throws NetflixException;

    NetflixResponse<CharapterRest> updateCharapter(@PathVariable Long id, @RequestBody CharapterRest charapterRest)
            throws NetflixException;

    NetflixResponse<CharapterRest> deleteCharapter(@PathVariable Long id)
            throws NetflixException;

    NetflixResponse<List<TvShow>> getTvShowsByCharapterId(@PathVariable Long id)
            throws NetflixException;

}
