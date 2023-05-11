package com.everis.d4i.tutorial.controllers.impl;

import com.everis.d4i.tutorial.controllers.CharapterController;
import com.everis.d4i.tutorial.entities.Charapter;
import com.everis.d4i.tutorial.entities.TvShow;
import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.CharapterRest;
import com.everis.d4i.tutorial.responses.NetflixResponse;
import com.everis.d4i.tutorial.services.CharapterService;
import com.everis.d4i.tutorial.utils.constants.CommonConstants;
import com.everis.d4i.tutorial.utils.constants.RestConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(RestConstants.APPLICATION_NAME + RestConstants.API_VERSION_1 + RestConstants.RESOURCE_CHARAPTER)
public class CharapterControllerImpl implements CharapterController {

    @Autowired
    private CharapterService charapterService;

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public NetflixResponse<CharapterRest> createCharapter(@RequestBody Charapter charapter)
        throws NetflixException {

        CharapterRest newCharapter = charapterService.createCharapter(charapter);

        return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
                newCharapter);

    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public NetflixResponse<List<CharapterRest>> getAllCharapters()
            throws NetflixException {

        return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
        charapterService.getAllCharapters());

    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = RestConstants.RESOURCE_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public NetflixResponse<CharapterRest> getCharapterById(@PathVariable Long id)
            throws NetflixException {
        return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
                charapterService.getCharapterById(id));
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = RestConstants.RESOURCE_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public NetflixResponse<CharapterRest> updateCharapter(@PathVariable Long id, @RequestBody CharapterRest charapterRest)
            throws NetflixException {

        charapterRest.setId(id);
        CharapterRest updateCharapter = charapterService.updateCharapter(charapterRest);

        return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
                updateCharapter);

    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = RestConstants.RESOURCE_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public NetflixResponse<CharapterRest> deleteCharapter(@PathVariable Long id)
            throws NetflixException {
        return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
                charapterService.deleteCharapter(id));
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = RestConstants.RESOURCE_ID + RestConstants.RESOURCE_TV_SHOW, produces = MediaType.APPLICATION_JSON_VALUE)
    public NetflixResponse<List<TvShow>> getTvShowsByCharapterId(@PathVariable Long id)
            throws NetflixException {
        return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
                charapterService.getTvShowsByCharapter(id));
    }

}
