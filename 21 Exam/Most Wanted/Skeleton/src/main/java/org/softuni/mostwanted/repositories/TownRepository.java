package org.softuni.mostwanted.repositories;

import org.softuni.mostwanted.domain.entity.Town;
import org.softuni.mostwanted.domain.export.JSON.TownJSONExportDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TownRepository extends JpaRepository<Town, Integer>{

    Town findOneByName(String name);

    @Query("select new org.softuni.mostwanted.domain.export.JSON.TownJSONExportDTO(" +
            "t.name,count(r.id)) from Racer as r join r.homeTown as t group by t.id " +
            "order by count(r.id) desc ,t.name asc")
    List<TownJSONExportDTO> getAllTownsWithRacers();
}
