package com.everis.d4i.tutorial.repositories;

import com.everis.d4i.tutorial.entities.ERole;
import com.everis.d4i.tutorial.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(ERole name);

}
