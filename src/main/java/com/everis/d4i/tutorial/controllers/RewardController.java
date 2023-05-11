package com.everis.d4i.tutorial.controllers;

import com.everis.d4i.tutorial.entities.Reward;
import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.RewardRest;
import com.everis.d4i.tutorial.responses.NetflixResponse;

import java.util.List;

public interface RewardController {

    NetflixResponse<List<Reward>> getRewardsByTvShowId(Long tvShowId)
            throws NetflixException;

}
