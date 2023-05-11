package com.everis.d4i.tutorial.services.impl;

import com.everis.d4i.tutorial.entities.Charapter;
import com.everis.d4i.tutorial.entities.TvShow;
import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.exceptions.NotFoundException;
import com.everis.d4i.tutorial.json.CharapterRest;
import com.everis.d4i.tutorial.repositories.CharapterRepository;
import com.everis.d4i.tutorial.services.CharapterService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CharapterServiceImpl implements CharapterService {

    @Autowired
    private CharapterRepository charapterRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public CharapterRest createCharapter(@RequestBody Charapter charapter) throws NetflixException{

        try {
            return modelMapper.map(charapterRepository.save(charapter), CharapterRest.class);
        }catch (EntityNotFoundException entityNotFoundException){
           throw new NotFoundException(entityNotFoundException.getMessage());
        }

    }

    @Override
    public List<CharapterRest> getAllCharapters() throws NetflixException {
        return charapterRepository.findAll().stream()
                .map(charapter -> modelMapper.map(charapter, CharapterRest.class)).collect(Collectors.toList());
    }

    @Override
    public CharapterRest getCharapterById(Long id) throws NetflixException {

        try {
            return modelMapper.map(charapterRepository.findById(id).orElse(null), CharapterRest.class);
        }catch (EntityNotFoundException entityNotFoundException){
            throw new NotFoundException(entityNotFoundException.getMessage());
        }

    }

    @Override
    public CharapterRest updateCharapter(CharapterRest charapterRest) throws NetflixException {

        Charapter charapterUpdated = charapterRepository.findById(charapterRest.getId()).orElse(null);

        charapterUpdated.setFirst_name(charapterRest.getFirst_name());
        charapterUpdated.setLast_name(charapterRest.getLast_name());
        charapterUpdated.setNationality(charapterRest.getNationality());
        charapterUpdated.setBirth(charapterRest.getBirth());
        charapterUpdated.setByography(charapterRest.getByography());

        try {
            return modelMapper.map(charapterRepository.save(charapterUpdated), CharapterRest.class);
        }catch (EntityNotFoundException entityNotFoundException){
            throw new NotFoundException(entityNotFoundException.getMessage());
        }

    }

    @Override
    public CharapterRest deleteCharapter(Long id) throws NetflixException {
        Charapter charapter = charapterRepository.findById(id).orElse(null);

        CharapterRest charapterDeleted = modelMapper.map(charapter, CharapterRest.class);

        charapterRepository.delete(charapter);

        try {
            return charapterDeleted;
        } catch (EntityNotFoundException entityNotFoundException){
            throw new NotFoundException(entityNotFoundException.getMessage());
        }

    }

    @Override
    public List<TvShow> getTvShowsByCharapter(Long id) throws NetflixException {

        try {
            return charapterRepository.findById(id).orElse(null).getTvShows();
        } catch (EntityNotFoundException entityNotFoundException){
            throw new NotFoundException(entityNotFoundException.getMessage());
        }

    }

}
