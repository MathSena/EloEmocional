package com.example.eloemocional.repository;

import com.example.eloemocional.models.Patiente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatienteRepository extends JpaRepository<Patiente, Integer>{
}
