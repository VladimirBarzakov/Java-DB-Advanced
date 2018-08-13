package org.softuni.mostwanted.repositories;

import org.softuni.mostwanted.domain.entity.RaceEntry;
import org.softuni.mostwanted.domain.entity.Racer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RaceEntryRepository extends JpaRepository<RaceEntry, Integer> {

    RaceEntry findOneById(int id);

    @Query("select r from RaceEntry as re join re.racer as r group by r.id order by count(re.id) desc")
    List<Racer> GetMostWanted();

    List<RaceEntry> getAllByRacer(Racer racer);
}
