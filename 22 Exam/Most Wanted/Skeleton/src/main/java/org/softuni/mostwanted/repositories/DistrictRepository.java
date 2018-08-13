package org.softuni.mostwanted.repositories;

import org.softuni.mostwanted.domain.entity.District;
import org.softuni.mostwanted.domain.entity.Town;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictRepository extends JpaRepository<District, Integer>{

    District findOneByNameAndTown(String name,Town town);

    List<District> findOneByName(String name);
}
