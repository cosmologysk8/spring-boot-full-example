package com.everis.d4i.tutorial.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import com.everis.d4i.tutorial.entities.Category;
import com.everis.d4i.tutorial.entities.TvShow;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.exceptions.NotFoundException;
import com.everis.d4i.tutorial.json.TvShowRest;
import com.everis.d4i.tutorial.repositories.TvShowRepository;
import com.everis.d4i.tutorial.services.TvShowService;

@Service
public class TvShowServiceImpl implements TvShowService {

	@Autowired
	private TvShowRepository tvShowRepository;

	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public List<TvShowRest> getTvShowsByCategory(Long categoryId) throws NetflixException {

		return tvShowRepository.findByCategoryId(categoryId).stream()
				.map(tvShow -> modelMapper.map(tvShow, TvShowRest.class)).collect(Collectors.toList());

	}

	@Override
	public TvShowRest getTvShowById(Long id) throws NetflixException {

		try {
			return modelMapper.map(tvShowRepository.getOne(id), TvShowRest.class);
		} catch (EntityNotFoundException entityNotFoundException) {
			throw new NotFoundException(entityNotFoundException.getMessage());
		}

	}

	@Override
	public TvShowRest updateTvShow(TvShowRest tvShowRest) throws NetflixException {

		TvShow tvShow = tvShowRepository.findById(tvShowRest.getId()).orElse(null);

		tvShow.setName(tvShowRest.getName());
		tvShow.setYear(tvShowRest.getYear());
		tvShow.setLongDescription(tvShowRest.getLongDescription());
		tvShow.setShortDescription(tvShowRest.getShortDescription());
		tvShow.setRecommendedAge(tvShowRest.getRecommendedAge());
		tvShow.setAdvertising(tvShowRest.getAdvertising());
		tvShow.setCategory((List<Category>) tvShowRest.getCategory());

		try {
			return modelMapper.map(tvShowRepository.save(tvShow), TvShowRest.class);
		} catch (EntityNotFoundException entityNotFoundException){
			throw new NotFoundException(entityNotFoundException.getMessage());
		}

	}

	@Override
	public TvShowRest deleteTvShow(Long id) throws NetflixException {
		// Buscar el objeto TvShow para eliminar en la base de datos
		TvShow deleteTvShow = tvShowRepository.findById(id).orElse(null);

		// Mapear el objeto TvShow a un TvShowRest
		TvShowRest deletedTvShow = modelMapper.map(deleteTvShow, TvShowRest.class);

		// Eliminar el objeto TvShow de la base de datos
		tvShowRepository.delete(deleteTvShow);

		try {
			return deletedTvShow;
		} catch (EntityNotFoundException entityNotFoundException){
			throw new NotFoundException(entityNotFoundException.getMessage());
		}

	}

}
