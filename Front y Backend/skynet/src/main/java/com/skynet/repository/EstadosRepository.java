package com.skynet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skynet.entity.EstadosVisitas;

public interface EstadosRepository extends JpaRepository<EstadosVisitas, Integer> {

}
