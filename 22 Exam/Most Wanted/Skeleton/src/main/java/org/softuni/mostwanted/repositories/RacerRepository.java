package org.softuni.mostwanted.repositories;

import org.softuni.mostwanted.domain.entity.Racer;
import org.softuni.mostwanted.domain.entity.Town;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface RacerRepository extends JpaRepository<Racer, Integer>{

    Racer findOneByName(String name);

    Racer findOneByNameAndAgeAndBountyAndHomeTown(
            String name, Integer age, BigDecimal bounty, Town homeTown);


}
