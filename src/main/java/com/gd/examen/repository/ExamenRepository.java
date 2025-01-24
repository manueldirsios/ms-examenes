package com.gd.examen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gd.examen.entity.ExamenEnt;
@Repository
public interface ExamenRepository extends JpaRepository<ExamenEnt, Integer> {

}
