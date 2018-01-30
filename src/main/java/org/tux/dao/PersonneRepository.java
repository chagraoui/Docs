package org.tux.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tux.entites.Personne;

@Repository
public interface PersonneRepository extends JpaRepository<Personne, Integer> {

}
