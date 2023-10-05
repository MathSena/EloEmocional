package com.example.eloemocional.services;

import com.example.eloemocional.models.Psychologist;
import com.example.eloemocional.models.dtos.PsychologistDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface PsychologistService {

    Psychologist findById(Integer id);

    List<Psychologist> findAll();

    Psychologist create(PsychologistDTO objDto);

    Psychologist update(Integer id, @Valid PsychologistDTO objDto);

    void delete(Integer id);
}
