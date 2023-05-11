package com.everis.d4i.tutorial.repositories;

import com.everis.d4i.tutorial.entities.Charapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharapterRepository extends JpaRepository<Charapter, Long> {



}
