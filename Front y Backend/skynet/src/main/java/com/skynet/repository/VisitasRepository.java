package com.skynet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skynet.entity.Visitas;

public interface VisitasRepository extends JpaRepository<Visitas, Integer> {

	List<Visitas> findByUsuariosId(Integer tecnicoId);
}
