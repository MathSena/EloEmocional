package com.example.eloemocional.services;

import com.example.eloemocional.models.Session;
import com.example.eloemocional.models.dtos.SessionDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface SessionService {

    Session findById(Integer id);

    List<Session> findAll();

    Session create(@Valid SessionDTO objDto) throws IllegalAccessException;

    Session update(Integer id, SessionDTO objDto) throws IllegalAccessException;

}
