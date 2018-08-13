package org.softuni.mostwanted.repositories;

import org.softuni.mostwanted.domain.entity.Race;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RaceRepository extends JpaRepository<Race, Integer> {
}
