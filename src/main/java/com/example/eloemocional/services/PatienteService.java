package com.example.eloemocional.services;

import com.example.eloemocional.models.Patiente;
import com.example.eloemocional.models.dtos.PatienteDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface PatienteService {
    Patiente findById(Integer id);

    List<Patiente> findAll();

    Patiente create(PatienteDTO objDto);

    Patiente update(Integer id, @Valid PatienteDTO objDto);

    void delete(Integer id);
}
