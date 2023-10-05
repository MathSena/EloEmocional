package com.example.eloemocional.services.impl;

import com.example.eloemocional.models.Patiente;
import com.example.eloemocional.models.Psychologist;
import com.example.eloemocional.models.Session;
import com.example.eloemocional.models.dtos.SessionDTO;
import com.example.eloemocional.repository.SessionRepository;
import com.example.eloemocional.services.PatienteService;
import com.example.eloemocional.services.PsychologistService;
import com.example.eloemocional.services.SessionService;
import com.example.eloemocional.services.exceptions.ObjectNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SessionImpl implements SessionService {

    @Autowired
    private SessionRepository repository;

    @Autowired
    private PsychologistService psychologistService;

    @Autowired
    private PatienteService patienteService;

    @Override
    public Session findById(Integer id) {
        Optional<Session> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Sessão não encontrada: " + id));
    }

    @Override
    public List<Session> findAll() {
        return repository.findAll();
    }

    @Override
    public Session create(@Valid SessionDTO objDto) throws IllegalAccessException {
        return repository.save(newSession(objDto));
    }

    private Session newSession(SessionDTO obj) throws IllegalAccessException {
        Psychologist psychologist = psychologistService.findById(obj.getPsychologist()
                .getId());
        Patiente patiente = patienteService.findById(obj.getPatiente()
                .getId());
        Session session = new Session();

        if (obj.getId() != null) {
            session.setId(obj.getId());
        }

        session.setTitle(obj.getTitle());
        session.setSessionCreationDate(obj.getSessionCreationDate());
        session.setSessionDate(obj.getSessionDate());
        session.setNotes(obj.getNotes());
        session.setStatus(obj.getStatus());
        session.setPsychologist(psychologist);
        session.setPatiente(patiente);
        return session;
    }

    @Override
    public Session update(Integer id, @Valid SessionDTO objDto) throws IllegalAccessException {
        objDto.setId(id);
        findById(id);
        Session oldObj;
        oldObj = newSession(objDto);
        return repository.save(oldObj);
    }
}
