package org.tux.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tux.entites.Personne;

public interface PersonneRepository extends JpaRepository<Personne, Long> {

}
