package com.everis.d4i.tutorial.services.impl;

import com.everis.d4i.tutorial.entities.Reward;
import com.everis.d4i.tutorial.entities.TvShow;
import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.RewardRest;
import com.everis.d4i.tutorial.json.TvShowRest;
import com.everis.d4i.tutorial.repositories.TvShowRepository;
import com.everis.d4i.tutorial.services.RewardService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class RewardServiceImpl implements RewardService {

    @Autowired
    private TvShowRepository tvShowRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public List<Reward> getRewardsByTvShowId(Long tvShowId) throws NetflixException {

        TvShow tvShow = tvShowRepository.findById(tvShowId).orElse(null);

        List<Reward> rewards = tvShow.getRewards();

        try {
            return rewards;
        } catch (EntityNotFoundException entityNotFoundException) {
            throw new EntityNotFoundException(entityNotFoundException.getMessage());
        }

    }

}
